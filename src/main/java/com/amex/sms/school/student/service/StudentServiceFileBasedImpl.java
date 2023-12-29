package com.amex.sms.school.student.service;

import com.amex.sms.school.model.PaginatedResponse;
import com.amex.sms.school.student.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 13 Nov, 2023
 */
@Repository("fileBasedService")
public class StudentServiceFileBasedImpl implements StudentService{
    @Override
    public Student create(Student student) {
        return null;
    }

    @Override
    public Student update(int id, Student student) {
        return null;
    }

    @Override
    public Student get(int id) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public PaginatedResponse<Student> getStudentPagination(Integer pageNumber, Integer pageSize, String sort, String email) {
        return null;
    }

    @Override
    public List<Student> search(String email, String sortFieldName, String sortOrder) {
        return null;
    }
}
