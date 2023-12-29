package com.amex.sms.school.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 30 Oct, 2023
 */
@Constraint(validatedBy = EmailValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface AmexEmail {
    String message() default "{com.amex.sms.school.validators.AmexEmail.message}";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}

