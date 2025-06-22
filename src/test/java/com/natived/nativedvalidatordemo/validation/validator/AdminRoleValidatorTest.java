package com.natived.nativedvalidatordemo.validation.validator;

import com.natived.nativedvalidatordemo.dto.ClientRequest;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminRoleValidatorTest {
    private AdminRoleValidator validator;
    private ConstraintValidatorContext context;
    private ConstraintValidatorContext.ConstraintViolationBuilder builder;

    @BeforeEach
    void setUp() {
        validator = new AdminRoleValidator();
        context = mock(ConstraintValidatorContext.class);
        builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(builder.addConstraintViolation()).thenReturn(context);
    }

    @Test
    void invalidWhenRoleIsBlank() {
        ClientRequest req = new ClientRequest();
        req.setRole("");
        assertFalse(validator.isValid(req, context));
    }

    @Test
    void invalidWhenRoleIsAdmin() {
        ClientRequest req = new ClientRequest();
        req.setRole("admin");
        assertFalse(validator.isValid(req, context));
    }

    @Test
    void validWhenRoleIsUser() {
        ClientRequest req = new ClientRequest();
        req.setRole("user");
        assertTrue(validator.isValid(req, context));
    }
}

