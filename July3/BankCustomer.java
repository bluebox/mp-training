package July3;

import java.util.List;
import java.util.ArrayList;

public class BankCustomer {
	
	private String name;
	private int id;
	List<BankAccount> accounts = new ArrayList<>();
	
	public BankCustomer(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
}
