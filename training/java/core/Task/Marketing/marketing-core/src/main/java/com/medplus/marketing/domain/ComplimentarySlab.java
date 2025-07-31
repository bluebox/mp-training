package com.medplus.marketing.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComplimentarySlab implements Serializable {
	private static final long serialVersionUID = -9039056013790440488L;
	
	private Long complimentarySlabId;
	private Double invoiceAmount;
	private String name;
}
