package com.amex.sms.school.service;

import com.amex.sms.school.model.PaginatedResponse;
import com.amex.sms.school.student.entity.Student;

import java.util.List;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 13 Nov, 2023
 */
public interface BaseService<T> {
    T create(T t);
    T update(int id, T t);
    T get(int id);
    List<T> getAll();
    void delete(int id);
}
