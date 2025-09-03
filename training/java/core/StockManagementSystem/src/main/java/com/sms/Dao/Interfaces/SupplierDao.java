package com.sms.Dao.Interfaces;

import java.util.List;

import com.sms.models.Supplier;

public interface SupplierDao {

	public Long addSupplier(Supplier supplier);
	 

	public List<Supplier> getSuppliers(String searchKey) ;
	  
}
