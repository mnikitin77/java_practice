package com.mvnikitin.practice.lesson4.dao;

import com.mvnikitin.practice.lesson4.entity.Student;

import java.util.List;

public interface StudentRepository {

    Student save(Student entity);

    List<Student> saveAll(List<Student> entities);

    void deleteById(Long id);

    void deleteAll();

    Student findById(Long id);

    List<Student> findAll();
}
