import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class EveryNthCharTest {
    EveryNthChar nthChar=new EveryNthChar();
    @Test
    public void everyNthChar() {
        char[] arr = {'h', 'e', 'l', 'l', 'o'};
        char[] res = {'e', 'l'};
        assertArrayEquals(res, nthChar.everyNthChar(arr, 2));
    }
    @Test
    public void everyNthChar2(){
        char[] arr2={'h','l','o'};
        char[] res2={'h','l','o'};
        assertArrayEquals(res2,nthChar.everyNthChar(arr2,5));
    }
}
