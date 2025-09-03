package com.sms.StockManagementSystem;

import com.sms.Dao.Implementation.PurchaseDaoImplementation;
import com.sms.models.PurchaseDetails;
import com.sms.models.PurchaseHeader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class purchaseDaoImplementationTest {

    @Autowired
    private PurchaseDaoImplementation purchaseDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("DELETE FROM tbl_purchase_details");
        jdbcTemplate.update("DELETE FROM tbl_purchase_header");
        jdbcTemplate.update("DELETE FROM tbl_products_stock");

      // jdbcTemplate.update("INSERT INTO tbl_products_stock (product_id, total_qty, name) VALUES ('C123', 100, 'Coughfsils');");
      // jdbcTemplate.update("INSERT INTO tbl_product (product_id, name) VALUES ('P00', 'Product 1')");
    }


    @Test
    void testCreatePurchase_success() {
        PurchaseHeader purchaseHeader = new PurchaseHeader();
        purchaseHeader.setSupplierId(10101L);
        purchaseHeader.setTotalTaxableAmount(1000);
        purchaseHeader.setTotalGst(180);
        purchaseHeader.setTotalAmount(1180);
        purchaseHeader.setCreatedBy("TestUser");

        PurchaseDetails detail = new PurchaseDetails();
        detail.setProductId("L1");
        detail.setQuantity(10);
        detail.setTaxableAmount(800);
        detail.setGstAmount(144);
        detail.setTotalAmount(944);
        detail.setExpiry(LocalDate.of(2026, 12, 31));

        purchaseHeader.setPd(List.of(detail));

        PurchaseHeader savedHeader = purchaseDao.createPurchase(purchaseHeader);

        assertNotNull(savedHeader.getPurchaseId(), "Purchase ID should not be null");
        assertEquals(1, jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_purchase_header", Integer.class));
        assertEquals(1, jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_purchase_details", Integer.class));

        Long productStock = jdbcTemplate.queryForObject(
           "SELECT total_qty FROM tbl_products_stock WHERE product_id = 'L1'", Long.class);
        assertNotNull(productStock);
       //System.out.println(productStock);
       assertEquals(10, productStock);
    }

    @Test
    void testCreatePurchase_failure_due_to_invalid_data() {
        PurchaseHeader purchaseHeader = new PurchaseHeader();
        purchaseHeader.setSupplierId(1L);
        purchaseHeader.setTotalTaxableAmount(-1000); 
        purchaseHeader.setTotalGst(-180); 
        purchaseHeader.setTotalAmount(-1180); 
        purchaseHeader.setCreatedBy("TestUser");

        PurchaseDetails detail = new PurchaseDetails();
        detail.setProductId("P001");
        detail.setQuantity(10);
        detail.setTaxableAmount(800);
        detail.setGstAmount(144);
        detail.setTotalAmount(944);
        detail.setExpiry(LocalDate.of(2026, 12, 31));  

        purchaseHeader.setPd(List.of(detail));

        assertThrows(RuntimeException.class, () -> purchaseDao.createPurchase(purchaseHeader));

        Integer purchaseCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_purchase_header", Integer.class);
        assertEquals(0, purchaseCount);

        Integer detailCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_purchase_details", Integer.class);
        assertEquals(0, detailCount);

//        Long productStock = jdbcTemplate.queryForObject(
//            "SELECT total_qty FROM tbl_products_stock WHERE product_id = 'P001'", Long.class);
//        assertNull(productStock); 
    }


    @Test
    void testCreatePurchase_failure_due_to_non_existent_product() {
        PurchaseHeader purchaseHeader = new PurchaseHeader();
        purchaseHeader.setSupplierId(1L);
        purchaseHeader.setTotalTaxableAmount(1000);
        purchaseHeader.setTotalGst(180);
        purchaseHeader.setTotalAmount(1180);
        purchaseHeader.setCreatedBy("TestUser");

        PurchaseDetails detail = new PurchaseDetails();
        detail.setProductId("P999");
        detail.setQuantity(10);
        detail.setTaxableAmount(800);
        detail.setGstAmount(144);
        detail.setTotalAmount(944);
        detail.setExpiry(LocalDate.of(2026, 12, 31));

        purchaseHeader.setPd(List.of(detail));

        assertThrows(RuntimeException.class, () -> purchaseDao.createPurchase(purchaseHeader));

        Integer purchaseCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_purchase_header", Integer.class);
        assertEquals(0, purchaseCount);

        Integer detailCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tbl_purchase_details", Integer.class);
        assertEquals(0, detailCount);

//       Long productStock = jdbcTemplate.queryForObject(
//           "SELECT total_qty FROM tbl_products_stock WHERE product_id = 'P999'", Long.class);
//        assertNull(productStock);
    }
}
