package com.medplus.marketing.util;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnData<T> {
	
	private int columnIndex;
    private ColumnValidator<T> validator;
    private Set<T> data;
    
    public ColumnData(int columnIndex, ColumnValidator<T> validator) {
        this.columnIndex = columnIndex;
        this.validator = validator;
        this.data = new HashSet<>(); 
    }
    
    @SuppressWarnings("unchecked")
	public void addRecord(Object cellData) {
        if (data == null) {
            data = new HashSet<>();
        }
        data.add((T)cellData);
    }
}
