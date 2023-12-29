package com.amex.sms.school.student.service;

import com.amex.sms.school.exceptions.NotFoundException;
import com.amex.sms.school.model.PaginatedResponse;
import com.amex.sms.school.service.BaseService;
import com.amex.sms.school.student.entity.Student;
import com.amex.sms.school.student.repo.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 */
    @Service("databaseService")
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentRepository studentRepository;



    /**
     * This method is to provide all the available students
     *
     * @param sortFieldName
     * @param sortOrder
     * @return List<Student>
     */
    public List<Student> search(String email, String sortFieldName, String sortOrder){
        logger.info("This is where our business logic resides");
        if(StringUtils.hasLength(email)){
            if(email.contains(",")){
                //String[] arr = email.split(",");
                //List<String> list = Arrays.asList(arr);
                //return studentRepository.findByEmailIn(list);
                return studentRepository.findByEmailIn(Arrays.asList(email.split(",")));
            }
            return studentRepository.findByEmail(email);
        }
        if("ASC".equalsIgnoreCase(sortOrder)){
            switch(sortFieldName.toLowerCase()){
                case "email":
                    return studentRepository.findAllByOrderByEmailAsc();
                case "name":
                    return studentRepository.findAllByOrderByFnameAsc();
                default:
                    return studentRepository.findAllByOrderByIdAsc();
            }
        }else{
            switch(sortFieldName.toLowerCase()){
                case "email":
                    return studentRepository.findAllByOrderByEmailDesc();
                case "name":
                    return studentRepository.findAllByOrderByFnameDesc();
                default:
                    return studentRepository.findAllByOrderByIdDesc();
            }
        }
    }



    public PaginatedResponse<Student> getStudentPagination(Integer pageNumber, Integer pageSize, String sort, String email) {
        Pageable pageable = null;
        if (sort != null) {
            // with sorting
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            // without sorting
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        Page<Student> result = studentRepository.findAll(pageable);
        PaginatedResponse<Student> response = new PaginatedResponse<>();
        response.setContent(result.getContent());
        response.setLast(result.isLast());
        response.setPageNo(result.getNumber());
        response.setPageSize(result.getSize());
        response.setTotalElements(result.getTotalElements());
        response.setTotalPages(result.getTotalPages());
        return response;
    }

    @Override
    public Student get(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }
        throw new NotFoundException("Student with id "+id+" is not found");
    }

    @Override
    public Student create(Student student) {
        if(!studentRepository.existsById(student.getId())){
            return studentRepository.save(student);
        }
        return studentRepository.save(student);
    }

    @Override
    public Student update(int id, Student student) {
        student.setId(id);
        if(studentRepository.existsById(id)){
            return studentRepository.save(student);
        }
        return null;
    }

    public void delete(int id) {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
