package com.kbc.KbcApp.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.kbc.KbcApp.pojos.GameConfiguration;



public class GameConfigurationRowMapper implements RowMapper<GameConfiguration> {

	@Override
	public GameConfiguration mapRow(ResultSet rs, int rowNum) throws SQLException {
		GameConfiguration gameConfig = new GameConfiguration();
		gameConfig.setNoQuestions(rs.getInt("NoQuestions"));
		gameConfig.setTimeAllocated(rs.getInt("TimeAllocated"));
		gameConfig.setModifiedAt(rs.getObject("ModifiedAt",LocalDateTime.class));
		gameConfig.setModifiedBy(rs.getString("ModifiedBy"));
		
		return gameConfig;
	}
}
