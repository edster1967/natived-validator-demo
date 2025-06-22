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
        StringBuilder sb = new StringBuilder();
        sb.append(field).append(" : ").append(message);
        errorResponse.put("message", String.format("%s %s", field, message));
        // Add errors field as a list of error messages
        errorResponse.put("errors", allErrors.stream().map(ObjectError::getDefaultMessage).toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
