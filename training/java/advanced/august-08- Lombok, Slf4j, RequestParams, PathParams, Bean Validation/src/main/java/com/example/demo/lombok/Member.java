package com.example.demo.lombok;

import lombok.*;

// specifying all requirements
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Member {
	private String name;
	private Integer age;
}
