package com.amex.sms.school;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 27 Oct, 2023
 */
public class MyTest {

    public int add(int i, int j){
        if(i == 5){
            throw new RuntimeException("value cannot be 5");
        }
        return i+j;
    }
    public int sub(int a, int b){
        return a-b;
    }
}
