package com.spring.ims.domain;

import java.util.List;

import lombok.Data;

@Data
public class FullOrder {
	private Orders orders;
	private List<OrderProductDetails> orderProductDetails;

}
