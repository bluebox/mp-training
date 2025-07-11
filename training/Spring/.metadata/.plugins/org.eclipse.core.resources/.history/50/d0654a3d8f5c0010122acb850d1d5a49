package com.spring.ims.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public ProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<String> listOfProducts(int supplierId, String search) {
		String sql = "SELECT CONCAT_WS(' - ',productName,productId) from products where (supplierId = :supplierId) AND (productName like :search OR CAST(productId as CHAR) like :search)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("supplierId", supplierId);
		params.addValue("search", "%"+search+"%");
		try {
			return namedParameterJdbcTemplate.queryForList(sql, params,String.class);
		}catch(Exception e) {
			System.out.println("Error in listOfProducts in ProductRepository : "+e.getMessage());
			return Collections.EMPTY_LIST;
		}
	}

	public float costOfProduct(int productId) {
		String sql = "SELECT productCost from products where productId = :productId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productId", productId);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, params,float.class);
		}catch(Exception e) {
			System.out.println("Error at costOfProduct in ProductRepository : "+e.getMessage());
			return 0;
		}
		
	}

	
	/*	
	create table products(
	productId int primary key auto_increment,
	supplierId int not null,
	productName varchar(100),
	productCost float);
	 */
	
	

}
