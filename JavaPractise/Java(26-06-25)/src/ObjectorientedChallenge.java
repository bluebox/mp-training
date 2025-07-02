
public class ObjectorientedChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankAccount b1=new BankAccount();
		b1.setAccno("9999");
		b1.setAccbal(1000.00);
		b1.setCutomername("Sreeja");
		b1.setEmail("abc@gmail.com");
		b1.setPhnno("8387818738");
		b1.depositingAmount(100);
		b1.withdrawingAmount(1200);

	}
}
	class BankAccount
	{
		private String accno;
		private double accbal;
		private String accname;
		private String cutomername;
		private String email;
		private String phnno;
		
		public String getAccno() {
			return accno;
		}
		public void setAccno(String accno) {
			this.accno = accno;
		}
		public double getAccbal() {
			return accbal;
		}
		public void setAccbal(double accbal) {
			this.accbal = accbal;
		}
		public String getAccname() {
			return accname;
		}
		public void setAccname(String accname) {
			this.accname = accname;
		}
		public String getCutomername() {
			return cutomername;
		}
		public void setCutomername(String cutomername) {
			this.cutomername = cutomername;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhnno() {
			return phnno;
		}
		public void setPhnno(String phnno) {
			this.phnno = phnno;
		}
		public void depositingAmount(double depositamount)
		{
		accbal+=depositamount;
		System.out.println("Availble balance after depositing is"+accbal);
		
		}
		public void withdrawingAmount(double withdrawlamount)
		{
		accbal-=withdrawlamount;
		if(accbal<0)
		{
			System.out.println("Not sufficient balance in the bank");
		}
		else
		{
			System.out.println("Availble balance after withdrawl is"+accbal);
		}
		}
	}
	

