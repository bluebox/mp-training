package updatedbookselling.bookcatalog.domain;

public class BookSalesDTO {
    private Integer bookId;
    private Integer totalSold; // Using Long for SUM(quantity) as it can be large

    // Constructors
    public BookSalesDTO() {
    }

    public BookSalesDTO(Integer bookId, Integer totalSold) {
        this.bookId = bookId;
        this.totalSold = totalSold;
    }

    // Getters and Setters
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(Integer totalSold) {
        this.totalSold = totalSold;
    }

    @Override
    public String toString() {
        return "BookSalesDTO{" +
               "bookId=" + bookId +
               ", totalSold=" + totalSold +
               '}';
    }
}