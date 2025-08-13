package com.example.SpringLomBokExample;

public class Example1 {
	public static void main(String args[]) {

		Example example = new Example("tarun", "SoftwareDeveloper");
		System.out.println(example);
		example.setName("ramana");
		System.out.println(example.getName());
		example.setOccupation("Software Engineer");
		System.out.println(example.getOccupation());

	}

}
