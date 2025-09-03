package com.sms.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.Dao.Interfaces.SupplierDao;
import com.sms.models.Supplier;
import com.sms.service.Interfaces.SupplierService;
import com.sms.utilities.Validations;

@Service
public class SupplierServiceImplementation implements SupplierService {

    @Autowired
    private SupplierDao sd;
  
  // private static  Validations validation;

    @Override
    public Long addSupplier(Supplier supplier) {
         Validations.supplierValidation(supplier);
        return sd.addSupplier(supplier);
    }

	@Override
	public List<Supplier> getSuppliers(String searchKey) {
		 return sd.getSuppliers(searchKey);
		
	}

   
    
    
    
   

       
}
