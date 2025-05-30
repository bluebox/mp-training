package inheritence;

public class Cubiod extends Rectangle{
	public double height= 2.5d;
	public Cubiod(double length, double width, double height) {
		length=super.length;
		width=super.width;
		if(height < 0) {
			this.height = 0.0d;
		}
		else {
		this.height = height;
	}
	}
	public double getHeightWithoutAnyParameters(){
		return height;
	}
	public double getVolumeWithoutAnyParameters(){
		return height*super.getAreaWithoutAnyParameters();
}
	public static void main(String [] args) {
	Rectangle rectangle= new Rectangle();
	System.out.println("Area of rectangle with length "+ rectangle.getLengthWithoutAnyParameters()+ " and width "+ rectangle.getWidthWithoutAnyParameters()+ " is " + rectangle.getAreaWithoutAnyParameters());
	Cubiod cubiod = new Cubiod(rectangle.getLengthWithoutAnyParameters(),rectangle.getWidthWithoutAnyParameters(), 10);
	double vol = cubiod.getVolumeWithoutAnyParameters();
	System.out.println("Volume of Cylinder with height "+ cubiod.getHeightWithoutAnyParameters() + "is " + vol);
}
}
