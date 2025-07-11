package updatedbookselling.bookcatalog.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import updatedbookselling.bookcatalog.domain.Book;
import updatedbookselling.bookcatalog.domain.WishList;

@Repository
public class WishListRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired 
    private BookRepository bookRepository;

    public List<WishList> getAllWishLists() {
        String sql = "SELECT * FROM wishlist";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WishList.class));
    }

    public WishList getWishListById(int id) {
        String sql = "SELECT wishlistId , memberId , bookId FROM wishlist WHERE wishlistId = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(WishList.class), id);
    }
    
    

    public int addToWishList(WishList wishList) {
        String sql = "INSERT INTO wishlist (memberId, bookId) VALUES (?, ?)";
        return jdbcTemplate.update(sql, wishList.getMemberId(), wishList.getBookId());
    }
    
    
    
    
    public  List<Book> getWishListOfUserById(Integer userId) {
    	System.out.println(userId);
    	System.out.println("---------------------------------------------------------");
    	String sql = "select bookId from wishlist where memberId = ?";
    	
//    	RowMapper<Integer> rowMapper = (rs, rowNum) -> rs.getInt("bookId");
//
//        // 2. Use jdbcTemplate.query with the RowMapper to get a List<Integer>
//        List<Integer> bookIds = jdbcTemplate.query(sql, rowMapper, userId);
    	
    	List<Integer> bookIds = jdbcTemplate.query(sql,(rs, rowNum) -> rs.getInt("bookId") , userId);
    	
    	List<Book> books = bookIds.stream().map(id -> bookRepository.getBookById(id)).toList();
    	System.out.println("---------------------------------------------------------");
    	
    	return books;
    }
    
    public int deleteWishListById(int id) {
        String sql = "DELETE FROM wishlist WHERE wishlistId = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Book> getTopWishListedBook() {
        
    	Map<Integer , Integer> response = new HashMap<>();
    	
    	String sql = "SELECT bookId , count(bookId) as book_count FROM wishlist GROUP BY bookId ORDER BY book_count DESC";
//        List<Integer> bookIds = jdbcTemplate.query(sql,(rs, rowNum) -> rs.getInt("bookId") );
    	
    	jdbcTemplate.query(sql,(rs, rowNum)-> {
    		response.put(rs.getInt("bookId") , rs.getInt("book_count") ) ;
    		
    		return "";
    	}			
    	);
    	
    	List<Book> books = response.keySet().stream()
					    	.map(k ->{
					    		Book book = new Book();
					    		book = bookRepository.getBookById(k);
					    		book.setwishListedCount(response.get(k));
					    		
					    		return book;
					    	}).toList();
   
//    	List<Book> books = bookIds.stream().map(id -> bookRepository.getBookById(id)).toList();
    	
    	return books;
        
    }
    
    
    public Boolean checkAlreadyWishListed(WishList wishlist) {
        String sql = "SELECT COUNT(*) FROM wishlist WHERE memberId = ? AND bookId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, wishlist.getMemberId(), wishlist.getBookId());
        System.out.println(count +" from wishlist repo..");
        return count != null && count > 0;
    }

}

