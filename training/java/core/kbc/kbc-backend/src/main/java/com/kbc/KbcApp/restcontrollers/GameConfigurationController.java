package com.kbc.KbcApp.restcontrollers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbc.KbcApp.pojos.GameConfiguration;
import com.kbc.KbcApp.pojos.NewConfig;
import com.kbc.KbcApp.serviceImpl.GameConfigurationServiceImpl;

@RestController
@RequestMapping("/api/game-config")
public class GameConfigurationController {
	
	private final GameConfigurationServiceImpl gameConfigService ;
	
	@Autowired
	public GameConfigurationController(GameConfigurationServiceImpl g) {
		this.gameConfigService = g;	
	}
	
	@PutMapping("/set-questions-and-timer")
	public ResponseEntity<?> setNumberOfQuestionsAndTimer(@RequestBody NewConfig config,HttpSession session) throws Exception{
		String modifiedBy=(String) session.getAttribute("userName");
			int questions=config.getNoQuestions();
			int seconds=config.getTimeAllocated();
		gameConfigService.setNumberOfQuestionsAndTimer(questions,seconds,modifiedBy);
		return ResponseEntity.ok("Number of questions and timer updated successfully");		
	}
	

	@GetMapping("/get-questions-and-timer") 
	public ResponseEntity<?> getNumberOfQuestionsAndTimer() throws Exception{
		NewConfig gameConfiguration = gameConfigService.getNumberOfQuestionsAndTimer();
		return ResponseEntity.ok(gameConfiguration);
	}
	
	@GetMapping("/get-game-configuration")
	public ResponseEntity<?> getGameConfiguration()  throws Exception{
		GameConfiguration gameConfig = gameConfigService.getGameConfigurations();
		return ResponseEntity.ok(gameConfig);
	}
}