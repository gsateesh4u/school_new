package com.amex.sms.school.exceptions;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 27 Oct, 2023
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(){
        super("Record Not Found");
    }
}
