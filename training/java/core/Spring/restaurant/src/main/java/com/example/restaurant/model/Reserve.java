package com.example.restaurant.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Reserve {

    @NotBlank(message = "Name cannot be blank")
    @Size(min=3,message = "Name should of minimum length 3")
    private String name;

    @NotBlank(message = "Date cannot be empty")
    private String date;

    @NotBlank(message = "Time cannot be blank")
    private String time;
}
