package com.libraryManagementSystem.utilities;

public class SQLQueries {

	private SQLQueries() {

	}

	public static final String MEMBER_INSERT = "INSERT INTO members (name, email, mobile, gender, address, status) VALUES (?, ?, ?,?,?,'A')";

	public static final String MEMBER_UPDATE = "UPDATE members SET name = ? ,email = ?, mobile = ?, gender = ?, address =? WHERE member_id=?";

	public static final String SELECT_ALL_MEMBERS = "SELECT * FROM members where status='A'";

	public static final String MEMBER_DELETE = "UPDATE members SET status = 'I' WHERE member_id = ? AND status = 'A'";

	public static final String MEMBER_SELECT_BY_MOBILE = "SELECT * FROM members WHERE mobile = ?";

	public static final String MEMBER_SELECT_BY_EMAIL = "SELECT * FROM members WHERE email = ?";

	// book

	public static final String BOOK_INSERT = "INSERT INTO books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";

	public static final String BOOK_UPDATE = "UPDATE books SET  title = ?, author = ?, category = ?, status = ?, availability = ? WHERE book_id = ? AND status = 'A'";

	public static final String BOOK_UPDATE_AVAILABILITY = "UPDATE books SET availability = ? WHERE book_id = ? AND status = 'A'";

	public static final String BOOK_SELECT_BY_TITLE_AUTHOR = "SELECT * FROM books WHERE LOWER(title) = LOWER(?) AND LOWER(author) = LOWER(?) AND status = 'A' ";

	public static final String BOOK_SELECT_ALL = "SELECT * FROM books WHERE status = 'A'";

	public static final String BOOK_SELECT_BY_ID = "SELECT * FROM books WHERE book_id = ? AND status = 'A'";

	public static final String BOOK_DELETE = "UPDATE books SET status = 'I' WHERE book_id = ? AND status = 'A'";

	public static final String BOOK_EXISTS_BY_TITLE_AUTHOR_EXCEPT_ID = "SELECT COUNT(*) FROM books WHERE title = ? AND author = ? AND book_id <> ? AND status = 'A'";

	// issue

	public static final String ISSUE_INSERT = "INSERT INTO issue_records (book_id, member_id, status, issue_date) VALUES (?, ?, ?, ?)";

	public static final String ISSUE_UPDATE_RETURN_DATE = "UPDATE issue_records  SET return_date = ? , status = 'R'  where issue_id = ?";

	public static final String ISSUE_SELECT_RETURN_DATE = "SELECT * FROM issue_records WHERE book_id = ? AND member_id = ? AND status = 'I' ";

	public static final String GET_BOOK_BY_CATEGORY = "SELECT category, COUNT(*) AS book_count FROM books WHERE status = 'A'   GROUP BY category";

	public static final String GET_ACTIVE_ISSUED_BOOKS = "SELECT m.member_id , m.name AS member_name , b.book_id ,b.title AS book_title , ir.issue_date FROM members m JOIN issue_records ir ON m.member_id = ir.member_id JOIN books b ON ir.book_id = b.book_id WHERE ir.return_date IS NULL ORDER BY m.member_id AND  b.status = 'A' AND b.availability= 'A'";

	public static final String GET_OVER_DUE_BOOKS = "SELECT b.book_id , b.title AS book_name , m.member_id , m.name AS member_name , ir.issue_date FROM issue_records ir JOIN members m ON ir.member_id = m.member_id JOIN books b ON ir.book_id = b.book_id WHERE ir.return_date IS NULL AND DATEDIFF(CURDATE(), ir.issue_date) > 14  AND b.status = 'A' ORDER BY ir.issue_date";
	// Log Tables SQLQueries
	public static final String BOOKS_LOG_INSERT = "INSERT INTO books_log (book_id,title, author, category, status, availability) VALUES (?,?, ?, ?, ?, ?)";

	public static final String MEMBERS_LOG_INSERT = "INSERT INTO members_log (member_id,name,email,mobile,gender,address) VALUES(?,?,?,?,?,?)";

	public static final String ISSUE_LOG_INSERT = "INSERT INTO issue_records_log (issue_id,book_id, member_id, status, issue_date,return_date) VALUES (?, ?, ?, ?,?,?)";

}
