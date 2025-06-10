public class NumberTOWordsConvertor{
    public static void main(String[] args){

        System.out.println(numberToWords(123));
        System.out.println(numberToWords(100));
        System.out.println(numberToWords(101));

    }
    public static String numberToWords(int number){
        String s="";

        number=reverse(number);
        while(number!=0){
            
            s=" "+s;
            switch(number%10){

                case 0:s="Zero"+s; break;
                case 1:s="One"+s; break;
                case 2:s="Two"+s ; break;
                case 3:s="Three"+s;break;
                case 4:s="Four"+s;break;
                case 5:s="Five"+s;break;
                case 6:s="Six"+s;break;
                case 7:s="Seven"+s;break;
                case 8:s="Eight"+s;break;
                case 9:s="Nine"+s;}
                number=number/10;
            }
            return  s;
   
    }

        }
    
