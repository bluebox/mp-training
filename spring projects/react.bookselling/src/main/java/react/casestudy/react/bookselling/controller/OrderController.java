package react.casestudy.react.bookselling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import react.casestudy.react.bookselling.domain.OrderHistory;
import react.casestudy.react.bookselling.domain.OrderRequest;
import react.casestudy.react.bookselling.domain.Orders;
import react.casestudy.react.bookselling.service.OrderService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {
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

    @GetMapping("/{orderId}/details")
    public ResponseEntity<List<OrderHistory>> getOrderDetails(@PathVariable int orderId) {
        return ResponseEntity.ok(orderService.getOrderDetails(orderId));
    }
} 