import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemovePairsTest {
    RemovePairs removePairs=new RemovePairs();
    @Test
    public void Test1(){
        assertEquals("ABCDEF",removePairs.remove("ABCDEFF"));
    }
    @Test
    public void Test2(){
        assertEquals("AB8EFG",removePairs.remove("ALO8EFFG"));
    }
    @Test
    public void Test3(){
        assertEquals("123456",removePairs.remove("112233445566"));
    }
    @Test
    public void Test4(){
        assertEquals("ZYZQB",removePairs.remove("ZYZQQB"));
    }
    @Test
    public void Test5(){
        assertEquals("A",removePairs.remove("A"));
    }
}
