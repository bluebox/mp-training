import java.util.Scanner;
public class Rectangle {
    private double width;
    private double length;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter width:");
        double width=sc.nextDouble();
        System.out.println("enter length");
        double length=sc.nextDouble();
        Rectangle r=new Rectangle(width,length);
        System.out.println("area of cudiod:"+(r.getarea()));
        System.out.println("enter height:");
        double height = sc.nextDouble();
        Cuboid c = new Cuboid(width, length, height);
        System.out.println("volume of cuboid:" + c.getVolume());

    }
    public Rectangle(double width,double length){
        this.width=width<0 ?0:width;
        this.length=length<0?0:length;
    }
    public double getwidth(){
        return width;
    }
    public double getlength(){
        return length;
    }
    public double getarea(){
        return width*length;
    }
    public static class Cuboid extends Rectangle {
        private double height;
    
        public Cuboid(double width, double length, double height) {
            super(width, length);
            this.height = height < 0 ? 0 : height;
        }
    
        public double getHeight() {
            return height;
        }
    
        public double getVolume() {
            return getwidth() * getlength() * height;
        }
    }
    
}
