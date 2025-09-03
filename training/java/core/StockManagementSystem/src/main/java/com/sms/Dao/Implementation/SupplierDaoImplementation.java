package com.sms.Dao.Implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sms.Dao.Interfaces.SupplierDao;
import com.sms.models.Supplier;

@Repository
@Transactional
public class SupplierDaoImplementation implements SupplierDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class SupplierRowMapper implements RowMapper<Supplier> {
        @Override
        public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
            Supplier supplier = new Supplier();
            supplier.setSupplierId(rs.getLong("supplier_id"));
            supplier.setName(rs.getString("name"));
            supplier.setGender(rs.getString("gender"));
            supplier.setMobile(rs.getLong("mobile"));
            supplier.setEmail(rs.getString("email"));
            supplier.setCountry(rs.getString("country"));
            supplier.setState(rs.getString("state"));
            supplier.setCity(rs.getString("city"));
            supplier.setAddress(rs.getString("address"));
            supplier.setCreatedAt(rs.getTimestamp("created_at"));
            supplier.setCreatedBy(rs.getString("created_by"));
            return supplier;
        }
    }

    @Override
    public Long addSupplier(Supplier supplier) {
        String sql = "INSERT INTO tbl_supplier (name, gender, mobile, email, country, state, city, address, created_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getGender());
            ps.setLong(3, supplier.getMobile());
            ps.setString(4, supplier.getEmail());
            ps.setString(5, supplier.getCountry());
            ps.setString(6, supplier.getState());
            ps.setString(7, supplier.getCity());
            ps.setString(8, supplier.getAddress());
            ps.setString(9, supplier.getCreatedBy());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    
    @Override
    public List<Supplier> getSuppliers(String searchKey) {
        StringBuilder sql = new StringBuilder(
            "SELECT supplier_id, name, gender, mobile, email, country, state, city, address, created_at, created_by " +
            "FROM tbl_supplier WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();

        if (searchKey != null && !searchKey.trim().isEmpty()) {
            try {
                
                Long id = Long.parseLong(searchKey.trim());
//                sql.append(" AND supplier_id = ?");
//               params.add(id);
//               
               sql.append(" AND supplier_id LIKE ?");
               params.add("%" + id + "%");
               
            } catch (NumberFormatException e) {
               
                sql.append(" AND name LIKE ?");
                params.add("%" + searchKey.trim() + "%");
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new SupplierRowMapper());
    }
}
