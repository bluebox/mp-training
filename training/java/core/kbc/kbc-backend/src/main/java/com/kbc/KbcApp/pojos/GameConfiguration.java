package com.kbc.KbcApp.pojos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameConfiguration {
	private Integer noQuestions;
	private Integer timeAllocated;
	private LocalDateTime modifiedAt;
	private String modifiedBy;	
}
