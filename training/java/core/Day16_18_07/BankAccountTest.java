package Day16_18_07;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankAccountTest {
	BankAccount acc;
	public static int count=0;

	@BeforeClass
	public static void beforeall() {
		System.out.println("Before Anything.....Count= "+count++);
	}
	@Before
	public void setup() {
		acc=new BankAccount("Saketh",5000,true);
		System.out.println("Running BEFORE setup....");
	}
	@Test
	public void exceptionChecker() {
		try {
			//acc.exceptionThrower();
		} catch (Exception e) {
			//fail("Exception occured......");
			
		}
	}
	
	@Test(expected=Exception.class)
	public void exceptionCheckerTest() throws Exception{
		try {
			acc.exceptionThrower();
		} catch (IOException e) {
			//fail("Exception occured......");
			
		}
	}
	
	@After
	public void afterCleaner() {
		System.out.println("After annotated test is executing.....Count= "+count++);
	}
	@Test
	public void getName() throws Exception{
		//fail("Manually failing this test");
		
	}
	
	@Test
	public void getBalance() throws Exception{
		//fail("This mesthod also ");
	}
	
	@Test
	public void deposite() throws Exception{
		//fail("This deposit method is failing");
		double bal=acc.getBalance();
		assertEquals(bal,5100,500);
	}
	
	@Test
	public void withdraw() throws Exception{
		
	}
	
	@Test
	public void isZeroAccount() {
		assertTrue(acc.isZeroAccount());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	@Test
	public void dummyTest() {
		//assertEquals(20,201);
	}

}
