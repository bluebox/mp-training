package updatedbookselling.bookcatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import updatedbookselling.bookcatalog.daoimpl.DiscountRepository;
import updatedbookselling.bookcatalog.domain.Discount;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public int addDiscount(Discount discount) {
    	
        return discountRepository.addDiscount(discount);
    }

    public int updateDiscount(int id, Discount discount) {
        return discountRepository.updateDiscount(id, discount);
    }

    public Discount getDiscountById(int id) {
        return discountRepository.getDiscountById(id);
    }
    
    public double getDiscountPercentage(double amount) {
    	return discountRepository.getDiscountPercentage(amount);
    }
    
    public boolean deleteDiscountById(int id) {
    	return discountRepository.deleteDiscountById(id);
    	
    }
    public List<Discount> getAllDiscounts() {
        return discountRepository.getAllDiscounts();
    }
}

