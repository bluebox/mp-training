package com.example.restaurant.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.Menu;

@Repository
public class MenuRepository {
	
	private JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public List<Menu> getMenu(){
		
		String que="select * from menu";
		List<Menu> menu=template.query(que, (result,row)->{
			Menu m=new Menu();
			m.setName(result.getString("name"));
			m.setPrice(result.getDouble("price"));
			return m;
		});
		return menu;
	}
	
}
