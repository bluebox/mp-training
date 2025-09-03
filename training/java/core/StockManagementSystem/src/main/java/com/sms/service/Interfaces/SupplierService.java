package com.sms.service.Interfaces;

import java.util.List;

import com.sms.models.Supplier;

public interface SupplierService {
	
	
 public Long addSupplier(Supplier supplier);
 
 
  public List<Supplier> getSuppliers(String searchKey);
 
  
 
}
