package CarpetCostCalculation;

public class Calculator {
Floor f1;
Carpet c1;
 public Calculator( Floor f1,Carpet c1)
 {
	 this.f1=f1;
	 this.c1=c1;
	 
 }
 public double gettotalcost()
 {
	 return f1.getarea() * c1.getcost();
 }
 
}
