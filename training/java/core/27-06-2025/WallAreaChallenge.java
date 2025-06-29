
public class WallAreaChallenge {

	double width;
	double height;
	WallAreaChallenge()
	{
		this.width=0;
		this.height=0;
	}
	WallAreaChallenge(double width,double height)
	{
		if(width<0)
			this.width=0;
		else
			this.width=width;
		
		if(height<0)
			this.height=0;
		else
			this.height=height;
	}
	double getWidth()
	{
		return width;
	}
	
	double getHeight()
	{
		return height;
	}
	public void setWidth(double width)
	{
		if(width<0)
			this.width=0;
		else
			this.width=width;
	}
	public void setHeight(double height)
	{
		if(height<0)
			this.height=0;
		else
			this.height=height;
			
	}
	
	public double getArea()
	{
		return height*width;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WallAreaChallenge wall =new WallAreaChallenge();
		wall.setHeight(5.0);
		wall.setWidth(3);
		System.out.println(wall.getArea());
		

	}

}
