package com.library.dto;

public class CategoryCount {
    private final String category;
    private final long count;

    public CategoryCount(String category, long count) {
        this.category = category;
        this.count = count;
    }

    public String getCategory() { return category; }
    public long getCount() { return count; }
}
