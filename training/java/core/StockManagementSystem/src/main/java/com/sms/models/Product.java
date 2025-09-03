package com.sms.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @NotBlank(message = "Product ID is required")
    @Size(max = 15, message = "Product ID must not exceed 15 characters")
    private String productId;

    @NotBlank(message = "Product name is required")
    @Size(max = 50, message = "Product name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "A|I", message = "Status must be 'A' for Active or 'I' for Inactive")
    private String status;
}
