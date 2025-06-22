package com.natived.nativedvalidatordemo.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    @Test
    void handleValidationException_ReturnsBadRequestWithFieldError() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "clientRequest");
        bindingResult.addError(new FieldError("clientRequest", "role", "role is a required field"));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<Map<String, Object>> response = handler.handleValidationException(ex);
        assertEquals(400, response.getStatusCodeValue());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals("BAD REQUEST", body.get("status"));
        assertTrue(body.get("timestamp") instanceof String);
        assertTrue(body.get("message").toString().contains("role"));
        assertTrue(body.get("message").toString().contains("role is a required field"));
    }
}

