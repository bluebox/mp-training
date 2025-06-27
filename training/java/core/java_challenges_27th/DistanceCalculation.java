
public class DistanceCalculation {

    static class Point {
        private int x;
        private int y;

        public Point() {
            this.x = 0;
            this.y = 0;
        }
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public double distance() {
            return Math.sqrt(x * x + y * y);
        }

  
        public double distance(Point p) {
            int dx = this.x - p.x;
            int dy = this.y - p.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        public double distance(int x, int y) {
            int dx = this.x - x;
            int dy = this.y - y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    // Main method to test functionality
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);

        System.out.println("p1 coordinates: (" + p1.getX() + ", " + p1.getY() + ")");
        System.out.println("p2 coordinates: (" + p2.getX() + ", " + p2.getY() + ")");

        System.out.println("Distance from p1 to origin: " + p1.distance());
        System.out.println("Distance from p1 to p2: " + p1.distance(p2));
        System.out.println("Distance from p1 to (1,2): " + p1.distance(1, 2));
    }
}
