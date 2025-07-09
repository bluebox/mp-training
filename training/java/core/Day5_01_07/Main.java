package Day5_01_07;

public class Main {
    public static void main(String[] args) {
        System.out.println(Status.SUCCESS);           
        System.out.println(Status.SUCCESS.getCode()); 
        Status stat=Status.NOT_FOUND;
        System.out.println(stat.getCode());
        
    }
}
