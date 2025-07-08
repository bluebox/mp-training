import java.util.Scanner;
public class Problem {
    

    public static void main(String[] args) {
        int[] arr=new int[5];
        Scanner sc=new Scanner(System.in);
        int front=-1,back=-1;
        while(true){
            System.out.println("enter a to add the numbers to queue");
            System.out.println("enter b to delete the element from front");
            System.out.println("enter c to see the elements");
            System.out.println("enter q to exit fromt the queue");
            String option=sc.next();
            if(option.equals("a")){
                System.out.println("enter the number to add");
                int num=sc.nextInt();
                if(front==-1){
                    
                    front++;
                    back++;
                    arr[front]=num;
                }
                else{
                    back++;
                    if(back-front>=arr.length){
                        System.out.println(" overflow");
                        for(int i=front;i<=back;i++){
                            System.out.println(arr[i]);
                        }
                        break;}
                    
                    arr[back%arr.length]=num;

                }
            }
            else if(option.equals("b")){
                System.out.println("enter the number to delete");
                
                if(front==-1){
                    System.out.println("is empty");
                    break;
                }
                else{
                    if(front%arr.length==back%arr.length){
                        front=-1;
                        back=-1;
                        System.out.println("is empty");
                        continue;
                    }
                    front++;
                    System.out.println("front"+front);
                    System.out.println("back"+back);
                    for(int i=front;i<=back;i++){
                            System.out.println("front"+front+" "+"back "+back+" "+arr[i%arr.length]);
                        }

                }
            }
            else if(option.equals("c")){
                if(front!=-1 ){
                    for(int i=front;i<=back;i++){
                            System.out.println("front"+front+" "+"back "+back+" "+arr[i%arr.length]);
                        }
                }

            }
            else{
                break;
            }

        }
        

    }
}
