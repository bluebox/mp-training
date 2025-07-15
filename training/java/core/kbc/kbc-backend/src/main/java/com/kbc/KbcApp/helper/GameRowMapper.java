package com.kbc.KbcApp.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.kbc.KbcApp.pojos.Game;

public class GameRowMapper implements RowMapper<Game> {
	@Override
	public Game mapRow(ResultSet rs, int agr1) throws SQLException {
		Game game = new Game();
		game.setGameId(rs.getInt("gameId"));
		game.setUserId(rs.getInt("userId"));
		game.setNumOfQuestions(rs.getInt("numOfQuestions"));
		game.setScore(rs.getInt("score"));
		game.setPlayedAt(rs.getObject("PlayedAt", LocalDateTime.class));

		return game;

	}

}
