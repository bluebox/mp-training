import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConverterTest {
    Converter converter=new Converter();
    @Test
    public void converterTest(){
        int a=10;
        int b=5;
        int res=300;
        assertEquals(res,converter.converter(a,b));
    }
    @Test
    public void converterTest2(){
       assertThrows(ArithmeticException.class,()->{
           converter.converter(10,0);
       });
    }
}