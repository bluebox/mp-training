package com.product.category.dao.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.product.category.dao.CategoryDAO;
import com.product.category.domain.Category;
import com.product.category.domain.CategoryRequest;
import com.product.category.domain.CategoryRequestSearchCriteria;
import com.product.category.domain.CategorySearchCriteria;
import com.product.enums.RequestStatus;

@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void createCategoryRequest(CategoryRequest categoryRequest) {
		String sql = "INSERT INTO category_requests (CategoryName, RequestedBy, Status, CreatedAtDate) " +
                "VALUES (:name, :requestedBy, :status, :createdDate)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", categoryRequest.getCategoryName());
		params.addValue("requestedBy", categoryRequest.getRequestedBy());
		params.addValue("status", RequestStatus.PENDING);
		params.addValue("created", LocalDateTime.now());
		jdbcTemplate.update(sql,params);
   
	}

	@Override
	public void updateCategoryRequest(List<Long> requestIds, Integer approvedBy, RequestStatus status) {
	    if (requestIds == null || requestIds.isEmpty()) return;

	    String inSql = requestIds.stream().map(id -> "?").collect(Collectors.joining(","));
	    String sql = "UPDATE category_requests SET Status = ?, ApprovedBy = ?, UpdatedAtDate = ? WHERE CategoryRequestId IN (" + inSql + ")";

	    List<Object> params = new ArrayList<>();
	    params.add(status.getCode());
	    params.add(approvedBy);
	    params.add(Timestamp.valueOf(LocalDateTime.now()));
	    params.addAll(requestIds);

	    jdbcTemplate.update(sql, params.toArray());

	}


	@Override
	public List<CategoryRequest> getRequest(CategoryRequestSearchCriteria searchCriteria) {
		
		int requestIdFlag = (searchCriteria.getRequestId() > 0)?1:0;
		
		int categoryNameFlag = (searchCriteria.getCategoryNames()==null)?0:1;
		
		String sql = "SELECT * FROM category_requests WHERE " +
                "(? = 0 OR request_id = ?) AND " +
                "(? = 0 OR category_name = ?)";
		
        return jdbcTemplate.query(sql.toString(), categoryRequestRowMapper(), requestIdFlag, searchCriteria.getRequestId(), searchCriteria.getCategoryNames(), categoryNameFlag);
		
	}

	@Override
	public void createCategory(Category category) {
		 String sql = "INSERT INTO category (CategoryName, CreatedAtDate, UpdatedAtDate) VALUES (?, ?, ?)";
	        jdbcTemplate.update(sql,
	                category.getCategoryName(),
	                Timestamp.valueOf(category.getCreatedAtDate()),
	                Timestamp.valueOf(category.getUpdatedAtDate()));
		
	}

	@Override
	public List<Category> get(CategorySearchCriteria searchCriteria) {
		
		int requestIdFlag = (searchCriteria.getCategoryIds() > 0)?1:0;
		
		int categoryNameFlag = (searchCriteria.getCategoryNames()==null)?0:1;
		
		String sql = "SELECT * FROM categories WHERE " +
                "(? = 0 OR request_id = ?) AND " +
                "(? = 0 OR category_name = ?)";
		
        return jdbcTemplate.query(sql.toString(), categoryRowMapper(), requestIdFlag, searchCriteria.getCategoryIds(), searchCriteria.getCategoryNames(), categoryNameFlag);
		
	
	}
	
	private RowMapper<CategoryRequest> categoryRequestRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            CategoryRequest cr = new CategoryRequest();
            cr.setCategoryRequestId(rs.getInt("CategoryRequestId"));
            cr.setCategoryName(rs.getString("CategoryName"));
            cr.setStatus(RequestStatus.valueOf(rs.getString("Status")));
            cr.setRequestedBy(rs.getLong("RequestedBy"));
            cr.setApprovedBy(rs.getLong("ApprovedBy"));
            cr.setCreatedAtDate(rs.getTimestamp("CreatedAtDate").toLocalDateTime());
            cr.setUpdatedAtDate(rs.getTimestamp("UpdatedAtDate").toLocalDateTime());
            return cr;
        };
    }
	
	private RowMapper<Category> categoryRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            Category c = new Category();
            c.setCategoryId(rs.getInt("CategoryId"));
            c.setCategoryName(rs.getString("CategoryName"));
            c.setCreatedAtDate(rs.getTimestamp("CreatedAtDate").toLocalDateTime());
            c.setUpdatedAtDate(rs.getTimestamp("UpdatedAtDate").toLocalDateTime());
            return c;
        };
    }

}


