package com.lms.web.model;

import org.springframework.beans.factory.annotation.Value;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Holidays {
	@Value("${spring.holidays}")
	private String holidays;
}
