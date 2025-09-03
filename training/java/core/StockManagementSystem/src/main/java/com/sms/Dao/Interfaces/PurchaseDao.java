package com.sms.Dao.Interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.sms.models.PurchaseDetails;
import com.sms.models.PurchaseHeader;

public interface PurchaseDao {

	 public PurchaseHeader createPurchase(PurchaseHeader ph);
	 
	 public List<PurchaseHeader>viewAllPurchases();  // purchase headder Table
	 
	// public PurchaseHeader getPurchaseByfilter(Long PurchaseId,LocalDateTime createdAt);
	 
	 public List<PurchaseDetails>viewPurchaseInDetail(Long purchaseId); // particular purchase whole details
	 
	 boolean existsBySupplierAndBatch(Long supplierId, Long batchId);
	 
	   public void decrease(Long batchId, int amount);
	   
	 public  void increase(Long batchId, int amount);
	 
}
