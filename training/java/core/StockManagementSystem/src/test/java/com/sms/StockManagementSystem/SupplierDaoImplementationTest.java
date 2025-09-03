package com.sms.StockManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;


import com.sms.Dao.Implementation.SupplierDaoImplementation;
import com.sms.models.Supplier;
@SpringBootTest
@Transactional
public class SupplierDaoImplementationTest {
	@Autowired
    private SupplierDaoImplementation supplierDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @BeforeEach
    void setUp() {
    
    }
    @Test
    void testAddSupplier_success()
    {
    	Supplier splr=new Supplier();
    	 // Integer cnt=  jdbcTemplate.update("SELECT COUNT(*) FROM tbl_suppliers",Integer.class);
    	// splr.setSupplierId(rs.getLong("supplier_id"));
         splr.setName("rani");
         splr.setGender("M");
         splr.setMobile(9949399582L);
         splr.setEmail("rani@gmail.com");
         splr.setCountry("india");
         splr.setState("telengene");
         splr.setCity("hyderabad");
         splr.setAddress("begumpet");
         //splr.setCreatedAt(rs.getTimestamp("created_at"));
         splr.setCreatedBy("gopi");
         Long id=supplierDao.addSupplier(splr);
         //System.out.println(cnt);
         assertEquals(10106,id);
    }
    
}
