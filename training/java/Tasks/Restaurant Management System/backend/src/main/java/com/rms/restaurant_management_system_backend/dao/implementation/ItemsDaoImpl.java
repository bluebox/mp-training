package com.rms.restaurant_management_system_backend.dao.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.constant.ItemStatus;
import com.rms.restaurant_management_system_backend.dao.ItemsDao;
import com.rms.restaurant_management_system_backend.domain.Items;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;
import com.rms.restaurant_management_system_backend.rowmappers.ItemRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class ItemsDaoImpl implements ItemsDao {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private ItemRowMapper itemRowMapper;

	public ItemsDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int addItem(Items item) {

		return jdbcTemplate.update(SqlQueries.ITEM_INSERT, item.getName(), item.getImageUrl(), item.getDescription(),
				item.getPrice(), item.getCategory().getName(), ItemAvailability.AVAILABLE.getName(),
				ItemStatus.ACTIVE.getName());

	}

	@Override
	public boolean existsByName(String name) {

		Integer count = jdbcTemplate.queryForObject(SqlQueries.ITEM_COUNT_SELECT_BY_NAME, Integer.class, name);
		return count != null && count > 0;
	}

	@Override
	public Items getItemById(int id) {
		List<Items> items = jdbcTemplate.query(SqlQueries.ITEM_SELECT_BY_ID, itemRowMapper, id);
		if (items.isEmpty()) {
			throw new RestaurantOperationException("Item with id " + id + " not found");
		}
		return items.get(0);
	}

	@Override
	public Items getItemByName(String name) {
		List<Items> items = jdbcTemplate.query(SqlQueries.ITEM_SELECT_BY_NAME, itemRowMapper, name);
		if (items.isEmpty()) {
			throw new RestaurantOperationException("Item with name " + name + " not found");
		}
		return items.get(0);
	}

	@Override
	public int updateItem(Items item) {
		return jdbcTemplate.update(SqlQueries.ITEM_UPDATE, item.getName(), item.getImageUrl(), item.getDescription(),
				item.getCategory().getName(), item.getPrice(), item.getId());
	}

	@Override
	public int changeAvailability(int id, ItemAvailability availability) {

		return jdbcTemplate.update(SqlQueries.UPDATE_AVAILABILITY, availability.getName(), id);

	}

	@Override
	public List<Items> getAllItems() {
		return jdbcTemplate.query(SqlQueries.ITEM_SELECT_ALL, itemRowMapper);
	}

	@Override
	public int deleteItem(int id) {
		return jdbcTemplate.update(SqlQueries.ITEM_DELETE, id);
	}

	@Override
	public List<Items> getSearchItems(String search, String category) {
		Map<String, Object> params = new HashMap<>();
		params.put("search", search);
		params.put("category", category);
		return namedParameterJdbcTemplate.query(SqlQueries.SEARCH_ITEMS, params, itemRowMapper);

	}

	@Override
	public void inserLog(Items item) {

		jdbcTemplate.update(SqlQueries.ITEM_INSERT_LOG, item.getId(), item.getName(), item.getImageUrl(),
				item.getDescription(), item.getPrice(), item.getCategory().getName(),
				ItemAvailability.AVAILABLE.getName(), ItemStatus.ACTIVE.getName());
	}

}
