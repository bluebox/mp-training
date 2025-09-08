package com.medplus.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnumDto {
	private String enumName;
    private String code;
    private String description;
}
