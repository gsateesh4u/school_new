package com.amex.sms.school.student.rest;

import com.amex.sms.school.exceptions.BadRequestException;
import com.amex.sms.school.model.AppError;
import com.amex.sms.school.model.PaginatedResponse;
import com.amex.sms.school.student.entity.Student;
import com.amex.sms.school.student.service.StudentService;
import com.amex.sms.school.student.service.StudentServiceImpl;
import com.amex.sms.school.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    Environment environment;

    @Value("school-name")
    String schoolName;

    @Autowired
    @Qualifier("databaseService")
    StudentService studentService;

    @GetMapping("/school_name")
    public String getSchoolName(){
        return schoolName;
    }

    @Operation(summary = "Get list of all students in a paginated way.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_200, description = "Success"),
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_500, description = "Server Error", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))})
    })
    @GetMapping(value="/students_with_paginated", produces = "application/json")
    public PaginatedResponse<Student> getAllP(@RequestParam(value = "email", required = false) String email,
                                              @RequestParam(value="pageNumber",required = false, defaultValue = "1") Integer pageNumber,
                                              @RequestParam(value="pageSize", required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(value="sort", required = false, defaultValue = "id") String sort){
        logger.info("GET All Request received");


        return studentService.getStudentPagination(pageNumber, pageSize, sort, email);
    }

    @Operation(summary = "Get list of all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_200, description = "Success"),
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_500, description = "Server Error", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))})
    })
    @GetMapping(value = "/students", produces = "application/json")
   // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Student> getAll(@RequestHeader(value = "consumer_name", required = false) String consumerName, @RequestParam(value = "fields", required = false) List<String> fields){
        logger.info("GET request received for GetAll from consumer "+consumerName);
        List<Student> all = studentService.getAll();
        all.forEach(a -> a.setFields(fields));
        return all;
    }

    @Operation(summary = "Search for a student, filter students , sort students etc.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_200, description = "Success"),
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_500, description = "Server Error", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))})
    })
    @GetMapping(value = "/students/search", produces = "application/json")
    public List<Student> search(@RequestParam(value = "email", required = false) String email,
                                @RequestParam(value = "sortFieldName", required = false, defaultValue = "id") String sortFieldName,
                                @RequestParam(value = "sortOrder", required = false, defaultValue = "ASC") String sortOrder){
        logger.info("GET request received for Search ");
        return studentService.search(email, sortFieldName, sortOrder);
    }

    @Operation(summary = "Get One Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_200, description = "Success"),
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_404, description = "Student Not found", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))}),
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_500, description = "Server Error", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))})
    })
    @GetMapping(value = "/students/{id}", produces = "application/json")
    public Student get(@PathVariable("id") int id){
        logger.info("GET request received for id ", id);
        return studentService.get(id);
    }

    @Operation(summary = "Create Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_200, description = "Created Student"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))}),
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_500, description = "Server Error", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))})
    })
    @PostMapping(value = "/students", produces = "application/json")
    public Student create(@RequestBody @Valid Student student, HttpServletRequest request){
        logger.info("CREATE request received");
        logger.info(student.toString());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            student.setDob(simpleDateFormat.parse(student.getDobStr()));
        } catch (ParseException e) {
            logger.info("Invalid DOBStr ->"+student.getDobStr());
            throw new BadRequestException("Date Format Should be YYYY-MM-DD");
        }
        try {
            student.setDoj(simpleDateFormat.parse(student.getDojStr()));
        } catch (ParseException e) {
            logger.info("Invalid DOBStr ->"+student.getDobStr());
            throw new BadRequestException("Date Format Should be YYYY-MM-DD");
        }
        return studentService.create(student);
    }


    @Operation(summary = "Update Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_200, description = "Success"),
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_404, description = "Student Not found", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))}),
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_500, description = "Server Error", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))})
    })
    @PutMapping(value= "/students/{id}", produces = "application/json")
    public Student update(@PathVariable("id") int id, @RequestBody Student student){
        logger.info("UPDATE request received for id ", id);
        logger.info(student.toString());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            student.setDob(simpleDateFormat.parse(student.getDobStr()));
        } catch (ParseException e) {
            logger.info("Invalid DOBStr ->"+student.getDobStr());
            throw new BadRequestException("Date Format Should be YYYY-MM-DD");
        }
        try {
            student.setDoj(simpleDateFormat.parse(student.getDojStr()));
        } catch (ParseException e) {
            logger.info("Invalid DOBStr ->"+student.getDobStr());
            throw new BadRequestException("Date Format Should be YYYY-MM-DD");
        }
        return studentService.update(id, student);
    }

    @Operation(summary = "Update Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_200, description = "Success", content = @Content),
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_404, description = "Student Not found", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))}),
            @ApiResponse(responseCode = AppConstants.HTTP_STATUS_500, description = "Server Error", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class))})
    })
    @DeleteMapping(value = "/students/{id}", produces = "application/json")
    public void delete(@PathVariable("id") int id){
        logger.info("DELETE request received for id ", id);
        studentService.delete(id);
    }

}
