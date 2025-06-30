public class problem4 {
    public static void main(String[] args) {
       
        int matches=0;
        int summatches=0;
        for (int i=1;i<=1000;i++){
            if((i%3==0)&&(i%5==0)){
                matches++;
                summatches=summatches+i;
                System.out.println("match  found " + i);

            }
            if(matches==5){
                break;

            }
        }
        System.out.println(summatches);
        }
    }

