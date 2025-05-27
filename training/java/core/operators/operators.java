import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class operators
{
    public static void main(String args[]) throws Exception
    {
        int testNumber = 10;
        System.out.println(testNumber);//printing a number using sout

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(testNumber));

    }
}