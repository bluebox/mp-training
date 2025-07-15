package com.kbc.KbcApp.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbc.KbcApp.dao.QuestionDao;
import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.Question;
import com.kbc.KbcApp.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
    @Autowired
    private QuestionDao questionDao;

    @Override
    @Transactional(rollbackFor= {Exception.class})
    public boolean addQuestion(Question question, String username) throws KbcException   {
    	try {
            return questionDao.addQuestion(question, username);
    	}catch(DuplicateKeyException e) {
			throw new KbcException("Question Already Exists");
		}
    }

 

	@Override
	public boolean updateQuestion(Question question, String username) {
		return questionDao.updateQuestion(question, username);
	}

	

	@Override
	public List<Question> showAllQuestions(Integer questionId, String status, String level) {
		return questionDao.showAllQuestions(questionId,status,level);
	}



	@Override
	public List<Map<String, String>> shuffle(List<Question> list, int n) {
	    List<Map<String, String>> res = new ArrayList<>();

	    Collections.shuffle(list);
	    List<Question> shuffledQuestions = list.stream().limit(n).collect(Collectors.toList());

	    for (Question q : shuffledQuestions) {
	        List<String> options =new ArrayList<>();
	        options.add(q.getOption1());
	        options.add(q.getOption2());
	        options.add(q.getOption3());
	        options.add(q.getOption4());
	        Collections.shuffle(options);

	        Map<String, String> map = new HashMap<>();
	        map.put("questionId", String.valueOf(q.getQuestionId()));
	        map.put("question", q.getQuestion());
	        map.put("optionA", options.get(0));
	        map.put("optionB", options.get(1));
	        map.put("optionC", options.get(2));
	        map.put("optionD", options.get(3));

	        res.add(map);
	    }

	    return res;
	}


	
}
