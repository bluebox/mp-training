package com.example.demo.repo;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.models.FoodItem;
@Repository
public class ItemRepo {
	@Autowired
	private JdbcTemplate jt;
	

public JdbcTemplate getJt() {
		return jt;
	}
@Autowired
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

public void additem(FoodItem fi)

{
	//System.out.println("akdaksd");
	String sql="insert into items(id,itemName,price,category)values(?,?,?,?)";
	jt.update(sql,fi.getId(),fi.getItemName(),fi.getPrice(),fi.getCategory());
	
	
}

public List<FoodItem> allItems(String ctgry)
{
	String sql="select * from items where category=?";
	RowMapper<FoodItem> rm=new RowMapper<FoodItem>() {
		
		@Override
		public FoodItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			FoodItem fi=new FoodItem();
			fi.setId(rs.getInt(1));
			fi.setItemName(rs.getString(2));
			fi.setPrice(rs.getDouble(3));
			fi.setCategory(rs.getString(4));
			return fi;
		}
	};
	 List<FoodItem>itms=jt.query(sql, rm, ctgry);
	 return itms;
	
	
}
}
