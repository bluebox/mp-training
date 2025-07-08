package com.spring.ims;



import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.spring.ims.domain.FullOrder;
import com.spring.ims.domain.OrderProductDetails;
import com.spring.ims.domain.Orders;
import com.spring.ims.service.Implementation;

@SpringBootApplication
public class ImsApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ImsApplication.class, args);
		Implementation impl = context.getBean(Implementation.class);
		
		System.out.println("All Supliers : " + impl.listOfSuppliers().toString());
		
//		System.out.println("Search Product by nothing : "+impl.listOfProducts("Arabindho - 1","ab"));
//		System.out.println("Search Product by name : "+impl.listOfProducts("Arabindho - 1","para"));
//		System.out.println("Search Product by id : "+impl.listOfProducts("Arabindho - 1","1"));
//		
//		System.out.println("Cost of Product Paracetamol - 1 : "+impl.costOfProduct("Paracetamol - 1"));
		
		List<OrderProductDetails> ord = new ArrayList<>();
		OrderProductDetails o1 = new OrderProductDetails();
		OrderProductDetails o2 = new OrderProductDetails();
		OrderProductDetails o3 = new OrderProductDetails();
		o1.setSupplier("Arabindho - 1");
		o2.setSupplier("Arabindho - 1");
		o3.setSupplier("Arabindho - 1");
		o1.setProduct("Paracetamol - 1");
		o2.setProduct("Ibuprofen - 4");
		o3.setProduct("Omeprazole - 15");
//		o1.setProductCost(impl.costOfProduct("Paracetamol - 1"));
//		o2.setProductCost(impl.costOfProduct("Ibuprofen - 4"));
//		o3.setProductCost(impl.costOfProduct("Omeprazole - 15"));
		o1.setProductQuantity(5);
		o2.setProductQuantity(10);
		o3.setProductQuantity(15);
		ord.add(o3);
		ord.add(o2);
		ord.add(o1);
		Orders order = new Orders();
		order.setOrderDiscout(19);
		FullOrder fullorder = new FullOrder();
		fullorder.setOrderProductDetails(ord);
		fullorder.setOrders(order);
		System.out.println(impl.creationOfOrder(fullorder));
		Orders ord1 = new Orders();
		Orders ord2 = new Orders();
		ord1.setOrderId(1);
		ord2.setOrderId(2);
		ord1.setOrderStatus("APPROVED");
		ord2.setOrderStatus("REJECTED");
		System.out.println(impl.approval(ord1));
		System.out.println(impl.approval(ord2));
		System.out.println("Pending Orders : "+impl.pendingOrders().toString());
		System.out.println("All Orders : "+impl.allOrders().toString());
		Orders ord3 = new Orders();
		ord3.setOrderId(4);
		System.out.println(impl.productsOfOrder(ord3).toString());
		Orders ord4 = new Orders();
		ord4.setOrderId(3);
		System.out.println(impl.withDrawOfOrder(ord4));
		
//		drop database ims;
//		create database ims;
//		use ims;
//
//
//
//		create table suppliers(
//		supplierId int primary key auto_increment,
//		supplierName varchar(100),
//		supplierContact bigint,
//		supplierAddress varchar(100));
//
//		create table products(
//		productId int primary key auto_increment,
//		supplierId int not null,
//		productName varchar(100),
//		productCost float);
//
//		create table orders(
//		orderId int primary key auto_increment,
//		orderDate date,
//		orderCost float,
//		orderDiscount int,
//		orderStatus varchar(10)
//		);
//
//		create table orderProductDetails(
//		orderDetailsId int primary key auto_increment,
//		orderId int,
//		supplier varchar(100),
//		product varchar(100),
//		productQuantity int,
//		productCost float);
//
//		create table employeeProducts(
//		stockId int ,
//		procuctName varchar(100),
//		productId int,
//		supplier varchar(100),
//		quantity int,
//		minQuan int,
//		maxQuan int);
//
//		insert into suppliers(supplierName,supplierContact,supplierAddress) values ("Arabindho",1234567890,"Medchal,Hyderabad");
//		insert into suppliers(supplierName,supplierContact,supplierAddress) values ("Dr.Reddys",9876543210,"Balanagar,Hyderabad");
//		insert into suppliers(supplierName,supplierContact,supplierAddress) values ("Bhima",6543217890,"Patanchervu,Hyderabad");
//		insert into suppliers(supplierName,supplierContact,supplierAddress) values ("Medplus",1221221220,"Madhapur,Hyderabad");
//
//		select * from suppliers;
//
//		-- Product: Paracetamol
//		INSERT INTO products (supplierId, productName, productCost) VALUES
//		(1, 'Paracetamol', 98.0),
//		(2, 'Paracetamol', 100.0),
//		(3, 'Paracetamol', 101.5);
//
//		-- Product: Ibuprofen
//		INSERT INTO products (supplierId, productName, productCost) VALUES
//		(1, 'Ibuprofen', 99.0),
//		(4, 'Ibuprofen', 102.5);
//
//		-- Product: Amoxicillin
//		INSERT INTO products (supplierId, productName, productCost) VALUES
//		(2, 'Amoxicillin', 97.0),
//		(3, 'Amoxicillin', 103.0),
//		(4, 'Amoxicillin', 100.0);
//
//		-- Product: Cetirizine
//		INSERT INTO products (supplierId, productName, productCost) VALUES
//		(1, 'Cetirizine', 95.0),
//		(2, 'Cetirizine', 100.0);
//
//		-- Product: Azithromycin
//		INSERT INTO products (supplierId, productName, productCost) VALUES
//		(3, 'Azithromycin', 98.5),
//		(4, 'Azithromycin', 100.0);
//
//		-- Product: Metformin
//		INSERT INTO products (supplierId, productName, productCost) VALUES
//		(2, 'Metformin', 99.0),
//		(4, 'Metformin', 101.0);
//
//		-- Product: Omeprazole
//		INSERT INTO products (supplierId, productName, productCost) VALUES
//		(1, 'Omeprazole', 100.0),
//		(3, 'Omeprazole', 98.0);
//
//		select * from products;
//		select * from orders;
//		select * from orderProductDetails;




		
	}

}
