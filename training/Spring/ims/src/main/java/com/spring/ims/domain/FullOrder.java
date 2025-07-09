package com.spring.ims.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullOrder {
	private Orders orders;
	private List<OrderProductDetails> orderProductDetails;

}
