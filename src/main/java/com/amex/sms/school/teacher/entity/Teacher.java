package com.amex.sms.school.teacher.entity;

import com.amex.sms.school.validators.AmexEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private int id;

    @Column(name = "fname")
    @Schema(name ="first name", description = "First name of the teacher with max of 80 characters", example = "sateesh")
    @NotBlank(message = "Student First Name is required.")
    private String fname;

    @Column(name = "mname")
    @Schema(name ="middle name", description = "Middle name of the teacher with max of 80 characters", example = "sateesh")
    private String mname;

    @Column(name = "lname")
    @Schema(name ="last name", description = "Last name of the teacher with max of 80 characters", example = "gullipalli")
    @NotBlank(message = "Student Last Name is required.")
    private String lname;

    @Column(name = "email")
    @Email(message = "Email format is not valid")
    //@AmexEmail(message = "Email id should end with @aexp.com")
    @Schema(name = "email", description = "Email id of the teacher with max of 255 characters and should end with @aexp.com", example = "sateesh.gullipalli@aexp.com")
    private String email;

    @Transient
    private String dobStr;

    @Transient
    private String dojStr;

    @Column(name = "dob")
    private Date dob;


    @Column(name = "doj")
    private Date doj;

    @Min(message = "Minimum grade should be greater than or equal to 1", value = 1)
    @Max(message = "Maximum grade should be less than or equal to 10", value = 10)
    @Column(name = "grade")
    private int grade;

    @Column(name = "phone")
    private String phone;

    @JsonIgnore
    @Transient
    private List<String> fields;

    @Column(name = "subject")
    private String subject;

}
