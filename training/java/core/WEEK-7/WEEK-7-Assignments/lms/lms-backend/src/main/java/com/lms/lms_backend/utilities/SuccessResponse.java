package com.lms.lms_backend.utilities;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SuccessResponse {

    public static ResponseEntity<Object> build(HttpStatus status, String message, Object data) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("message", message);
        if (data != null) {
            body.put("data", data);
        }
        return new ResponseEntity<>(body, status);
    }

    public static ResponseEntity<Object> build(HttpStatus status, String message) {
        return build(status, message, null);
    }
}
