
package com.kbc.KbcApp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.Question;

@Service
public interface QuestionService {
	
    public boolean addQuestion(Question question,String username) throws KbcException ;
        
    public boolean updateQuestion(Question question, String username);

    public List<Map<String,String>> shuffle(List<Question> list, int n);
    
    public List<Question> showAllQuestions(Integer questionId,String status, String level);

}
