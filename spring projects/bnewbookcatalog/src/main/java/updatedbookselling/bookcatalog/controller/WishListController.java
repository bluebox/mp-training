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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import updatedbookselling.bookcatalog.domain.Book;
import updatedbookselling.bookcatalog.domain.WishList;
import updatedbookselling.bookcatalog.service.WishListService;

@RestController
@CrossOrigin(origins = "http://localhost:3000" )
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @GetMapping
    public List<WishList> getAllWishLists() {
        return wishListService.getAllWishLists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWishListById(@PathVariable int id) {
        
        Map<String , String> response = new HashMap<>();
        try {  
            return ResponseEntity.ok( wishListService.getWishListById(id));
        } catch (Exception e) {
        	e.printStackTrace();
        	response.put("status", "404");
	        response.put("message", "WishList not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToWishList(@RequestBody WishList wishList) {
    	
    	Map<String, Object> response = new HashMap<>();

        if (!wishListService.isAlreadyWishListed(wishList)) {
            int result = wishListService.addToWishList(wishList);
            if (result > 0) {
                response.put("message", "Book added to wishlist.");
                response.put("success", true);
            } else {
                response.put("message", "Failed to add to wishlist.");
                response.put("success", false);
            }
        } else {
            response.put("message", "Book already wishlisted.");
            response.put("success", false);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public String deleteWishListById(@PathVariable int id) {
        int result = wishListService.deleteWishListById(id);
        return result > 0 ? "Wishlist item deleted." : "Wishlist item not found.";
    }
    
    @GetMapping("/users/{userId}")
    public  ResponseEntity<?> getWishListOfUserById(@PathVariable Integer userId) {
    	
    	List<Book> book = wishListService.getWishListOfUserById(userId);
    	return ResponseEntity.ok(book);
    }

    @GetMapping("/top-book")
    public List<Book> getTopWishListedBook() {
         return  wishListService.getTopWishListedBook();
    }
    
    @PostMapping("/check")
    public ResponseEntity<Boolean> checkAlreadyWishListed(@RequestBody WishList wishList) {
        boolean exists = wishListService.isAlreadyWishListed(wishList);
        return ResponseEntity.ok(exists);
    }
}
