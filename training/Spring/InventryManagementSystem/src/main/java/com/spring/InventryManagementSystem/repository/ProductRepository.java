package com.spring.InventryManagementSystem.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.InventryManagementSystem.domain.Product;
import com.spring.InventryManagementSystem.interfaces.repository.ProductRepositoryInterface;

@Repository
public class ProductRepository implements ProductRepositoryInterface {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public List<Product> getAllProducts(String supplier) {
		String sql = "SELECT * FROM product WHERE supplier = :supplier";
		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("supplier", supplier);
		return namedParameterJdbcTemplate.query(sql,params ,new BeanPropertyRowMapper<>(Product.class));
	}

	@Override
	public List<Product> lowStock() {
		String sql = "SELECT * FROM product WHERE quantity < minQuantity";
		return namedParameterJdbcTemplate.query(sql,new MapSqlParameterSource() ,new BeanPropertyRowMapper<>(Product.class));
	}


}
