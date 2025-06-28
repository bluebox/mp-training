import java.util.Scanner;

public class floor{
    private double width;
    private double height;
    public static void main(String[] args) {
        
        



        floor w=new floor();
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the width:");
        
        double width=sc.nextDouble();

        System.out.println("enter height:");
        double height=sc.nextDouble();
        w.setwidth(width);
        w.setHeight(height);
        System.out.print("area of floor is :"+w.area());

        System.out.println("\nEnter carpet cost per unit area:");
        double cost = sc.nextDouble();
        carpet c = w.new carpet(cost);
        calculator calc = w.new calculator(w, c);
        System.out.println("Total cost: " + calc.gettotalcost());
    }

    public floor() {
        this.width = width;
        this.height = height;
    }

    public floor(double width,double height){
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

    public class carpet{
    private double cost;
    
    public carpet(double cost){
        this.cost=cost<0 ?0:cost;
    }
    public double getCost(){
        return cost;
    }

    

}
public class calculator{
    private floor floor;
    private carpet carpet;
    public calculator(floor floor,carpet carpet){
        this.floor=floor;
        this.carpet=carpet;
    }
    public double gettotalcost(){
        return floor.area() * carpet.getCost();
    }

}


}
