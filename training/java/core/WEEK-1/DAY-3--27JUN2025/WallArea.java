class Wall {
    private double width;
    private double height;

    // No-args constructor
    public Wall() {
        this.width = 0;
        this.height = 0;
    }

    // Constructor with width and height parameters
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

    // Getter for width
    public double getWidth() {
        return this.width;
    }

    // Getter for height
    public double getHeight() {
        return this.height;
    }

    // Setter for width
    public void setWidth(double width) {
        if (width < 0) {
            this.width = 0;
        } else {
            this.width = width;
        }
    }

    // Setter for height
    public void setHeight(double height) {
        if (height < 0) {
            this.height = 0;
        } else {
            this.height = height;
        }
    }

    // Method to calculate area
    public double getArea() {
        return this.width * this.height;
    }
}

public class WallArea {
    public static void main(String[] args) {
        Wall wall = new Wall(5, 4);
        System.out.println("Area = " + wall.getArea());

        wall.setHeight(-1.5);
        System.out.println("Width = " + wall.getWidth());
        System.out.println("Height = " + wall.getHeight());
        System.out.println("Area = " + wall.getArea());
    }
}
