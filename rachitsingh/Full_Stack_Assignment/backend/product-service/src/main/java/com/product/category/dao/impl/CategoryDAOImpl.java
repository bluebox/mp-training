package com.product.category.dao.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.product.category.dao.CategoryDAO;
import com.product.category.domain.Category;
import com.product.category.domain.CategoryRequest;
import com.product.category.domain.CategoryRequestSearchCriteria;
import com.product.category.domain.CategorySearchCriteria;
import com.product.enums.RequestStatus;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void createCategoryRequest(CategoryRequest categoryRequest) {
		String sql = "INSERT INTO category_requests (CategoryName, RequestedBy, Status, CreatedAtDate) " +
                "VALUES (:name, :requestedBy, :status, :createdDate)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", categoryRequest.getCategoryName());
		params.addValue("requestedBy", categoryRequest.getRequestedBy());
		params.addValue("status", RequestStatus.PENDING.getCode());
		params.addValue("createdDate", LocalDateTime.now());
		jdbcTemplate.update(sql,params);
   
	}

	@Override
	public void updateCategoryRequest(List<Long> requestIds, Integer approvedBy, RequestStatus status) {
	    if (requestIds == null || requestIds.isEmpty()) return;

	    String sql = "UPDATE category_requests " +
	                 "SET Status = :status, ApprovedBy = :approvedBy, UpdatedAtDate = :updatedAtDate " +
	                 "WHERE CategoryRequestId IN (:requestIds)";

	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("status", status.getCode());
	    params.addValue("approvedBy", approvedBy);
	    params.addValue("updatedAtDate", LocalDateTime.now());
	    params.addValue("requestIds", requestIds);

	    jdbcTemplate.update(sql, params);
	}



	@Override
	public List<CategoryRequest> getRequest(CategoryRequestSearchCriteria searchCriteria) {
		
		int requestIdFlag = (searchCriteria.getRequestId() > 0)?1:0;
		
		int statusFlag= 1;
		
		int categoryNameFlag = (searchCriteria.getCategoryNames()==null)?0:1;		
		String sql = "SELECT * FROM category_requests WHERE " +
					"(:requestidFlag = 0 OR CategoryRequestId = :Categoryrequest) AND " +
                "(:categorynameFlag = 0 OR CategoryName  = :categoryname) and (:statusflag = 0 OR Status = :status)";
	
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("requestidFlag", requestIdFlag);
		params.addValue("Categoryrequest", searchCriteria.getCategoryIds());
		params.addValue("categorynameFlag", categoryNameFlag);
		params.addValue("categoryname", searchCriteria.getCategoryNames());
		params.addValue("statusflag", statusFlag);
		params.addValue("status", searchCriteria.getStatus());
		
      return jdbcTemplate.query(sql, params, categoryRequestRowMapper());
      
	}

	@Override
	public void createCategory(Category category) {
	    String sql = "INSERT INTO category (CategoryName, CreatedAtDate, UpdatedAtDate) " +
	                 "VALUES (:categoryName, :createdAtDate, :updatedAtDate)";

	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("categoryName", category.getCategoryName());
	    params.addValue("createdAtDate", Timestamp.valueOf(category.getCreatedAtDate()));
	    params.addValue("updatedAtDate", Timestamp.valueOf(category.getUpdatedAtDate()));

	    jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Category> get(CategorySearchCriteria searchCriteria) {
		
		int requestIdFlag = (searchCriteria.getCategoryIds() > 0)?1:0;
		
		int categoryNameFlag = (searchCriteria.getCategoryNames()==null)?0:1;
		
		String sql = "SELECT * FROM categories WHERE " +
               "(:requestidFlag = 0 OR CategoryRequestId = :categoryRequestId) AND " +
                "(:categorynameFlag = 0 OR CategoryName  = :categoryName)";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("requestidFlag", requestIdFlag);
		params.addValue("categoryRequestId", searchCriteria.getCategoryIds());
		params.addValue("categorynameFlag", categoryNameFlag);
		params.addValue("categoryName", searchCriteria.getCategoryNames());
       return jdbcTemplate.query(sql, params, categoryRowMapper());
		
	
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


