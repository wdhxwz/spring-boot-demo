package com.wangdh.springboot.quickstart.controller.mysql.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wangdh
 * @Description
 * @date 2018-01-26
 */
@Entity
@Table(name="role")
public class Role implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    public Role(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}