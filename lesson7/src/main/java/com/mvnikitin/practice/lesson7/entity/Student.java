package com.mvnikitin.practice.lesson7.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Byte age;
}
