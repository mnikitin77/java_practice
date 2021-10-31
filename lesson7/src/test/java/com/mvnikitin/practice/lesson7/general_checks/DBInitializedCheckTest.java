package com.mvnikitin.practice.lesson7.general_checks;

import com.mvnikitin.practice.lesson7.Lesson7Application;
import com.mvnikitin.practice.lesson7.dao.StudentReposity;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = Lesson7Application.class)
public class DBInitializedCheckTest {

    @Autowired
    private StudentReposity studentReposity;

    @Test
    @DisplayName("Когда приложение стартовало, все студенты из ликвибейз-ченджлога на месте")
    public void when_ApplicationStarted_ThereAreAllInsertedItemsInStudentTable() {
        int initialStudentsCount = 4;
        Assertions.assertThat(studentReposity.count()).isEqualTo(initialStudentsCount);
        studentReposity.findAll().forEach(it -> log.info("stident: " + it.toString()));
    }
}
