package com.example.demo.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("prototype")
public class FoodItem {


	@NotNull(message = "ID is required")
    @Positive(message = "ID must be positive")
    private Integer id;

    @NotBlank(message = "Item name is required")
    private String itemName;

    @NotNull(message = "Price is required")
    @Min(value = 10, message = "Price must be at least 10")
    private Double price;

    @NotBlank(message = "Category is required")
    private String category;
    
}
