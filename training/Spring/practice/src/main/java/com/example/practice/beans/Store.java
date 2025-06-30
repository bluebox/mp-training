package com.example.practice.beans;

import java.util.ArrayList;
import java.util.List;

public class Store {
	
	static List<Contact> listContact = new ArrayList<>();
	
	public static void add(Contact contact) {
		listContact.add(contact);
	}

}
