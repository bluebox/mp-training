package com.kbc.KbcApp.daoImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kbc.KbcApp.dao.QuestionDao;
import com.kbc.KbcApp.helper.QuestionRowMapper;
import com.kbc.KbcApp.pojos.Question;
import com.kbc.KbcApp.utilites.DBQueries;

@Repository
public class QuestionDaoImpl implements QuestionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public boolean addQuestion(Question question, String username) {
			int cnt = jdbcTemplate.update(DBQueries.INSERT_QUESTION,
					question.getQuestion(),
					question.getOption1(),
					question.getOption2(),
					question.getOption3(),
					question.getOption4(),
					question.getLevel().toString(),
					question.getStatus().toString(),
					LocalDateTime.now(),
					username);
			return cnt>0;
	}


	
	@Override
	public boolean updateQuestion(Question question, String username) {
		insertQuestionToLog(question.getQuestionId());
		int cnt=jdbcTemplate.update(DBQueries.UPDATE_QUESTION,
				question.getQuestion(),
				question.getOption1(),
				question.getOption2(),
				question.getOption3(),
				question.getOption4(),
				question.getLevel().toString(),
				question.getStatus().toString(),
				username,
				LocalDateTime.now(),
				question.getQuestionId());
		return cnt>0;
	}

	
	private void insertQuestionToLog(int questionId) {
		List<Question> ques = showAllQuestions(questionId, null,null);
		Question question=ques.get(0);
		jdbcTemplate.update(DBQueries.INSERT_QUESTION_TO_LOG,
				question.getQuestionId(),
				question.getQuestion(),
				question.getOption1(),
				question.getOption2(),
				question.getOption3(),
				question.getOption4(),
				question.getLevel().toString(),
				question.getStatus().toString(),
				question.getCreatedAt(),
				question.getCreatedBy(),
				question.getModifiedBy(),
				question.getModifiedAt()
				);
	}


	@Override
	public List<Question> showAllQuestions(Integer questionId, String status, String level) {
		int questionIdFlag=questionId!=null ? 1:0;
		int statusFlag=status!=null ? 1:0;
		int levelFlag=level!=null ? 1:0;

		  MapSqlParameterSource params = new MapSqlParameterSource()
		            .addValue("questionId", questionId)
		            .addValue("status", status)
		            .addValue("level", level)
		            .addValue("questionIdFlag", questionIdFlag)
		            .addValue("statusFlag", statusFlag)
		            .addValue("levelFlag", levelFlag);
		List<Question> quesList= namedParameterJdbcTemplate.query(DBQueries.GET_QUESTION,params, new QuestionRowMapper());
		return quesList;
	}

	

}
