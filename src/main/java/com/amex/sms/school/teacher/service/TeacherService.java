package com.amex.sms.school.teacher.service;

import com.amex.sms.school.teacher.entity.Teacher;
import com.amex.sms.school.teacher.repo.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author sateesh.gullipalli
 * This class is to hold business logic for teacher management
 */
@Service
public class TeacherService {

    Logger logger = LoggerFactory.getLogger(TeacherService.class);

    @Autowired
    TeacherRepository teacherRepository;

    public List<Teacher> getAll(String email){
        logger.info("This is where our business logic resides");
        return teacherRepository.findAll();
    }

    public Optional<Teacher> get(int id) {
        return teacherRepository.findById(id);
    }

    public Teacher create(Teacher teacher) {
        if(!teacherRepository.existsById(teacher.getId())){
            return teacherRepository.save(teacher);
        }
        return null;
    }

    public Teacher update(int id, Teacher teacher) {
        teacher.setId(id);
        if(teacherRepository.existsById(id)){
            return teacherRepository.save(teacher);
        }
        return null;
    }

    public void delete(int id) {
        if(teacherRepository.existsById(id)){
            teacherRepository.deleteById(id);
        }
    }
}
