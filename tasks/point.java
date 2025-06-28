import java.util.Scanner;
public class point {
    private int x;
    private int y;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter x value:");
        int x=sc.nextInt();
        System.out.println("enter y value:");
        int y=sc.nextInt();
        point p=new point(6,5);
        point p1=new point(3,1);
        System.out.println("distance(0,0)"+p.distance());
        System.out.println("distance(p)"+p.distance(p1));
        System.out.println("distance(x,y)"+p.distance(x,y));
        point p2=new point();
        System.out.println("distance()"+p2.distance());


        
    }
    public point(){
        this.x=x;
        this.y=y;
    }
    public point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public double distance(){
        double result=Math.sqrt((x*x +y*y));
        return result;
    }
    public double distance(point p){
        double result = Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
        return result;
    }
    public double distance(int x,int y){
        double result=Math.sqrt((this.x-x)*(this.x-x) +(this.y-y)*(this.y-y));
        return result;
    }
    
}
