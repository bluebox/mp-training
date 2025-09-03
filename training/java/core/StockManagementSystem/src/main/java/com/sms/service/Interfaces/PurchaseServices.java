package com.sms.service.Interfaces;


import java.util.List;

import com.sms.models.PurchaseDetails;
import com.sms.models.PurchaseHeader;

public interface PurchaseServices {
	
	
 public PurchaseHeader createPurchase(PurchaseHeader ph);
 
 public List<PurchaseHeader>viewAllPurchases();  // purchase headder Table
 

 
 public List<PurchaseDetails>viewPurchaseInDetail(Long purchaseId); // particular purchase whole details
 
 
}
