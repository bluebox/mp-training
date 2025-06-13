package com.storefront.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.storefront.model.*;

public class csvFileReaderUtility {
	public static Map<LocalDateTime, List<OrderItem>> readAllOrders(String pathName) {
		Map<LocalDateTime, List<OrderItem>> ordersMap = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try (BufferedReader reader = new BufferedReader(new FileReader(pathName))) {
			reader.readLine();

			String record;

//			order_details table (.csv format)
//			attributes[0] -> order_date
//			attributes[1] -> product_name
//			attributes[2] -> quantity
//			attributes[3] -> price

			while ((record = reader.readLine()) != null) {
				String[] attributes = record.split(",");
				LocalDateTime date = LocalDateTime.parse(attributes[0], formatter);
				String productName = attributes[1];
				int quantity = Integer.parseInt(attributes[2]);
				BigDecimal price = new BigDecimal(attributes[3]);

				ordersMap.computeIfAbsent(date, k -> new ArrayList<>())
						.add(new OrderItem(productName, quantity, price));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordersMap;
	}
}
