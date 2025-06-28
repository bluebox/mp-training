import java.util.Scanner;

public class wall {
    private double width;
    private double height;
    public static void main(String[] args) {
        wall w=new wall();
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the width:");
        double width=sc.nextDouble();

        System.out.println("enter height:");
        double height=sc.nextDouble();
        w.setwidth(width);
        w.setHeight(height);
        System.out.print("area of wall is :"+w.area());
    }
    public wall(){
        this.width = width;
        this.height = height;
    }
    public wall(double width,double height){
        this.width = width < 0 ? 0 : width;
        this.height = height < 0 ? 0 : height;
    }
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }
    public void setwidth(double width){
        this.width=width<0 ?0: width;
    }
    public void setHeight(double height){
        this.height=height<0 ? 0:height;

    }
    public double area(){
        return width*height;
    }
}
