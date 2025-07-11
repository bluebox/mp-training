package updatedbookselling.bookcatalog.daoimpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import updatedbookselling.bookcatalog.domain.Book;
import updatedbookselling.bookcatalog.domain.BookSalesDTO;
import updatedbookselling.bookcatalog.domain.OrderHistory;
import updatedbookselling.bookcatalog.domain.Orders;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    BookRepository bookRepository;

    public void createOrder(Orders order) {
        String sql = "INSERT INTO Orders (memberId,totalBooks , booksDiscount,slabDiscount,originalPrice, finalPrice,purchaseDate) VALUES (?, ?, ? , ? , ? , ? ,? )";
        jdbcTemplate.update(sql, order.getMemberId(), order.getTotalBooks() , 
        		order.getBooksDiscount(),order.getSlabDiscount() , 
        		order.getOriginalPrice() ,order.getFinalPrice() , order.getPurchaseDate());
    }

    public int getLastOrderId() {
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    public int getBookQuantity(int bookId) {
        String sql = "SELECT quantity FROM Book WHERE bookId = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
    }

    public void decrementBookQuantity(int bookId, int qty) {
        String sql = "UPDATE Book SET quantity = quantity - ? WHERE bookId = ?";
        jdbcTemplate.update(sql, qty, bookId);
    }

    public void insertOrderHistory(OrderHistory item) {
        String sql = "INSERT INTO OrderHistory (orderId, bookId, quantity, originalPrice , finalPrice ) VALUES (?, ?, ?, ? , ?)";
        jdbcTemplate.update(sql, item.getOrderId(), item.getBookId(), item.getQuantity(), item.getOriginalPrice() , item.getFinalPrice());
    }

    public List<Orders> getAllOrders() {
        String sql = "SELECT orderId , memberId, totalBooks , booksDiscount,slabDiscount,originalPrice, finalPrice,purchaseDate FROM Orders";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Orders.class));
    }
    
    public List<Orders> getAllOrdersOfUserById(@PathVariable int userId){
    	
    	String sql = "select orderId , memberId , totalBooks ,booksDiscount,slabDiscount,originalPrice, finalPrice,purchaseDate from Orders where memberId = ?";
    	
    	return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Orders.class) ,userId);
    	
//    	return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            Orders order = new Orders();
//            order.setOrderId(rs.getInt("orderId"));
//            order.setMemberId(rs.getInt("memberId"));
//            order.setTotalBooks(rs.getInt("totalBooks"));
//            order.setBooksDiscount(rs.getDouble("booksDiscount"));
//            order.setSlabDiscount(rs.getDouble("slabDiscount"));
//            order.setOriginalPrice(rs.getDouble("originalPrice"));
//            order.setFinalPrice(rs.getDouble("finalPrice"));
//            order.setPurchaseDate(rs.getDate("purchaseDate"));
//            return order;
//        } , userId);
    	
    	
    }

    public List<OrderHistory> getOrderHistoryByOrderId(int orderId) {
        String sql = "SELECT orderId, bookId, quantity, originalPrice , finalPrice  FROM OrderHistory WHERE orderId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderHistory.class), orderId);
    }
    
    //top selling books
    
//    public List<Book> getTopSellingBooks(){
//    	String sql = "SELECT bookId, SUM(quantity) AS totalSold FROM OrderHistory GROUP BY bookId ORDER BY totalSold DESC LIMIT 5";
//    	
//    	List<BookSalesDTO> topSellingBooks = jdbcTemplate.query(sql , (rs , rowNum) ->{
//    		BookSalesDTO book = new BookSalesDTO();
//    		book.setBookId(rs.getInt("bookId"));
//    		book.setTotalSold(rs.getInt("totalSold"));
//    		return book;
//    	});
//    	
//    	List<Book> topBooks = topSellingBooks.stream().map(bookDTO -> bookRepository.getBookById(bookDTO.getBookId()) ).toList();
//    	
//    	return topBooks;
//    }
    
    public List<Book> getTopSellingBooks(){
    	
    	Map<Integer , Integer> response = new LinkedHashMap<>(); 
    	String sql = "SELECT bookId, SUM(quantity) AS totalSold FROM OrderHistory GROUP BY bookId ORDER BY totalSold DESC LIMIT 5";
    	
    	jdbcTemplate.query(sql,(rs, rowNum)-> {
    		response.put(rs.getInt("bookId") , rs.getInt("totalSold") ) ;
    		
    		return "";
    	}			
    	);
    	
    	List<Book> books = response.keySet().stream()
					    	.map(k ->{
					    		Book book = new Book();
					    		book = bookRepository.getBookById(k);
					    		book.setSoldCount(response.get(k));
					    		
					    		return book;
					    	}).toList();
    	
    	return books;
    }
}
