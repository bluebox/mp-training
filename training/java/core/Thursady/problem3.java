public class problem3 {
    public static void main(String[] args) {
       
             int temp = 0;
             int number = 1;
             int sum = 0;
             System.out.println("the odd number");
         while(temp<100){

            if(number %2!=0){
                sum +=number;
                temp++;
                System.out.println(number);
        
            }
            number++;

         }
         System.out.println("their sum is" + sum);

         }

    
    
    }





