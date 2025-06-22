package com.natived.nativedvalidatordemo.validation.validator;

import com.natived.nativedvalidatordemo.dto.ClientRequest;
import com.natived.nativedvalidatordemo.validation.ValidAdminRoleSpecies;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdminRoleSpeciesValidator implements ConstraintValidator<ValidAdminRoleSpecies, ClientRequest> {
    @Override
    public boolean isValid(ClientRequest value, ConstraintValidatorContext context) {
        if (value == null) return true;
        String role = value.getRole();
        String species = value.getSpecies();
        if ("admin".equalsIgnoreCase(role)) {
            if (species == null || !"puppy".equalsIgnoreCase(species)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("For role 'admin', species must be 'puppy'.")
                        .addPropertyNode("species").addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}

