package com.amex.sms.school.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 30 Oct, 2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppError {
    private String code;
    private List<String> errors;
}
