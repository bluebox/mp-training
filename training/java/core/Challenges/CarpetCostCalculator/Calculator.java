package training.java.core.Challenges.CarpetCostCalculator;

public class Calculator {
    Floor floor;
    Carpet carpet;

    public Calculator(Floor floor, Carpet carpet){
        this.floor = floor;
        this.carpet = carpet;
    }

    public double getTotalCost(){
        double area = floor.getArea();
        double cost = carpet.getCost();
        return area*cost;
    }

    // public static void main(String args[]){
    //     Carpet c = new Carpet(3.5);
    //     Floor f = new Floor(2.75, 4.0);
    //     Calculator calculator = new Calculator(f, c);
    //     double totalCost = calculator.getTotalCost();
    //     System.out.println("Total cost is:"+totalCost);
    // }
}
