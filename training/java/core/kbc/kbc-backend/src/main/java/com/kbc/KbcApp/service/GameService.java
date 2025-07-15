package com.kbc.KbcApp.service;



import java.util.List;

import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.Game;

public interface GameService {

	boolean addGame(Game game) throws KbcException;

	Double getAvgScorePercentageByUserId(int userId);

	List<Game> getAllGames(Integer userId);
	
	//void addGameHistory(Game game, String QuestionIds);
}
