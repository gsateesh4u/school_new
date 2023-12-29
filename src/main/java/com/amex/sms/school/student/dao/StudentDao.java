package com.amex.sms.school.student.dao;

import com.amex.sms.school.student.entity.Student;

import java.util.Optional;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 13 Nov, 2023
 */
public interface StudentDao {
    Optional<Student> findById(int id);
}
