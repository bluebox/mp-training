package com.medplus.marketing.util;

import org.apache.poi.ss.usermodel.Cell;

@FunctionalInterface
public interface ColumnValidator<T> {
	T validateAndGet(Cell cell,int rowNum);
}
