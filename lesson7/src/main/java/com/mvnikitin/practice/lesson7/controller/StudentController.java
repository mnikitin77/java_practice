package com.mvnikitin.practice.lesson7.controller;

import com.mvnikitin.practice.lesson7.dao.StudentReposity;
import com.mvnikitin.practice.lesson7.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class StudentController {

    private final StudentReposity studentReposity;

    @GetMapping
    public String show(Model model) {
        List<Student> students = studentReposity.findAll();
        model.addAttribute("student", new Student());
        model.addAttribute("students", students);
        return "index";
    }

    @PostMapping("/student")
    public String add(Student student) {
        studentReposity.save(student);
        return "redirect:/";
    }
}
