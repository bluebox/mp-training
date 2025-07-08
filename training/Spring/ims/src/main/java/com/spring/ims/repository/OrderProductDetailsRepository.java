package com.spring.ims.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.ims.domain.OrderProductDetails;
import com.spring.ims.domain.Orders;
import com.spring.ims.row.mapper.OrderProductDetailsRowMapper;

@Repository
public class OrderProductDetailsRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public OrderProductDetailsRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	// Adding List Of Products
	public boolean addProducts(List<OrderProductDetails> listOfProducts, int orderId) {
		String sql = "INSERT INTO orderProductDetails(orderId,supplier,product,productQuantity,productCost) VALUES(:orderId,:supplier,:product,:productQuantity,:productCost)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		try {
			for(int i=0;i<listOfProducts.size();i++) {
				params.addValue("orderId", orderId);
				params.addValue("supplier",listOfProducts.get(i).getSupplier());
				params.addValue("product", listOfProducts.get(i).getProduct());
				params.addValue("productQuantity", listOfProducts.get(i).getProductQuantity());
				params.addValue("productCost", listOfProducts.get(i).getProductCost()*listOfProducts.get(i).getProductQuantity());
				namedParameterJdbcTemplate.update(sql, params);
			}
			return true;
		}catch(Exception e) {
			System.out.println("Error at addProducts in OrderPrdoductRepository : "+e.getMessage());
			return false;
		}		
	}
	
	//List Of OrderProductDetails for a Order
	public List<OrderProductDetails> ProductsOfOrder(Orders order) {
		String sql = "SELECT orderDetailsId,orderId,supplier,product,productQuantity,productCost FROM orderProductDetails WHERE orderId = :orderId";
		try {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("orderId",order.getOrderId());
			return namedParameterJdbcTemplate.query(sql, params,new OrderProductDetailsRowMapper());
		}catch(Exception e) {
			System.out.println("Error at productsOfOrder in OrderPrdoductRepository : "+e.getMessage());
			return Collections.EMPTY_LIST;
		}
	}

	/*
	 * create table orderProductDetails( orderDetailsId int primary key
	 * auto_increment, orderId int, supplier varchar(100), product varchar(100),
	 * productQuantity int, productCost float);
	 */

}
