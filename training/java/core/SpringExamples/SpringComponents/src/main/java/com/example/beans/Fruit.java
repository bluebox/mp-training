package com.example.beans;

import org.springframework.stereotype.Component;

@Component
public class Fruit {
private String name;
private int price;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public Fruit() {
	super();
	// TODO Auto-generated constructor stub
}
public Fruit(String name, int price) {
	super();
	this.name = name;
	this.price = price;
}
@Override
public String toString() {
	return "Fruit [name=" + name + ", price=" + price + "]";
}

}
