
public class Wall {
	private double width;
	private double height;
	public Wall()
	{
		System.out.println("No argument Constructor");
	}
	public Wall(double width,double height)
	{
		this.width  = (width  < 0) ? 0 : width;
	    this.height= (height < 0) ? 0 :height;
		
	}
	public double getWidth()
	{
		return width;
	}
	public double getHeight()
	{
		return height;
	}
	public void setWidth(double width)
	{
		this.width  = (width  < 0) ? 0 : width;
	}
	public void setHeight(double height)
	{
		this.height = (height < 0) ? 0 : height;
	}
	public double getArea()
	{
		return width*height;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Wall w1=new Wall(20.00,20.00);
		System.out.println(w1.getArea());
		Wall w2=new Wall();
		w1.setHeight(10.00);
		w1.setWidth(15.00);
		System.out.println(w1.getArea());
		Wall w3=new Wall(-10,20);
		System.out.println(w3.getArea());
	}

}
