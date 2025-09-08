package com.medplus.Roles_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnumResponse {
    private String name;
    private String code;
    private String description;
}
