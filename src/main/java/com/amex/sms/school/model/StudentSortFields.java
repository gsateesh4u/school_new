package com.amex.sms.school.model;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 01 Nov, 2023
 */
public enum StudentSortFields {

    ID("id"),
    NAME("name"),
    EMAIL("email");
    private String value;
    StudentSortFields(String value){
        this.value = value;
    }
}
