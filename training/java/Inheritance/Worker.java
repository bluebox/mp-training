package Inheritance;

public class Worker {
    public String name;
    protected String birthDate; 
    protected String endDate;  

    public Worker(String name) {
        this(name,"28/02/2004");
    }

    public Worker(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
    public int getAge(){
        String[] str=birthDate.split("/");
        return 2025-Integer.parseInt(str[2]);

    }
    public double collectPay(){
        return 20.0;
    }
    public String terminate(String endDate){
        this.endDate = endDate;
        return "Worker terminated on "+endDate;
    }

    public String display(){
        return "Worker { \n Name: "+name+"\nBirth Date: "+birthDate+"\nEnd Date:" + endDate +"\n}";
    }
}
