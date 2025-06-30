import java.util.Scanner;

public class Wall {
    private double width;
    private double height;

    
    public Wall() {
        this.width = 0;
        this.height = 0;
    }

    
    public Wall(double width, double height) {
        if (width < 0) {
            this.width = 0;
        } else {
            this.width = width;
        }

        if (height < 0) {
            this.height = 0;
        } else {
            this.height = height;
        }
    }

  
    public double getWidth() {
        return width;
    }

  
    public double getHeight() {
        return height;
    }

   
    public void setWidth(double width) {
        if (width < 0) {
            this.width = 0;
        } else {
            this.width = width;
        }
    }

   
    public void setHeight(double height) {
        if (height < 0) {
            this.height = 0;
        } else {
            this.height = height;
        }
    }

    
    public double getArea() {
        return width * height;
    }

  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter wall width:");
        double userWidth = scanner.nextDouble();

        System.out.println("Enter wall height:");
        double userHeight = scanner.nextDouble();

        
        Wall wall = new Wall(userWidth, userHeight);

        System.out.println("Initial Wall Area: " + wall.getArea());

      
        System.out.println("Enter new width:");
        double newWidth = scanner.nextDouble();
        wall.setWidth(newWidth);

        System.out.println("Enter new height:");
        double newHeight = scanner.nextDouble();
        wall.setHeight(newHeight);

        System.out.println("Updated Wall Area: " + wall.getArea());

        scanner.close();
    }
}
