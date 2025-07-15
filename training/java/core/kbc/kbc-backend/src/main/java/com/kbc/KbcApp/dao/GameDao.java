package com.kbc.KbcApp.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kbc.KbcApp.pojos.Game;

@Transactional
public interface GameDao {
	void addGame(Game game);

	Double getSumOfScorePercentageByUserId(int userId);

	Integer getTotalNumberOfGamesOfUserId(int userId);

	List<Game> getAllGames(Integer userId);
	
	//public void addGameHistory(Game game, String Questions);

}
