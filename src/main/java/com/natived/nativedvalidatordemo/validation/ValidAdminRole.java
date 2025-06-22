package com.natived.nativedvalidatordemo.validation;

import com.natived.nativedvalidatordemo.validation.validator.AdminRoleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdminRoleValidator.class)
public @interface ValidAdminRole {
    String message() default "species is a required field";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
