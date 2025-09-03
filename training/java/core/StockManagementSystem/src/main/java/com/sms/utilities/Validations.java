package com.sms.utilities;

import com.sms.models.Product;
import com.sms.models.PurchaseDetails;
import com.sms.models.PurchaseHeader;
import com.sms.models.Supplier;
import com.sms.Exceptions.*;

public class Validations {

    public static void productValidation(Product product) {
        if (product == null) {
            throw new ProductExceptions("Product cannot be null");
        }

        if (product.getProductId() == null || product.getProductId().isBlank()) {
            throw new ProductExceptions("Product ID is required");
        }

        if (product.getName() == null || product.getName().isBlank()) {
            throw new ProductExceptions("Product name is required");
        }

        if (product.getStatus() == null || product.getStatus().isBlank()) {
            throw new ProductExceptions("Product status is required");
        }
    }

    public static void purchaseValidation(PurchaseHeader ph) {
        if (ph == null || ph.getSupplierId() == null || ph.getTotalAmount() <= 0
                || ph.getCreatedBy() == null || ph.getCreatedBy().isBlank()
                || ph.getPd() == null || ph.getPd().isEmpty()) {
            throw new InvalidDataExceptions("Invalid purchase header data");
        }

        for (PurchaseDetails pd : ph.getPd()) {
            if (pd.getProductId().isBlank() || pd.getQuantity() == null || pd.getQuantity() <= 0
                    || pd.getTotalAmount() <= 0 || pd.getExpiry() == null
                    || pd.getExpiry().isBefore(java.time.LocalDate.now().plusDays(1))) {
                throw new InvalidDataExceptions("Invalid purchase detail data");
            }
        }
    }

//    public static void supplierValidation(Supplier s) {
//        if (s == null || s.getName().isBlank() || 
//                ||
//                || s.getEmail().isBlank() || s.getCountry().isBlank() || s.getState().isBlank()
//                || s.getCity().isBlank() || s.getAddress().isBlank() || s.getCreatedBy().isBlank()) {
//            throw new SupplierExceptions("Invalid supplier data");
//        }
//    }
    
    public static void supplierValidation(Supplier s)
    {
    	if(s==null)
    	{
    		throw new SupplierExceptions("supplier data is null");
    	}
    	if(s.getName().isBlank())
    	{
    		throw new SupplierExceptions("Name field is empty");
    	}
    	if(s.getGender().isBlank() || !s.getGender().matches("M|F"))
    	{
    		throw new SupplierExceptions("Invalid Gender");
    	}
    	if( s.getMobile() == null || s.getMobile() < 1000000000L || s.getMobile() > 9999999999L) 
    	{
    		throw new SupplierExceptions("Invalid Mobile number please check");
    	}
    	 if(s.getEmail().isBlank())
    	 {
    		 throw new SupplierExceptions("Invalid email please check");
    	 }
    	 if(s.getCountry().isBlank())
    	 {
    		 throw new SupplierExceptions("Invalid Country please select correct country");
    	 }
    	 if(s.getState().isBlank())
    	 {
    		 throw new SupplierExceptions("Invalid State please select correct state");
    	 }
    	 if(s.getCity().isBlank())
    	 {
    		 throw new SupplierExceptions("invalid city please select city correctly");
    	 }
    	 if(s.getCreatedBy().isBlank())
    	 {
    		 throw new SupplierExceptions("please mention who is creating this");
    	 }
    	if(s.getAddress().isBlank())
    	{
    		throw new SupplierExceptions("Invalid Supplier address");
    	}
    }
}