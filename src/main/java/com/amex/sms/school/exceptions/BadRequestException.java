package com.amex.sms.school.exceptions;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 27 Oct, 2023
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(){
        super("Record Not Found");
    }
}
