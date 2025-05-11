package com.menu.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HashPasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Skip validation for hashed passwords (e.g., bcrypt hashes are typically longer than 60 characters)
        if (value != null && value.length() > 60) { // Adjust this condition if using a different hashing algorithm
            return true; // Skip validation for already hashed passwords
        }

        // Validate plain-text passwords
        return value != null && value.matches("^(?=.*[A-Z])(?=.*[!@#$*])[A-Za-z\\d!@#$*]{8,32}$");
    }
}
