package com.wangdh.springboot.quickstart;

import com.wangdh.springboot.quickstart.config.RedisConfig;
import com.wangdh.springboot.quickstart.controller.mysql.entity.Deparment;
import com.wangdh.springboot.quickstart.controller.mysql.entity.Role;
import com.wangdh.springboot.quickstart.controller.mysql.entity.User;
import com.wangdh.springboot.quickstart.redis.UserRedis;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangdh
 * @Description
 * @date 2018-01-26
 */
@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes ={RedisConfig.class})
@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest(classes ={RedisConfig.class})
public class RedisTest {
    private static Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    UserRedis userRedis;

    @Before
    public void setUp(){
        Deparment deparment = new Deparment();
        deparment.setName("开发部");

        Role role = new Role();
        role.setName("admin");

        User  user = new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDeparment(deparment);

        List<Role> roles = new ArrayList<>(2);
        roles.add(role);
        user.setRoles(roles);

        String key = this.getClass().getName()+":userByName:"+user.getName();
        userRedis.delete(key);
        userRedis.add(key,100L,user);
    }

    @Test
    public void get(){
        User user = userRedis.get(this.getClass().getName()+":userByName:user");
        Assert.assertNotNull("数据为空",user);

        logger.info("====user==== user name:{},department name:{},role name:{}",
                user.getName(),
                user.getDeparment().getName(),
                user.getRoles().get(0).getName());
    }
}
