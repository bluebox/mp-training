package Day_1_7_25;

public class MAP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mappable tower = new Building("gjhgjh", Usagetype.ENTERTAINMENT);
        Mappable house = new Building("house", Usagetype.RESIDENTIAL);
        Mappable powerLine = new UtilityLine("mainroad", Utilitytype.Electrical);
        Mappable dataLine = new UtilityLine("internetlink", Utilitytype.Fiberoptic);

        Mappable.mapIt(tower);
        Mappable.mapIt(house);
        Mappable.mapIt(powerLine);
        Mappable.mapIt(dataLine);
	}

}
