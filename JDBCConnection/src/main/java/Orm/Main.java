package Orm;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Scanner sc = new Scanner(System.in);
        System.out.println("Following are the available products:");
        System.out.println("dolo -> 200");
        System.out.println("para -> 100");
        System.out.println("moov -> 50");
        System.out.println("ors -> 30");

        System.out.println("Enter Customer name:");
        String name = sc.nextLine();
        Order order = new Order(name);

        System.out.println("Enter total number of items to buy:");
        int n = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < n; i++) {
            System.out.println("Enter product name:");
            String product = sc.nextLine();
            
            System.out.println("Enter quantity:");
            int qt = sc.nextInt();
            sc.nextLine(); 

            OrderItem item = new OrderItem(product, qt);
            item.setOrder(order); 
            order.getItems().add(item); 
        }
        Order or=session.get(Order.class,15);
        OrderItem ot=new OrderItem("fsdlj",3);
        
        session.persist(order); 
        tx.commit();
        session.close();
        sessionFactory.close();
    }
}
