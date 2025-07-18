package com.product.util;

import org.springframework.http.ResponseEntity;

import com.product.dto.ApiResponse;

public class ResponseUtil {

	public static <T> ResponseEntity<ApiResponse<T>> success(String msg, T data) {
        return ResponseEntity.ok(new ApiResponse<>(true, msg, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> failure(String msg) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, msg, null));
    }
}
