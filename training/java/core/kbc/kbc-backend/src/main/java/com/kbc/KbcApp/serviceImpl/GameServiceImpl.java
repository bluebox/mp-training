package com.kbc.KbcApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbc.KbcApp.dao.GameDao;
import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.Game;
import com.kbc.KbcApp.service.GameService;

@Transactional
@Service
public class GameServiceImpl implements GameService {

	private GameDao gameDao;

	@Autowired
	public void GameService(GameDao gameDao) {
		this.gameDao = gameDao;
	}

	@Transactional
	@Override
	public boolean addGame(Game game) throws KbcException  {

		boolean isScoreCorrect = false;
		boolean doesUserExists = false;

		// logic
		if (game.getNumOfQuestions() >= game.getScore()) {
			isScoreCorrect = true;
		}
		if (game.getUserId() != null && game.getUserId() > 0) {
			doesUserExists = true;
		}

		if (isScoreCorrect && doesUserExists) {
			gameDao.addGame(game);
			return true;
		} else {
			throw new KbcException("Values of Fields are Incorrect.");
		}
	}

	public Double getAvgScorePercentageByUserId(int userId) {

		Double sum = gameDao.getSumOfScorePercentageByUserId(userId);
		Integer totalGames = gameDao.getTotalNumberOfGamesOfUserId(userId);

		return sum / totalGames;

	}

	public List<Game> getAllGames(Integer userId) {

		return gameDao.getAllGames(userId);

	}

}
