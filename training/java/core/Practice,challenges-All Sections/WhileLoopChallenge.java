public class WhileLoopChallenge {
    public static void main(String[] args) {
        int i=5,c=0;
        while(i<=20)
        {
            if(isEvenNumber(i))
            {
                System.out.println(i);
                c++;
            }
            if(c==5)
                break;
            i++;
        }
        System.out.println("Total even numbers are "+c);
        System.out.println("Total odd numbers are "+(20-c));
    }
    public static boolean isEvenNumber(int num)
    {
        if(num%2==0)
            return true;
        return false;
    }
}
