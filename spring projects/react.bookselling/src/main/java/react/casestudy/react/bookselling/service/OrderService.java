package react.casestudy.react.bookselling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import react.casestudy.react.bookselling.dao.OrderRepository;
import react.casestudy.react.bookselling.domain.OrderHistory;
import react.casestudy.react.bookselling.domain.Orders;

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

    public List<OrderHistory> getOrderDetails(int orderId) {
        return orderRepository.getOrderHistoryByOrderId(orderId);
    }
}
