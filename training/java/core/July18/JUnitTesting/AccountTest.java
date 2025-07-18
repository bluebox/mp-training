package maven.challenges;


import org.junit.Assert;
import org.junit.Test;


public class AccountTest {
	
	
	@Test
	public void testDeposit() {
		Account account = new Account("dasu" , 200);
		account.deposite(new Integer(200));
		Assert.assertEquals(new Integer(400),account.amount);
	}
	
	@Test
	public void testWithdraw() {
		Account account = new Account("dasu",100);
		account.withdraw(100);
		Assert.assertEquals(new Integer(0),account.amount);

	}
}
