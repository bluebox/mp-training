import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MainTest {
    Main main=new Main();
    @Test
    public void testAdd(){
        assertEquals(5,main.add(2,3));
    }
    @Test
    public void testSub(){
        assertEquals(4,main.sub(10,6));
    }

}
