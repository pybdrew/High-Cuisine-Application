package com.menu.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

// This annotation is used for validating passwords
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HashPasswordValidator.class) // Referencing the validator implementation
public @interface ValidPassword {
    String message() default "Password must contain at least one uppercase letter and one special character (!, @, #, $, *)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
