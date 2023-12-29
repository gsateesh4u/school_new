package com.amex.sms.school;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 07 Nov, 2023
 */
class MyTestTest {

    @Test
    void add() {
        MyTest test = new MyTest();
        assertEquals(10, test.add(4,6));
        Exception ex = assertThrows(RuntimeException.class, () -> {test.add(5,5);});
        assertEquals("value cannot be 5", ex.getMessage());
    }

    @Test
    void sub() {
    }
}