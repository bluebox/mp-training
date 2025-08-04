//package DAO;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import domain.Book;
//import domain.checking_enum.Availability;
//import domain.checking_enum.Status;
//public class Databasemanager {
//	private final String url="jdbc:mysql://127.0.0.1:3306/library_management_system";
//	private final String username="root";
//	private final String password="9490";
//	private List<Book> list=new ArrayList<>();
//	public Connection connectionestablish() {
//		Connection c=null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			
//			e.printStackTrace();
//		}
//		try{
//			 c = DriverManager.getConnection(url, username, password);
//			System.out.println("connection established with db");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return c;
//		
//
//	}
//	public int addBooks(Book book ) throws SQLIntegrityConstraintViolationException {
//		String query="insert into library_management_system.books(title,author,category,status,availability) values (?,?,?,?,?)";
//		Connection c=connectionestablish();
//		try {
//				
//				c.setAutoCommit(false);
//				PreparedStatement preparedstatement=c.prepareStatement(query);
//				preparedstatement.setString(1,book.getTitle());
//				preparedstatement.setString(2,book.getAuthor());
//				preparedstatement.setString(3,book.getCategory());
//				preparedstatement.setString(4,book.getStatus().getType());
//				preparedstatement.setString(5,book.getAvailability().getType());
//				int b=preparedstatement.executeUpdate();
//				c.commit();
//				return b;
//			
//		}
//		catch(SQLIntegrityConstraintViolationException e1) {
//			throw new SQLIntegrityConstraintViolationException();
//		}
//		catch(SQLException e) {
//			try {
//				c.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//			
//		}
//		return 0;
//		
//		
//	}
//	public boolean updateBookDetails(int id,Book book) {
//		Connection c=connectionestablish();
//		System.out.println("Id"+id+"book"+book);
//		Book book1=null;
//		String query="update library_management_system.books set category=?,status=? where bookid=?";
//		String querylog="insert into library_management_system.books_log(title,author,category,status,availability) values (?,?,?,?,?)";
//		try {
//			c.setAutoCommit(false);
//			PreparedStatement preparedstatement=c.prepareStatement(query);
//			PreparedStatement preparedstatementlog=c.prepareStatement(querylog);
//			preparedstatementlog.setString(1,book.getTitle());
//			preparedstatementlog.setString(2,book.getAuthor());
//			preparedstatementlog.setString(3,book.getCategory());
//			preparedstatementlog.setString(4,book.getStatus().getType());
//			if(book.getStatus()==Status.INACTIVE) {
//				preparedstatementlog.setString(5,null);
//				Updatebookavailability(book,null);
//				
//			}
//			else {
//			preparedstatementlog.setString(5,book.getAvailability().getType());
//			}
//			preparedstatement.setString(1, book.getCategory());
//			preparedstatement.setString(2, book.getStatus().getType());
//			preparedstatement.setInt(3, id);
//			
//			preparedstatementlog.execute();
//			//preparedstatement.setInt(5, book.getid());
//			int res=preparedstatement.executeUpdate();
//			System.out.println(res);
//			if (res>0) {
//				
//				c.commit();
//				return true;
//				
//			}
//			else {
//				c.rollback();
//			}
//			
//			
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//		
//		
//	}
//	public int getbookid(Book book,Connection c) {
//		int bookid=0;
//		String query="select bookid from library_management_system.books where title=? and author=?";
//		try {
//			PreparedStatement preparedstatement=c.prepareStatement(query);
//			preparedstatement.setString(1, book.getTitle());
//			preparedstatement.setString(2, book.getAuthor());
//			ResultSet res=preparedstatement.executeQuery();
//			res.next();
//			 bookid=res.getInt("bookid");
//			
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return bookid;
//		
//	}
//	public boolean Updatebookavailability(Book book,Availability availability) {
//		Connection c=connectionestablish();
//		try {
//			
//			c.setAutoCommit(false);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int bookid = getbookid(book,c);
//		System.out.println(bookid);
//		String query="update library_management_system.books set Availability=? where bookid=?";
//		String querylog="insert into library_management_system.books_log(title,author,category,status,availability) values (?,?,?,?,?)";
//		
//		try {
//			PreparedStatement preparedstatement=c.prepareStatement(query);
//			PreparedStatement preparedstatementlog=c.prepareStatement(querylog);
//			preparedstatementlog.setString(1,book.getTitle());
//			preparedstatementlog.setString(2,book.getAuthor());
//			preparedstatementlog.setString(3,book.getCategory());
//			preparedstatementlog.setString(4,book.getStatus().getType());
//			
//			preparedstatementlog.setString(5,book.getAvailability().getType());
//			
//			
//			preparedstatementlog.execute();
//			if(availability!=null)
//				preparedstatement.setString(1, availability.getType());
//			else
//				preparedstatement.setString(1, null);
//
//			
//			
//			preparedstatement.setInt(2,bookid);
//			int res=preparedstatement.executeUpdate();
//			if(res>0)
//			{c.commit();
//			return true;}
//			else {
//				c.rollback();	
//				}
//		}catch(SQLException e) {
//			try {
//				c.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	public List<Book> viewallbooks() {
//		Connection c=connectionestablish();
//		String query="select * from books";
//		try {
//			Statement statement=c.createStatement();
//			
//			
//			ResultSet res=statement.executeQuery(query);
//			while(res.next()) {
//				list.add(new Book(res.getInt("bookid"),res.getString("title"),res.getString("author"),res.getString("category"),Status.getstatus(res.getString("status")),Availability.getstatus(res.getString("availability"))));
//			}
//		}
//		catch(SQLException e) {
//			
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				c.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return list;
//	}
//	public boolean getBookbyId(int bookid) {
//	Connection c=connectionestablish();
//	
//	String query="select title from library_management_system.books where bookid=?";
//	try {
//		PreparedStatement preparedstatement=c.prepareStatement(query);
//		preparedstatement.setInt(1, bookid);
//		
//		ResultSet res=preparedstatement.executeQuery();
//		if(res.next()) {
//			return true;
//		}
//		
//		
//	}
//	catch(SQLException e) {
//		e.printStackTrace();
//	}
//	return false;
//	
//	}
//	
//	public Book getBookwithId(int bookid) {
//		Connection c=connectionestablish();
//		Book book=null;
//		String query="select * from library_management_system.books where bookid=?";
//		try {
//			PreparedStatement preparedstatement=c.prepareStatement(query);
//			preparedstatement.setInt(1, bookid);
//			
//			ResultSet res=preparedstatement.executeQuery();
//			if(res.next()) {
//				book=new Book(res.getInt("bookid"),res.getString("title"),res.getString("author"),res.getString("category"),Status.getstatus(res.getString("status")),Availability.getstatus(res.getString("availability")));
//			}
//			
//			
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return book;
//		
//		}
//	
//	
//	
//	
//	
//}
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Book;
import domain.checking_enum.Availability;
import domain.checking_enum.Status;
public class Databasemanager implements bookInterface {
	private final String url="jdbc:mysql://127.0.0.1:3306/library_management_system";
	private final String username="devuser";
	private final String password="Bobby@514";
	private List<Book> list=new ArrayList<>();
	public Connection connectionestablish() {
		Connection c=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try{
			 c = DriverManager.getConnection(url, username, password);
			System.out.println("connection established with db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
		

	}
	public int addBooks(Book book ) throws SQLIntegrityConstraintViolationException {
		String query="insert into library_management_system.books(title,author,category,status,availability) values (?,?,?,?,?)";
		Connection c=connectionestablish();
		try {
				
				c.setAutoCommit(false);
				PreparedStatement preparedstatement=c.prepareStatement(query);
				preparedstatement.setString(1,book.getTitle());
				preparedstatement.setString(2,book.getAuthor());
				preparedstatement.setString(3,book.getCategory());
				preparedstatement.setString(4,book.getStatus().getType());
				preparedstatement.setString(5,book.getAvailability().getType());
				int b=preparedstatement.executeUpdate();
				c.commit();
				return b;
			
		}
		catch(SQLIntegrityConstraintViolationException e1) {
			throw new SQLIntegrityConstraintViolationException();
		}
		catch(SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		}
		return 0;
		
		
	}
public boolean updateBookDetails(int id,Book book) {
		Connection c=connectionestablish();
		System.out.println("Id"+id+"book"+book);
		Book book1=getBookwithId(id);
		if(book1==null) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		String query="update books set category=?,status=? where bookid=?";
		String querylog="insert into library_management_system.books_log(bookid,title,author,category,status,availability) values (?,?,?,?,?,?)";
		try {
			c.setAutoCommit(false);
			PreparedStatement preparedstatement=c.prepareStatement(query);
			PreparedStatement preparedstatementlog=c.prepareStatement(querylog);
			preparedstatementlog.setInt(1,book1.getBookid());
			preparedstatementlog.setString(2,book1.getTitle());
			preparedstatementlog.setString(3,book1.getAuthor());
			preparedstatementlog.setString(4,book1.getCategory());
			preparedstatementlog.setString(5,book1.getStatus().getType());
			
			preparedstatementlog.setString(6,book1.getAvailability().getType());
			
			preparedstatement.setString(1, book.getCategory());
			preparedstatement.setString(2, book.getStatus().getType());
			preparedstatement.setInt(3, id);
			
			preparedstatementlog.execute();
			//preparedstatement.setInt(5, book.getid());
			int res=preparedstatement.executeUpdate();
			System.out.println(res);
			if (res>0) {
				
				c.commit();
				return true;
				
			}
			
			
			
		}
		catch(SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
		
		
	}
	public int getbookid(Book book,Connection c) {
		int bookid=-1;
		String query="select bookid from books where title=? and author=?";
		try {
			PreparedStatement preparedstatement=c.prepareStatement(query);
			preparedstatement.setString(1, book.getTitle());
			preparedstatement.setString(2, book.getAuthor());
			ResultSet res=preparedstatement.executeQuery();
			if(res.next())
			 bookid=res.getInt("bookid");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return bookid;
		
	}
	public boolean Updatebookavailability(Book book,Availability availability) {
		Connection c=connectionestablish();
		
		int bookid = getbookid(book,c);
		System.out.println(bookid);
		String query="update books set Availability=? where bookid=?";
		String querylog="insert into books_log(title,author,category,status,availability) values (?,?,?,?,?)";
		
		try {
			c.setAutoCommit(false);
			PreparedStatement preparedstatement=c.prepareStatement(query);
			PreparedStatement preparedstatementlog=c.prepareStatement(querylog);
			preparedstatementlog.setString(1,book.getTitle());
			preparedstatementlog.setString(2,book.getAuthor());
			preparedstatementlog.setString(3,book.getCategory());
			preparedstatementlog.setString(4,book.getStatus().getType());
			
			preparedstatementlog.setString(5,book.getAvailability().getType());
			
			
			preparedstatementlog.execute();
			if(availability!=null)
				preparedstatement.setString(1, availability.getType());
			else
				preparedstatement.setString(1, null);

			
			
			preparedstatement.setInt(2,bookid);
			int res=preparedstatement.executeUpdate();
			if(res>0)
			{c.commit();
			return true;}
			
		}catch(SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<Book> viewallbooks() {
		Connection c=connectionestablish();
		String query="select bookid,title,author,category,status,availability from books";
		try {
			Statement statement=c.createStatement();
			
			
			ResultSet res=statement.executeQuery(query);
			while(res.next()) {
				list.add(new Book(res.getInt("bookid"),res.getString("title"),res.getString("author"),res.getString("category"),Status.getstatus(res.getString("status")),Availability.getstatus(res.getString("availability"))));
			}
		}
		catch(SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public boolean getBookbyId(int bookid) {
	Connection c=connectionestablish();
	
	String query="select title from library_management_system.books where bookid=?";
	try {
		PreparedStatement preparedstatement=c.prepareStatement(query);
		preparedstatement.setInt(1, bookid);
		
		ResultSet res=preparedstatement.executeQuery();
		if(res.next()) {
			return true;
		}
		
		
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	finally {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return false;
	
	}
	
	public Book getBookwithId(int bookid) {
		Connection c=connectionestablish();
		Book book=null;
		String query="select bookid,title,author,category,status,availability from books where bookid=?";
		try {
			PreparedStatement preparedstatement=c.prepareStatement(query);
			preparedstatement.setInt(1, bookid);
			
			ResultSet res=preparedstatement.executeQuery();
			if(res.next()) {
				book=new Book(res.getInt("bookid"),res.getString("title"),res.getString("author"),res.getString("category"),Status.getstatus(res.getString("status")),Availability.getstatus(res.getString("availability")));
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return book;
		
		}
	
	
	
	
	
}
