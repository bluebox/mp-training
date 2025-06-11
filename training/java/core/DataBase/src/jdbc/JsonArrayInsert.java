package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class JsonArrayInsert {
	
	
	class User{
		private int userId;
		private String name;
		private String address;
		private String contact;
		
		public User(int userId, String name, String address, String contact) {
			super();
			this.userId = userId;
			this.name = name;
			this.address = address;
			this.contact = contact;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		@Override
		public String toString() {
			return "{\"user_id\":" + userId + ",\"name\":\"" + name + "\",\"address\":\"" + address + "\",\"contact\":\"" + contact + "\"}";
		}
		
		
	}
	
	
	public String toJson(List<User>users)
	{
		String json_list = "[";
		int i = 0;
		for(var user:users)
		{
			if(i != users.size()-1)
				json_list=json_list+user.toString()+",";
			else
				json_list+=user.toString();
			i+=1;
		}
		json_list+=']';
		System.out.println(json_list);
		return json_list;
	}
	
	public List<User> createUsers(int n)
	{
		List<User> users = new ArrayList();
		int uid = -1;
		for(int i = 0;i<n;i++)
		{
			User user;
			Scanner sc = new Scanner(System.in);
			System.out.println("Name :: ");
			String name = sc.nextLine();
			System.out.println("Address :: ");
			String address = sc.nextLine();
			System.out.println("Contact :: ");
			String contact = sc.nextLine();
			Random rand = new Random();
			if(uid<0)
			{
				uid = rand.nextInt(100)+1;
			}else {
				uid +=1;
			}
			users.add(new User(uid,name,address,contact));
			
		}
		return users;
	}
	
	public static void main(String[] args) {
//		int n = new Scanner(System.in).nextInt();
//		new Scanner(System.in).nextLine();
//		JsonArrayInsert jsa = new JsonArrayInsert();
//		List<User> users = jsa.createUsers(n);
//		String json = jsa.toJson(users);
		
		
		String url = "jdbc:mysql://localhost:3306/shopping";
        String user = "training";
        String password = "medplus";
        
        try {
			Connection conn = DriverManager.getConnection(url,user,password);
//			CallableStatement stmt = conn.prepareCall("{Call insert_user(?)}");
//			stmt.setString(1,json);
//			stmt.execute();
			PreparedStatement stmt = conn.prepareStatement("select * from users");
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				System.out.println("user id :: "+rs.getInt("user_id")+"\nname :: "+rs.getString("name")+"\naddress :: "+rs.getString("address")+"\ncontact :: "+rs.getString("contact"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
