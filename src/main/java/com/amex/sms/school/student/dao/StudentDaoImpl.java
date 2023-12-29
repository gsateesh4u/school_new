package com.amex.sms.school.student.dao;

import com.amex.sms.school.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 13 Nov, 2023
 */
@Repository("postgresImpl")
public class StudentDaoImpl implements StudentDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Student> findById(int id) {
        return Optional.of(jdbcTemplate.queryForObject("select * from student where id="+id, Student.class));
    }
}
