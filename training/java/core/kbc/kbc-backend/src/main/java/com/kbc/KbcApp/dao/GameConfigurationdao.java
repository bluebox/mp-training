package com.kbc.KbcApp.dao;

import org.springframework.stereotype.Repository;

import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.GameConfiguration;
import com.kbc.KbcApp.pojos.NewConfig;

@Repository
public interface GameConfigurationdao {
	
	NewConfig getNumberOfQuestionsAndTimer();
	GameConfiguration getGameConfigurations();
	Boolean setNumberOfQuestionsAndTimer(int questions, int seconds,  String modifiedBy) throws KbcException;
}