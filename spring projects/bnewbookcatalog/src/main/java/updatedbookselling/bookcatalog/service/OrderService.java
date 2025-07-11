package updatedbookselling.bookcatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import updatedbookselling.bookcatalog.daoimpl.OrderRepository;
import updatedbookselling.bookcatalog.domain.Book;
import updatedbookselling.bookcatalog.domain.BookSalesDTO;
import updatedbookselling.bookcatalog.domain.OrderHistory;
import updatedbookselling.bookcatalog.domain.Orders;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public int placeOrder(Orders order, List<OrderHistory> items) {
        for (OrderHistory item : items) {
            int available = orderRepository.getBookQuantity(item.getBookId());
            if (available < item.getQuantity()) {
                throw new RuntimeException("Book ID " + item.getBookId() + " has insufficient stock.");
            }
        }

        orderRepository.createOrder(order);
        int orderId = orderRepository.getLastOrderId();

        for (OrderHistory item : items) {
            item.setOrderId(orderId);
            orderRepository.insertOrderHistory(item);
            orderRepository.decrementBookQuantity(item.getBookId(), item.getQuantity());
        }

        return orderId;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.getAllOrders();
    }
    
    public List<Orders> getAllOrdersOfUserById(@PathVariable int userId){
    	
    	return orderRepository.getAllOrdersOfUserById(userId);
    }

    public List<OrderHistory> getOrderDetails(int orderId) {
        return orderRepository.getOrderHistoryByOrderId(orderId);
    }
    
    public List<Book> getTopSellingBooks(){
    	
    	return orderRepository.getTopSellingBooks();
    }
}
