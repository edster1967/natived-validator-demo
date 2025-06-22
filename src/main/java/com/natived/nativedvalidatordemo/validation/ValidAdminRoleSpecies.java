package com.natived.nativedvalidatordemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = com.natived.nativedvalidatordemo.validation.validator.AdminRoleSpeciesValidator.class)
@Target({ TYPE })
@Retention(RUNTIME)
public @interface ValidAdminRoleSpecies {
    String message() default "If role is 'admin', species must be 'puppy' and not blank. Otherwise, species is optional.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

