public class Worker {
    private String name;
    private String birthDate;
    protected String endDate;

    public Worker(){


    }

    public Worker(String name,String birthDate){
        this.name=name;
        this.birthDate= birthDate;
         

    }
    public int getAge(){
        int currentYear=2025;
        int birthYear=Interger.parseInt(birthdate.substring(6));
        

    }    
}
