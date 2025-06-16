import java.util.*;
class Contact{
   String name;
   long phone;
   public Contact(String name,long phone){
      this.name=name;
      this.phone=phone;
   }
}
class MobileManager{
    static List<Contact> contact=new ArrayList<>();
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args){
        while(true){
            System.out.println("1.add, 2.delete, 3.search, 4.view, 5.exit");
            int ch=sc.nextInt();
            switch(ch){
                case 1:addContact();
                       break;
                case 2:deleteContact();
                       break;
                case 3:searchContact();
                       break;
                case 4:viewContact();
                       break;
                case 5:System.out.println("exit");
                       return;
                default:System.out.println("Invalid choice");
             }
         }
    }
    static void addContact(){
         System.out.println("enter name");
         String name=sc.next();
         System.out.println("enter phone");
         long phone=sc.nextLong();
         contact.add(new Contact(name,phone));
    }
    static void viewContact(){
         if(contact.isEmpty()){
             System.out.println("no contacts");
         }else{
             for(Contact c:contact){
                 System.out.println(c.name+" -> "+c.phone);
             }
         }
     }
     static void deleteContact(){
         if(contact.isEmpty()){
             System.out.println("no contacts");
         }else{
             System.out.println("enter contact name to delete");
             String sname=sc.next();
             for(int i=0;i<contact.size();i++){
                  if(contact.get(i).name.equals(sname)){
                       contact.remove(i);
                       System.out.println("contact deleted");
                       return;
                  }
             }
             System.out.println("contact not found");
         }
     }
     static void searchContact(){
         if(contact.isEmpty()){
             System.out.println("no contacts");
         }else{
             System.out.println("enter contact name to search");
             String sname=sc.next();
             for(int i=0;i<contact.size();i++){
                  if(contact.get(i).name.equals(sname)){
                       System.out.println("contact found "+sname+" "+contact.get(i).phone);
                       return;
                  }
             }
             System.out.println("Not found");
         }

     }
}
