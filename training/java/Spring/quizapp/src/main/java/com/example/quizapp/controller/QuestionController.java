package com.example.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.model.Question;
import com.example.quizapp.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@GetMapping("/AllQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		//return "Hi, These are your questions";
//		List<Question> questions = questionService.getAllQuestions();
//		questions.forEach(q -> System.out.println(q));
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/category/{cat}")
	public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable("cat") String category){
		return questionService.getAllQuestionsByCategory(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
}
