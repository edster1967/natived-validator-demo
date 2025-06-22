package com.natived.nativedvalidatordemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        ObjectError objectError = allErrors.get(0);
        String message = objectError.getDefaultMessage();
        String field = (objectError instanceof FieldError) ? ((FieldError) objectError).getField() : null;

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "BAD REQUEST");
        errorResponse.put("timestamp", LocalDateTime.now().toString());
        // message: first error, formatted as 'field message' or just message if field is null
        errorResponse.put("message", field != null ? String.format("%s %s", field, message) : message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
