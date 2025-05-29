package ExploringNestedClassesWithNGOandDonor;

public class Main {
	public static void main(String [] args)
	{
		NGO newNGO = new NGO("Suvidha Foundation");
		
		// static inner class used (NOTE: Should not be accessed via NGO class object newNGO (will give compilation error). So directly access it using enclosing class (NGO) name
		NGO.NGOGoal.displayNGOgoal();
		
		newNGO.insertNewDonor("Raghav Mukherjee", "mukherjeeraghav12@gmail.com");
		newNGO.insertNewDonor("Narsimha Reddy", "reddynarsimha32@gmail.com");
		newNGO.insertNewDonor("Bhavesh Joshi", "bhavesh1203@gmail.com");
		
		// calling and using inner (non-static) class methods from NGO class object
		System.out.println();
		newNGO.getDonors().get(0).insertNewDonation("Class VI Books", 9);
		newNGO.getDonors().get(0).notifyDonor();
		
		System.out.println();
		newNGO.getDonors().get(1).insertNewDonation("Clothes", 24);
		newNGO.getDonors().get(1).notifyDonor();
		
		System.out.println();
		newNGO.getDonors().get(2).insertNewDonation("Electric Coolers", 8);
		newNGO.getDonors().get(2).notifyDonor();
		
		System.out.println();
		newNGO.displayAllDonors();
	}
}
