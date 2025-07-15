package com.kbc.KbcApp.restcontrollers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.Question;
import com.kbc.KbcApp.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionRestController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("/addQuestion")
	public ResponseEntity<String> addQuestion(@Valid @RequestBody Question question, BindingResult bindingResult, HttpSession session)
			throws KbcException {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid Fields");
		}
		String user=(String) session.getAttribute("userName");
		boolean result = questionService.addQuestion(question,user);
		if (result) {
			return ResponseEntity.ok("Question added successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed to add question.");
		}
	}

	@PutMapping("/updateQuestion")
	public ResponseEntity<String> updateQuestion(@Valid @RequestBody Question question, BindingResult bindingResult, HttpSession session)
			throws KbcException {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid Fields");
		}
		String user=(String) session.getAttribute("userName");
		boolean updated = questionService.updateQuestion(question,user );
		if (updated) {
			return ResponseEntity.ok("Question updated successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed to update question.");
		}
	}

	
	@GetMapping("/checkAnswer/{id}/{selectedAnswer}")
	public ResponseEntity<Boolean> getAllQuestions(@PathVariable String selectedAnswer, @PathVariable int id) {
		List<Question> list = questionService.showAllQuestions(id, null, null);
		if (list.get(0).getOption1().equals(selectedAnswer)) {
			return ResponseEntity.status(HttpStatus.OK).body(true);
		}
		return ResponseEntity.ok(false);
	}

	@GetMapping("/{level}/{numOfQuestions}")
	public ResponseEntity<?> getQuestions(@PathVariable String level, @PathVariable int numOfQuestions) {
		List<Question> list = questionService.showAllQuestions(null, "ACTIVE", level.toLowerCase());
		if (list.size() == 0) {
			return ResponseEntity.status(HttpStatus.OK).body("Questions List is Empty");
		}
		List<Map<String,String>> list1=questionService.shuffle(list, numOfQuestions);
		return ResponseEntity.ok(list1);
	}

	@GetMapping("/getAllQuestions")
	public ResponseEntity<?> getAllQuestions() {
		List<Question> list = questionService.showAllQuestions(null, null, null);
		if (list.size() == 0) {
			return ResponseEntity.status(HttpStatus.OK).body("Questions List is Empty");
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/filter/{status}")
	public ResponseEntity<?> getQuestionByParameter(@PathVariable String status) {
		List<Question> list;
		if (status.toUpperCase().equals("ACTIVE") || status.toUpperCase().equals("INACTIVE")) {
			list = questionService.showAllQuestions(null, status.toUpperCase(), null);
		} else {
			list = questionService.showAllQuestions(null, null, status.toUpperCase());
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/filter/{status}/{level}")
	public ResponseEntity<?> getQuestionByParameters(@PathVariable String status, @PathVariable String level) {
		List<Question> list = questionService.showAllQuestions(null, status.toUpperCase(), level.toUpperCase());
		return ResponseEntity.ok(list);
	}

}
