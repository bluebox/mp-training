package updatedbookselling.bookcatalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import updatedbookselling.bookcatalog.domain.OrderHistory;
import updatedbookselling.bookcatalog.domain.OrderRequest;
import updatedbookselling.bookcatalog.domain.Orders;
import updatedbookselling.bookcatalog.service.OrderService;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {
    	
    	System.out.println(request.toString());
    	
        try {
            int orderId = orderService.placeOrder(request.getOrder(), request.getItems());
            return ResponseEntity.ok("Order placed successfully with ID: " + orderId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getAllOrdersOfUserById(@PathVariable int userId){
    	
    	List<Orders> orders= orderService.getAllOrdersOfUserById(userId);
    	
    	return orders == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("no orders") : ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{orderId}/details")
    public ResponseEntity<List<OrderHistory>> getOrderDetails(@PathVariable int orderId) {
        return ResponseEntity.ok(orderService.getOrderDetails(orderId));
    }
    
    @GetMapping("/top-selling-book")
    public ResponseEntity<?> topSellingBooks(){
    	return ResponseEntity.ok(orderService.getTopSellingBooks());
    }
    
} 