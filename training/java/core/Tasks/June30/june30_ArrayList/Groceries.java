package corejava.june30_ArrayList;

import java.util.ArrayList;
import java.util.List;

public class Groceries {
	private List<String> groceries;

	public Groceries() {
		this.groceries=new ArrayList<>();
	}

	public List<String> getGroceries() {
		return groceries;
	}

	public void setGroceries(ArrayList<String> groceries) {
		ArrayList<String> list= groceries;
		for(String listItem:list) {
			if(!this.groceries.contains(listItem)) {
				this.groceries.add(listItem);
			}
		}	
	}
	
	public void removeGroceries(ArrayList<String> groceries) {
		ArrayList<String> list= groceries;
		for(String listItem:list) {
			if(this.groceries.contains(listItem)) {
				this.groceries.remove(listItem);
			}
		}	
	}
	
	public void displayList() {
		for(String list:groceries) {
			System.out.println(list);
		}
	}
	
}
