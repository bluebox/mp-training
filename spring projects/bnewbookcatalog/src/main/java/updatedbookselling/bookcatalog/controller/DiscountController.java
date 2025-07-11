package updatedbookselling.bookcatalog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import updatedbookselling.bookcatalog.domain.Discount;
import updatedbookselling.bookcatalog.service.DiscountService;

@RestController
@CrossOrigin(origins = "http://localhost:3000" )
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/add")
    public ResponseEntity<?> addDiscount(@RequestBody Discount discount) {
    	Map<String , String > response = new HashMap<>();
    	try {
    		int result = discountService.addDiscount(discount);
            if(result > 0) {
            	response.put("message", "Discount added successfully.");
            }
            else {
            	 response.put("message", "Failed to add discount or enter unique price.") ;
            }
            
            return ResponseEntity.ok(response);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>("Error: Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);

    	}
        
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateDiscount(@PathVariable int id, @RequestBody Discount discount) {
        
        
        
        Map<String , String > response = new HashMap<>();
    	try {
    		int result = discountService.updateDiscount(id, discount);
            if(result > 0) {
            	response.put("message", "Discount updated successfully.");
            }
            else {
            	 response.put("message", "Failed to add discount or enter unique price.") ;
            }
            
            return ResponseEntity.ok(response);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>("Error: Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);

    	}
    }

    @GetMapping("/{id}")
    public Discount getDiscountById(@PathVariable int id) {
        return discountService.getDiscountById(id);
    }
    
    @GetMapping("/discount-percentage")
    public ResponseEntity<?> getDiscountPercentage(@RequestParam double amount) {
    	Map<String , Double> response = new HashMap<>();
    	
    	response.put("percentage",  discountService.getDiscountPercentage(amount));
    	
    	return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteDiscountById(@PathVariable int id) {
    	
    	boolean result = discountService.deleteDiscountById(id);
    	
    	return result ? "Discount deleted successfully." : "Failed to delete discount. Enter valid Id";
    }

    @GetMapping("/list")
    public List<Discount> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }
}
