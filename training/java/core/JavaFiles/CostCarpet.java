
public class CostCarpet {

	public static void main(String[] args) {
		
		Carpet carpet=new Carpet(3.5);
		Floor floor =new Floor(2.75,4.0);
		
		CostCal costcal =new CostCal(floor,carpet);
		
		System.out.println("Total ="+costcal.getTotalCost());
	

	}

}
