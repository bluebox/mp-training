package com.sms.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.Dao.Interfaces.ProductDao;
import com.sms.models.Product;
import com.sms.models.ProductStock;
import com.sms.service.Interfaces.ProductService;
import com.sms.utilities.Validations;

@Service
public class ProductServiceImplementation implements ProductService {
	
	
   // private static  Validations vld;
    @Autowired
    private ProductDao pd;
    


	

	@Override
    public String addProduct(Product product) {
		//System.out.println(vld + " " + pd);
        Validations.productValidation (product);
        return pd.addProduct(product);
        
    }

	@Override
	public List<Product> getProducts(String searchKey) {
		return pd.getProducts(searchKey);
	}

	@Override
	public List<ProductStock> ViewProductStock() {
		return pd.ViewProductStock();
	}

	

	

	

   
}
