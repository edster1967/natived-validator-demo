package com.natived.nativedvalidatordemo.validation.validator;

import com.natived.nativedvalidatordemo.dto.ClientRequest;
import com.natived.nativedvalidatordemo.validation.ValidAdminRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class AdminRoleValidator implements ConstraintValidator<ValidAdminRole, ClientRequest> {
    @Override
    public void initialize(ValidAdminRole constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClientRequest request, ConstraintValidatorContext context) {
        String role = request.getRole();
        if ("admin".equalsIgnoreCase(role) || !StringUtils.hasLength(role)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("role is a required field")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
