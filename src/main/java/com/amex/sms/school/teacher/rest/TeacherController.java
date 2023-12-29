package com.amex.sms.school.teacher.rest;

import com.amex.sms.school.exceptions.BadRequestException;
import com.amex.sms.school.teacher.entity.Teacher;
import com.amex.sms.school.teacher.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


/**
 * Created bY sateesh
 * clreated on 101//
 *
 */
@RestController
public class TeacherController {

    Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    Environment environment;

    @Autowired
    TeacherService teacherService;

    @GetMapping("/teachers")
    public List<Teacher> getAll(@RequestParam(value = "email", required = false) String email){
        logger.info("GET All Request received");
        return teacherService.getAll(email);
    }

    @GetMapping("/teachers/{id}")
    public Teacher get(@PathVariable("id") int id){
        logger.info("GET request received for id "+id);
        Optional<Teacher> teacher =  teacherService.get(id);
        if(teacher.isPresent()){
            return teacher.get();
        }
        return null;
    }

    @PostMapping("/teachers")
    public Teacher create(@RequestBody Teacher teacher){
        logger.info("CREATE request received");
        logger.info(teacher.toString());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            teacher.setDob(simpleDateFormat.parse(teacher.getDobStr()));
        } catch (ParseException e) {
            logger.info("Invalid DOBStr ->"+teacher.getDobStr());
            throw new BadRequestException("Date Format Should be YYYY-MM-DD");
        }
        try {
            teacher.setDoj(simpleDateFormat.parse(teacher.getDojStr()));
        } catch (ParseException e) {
            logger.info("Invalid DOBStr ->"+teacher.getDobStr());
            throw new BadRequestException("Date Format Should be YYYY-MM-DD");
        }
        return teacherService.create(teacher);
    }

    @PutMapping("/teachers/{id}")
    public Teacher update(@PathVariable("id") int id, @RequestBody Teacher teacher){
        logger.info("UPDATE request received for id "+id);
        logger.info(teacher.toString());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            teacher.setDob(simpleDateFormat.parse(teacher.getDobStr()));
        } catch (ParseException e) {
            logger.info("Invalid DOBStr ->"+teacher.getDobStr());
            throw new BadRequestException("Date Format Should be YYYY-MM-DD");
        }
        try {
            teacher.setDoj(simpleDateFormat.parse(teacher.getDojStr()));
        } catch (ParseException e) {
            logger.info("Invalid DOBStr ->"+teacher.getDobStr());
            throw new BadRequestException("Date Format Should be YYYY-MM-DD");
        }
        return teacherService.update(id, teacher);
    }

    @DeleteMapping("/teachers/{id}")
    public void delete(@PathVariable("id") int id){
        logger.info("DELETE request received for id "+id);
        teacherService.delete(id);
    }

}
