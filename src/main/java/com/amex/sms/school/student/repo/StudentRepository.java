package com.amex.sms.school.student.repo;

import com.amex.sms.school.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByEmail(String email);

    List<Student> findByEmailIn(List<String> asList);

    List<Student> findAllByOrderByIdAsc();

    List<Student> findAllByOrderByEmailAsc();

    List<Student> findAllByOrderByFnameAsc();

    List<Student> findAllByOrderByIdDesc();

    List<Student> findAllByOrderByEmailDesc();

    List<Student> findAllByOrderByFnameDesc();
}
