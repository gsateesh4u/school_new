package com.amex.sms.school.student.rest;

import com.amex.sms.school.student.entity.Student;
import com.amex.sms.school.student.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 07 Nov, 2023
 */
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentServiceImpl studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void getAllP() {
    }

    //@Test
    void getAll() {
        List<Student> list = new ArrayList<>();
        //list.add(new Student(101, "sateesh", "sateesh@gmail.com"));

        when(studentService.getAll()).thenReturn(list);
        //assertEquals(list, studentController.getAll(null));
    }

    @Test
    void search() {
    }

    @Test
    void get() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}