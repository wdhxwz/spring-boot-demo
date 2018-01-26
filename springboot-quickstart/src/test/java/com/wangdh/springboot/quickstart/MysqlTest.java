package com.wangdh.springboot.quickstart;

import com.wangdh.springboot.quickstart.config.JpaConfiguration;
import com.wangdh.springboot.quickstart.controller.mysql.entity.Deparment;
import com.wangdh.springboot.quickstart.controller.mysql.entity.Role;
import com.wangdh.springboot.quickstart.controller.mysql.entity.User;
import com.wangdh.springboot.quickstart.controller.mysql.repository.DeparmentRepository;
import com.wangdh.springboot.quickstart.controller.mysql.repository.RoleRepository;
import com.wangdh.springboot.quickstart.controller.mysql.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author wangdh
 * @Description
 * @date 2018-01-26
 */
@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = {JpaConfiguration.class})
@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest(classes ={JpaConfiguration.class})
/**
 * 单元测试的时候遇到这个：Cannot determine embedded database driver class for database type NONE
 * 因为没有数据源
 * 需要增加@TestPropertySource(locations = "classpath:application.properties")
 * 指定数据源的配置
 */
public class MysqlTest {
    private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    DeparmentRepository deparmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Before
    public void initData(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        deparmentRepository.deleteAll();

        Deparment deparment = new Deparment();
        deparment.setName("开发部");
        deparmentRepository.save(deparment);
        Assert.assertNotNull(deparment.getId());

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.assertNotNull(role.getId());

        User user = new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDeparment(deparment);

        List<Role> roles = roleRepository.findAll();
        Assert.assertNotNull(roles);
        user.setRoles(roles);
        userRepository.save(user);

        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.assertNotNull(page);

        for (User user:page.getContent()) {
            logger.info("====user==== user name:{},department name:{},role name:{}",
                    user.getName(),
                    user.getDeparment().getName(),
                    user.getRoles().get(0).getName());
        }
    }
}
