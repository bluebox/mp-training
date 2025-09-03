package com.rms.restaurant_management_system_backend.dao.implementation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.dao.OrderDetailsDao;
import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.rowmappers.OrderDetailsRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

	private final JdbcTemplate jdbcTemplate;

	public OrderDetailsDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int[] insertOrderDetails(List<OrderDetails> orderDetailsList) {
		String insertQuery = SqlQueries.ORDER_DETAILS_INSERT;
		return jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				OrderDetails orderDetails = orderDetailsList.get(i);
				ps.setInt(1, orderDetails.getOrderId());
				ps.setInt(2, orderDetails.getItemId());
				ps.setInt(3, orderDetails.getQuantity());
				ps.setDouble(4, orderDetails.getPrice());
			}

			@Override
			public int getBatchSize() {
				return orderDetailsList.size();
			}
		});
	}

	@Override
	public List<OrderDetails> selectAllOrderDetails() {
		String selectQuery = SqlQueries.ORDER_DETAILS_SELECT;
		return jdbcTemplate.query(selectQuery, new OrderDetailsRowMapper());
	}

//	@Override
//	public List<OrderDetails> getAllOrderDetails() {
//		return jdbcTemplate.query(SqlQueries.GET_ALL_ORDERDETAILS,
//	        (rs, rowNum) -> {
//	            OrderDetails od = new OrderDetails();
//	            od.setOrderDetailsId(rs.getInt("ord_details_id"));
//	            od.setOrderId(rs.getInt("ord_id"));
//	            od.setItemId(rs.getInt("item_id"));
//	            od.setQuantity(rs.getInt("quantity"));
//	            od.setPrice(rs.getDouble("price"));
//	            return od;
//	        });
//	}


}
