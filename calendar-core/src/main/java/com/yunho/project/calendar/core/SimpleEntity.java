package com.yunho.project.calendar.core;

import com.yunho.project.calendar.core.domain.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
public class SimpleEntity extends BaseEntity {

    private String name;

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "SimpleEntity{" +
                "name='" + name + '\'' +
                super.getCreatedAt() +
                '}';
    }
}
