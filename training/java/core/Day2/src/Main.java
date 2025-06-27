public class Main {
    public static void main(String[] args) {
        Account ac =new Account("12345",1000,"vejas","vejas@123.com","20101202020");
        System.out.println(ac.getAccNumber());

        SimpleCalc s1=new SimpleCalc();
        s1.setFirstNum(2.0);
        s1.setSecNum(3.0);
        System.out.println("add : "+s1.getAddition());
        System.out.println("diff : "+s1.getSub());
        System.out.println("product : "+s1.getProd());


        Person p1=new Person();
        p1.setFirstName("");
        p1.setLastName("babu");
        System.out.println("full name : "+p1.getFullName());
    }
}
