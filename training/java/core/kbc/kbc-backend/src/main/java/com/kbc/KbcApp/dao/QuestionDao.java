package com.kbc.KbcApp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kbc.KbcApp.pojos.Question;

@Repository
public interface QuestionDao {
	
	public boolean addQuestion(Question question, String username);
	
	public boolean updateQuestion(Question question, String username);
		
    public List<Question> showAllQuestions(Integer questionId,String status, String level);
	
}
