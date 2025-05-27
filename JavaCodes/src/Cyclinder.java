
public class Cyclinder extends CircleArea {
	 public double height;
     public Cyclinder(double radius,double height) {
    	 super(radius);
    	 if(height<0) {
    		 this.height=0;
    	 }
    	 else this.height=height;
     }
     public double getHeight() {
    	 return this.height;
     }
     public double getVolume() {
    	 double area=this.getArea()*this.height;
         return area;
     }
}
