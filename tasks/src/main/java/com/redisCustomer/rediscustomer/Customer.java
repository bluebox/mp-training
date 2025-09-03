package com.redisCustomer.rediscustomer;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash("Customer")
public class Customer {
	@Id
	@Indexed
	private int id;
	private String name;
	private long phone;
	private String email;
}
