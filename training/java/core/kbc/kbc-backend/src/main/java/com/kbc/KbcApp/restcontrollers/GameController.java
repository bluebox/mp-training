package com.kbc.KbcApp.restcontrollers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.Game;
import com.kbc.KbcApp.service.GameService;

@RestController
@RequestMapping("/api/game")
public class GameController {

	private final GameService gameService;

	@Autowired
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping("/add")
	public ResponseEntity<String> addGame(@Valid @RequestBody Game game, BindingResult bindingResult,HttpSession session) throws KbcException {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Fields");
		}
		if (game == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Game is null");
		}

		if (game.getNumOfQuestions() < game.getScore()) {
			return ResponseEntity.status(400).body("Invalid Score-Questions Ratio");
		}
		int userId=(int) session.getAttribute("userId");
		game.setUserId(userId);
		boolean gameAdded = gameService.addGame(game);

		if (gameAdded) {
			return ResponseEntity.ok("Game added successfully.");
		} else {
			return ResponseEntity.status(500).body("Failed to add game.");
		}
	}

	@GetMapping("/avgScorePercentage/{userId}")
	public ResponseEntity<?> getAvgScorePercentageByUserId(@PathVariable int userId) {

		if (userId < 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid UserId");
		}
		Integer userIdInt = Integer.valueOf(userId);

		if (userIdInt != null) {
			Double result = gameService.getAvgScorePercentageByUserId(userId);
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UserId is null");
		}
	}

	@GetMapping("/viewGames/{userId}")
	public ResponseEntity<?> getAllGames(@PathVariable int userId) {

		if (userId > 0) {
			List<Game> games = gameService.getAllGames(Integer.valueOf(userId));
			return ResponseEntity.ok(games);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UserId is invalid");
		}

	}

	@GetMapping("/viewGames")
	public ResponseEntity<?> getAllGames() {
		List<Game> games = gameService.getAllGames(null);
		return ResponseEntity.ok(games);
	}

}
