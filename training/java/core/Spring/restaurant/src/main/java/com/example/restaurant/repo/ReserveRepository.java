package com.example.restaurant.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Reserve;

@Repository
public class ReserveRepository {
	
	private JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public void saveReserve(Reserve reserve) {
		
		String statement="insert into reserve(name,date,time) values(?,?,?)";
		template.update(statement,reserve.getName(),reserve.getDate(),reserve.getTime() );
	}
	
	public List<Reserve> getAllReserves(){
		
		String que="select * from resrve";
		List<Reserve> reserves=template.query(que, (result,row)->{
			Reserve m=new Reserve();
			m.setName(result.getString("name"));
			m.setDate(result.getString("date"));
			m.setTime(result.getString("time"));
			return m;
		});
		return reserves;
	}
}
