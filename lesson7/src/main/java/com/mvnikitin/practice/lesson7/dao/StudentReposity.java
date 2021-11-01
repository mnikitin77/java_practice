package com.mvnikitin.practice.lesson7.dao;

import com.mvnikitin.practice.lesson7.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentReposity extends JpaRepository<Student, Long> {
}
