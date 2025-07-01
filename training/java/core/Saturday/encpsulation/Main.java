public class Main  {
    public static void main(String[] args) {
        
    
    Bankaccount pen = new Bankaccount();

    
    pen.setAccountnumber(59848);
    pen.setCustomername("santosh");
    pen.setEmail("emal123");
    pen.setPhonenumber(98745632);

    pen.withDrawMoney(564);
    System.out.println(pen.getAccountbalance());
    }

}

