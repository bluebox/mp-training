package com.kbc.KbcApp.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.kbc.KbcApp.enums.LevelEnum;
import com.kbc.KbcApp.enums.StatusEnum;
import com.kbc.KbcApp.pojos.Question;

public class QuestionRowMapper implements RowMapper<Question> {

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		Question question=new Question();
		question.setQuestionId(rs.getInt(1));
		question.setQuestion(rs.getString(2));
		question.setOption1(rs.getString(3));
		question.setOption2(rs.getString(4));
		question.setOption3(rs.getString(5));
		question.setOption4(rs.getString(6));
		question.setLevel(LevelEnum.valueOf(rs.getString(7)));
		question.setStatus(StatusEnum.valueOf(rs.getString(8)));
		question.setCreatedAt(rs.getObject(9,LocalDateTime.class));
		question.setCreatedBy(rs.getString(10));
		question.setModifiedAt(rs.getObject(11,LocalDateTime.class));
		question.setModifiedBy(rs.getString(12));
		return question;
	}
	
}
