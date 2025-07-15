package com.kbc.KbcApp.helper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.kbc.KbcApp.pojos.NewConfig;

public class GameConfigurationQuesAndTimerRowMapper implements RowMapper<NewConfig>{

	@Override
	public NewConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		int x=rs.getInt("NoQuestions");
		int y=rs.getInt("TimeAllocated");
		NewConfig gameConfig = new NewConfig(x,y);
		return gameConfig;
	}
}
