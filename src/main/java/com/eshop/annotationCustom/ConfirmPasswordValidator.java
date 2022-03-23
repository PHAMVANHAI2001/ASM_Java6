package com.eshop.annotationCustom;

import com.eshop.dto.Register;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ValidateConfirmPassword, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj instanceof Register register) {
            return register.getPassword().equals(register.getConfirmPassword());
        }
        return true;
    }

}
