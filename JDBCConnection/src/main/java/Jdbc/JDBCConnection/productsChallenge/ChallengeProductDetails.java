package Jdbc.JDBCConnection.productsChallenge;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;
class Pair{
	public String product_name;
	public int cost;
	public Pair(String a,int b) {
		this.product_name=a;
		this.cost=b;
	}
}
public class ChallengeProductDetails {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/test";
		String user="Anand";
		String password="1925112816@Aa";
		MysqlDataSource md=new MysqlDataSource();
		md.setUrl(url);
		md.setUser(user);
		md.setPassword(password);
		try(Connection conn=md.getConnection()){
		    String q="""
		    		create table orderDetails
		    		(
		    		 id int primary key not null auto_increment,
		    		 product_id int,
		    		 product_name varchar(2000),
		    		 cost int,
		    		 foreign key (product_id) references products(product_id) 
		    		 );
		    		""";
		    ChallengeClassToCreateTable.createTable(conn, q);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
        Scanner sc=new Scanner(System.in);
        Map<Integer,Pair>mp=new HashMap<>();
        mp.put(1,new Pair("dolo",100));
        mp.put(2,new Pair("citris",200));
        mp.put(3,new Pair("para",300));
        mp.put(4,new Pair("move",100));
        mp.put(5,new Pair("jandu",100));
        mp.put(6,new Pair("vicks",20));
        mp.put(7,new Pair("saradon",50));
        mp.put(8,new Pair("mask",20));
        mp.put(9,new Pair("inhaler",20));
        mp.put(10,new Pair("ors",20));
        while(true) {
        	System.out.println("enter 1->dolo & quantity");
        	System.out.println("enter 2->citris & quantity");
        	System.out.println("enter 3->para & quantity");
        	System.out.println("enter 4->move & quantity");
        	System.out.println("enter 5->jandu & quantity");
        	System.out.println("enter 6->vicks & quantity");
        	System.out.println("enter 7->saradon & quantity");
        	System.out.println("enter 8->mask & quantity");
        	System.out.println("enter 9->inhaler & quantity");
        	System.out.println("enter 10->ors & quantity");
        	System.out.println("0 to exit");
        	int a=sc.nextInt();
        	sc.nextLine();
        	if(a==0)break;
        	System.out.println("enter qt");
        	int qt=sc.nextInt();
        	sc.nextLine();
        	InsertIntoProductDetailsTable.addOrderDetails(a,mp.get(a).product_name,mp.get(a).cost*qt);
        }
	}

}
