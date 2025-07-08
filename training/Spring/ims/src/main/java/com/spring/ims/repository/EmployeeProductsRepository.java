package com.spring.ims.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeProductsRepository {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public EmployeeProductsRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	/*
	 create table employeeProducts(
	stockId int ,
	procuctName varchar(100),
	productId int,
	supplier varchar(100),
	quantity int,
	minQuan int,
	maxQuan int);
	 */
	
	

}
