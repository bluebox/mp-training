package com.kbc.KbcApp.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbc.KbcApp.daoImpl.GameConfigurationDAOImpl;
import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.GameConfiguration;
import com.kbc.KbcApp.pojos.NewConfig;
import com.kbc.KbcApp.service.GameConfigurationService;
@Service
public class GameConfigurationServiceImpl implements GameConfigurationService{
	
	@Autowired
	GameConfigurationDAOImpl gameConfigDao;
	
	public void setNumberOfQuestionsAndTimer(int questions, int seconds,  String modifiedBy) throws Exception{
		if(questions <=0 || questions>=20 ) {
			throw new KbcException("Number of questions must be between 1 and 20");
		}
		if(seconds <=0 || seconds>300) {
			throw new KbcException("Timer must be between 1 sec and 300 sec (5 mins)");
		}
		 gameConfigDao.setNumberOfQuestionsAndTimer(questions, seconds, modifiedBy);	
	}

	@Override
	public NewConfig getNumberOfQuestionsAndTimer() throws Exception{
		return gameConfigDao.getNumberOfQuestionsAndTimer();
	}

	@Override
	public GameConfiguration getGameConfigurations() throws Exception {
		return gameConfigDao.getGameConfigurations();
	}
	
	
}