package com.example.SpringLomBokExample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Example {

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String occupation;
}
