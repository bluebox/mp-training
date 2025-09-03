package com.sms.Dao.Implementation;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sms.Dao.Interfaces.PurchaseDao;
import com.sms.models.PurchaseDetails;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sms.models.PurchaseHeader;
@Repository
@Transactional (rollbackFor = Exception.class)
public class PurchaseDaoImplementation  implements PurchaseDao{

	
	  @Autowired
	    private JdbcTemplate jdbcTemplate;
	 @Override
	
	 public PurchaseHeader createPurchase(PurchaseHeader ph) {
		    String headerSql = "INSERT INTO tbl_purchase_header " +
		            "(supplier_id, total_taxable_amount, total_gst, total_amount, created_by) " +
		            "VALUES (?, ?, ?, ?, ?)";

		    KeyHolder keyHolder = new GeneratedKeyHolder();
		    jdbcTemplate.update(connection -> {
		        PreparedStatement ps = connection.prepareStatement(headerSql, Statement.RETURN_GENERATED_KEYS);
		        ps.setLong(1, ph.getSupplierId());
		        ps.setDouble(2, ph.getTotalTaxableAmount());
		        ps.setDouble(3, ph.getTotalGst());
		        ps.setDouble(4, ph.getTotalAmount());
		        ps.setString(5, ph.getCreatedBy());
		        return ps;
		    }, keyHolder);

		    Long generatedPurchaseId = keyHolder.getKey().longValue();
		    ph.setPurchaseId(generatedPurchaseId);

		    String detailSql = "INSERT INTO tbl_purchase_details " +
		            "(purchase_id, product_id, quantity, taxable_amount, gst_amount, total_amount, expiry) " +
		            "VALUES (?, ?, ?, ?, ?, ?, ?)";

		    for (PurchaseDetails pd : ph.getPd()) {
		        jdbcTemplate.update(detailSql,
		                generatedPurchaseId,
		                pd.getProductId(),
		                pd.getQuantity(),
		                pd.getTaxableAmount(),
		                pd.getGstAmount(),
		                pd.getTotalAmount(),
		                pd.getExpiry());
		        
		       
		        updateProductStock(pd.getProductId(), pd.getQuantity());
		    }

		    return ph;
		}

	 private void updateProductStock(String productId, long purchasedQuantity) {
		    
		    String checkProductSql = "SELECT total_qty FROM tbl_products_stock WHERE product_id = ?";

		    List<Long> existingStockList = jdbcTemplate.query(checkProductSql, new Object[]{productId}, (rs, rowNum) -> rs.getLong("total_qty"));
		    
		    if (existingStockList.isEmpty()) {
		        
		        String insertStockSql = "INSERT INTO tbl_products_stock (product_id, name, total_qty) " +
		                "SELECT ?, name, ? FROM tbl_product WHERE product_id = ?";
		        jdbcTemplate.update(insertStockSql, productId, purchasedQuantity, productId);
		    } else {
		        Long existingStock = existingStockList.get(0);
		        String updateStockSql = "UPDATE tbl_products_stock SET total_qty = total_qty + ? WHERE product_id = ?";
		        jdbcTemplate.update(updateStockSql, purchasedQuantity, productId);
		    }
		}
	 




	 @Override
	 public List<PurchaseHeader> viewAllPurchases() {
	     String sql = "SELECT * FROM tbl_purchase_header";

	     return jdbcTemplate.query(sql, (rs, rowNum) -> {
	         PurchaseHeader ph = new PurchaseHeader();
	         ph.setPurchaseId(rs.getLong("purchase_id"));
	         ph.setSupplierId(rs.getLong("supplier_id"));
	         ph.setTotalTaxableAmount(rs.getDouble("total_taxable_amount"));
	         ph.setTotalGst(rs.getDouble("total_gst"));
	         ph.setTotalAmount(rs.getDouble("total_amount"));
	         ph.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
	         ph.setCreatedBy(rs.getString("created_by"));
	         return ph;
	     });
	 }

//	@Override
//	public PurchaseHeader getPurchaseByfilter(Long PurchaseId,LocalDateTime createdAt) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	 @Override
	 public List<PurchaseDetails> viewPurchaseInDetail(Long purchaseId) {
	     String sql = "SELECT * FROM tbl_purchase_details WHERE purchase_id = ?";

	     return jdbcTemplate.query(sql, new Object[]{purchaseId}, (rs, rowNum) -> {
	         PurchaseDetails pd = new PurchaseDetails();
	         pd.setBatchId(rs.getLong("batch_id"));
	         pd.setPurchaseId(rs.getLong("purchase_id"));
	         pd.setProductId(rs.getString("product_id"));
	         pd.setQuantity(rs.getInt("quantity"));
	         pd.setTaxableAmount(rs.getDouble("taxable_amount"));
	         pd.setGstAmount(rs.getDouble("gst_amount"));
	         pd.setTotalAmount(rs.getDouble("total_amount"));
	         pd.setExpiry(rs.getDate("expiry") != null ? rs.getDate("expiry").toLocalDate() : null);
	         return pd;
	     });
	 }


	 @Override
	 public boolean existsBySupplierAndBatch(Long supplierId, Long batchId) {
		// TODO Auto-generated method stub
		return false;
	 }


	 @Override
	 public void decrease(Long batchId, int amount) {
		// TODO Auto-generated method stub
		
	 }


	 @Override
	 public void increase(Long batchId, int amount) {
		// TODO Auto-generated method stub
		
	 }


}
