package WallMain;

public class Wall {
 private double height;
 private double width;
 public Wall()
 {
	 
 }
 public Wall(double height,double width)
 {
	 if(height<0)
	 {
		 this.height=0;
	 }
	 if(height<0)
	 {
		 height=0;
	 }
	 this.height=height;
	 this.width=width;
	 
	
 }
 public double getwidth()
 {
	 return this.width;
 }
 public double getheight()
 {
	 return this.height;
 }
 public void setwidth(double width)
 {
	 if(width<0)
	 {
		 this.width=0;
	 }
	 this.width=width;
 }
 public void setheight(double height)
 {
	 if(height<0)
	 {
		 this.height=0;
	 }
	 this.height=height;
 }
 public double getarea()
 {
	 return this.width*this.height;
 }
}
