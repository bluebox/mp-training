package Gym;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    //Below Function gives the date after completion of the plan
    public static String getFutureDate(int n, String startDate) {
        LocalDate planStatDate=LocalDate.parse(startDate,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate planEndDate=planStatDate.plusMonths(n);
        return planEndDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public static String getEndDate(String plan,String date){
        String[] planArray = plan.split(" / ");
        int duration;
        String months=planArray[1];
        if(months.charAt(0) =='1') duration =12;
        else duration = (int) months.charAt(0)-48;
        return getFutureDate(duration, date);
    } 

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int exit=1;
        System.out.println("-".repeat(30));
        System.out.println("Welcome to CultFit Unisex Gym!");
        System.out.println("-".repeat(30));
        Gym gymRecord=new Gym();
        while(exit==1){
            System.out.println("1. Add Member Manually");
            System.out.println("2. Add Member by Scanner");
            System.out.println("3. Delete Member");
            System.out.println("4. Update Membership Plan");
            System.out.println("5. Delete the Record data");
            System.out.println("6. Display Members");
            System.out.println("7. Show Single Member Details");
            System.out.println("0. To Exit");
            System.out.println("-".repeat(40));
            System.out.print("Select an option to continue :");
            int optedChoice=-1;
            MembershipPlan plan=new MembershipPlan();

            while(true){
                try{
                    int option = Integer.parseInt(sc.next());
                    optedChoice=option;
                    break;
                }catch(NumberFormatException e){
                    System.out.print("-".repeat(40)+" \nDisclaimer: Invalid Option Selected!\n"+"-".repeat(40)+" \nEnter a valid option :");
                }
            }
             switch(optedChoice){
                case 1 -> {

                    Member newMember1 = new Member("Varun", "20", "Male", plan.platinumPlan3, "02/06/2025",getEndDate(plan.goldPlan1,"02/06/2025"));
                    Member newMember2 = new Member("Shyam", "20", "Male", plan.platinumPlan3, "02/09/2025",getEndDate(plan.goldPlan1,"02/06/2025"));
                    
                    gymRecord.add(newMember1);
                    gymRecord.add(newMember2);
                }
                case 2 ->{
                    System.out.println("-".repeat(15)+"\nAdd Member");
                    System.out.print("Enter Name :");
                    String name=sc.next();
                    System.out.print("Enter Age :");
                    String age;
                    while(true){
                        try{
                            age=sc.next();
                            Integer.parseInt(age);
                            break;
                        }catch(NumberFormatException e){
                            System.out.println("Invalid Age Value!");
                        }
                    }
                    System.out.print("Enter Gender :");
                    String gender=sc.next();
                    //you can also add the start date manually
                    System.out.print("Enter the start Date (in dd/MM/yyyy format):");
                    String startDate=sc.next();
                    //String startDate=LocalDate.now()format(DzteTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String planName="";
                    System.out.print("Do you want any Plan (1 for yes / 0 for No) :");
                    String planOption;
                    while(true){
                        planOption=sc.next();
                        try{
                            Integer.parseInt(planOption);
                            if(planOption.equals("1") || planOption.equals("0")){
                                break;
                            }
                            else{
                                System.out.println("Invalid Option Selected!");
                            }
                        }catch(NumberFormatException e){
                            System.out.println("Invalid Value!");
                        }
                    }
                    switch(planOption){
                        case "1" ->{
                            System.out.println("Select your Plan : \n1.Gold Plan \n2.Platinum Plan");
                            String planType;
                            while(true){
                                try{
                                    planType=sc.next();
                                    Integer.parseInt(planType);
                                    if(planType.equals("1") || planType.equals("2")){
                                        break;
                                    }
                                    else{
                                        System.out.println("Invalid Plan Selected!");
                                    }
                                }catch(NumberFormatException e){
                                    System.out.println("Invalid Value!");
                                }
                            }
                            switch(planType){
                                case "1" -> {
                                    System.out.println("Select the period:\n1.Gold-1 / 3 Months / 4999/-\n" + "2.Gold-2 / 6 Months / 6999/-\n" + "3.Gold-3 / 12 Months / 14999/-");
                                    String period;
                                    while(true){
                                        try{
                                            period=sc.next();
                                            Integer.parseInt(period);
                                            if(period.equals("1") || period.equals("2") || period.equals("3")){
                                                break;
                                            }
                                            else{
                                                System.out.println("Invalid Option Selected!");
                                            }
                                        }catch(NumberFormatException e){
                                            System.out.println("Invalid Value!");
                                        }
                                    }
                                    switch(period){
                                        case "1" ->{
                                            planName=plan.goldPlan1;
                                        }
                                        case "2" -> {
                                            planName=plan.goldPlan2;
                                        }
                                        case "3" -> {
                                            planName=plan.goldPlan3;
                                        }
                                        default ->{
                                            System.out.println("-".repeat(15)+"\nInvalid Choice!\n"+"-".repeat(15));
                                        }
                                    }
                                }
                                case "2" ->{
                                    System.out.println("Select the period:\n1.Platinum-1 / 3 Months / 6999/- \n" + "2.Platinum-2 / 6 Months / 12999/-\n" + "3.Platinum-3 / 12 Months / 21999/-");
                                    String period;
                                    while(true){
                                        try{
                                            period=sc.next();
                                            Integer.parseInt(period);
                                            if(period.equals("1") || period.equals("2") || period.equals("3")){
                                                break;
                                            }
                                            else{
                                                System.out.println("Invalid Option Selected!");
                                            }
                                        }catch(NumberFormatException e){
                                            System.out.println("Invalid Value!");
                                        }
                                    }
                                    switch(period){
                                        case "1" ->{
                                            planName=plan.platinumPlan1;
                                        }
                                        case "2" -> {
                                            planName=plan.platinumPlan2;
                                        }
                                        case "3" -> {
                                            planName=plan.platinumPlan3;
                                        }
                                        default ->{
                                            System.out.println("-".repeat(15)+"\nInvalid Choice!\n"+"-".repeat(15));
                                        }
                                    }
                                }
                                default ->{
                                    System.out.println("-".repeat(15)+"\nInvalid Choice!\n"+"-".repeat(15));
                                }
                            }
                        }
                        case "0"-> {
                            planName=plan.basicPlan;
                        }
                        default ->{
                            System.out.println("-".repeat(15)+"\nInvalid Choice!\n"+"-".repeat(15));
                        }
                    }
                    
                    String endDate="";
                    endDate=getEndDate(planName,startDate);
                    Member newMember3 = new Member(name,age,gender,planName,startDate,endDate);
                    gymRecord.add(newMember3);
                    gymRecord.DisplayMembersRecord();
                }
                case 3 ->{
                    System.out.println("Enter the member Id: ");
                    String memberId = sc.next();
                    gymRecord.deleteMember(memberId);
                }
                case 4 ->{
                    System.out.println("Enter the member Id: ");
                    String memberId = sc.next();
                    String age=sc.next();
                    gymRecord.update(memberId,age);

                }
                case 5 ->{
                    gymRecord.deleteRecordData();
                     System.out.println("Record is Deleted");
                }
                case 6 ->{
                    System.out.println("-".repeat(15)+"\nGym Members Record!\n"+"-".repeat(15));
                    gymRecord.DisplayMembersRecord();
                }
                case 7 ->{
                    System.out.println("Enter the member Id: ");
                    String memberId = sc.next();
                    gymRecord.getMember(memberId);
                }
                case 0 -> {
                    exit=0;
                }
                default ->{
                    System.out.println("-".repeat(15)+"\nInvalid Choice!\n"+"-".repeat(15));
                }
            }
        }
    }
}