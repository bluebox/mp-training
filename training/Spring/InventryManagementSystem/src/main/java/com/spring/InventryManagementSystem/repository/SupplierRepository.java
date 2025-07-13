package com.spring.InventryManagementSystem.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.InventryManagementSystem.domain.Supplier;
import com.spring.InventryManagementSystem.interfaces.repository.SupplierRepositoryInterface;

@Repository
public class SupplierRepository implements SupplierRepositoryInterface {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public SupplierRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public List<Supplier> getAllSuppliers() throws SQLException {
		String sql = "SELECT * FROM suppliers";
		return namedParameterJdbcTemplate.query(sql,new MapSqlParameterSource(),new BeanPropertyRowMapper<>(Supplier.class));
	}

}
