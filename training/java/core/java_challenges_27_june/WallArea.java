
 public class WallArea{
	
 static class Wall {
	 private double width;
	    private double height;

	    public Wall() {
	        this.width = 0;
	        this.height = 0;
	    }

	    public Wall(double width, double height) {
	        this.width = width < 0 ? 0 : width;
	        this.height = height < 0 ? 0 : height;
	    }

	    public double getWidth() {
	        return width;
	    }

	    public double getHeight() {
	        return height;
	    }

	    public void setWidth(double width) {
	        this.width = width < 0 ? 0 : width;
	    }

	    public void setHeight(double height) {
	        this.height = height < 0 ? 0 : height;
	    }

	    public double getArea() {
	        return this.width * this.height;
	    }
}
	 public static void main(String args[]) {
		 	Wall w=new Wall(100,20);
		 double area=	w.getArea();
		 System.out.println(area);
		 	
	 }
 }
