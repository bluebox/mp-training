package com.dynamic_library_management.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import com.dynamic_library_management.dao.ReportsDaoInterface;
import com.dynamic_library_management.exceptions.DatabaseException;
import com.dynamic_library_management.utilities.ConnectionPoolingServlet;

public class ReportsDaoImplementation implements ReportsDaoInterface {
	
	private final DataSource dataSource = ConnectionPoolingServlet.getDataSource();

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public Map<Object, Long> countOfBooksPerCategory() {
		Map<Object, Long> countMap=null;
		List<String> booksList=new ArrayList<>();
		String selectQuery="select category from lms.books";
		ResultSet resultSet;
		try (Connection conn = getDataSource().getConnection();
				Statement statement = conn.createStatement()){
			resultSet = statement.executeQuery(selectQuery);
			while(resultSet.next()) 
			{
				String category=resultSet.getString(1);
				booksList.add(category);
			}
			countMap=booksList.stream()
				.collect(Collectors.groupingBy(c -> c,Collectors.counting()));
			
		} catch (SQLException e) {
			new DatabaseException("Error Getting Book Details...");
		}
		return countMap;
	}
	

	public List<List<String>> getOverdueBooks() {
		//return dao.getOverdueBooks();
		List<List<String>> temp = new ArrayList<>();

	    String sql = "SELECT m.member_id, m.name, b.book_id, b.title, ir.issue_date, ir.return_date FROM members m JOIN issue_records ir ON m.member_id = ir.member_id JOIN books b ON ir.book_id = b.book_id";

	    try (Connection conn = getDataSource().getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            List<String> issue = new ArrayList<>();
	            issue.add(String.valueOf(rs.getInt("member_id")));   
	            issue.add(rs.getString("name"));                     
	            issue.add(rs.getString("book_id"));                  
	            issue.add(rs.getString("title"));                    
	            issue.add(rs.getString("issue_date"));               
	            issue.add(rs.getString("return_date"));              
	            temp.add(issue);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    List<List<String>> overdue = temp.stream()
	        .filter(b -> b.get(5) == null || b.get(5).trim().isEmpty())
	        .filter(b -> {
	            try {
	                LocalDate issueDate = LocalDate.parse(b.get(4));
	                return issueDate.isBefore(LocalDate.now().minusDays(17));
	            } catch (Exception e) {
	                return false; 
	            }
	        })
	        .collect(Collectors.toList());

	    System.out.println(overdue);
	    return overdue;
	}

	@Override
	public List<List<String>> getActiveIssuedBooks() {
		List<List<String>> data =  new IssueRecordDaoImplementation().getStatusTable().stream()
				.filter(row -> row.get(4).equals("A") && row.get(3).equals("I"))
				.collect(Collectors.toList());
		System.out.println(data);
		return data;
	}
}
