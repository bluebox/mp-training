package com.kbc.KbcApp.daoImpl;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kbc.KbcApp.dao.GameConfigurationdao;
import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.helper.GameConfigurationQuesAndTimerRowMapper;
import com.kbc.KbcApp.helper.GameConfigurationRowMapper;
import com.kbc.KbcApp.pojos.GameConfiguration;
import com.kbc.KbcApp.pojos.NewConfig;
import com.kbc.KbcApp.utilites.DBQueries;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class GameConfigurationDAOImpl implements GameConfigurationdao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Boolean setNumberOfQuestionsAndTimer(int questions,int seconds, String modifiedBy) throws KbcException {
		int rowsEffected;
		try {
			GameConfiguration g=getGameConfigurations();
			   updateGameConfigLog(g.getNoQuestions(),g.getTimeAllocated(),g.getModifiedAt(),g.getModifiedBy());
			   rowsEffected = jdbcTemplate.update(DBQueries.UPDATE_GAME_CONFIGURATION,questions,seconds,LocalDateTime.now(), modifiedBy);
		}
		catch(Exception exe) {
			log.error("Error : {}",exe);
			throw new KbcException("Unable to update game configuration");
		}
		return rowsEffected > 0;

	}
	@Override
	public NewConfig getNumberOfQuestionsAndTimer() {
			return jdbcTemplate.queryForObject(DBQueries.GET_TIME_AND_QUESTIONS,new GameConfigurationQuesAndTimerRowMapper());		
	}
	
	private Integer updateGameConfigLog(int noOfQuestions, int timeAllocated,  LocalDateTime modifiedAt,String modifiedBy) {
		return jdbcTemplate.update(DBQueries.INSERT_GAME_CONFIGURATION_LOG,noOfQuestions,timeAllocated,modifiedAt,modifiedBy);
	}
	
	@Override
	public GameConfiguration getGameConfigurations() {
		return  jdbcTemplate.queryForObject(DBQueries.READ_GAME_CONFIGURATION, new GameConfigurationRowMapper());
	}
	
	
	
	
}