package com.amex.sms.school.student.entity;

import com.amex.sms.school.config.StudentSerializer;
import com.amex.sms.school.validators.AmexEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
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
@Table(name = "student")
@JacksonXmlRootElement(localName = "Student")
@Schema(description = "All details about the student. ")
@JsonSerialize(using = StudentSerializer.class)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    @Schema(name = "id", description = "ID of the student which is unique", example = "0")
    @JacksonXmlProperty(localName = "StudentId")
    private int id;

    @Column(name = "fname")
    @Schema(name ="first name", description = "First name of the student with max of 80 characters", example = "sateesh")
    @NotBlank(message = "Student First Name is required.")
    private String fname;

    @Column(name = "mname")
    @Schema(name ="middle name", description = "Middle name of the student with max of 80 characters", example = "sateesh")
    private String mname;

    @Column(name = "lname")
    @Schema(name ="last name", description = "Last name of the student with max of 80 characters", example = "gullipalli")
    @NotBlank(message = "Student Last Name is required.")
    private String lname;

    @Column(name = "email")
    @Email(message = "Email format is not valid")
    //@AmexEmail(message = "Email id should end with @aexp.com")
    @Schema(name = "email", description = "Email id of the student with max of 255 characters and should end with @aexp.com", example = "sateesh.gullipalli@aexp.com")
    private String email;


    @Column(name = "dob")
    private Date dob;

    @Transient
    private String dobStr;

    @Transient
    private String dojStr;

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

}
