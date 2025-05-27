package MethodOverloading;

import java.util.*;

public class SearchProduct {
	
	//search by product name
	public static List<String> searchProducts(List<String> inventory,String productName){
		List<String> results=new ArrayList<>();
		for(String item:inventory) {
			if(item.equalsIgnoreCase(productName)) {
				results.add(item);
			}
		}
		return results;
	}
	//search by item id
	public static List<String> searchProducts(List<String> inventory,String itemId,boolean isFound){
		List<String> results=new ArrayList<>();
		for(String item:inventory) {
			if(item.contains("itemId:" +itemId+",")) {
				results.add(item);
			}
		}
		return results;
	}
	
	
	public static void main(String[] args) {
		List<String> inventory=List.of(
				"itemId:MEDPLUS101,name:para,Category:emergency",
				"itemId:MEDPLUS102,name:euro,Category:vitamin");
		System.out.println(searchProducts(inventory,"MEDPLUS101",true));
	}
}

