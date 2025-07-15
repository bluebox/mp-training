package com.kbc.KbcApp.pojos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Game {
	private Integer gameId;
	private Integer userId;
	private Integer numOfQuestions;
	private Integer score;
	private LocalDateTime playedAt;
}
