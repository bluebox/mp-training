package Service;

import java.util.List;
import java.util.Map;
import domain.Book;
import domain.Member;

public interface ReportsInterface {
    Map<String, Long> count_of_books_percategory();
    List<Member> members_with_statusissue();
    List<Book> overduebooks();
}
