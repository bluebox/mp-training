package updatedbookselling.bookcatalog.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import updatedbookselling.bookcatalog.domain.Discount;

@Repository
public class DiscountRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public int addDiscount(Discount discount) {
    	
    	try {
    		String sql = "INSERT INTO Discount (price , discount) VALUES (?, ?)";
            return jdbcTemplate.update(sql, discount.getPrice() , discount.getDiscount());
    	}
        catch(Exception e) {
        	return 0;
        }
    }

    public int updateDiscount(int id, Discount discount) {
        String sql = "UPDATE Discount SET price=?, discount=? WHERE id=?";
        return jdbcTemplate.update(sql,discount.getPrice() , discount.getDiscount() , id);
    }

    public Discount getDiscountById(int id) {
        String sql = "SELECT id , price , discount FROM Discount WHERE  id= ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Discount.class), id);
    }
    
    public double getDiscountPercentage(double amount) {
    	String sql = "select discount from Discount where price >= ? order by price limit 1";
    	Double discount = jdbcTemplate.queryForObject(sql , Double.class , amount);
    	if(discount == null) {
    		return 20;
    	}
    	return discount;
    }
    
    public Boolean deleteDiscountById(int id) {
    	String sql = "delete from Discount where id= ?";
    	
    	return jdbcTemplate.update(sql ,id) > 0;
    	
    	
    }
    
    public List<Discount> getAllDiscounts() {
        String sql = "SELECT id , price ,discount  FROM Discount";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Discount discount = new Discount();
            discount.setId(rs.getInt("id"));
            discount.setPrice(rs.getDouble("price"));
            discount.setDiscount(rs.getDouble("discount"));
            
            return discount;
        });
    }


}
