package com.natived.nativedvalidatordemo.validation.validator;

import com.natived.nativedvalidatordemo.dto.ClientRequest;
import com.natived.nativedvalidatordemo.validation.ValidAdminRoleSpecies;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminRoleSpeciesValidatorTest {
    private AdminRoleSpeciesValidator validator;
    private ConstraintValidatorContext context;
    private ConstraintValidatorContext.ConstraintViolationBuilder builder;
    private ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeBuilder;

    @BeforeEach
    void setUp() {
        validator = new AdminRoleSpeciesValidator();
        context = mock(ConstraintValidatorContext.class);
        builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        nodeBuilder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext.class);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(builder.addPropertyNode(anyString())).thenReturn(nodeBuilder);
    }

    @Test
    void validWhenRoleIsNotAdmin() {
        ClientRequest req = new ClientRequest();
        req.setRole("user");
        req.setSpecies(null);
        assertTrue(validator.isValid(req, context));
    }

    @Test
    void validWhenRoleIsAdminAndSpeciesIsPuppy() {
        ClientRequest req = new ClientRequest();
        req.setRole("admin");
        req.setSpecies("puppy");
        assertTrue(validator.isValid(req, context));
    }

    @Test
    void invalidWhenRoleIsAdminAndSpeciesIsNotPuppy() {
        ClientRequest req = new ClientRequest();
        req.setRole("admin");
        req.setSpecies("cat");
        assertFalse(validator.isValid(req, context));
    }

    @Test
    void invalidWhenRoleIsAdminAndSpeciesIsNull() {
        ClientRequest req = new ClientRequest();
        req.setRole("admin");
        req.setSpecies(null);
        assertFalse(validator.isValid(req, context));
    }
}
