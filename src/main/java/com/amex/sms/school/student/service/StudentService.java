package com.amex.sms.school.student.service;

import com.amex.sms.school.model.PaginatedResponse;
import com.amex.sms.school.service.BaseService;
import com.amex.sms.school.student.entity.Student;

import java.util.List;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 13 Nov, 2023
 */
public interface StudentService extends BaseService<Student> {
    Student create(Student student);
    Student update(int id, Student student);
    Student get(int id);
    List<Student> getAll();
    void delete(int id);
    PaginatedResponse<Student> getStudentPagination(Integer pageNumber, Integer pageSize, String sort, String email);

    List<Student> search(String email, String sortFieldName, String sortOrder);
}
