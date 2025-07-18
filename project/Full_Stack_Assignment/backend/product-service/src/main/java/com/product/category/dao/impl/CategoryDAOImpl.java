package com.product.category.dao.impl;

import java.sql.ResultSet;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.product.Exceptions.CategoryRequestDatabaseOperationException;
import com.product.category.dao.CategoryDAO;
import com.product.category.domain.Category;
import com.product.category.domain.CategoryRequest;
import com.product.category.domain.CategoryRequestSearchCriteria;
import com.product.category.domain.CategorySearchCriteria;
import com.product.enums.RequestStatus;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void createCategoryRequest(CategoryRequest categoryRequest) {
		String sql = "INSERT INTO category_requests (CategoryName, RequestedBy, Status, CreatedAtDate) " +
                "VALUES (:name, :requestedBy, :status, :createdDate)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", categoryRequest.getCategoryName());
		params.addValue("requestedBy", categoryRequest.getRequestedBy());
		params.addValue("status", RequestStatus.PENDING.getCode());
		params.addValue("createdDate", LocalDateTime.now());
		namedParameterJdbcTemplate.update(sql,params);
   
	}

	@Override
	public void updateCategoryRequest(List<Long> requests, Integer approvedBy, RequestStatus status) {
	    if (requests == null || requests.isEmpty()) return;
	    
	    String selectSql = "SELECT CategoryName, RequestedBy, Status, UpdatedAtDate " +
                "FROM category_requests WHERE CategoryRequestId IN (:requestIds)";

	    List<Map<String, Object>> recordsToLog = namedParameterJdbcTemplate.queryForList(selectSql, Collections.singletonMap("requestIds", requests));


	   String insertLogSql = "INSERT INTO category_request_log (CategoryName, RequestedBy, Status, UpdatedAtDate) " +
                   "VALUES (:categoryName, :requestedBy, :status, :updatedAtDate)";

//	   for (Map<String, Object> row : recordsToLog) {
//		   MapSqlParameterSource logParams = new MapSqlParameterSource();
//		   logParams.addValue("categoryName", row.get("CategoryName"));
//		   logParams.addValue("requestedBy", row.get("RequestedBy"));
//		   logParams.addValue("status", row.get("Status"));
//		   logParams.addValue("updatedAtDate", row.get("UpdatedAtDate"));
//
//		   namedParameterJdbcTemplate.update(insertLogSql, logParams);
//	   }

	    String sql = "UPDATE category_requests " +
	                 "SET Status = :status, ApprovedBy = :approvedBy, UpdatedAtDate = :updatedAtDate " +
	                 "WHERE CategoryRequestId IN (:requestIds)";

	    System.out.println(status.toString().charAt(0));
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("status", status.getCode());
	    params.addValue("approvedBy", approvedBy);
	    params.addValue("updatedAtDate", LocalDateTime.now());
	    params.addValue("requestIds", requests);

	    namedParameterJdbcTemplate.update(sql, params);
	}


	@Override
	public List<CategoryRequest> getRequest(CategoryRequestSearchCriteria searchCriteria) throws CategoryRequestDatabaseOperationException {
	    try {
	        System.out.println("Request ID: " + searchCriteria.getRequestId());

	        int requestIdFlag = (searchCriteria.getRequestId() != null) ? 1 : 0;
	        int statusFlag = (searchCriteria.getStatus() != null) ? 1 : 0;
	        int categoryNameFlag = (searchCriteria.getCategoryName() != null) ? 1 : 0;

	        System.out.println("Flags set: requestIdFlag=" + requestIdFlag + ", statusFlag=" + statusFlag + ", categoryNameFlag=" + categoryNameFlag);

	        String sql = "SELECT * FROM category_requests WHERE " +
	                "(:requestidFlag = 0 OR CategoryRequestId = :Categoryrequest) AND " +
	                "(:categorynameFlag = 0 OR CategoryName = :categoryname) AND " +
	                "(:statusflag = 0 OR Status = :status)";

	        MapSqlParameterSource params = new MapSqlParameterSource();
	        params.addValue("requestidFlag", requestIdFlag);
	        params.addValue("Categoryrequest", searchCriteria.getRequestId());
	        params.addValue("categorynameFlag", categoryNameFlag);
	        params.addValue("categoryname", searchCriteria.getCategoryName());
	        params.addValue("statusflag", statusFlag);
	        if(statusFlag==1) {
	        params.addValue("status", String.valueOf(searchCriteria.getStatus().charAt(0)));
	        }
	        else {
	        	params.addValue("status", null);
	        }
	        System.out.println("Executing query with params: " + params.getValues());

	        return namedParameterJdbcTemplate.query(sql, params, categoryRequestRowMapper());

	    } catch (Exception e) {
	        System.err.println("Error occurred while fetching category requests: " + e.getMessage());
	        e.printStackTrace();
	        throw new CategoryRequestDatabaseOperationException("Failed to retrieve category requests", e);
	    }
	}


	@Override
	public void createCategory(Category category) {
	    String sql = "INSERT INTO categories (CategoryName) " +
	                 "VALUES (:categoryName)";

	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("categoryName", category.getCategoryName());
	    namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public List<Category> get(CategorySearchCriteria searchCriteria) {
		
		System.out.println("flad 1 calling");
		int requestIdFlag = (searchCriteria.getCategoryIds() == null)?0:1;
		System.out.println(requestIdFlag);
		System.out.println("flad 2 calling");
		
		int categoryNameFlag = (searchCriteria.getCategoryNames()==null)?0:1;
		System.out.println(categoryNameFlag);
		String sql = "SELECT * FROM categories WHERE " +
               "(:requestidFlag = 0 OR CategoryId = :categoryId) AND " +
                "(:categorynameFlag = 0 OR CategoryName  = :categoryName)";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("requestidFlag", requestIdFlag);
		params.addValue("categoryId", searchCriteria.getCategoryIds());
		params.addValue("categorynameFlag", categoryNameFlag);
		params.addValue("categoryName", searchCriteria.getCategoryNames());

		System.out.println("query calling");
       return namedParameterJdbcTemplate.query(sql, params, categoryRowMapper());
		
	
	}
	
	private RowMapper<CategoryRequest> categoryRequestRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            CategoryRequest cr = new CategoryRequest();
            cr.setCategoryRequestId(rs.getLong("CategoryRequestId"));
            cr.setCategoryName(rs.getString("CategoryName"));
            cr.setStatus(RequestStatus.fromCode(rs.getString("Status")));
            cr.setRequestedBy(rs.getLong("RequestedBy"));
            cr.setApprovedBy(rs.getLong("ApprovedBy"));
           
           Timestamp createdAtTimestamp = rs.getTimestamp("CreatedAtDate");
           cr.setCreatedAtDate(createdAtTimestamp != null ? createdAtTimestamp.toLocalDateTime() : null);

           Timestamp updatedAtTimestamp = rs.getTimestamp("UpdatedAtDate");
           cr.setUpdatedAtDate(updatedAtTimestamp != null ? updatedAtTimestamp.toLocalDateTime() : null);

            return cr;
        };
    }
	
	private RowMapper<Category> categoryRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            Category c = new Category();
            c.setCategoryId(rs.getInt("CategoryId"));
            c.setCategoryName(rs.getString("CategoryName"));
            c.setCreatedAtDate(rs.getTimestamp("CreatedAtDate") != null
                    ? rs.getTimestamp("CreatedAtDate").toLocalDateTime()
                    : null);
                c.setUpdatedAtDate(rs.getTimestamp("UpdatedAtDate") != null
                    ? rs.getTimestamp("UpdatedAtDate").toLocalDateTime()
                    : null);
           
            return c;
        };
    }

	

}


