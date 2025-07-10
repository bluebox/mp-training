package com.spring.ims.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.ims.domain.EmployeeProducts;
import com.spring.ims.domain.Product;
import com.spring.ims.interfaces.repository.ProductRepositoryInterface;

@Repository
public class ProductRepository implements ProductRepositoryInterface{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public ProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	//List Of Products
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
	
	//Cost Of Products
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
	
	//Supplier Of Product
	public int supplierOfProduct(int productId) {
		String sql = "SELECT supplierId from products where productId = :productId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productId", productId);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, params,Integer.class);
		}catch(Exception e) {
			System.out.println("Error at supplierOfProduct in ProductRepository : "+e.getMessage());
			return 0;
		}
	}

	@Override
	public Product fetchProduct(String productName,int productId) {
		String sql = "SELECT supplierId from products where productName = :productName AND productId=:productId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productName", productName);
		params.addValue("productId", productId);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, params,new BeanPropertyRowMapper<Product>());
		}catch(Exception e) {
			System.out.println("Error at supplierOfProduct in ProductRepository : "+e.getMessage());
			return null;
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
