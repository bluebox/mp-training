package model;

public class BookCategoryCount {

	private final String category;
    private final Integer count;

    public BookCategoryCount(String category, Integer count) {
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public Integer getCount() {
        return count;
    }
    
    @Override
    public String toString() {
    	return "BookCategoryCount [category=" + category + ", count=" + count + "]";
    }
}
