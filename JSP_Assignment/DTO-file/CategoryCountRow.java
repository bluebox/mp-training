package com.library.dto;

public class CategoryCountRow {
    private String category;
    private long count;

    public CategoryCountRow(String category, long count) {
        this.category = category;
        this.count = count;
    }

    public String getCategory() { return category; }
    public long getCount() { return count; }
}