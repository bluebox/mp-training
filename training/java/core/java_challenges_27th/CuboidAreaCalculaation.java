
public class CuboidAreaCalculaation {
	static class Rectangle{
		private double width;
		private double length;
		
		Rectangle(double width, double length){
			this.width=width<0?0:width;
			this.length=length<0?0:length;
		}
		public double getWidth() {
			return width;
		}
		public void setWidth(double width) {
			this.width = width<0?0:width;
		}
		public double getLength() {
			return length<0?0:length;
		}
		public void setLength(double length) {
			this.length = length;
		}
		public double getArea() {
			return getWidth()*getLength();
		}
	}
	static class Cuboid extends Rectangle{
		private double height;
		Cuboid(double width,double length, double height){
			super(width, length);
			this.height=height<0?0:height;
			
		}
		public double getHeight() {
			return height;
		}
		public void setHeight(double height) {
			this.height = height<0?0:height;
		}
		public double getVolume() {
			return getArea()*height;
		} 
		
	}
	   public static void main(String[] args) {
		   Rectangle rectangle=new Rectangle(5,10);
		   System.out.println(rectangle.getArea());
	        Cuboid cuboid = new Cuboid(5, 10, 5);
	        System.out.println("Volume = " + cuboid.getVolume());
	        
	    }
}
