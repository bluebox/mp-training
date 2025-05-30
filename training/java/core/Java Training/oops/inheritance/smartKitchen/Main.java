package oops.inheritance.smartKitchen;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		coffeMaker brewMaster=new coffeMaker();
		DishWasher dishWasher=new DishWasher();
		Refrigerater iceBox=new Refrigerater();
		SmartKichen smrt=new SmartKichen(brewMaster,dishWasher,iceBox);
		smrt.setKitchenState(true,false,true);
		smrt.doKitchenWork();
		smrt.addWater();
		smrt.pourMilk();
		smrt.loadDishMaker();
		
	}

}
