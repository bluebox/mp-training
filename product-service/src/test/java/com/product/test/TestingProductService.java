package com.product.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.product.Exceptions.ProductRequestDatabaseOperationException;
import com.product.app.ProductConfiguration;
import com.product.dao.ProductRequestDAO;
import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;
import com.product.enums.RequestStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest(classes = ProductConfiguration.class)
public class TestingProductService {
	@Autowired
	private ProductRequestDAO productRequestDAO;

	@Test
	public void test() throws ProductRequestDatabaseOperationException {
		productRequestDAO.fetchByCriteria(new SearchProductRequestCriteria());
		productRequestDAO.createProductRequest(new ProductRequest((long) 101, "Dell XPS 15",
				"High-performance laptop with 16GB RAM and 512GB SSD", BigDecimal.valueOf(15000.00), 5, 1L,
				RequestStatus.PENDING, 2L, LocalDateTime.now(), LocalDateTime.now()));
	}
}
