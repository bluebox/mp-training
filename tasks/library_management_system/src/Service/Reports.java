package Service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import DAO.Databasemanager;
import domain.Book;
import domain.Issue_records;
import domain.Member;
import domain.checking_enum.Status_issue;

public class Reports implements ReportsInterface {
    
    Databasemanager dbmanager = new Databasemanager();

    @Override
    public Map<String, Long> count_of_books_percategory() {
        List<Book> list = dbmanager.viewallbooks();
        Map<String, Long> map = list.stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
        map.forEach((x, y) -> System.out.println(x + " " + y));
        return map;
    }

    @Override
    public List<Member> members_with_statusissue() {
        IssueRecordService dbmanager = new IssueRecordService();
        MemberService membermanager = new MemberService();

        List<Issue_records> list = new ArrayList<>();
        try {
            list = dbmanager.getAllIssuedRecords();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Integer> listmemberid = list.stream()
                .filter(x -> x.getStatus_issue().equals(Status_issue.ISSUED))
                .map(Issue_records::getMemberid)
                .collect(Collectors.toList());

        List<Member> listMember = new ArrayList<>();
        for (Integer memberid : listmemberid)
            listMember.add(membermanager.getByid(memberid));
        return listMember;
    }

    @Override
    public List<Book> overduebooks() {
        IssueRecordService dbmanager = new IssueRecordService();
        BookService bookmanager = new BookService();

        List<Issue_records> overduerecords = new ArrayList<>();
        try {
            overduerecords = dbmanager.getAllIssuedRecords();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Issue_records> records = overduerecords.stream()
                .filter(o -> {
                    LocalDate issueDate = ((Date) o.getIssuedate()).toLocalDate();
                    if (o.getReturndate() == null) {
                        Period period = Period.between(issueDate, LocalDate.now());
                        return period.getDays() > 10 || period.getMonths() > 1 || period.getYears() > 1;
                    } else {
                        LocalDate returnDate = ((Date) o.getReturndate()).toLocalDate();
                        Period period = Period.between(issueDate, returnDate);
                        return period.getDays() > 10 || period.getMonths() > 1 || period.getYears() > 1;
                    }
                })
                .toList();

        List<Book> books = new ArrayList<>();
        for (Issue_records i : records) {
            books.add(bookmanager.getbookwithid(i.getBookid()));
        }
        return books;
    }
}
