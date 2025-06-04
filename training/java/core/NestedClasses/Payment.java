package NestedClasses;
//inner class exdample
public class Payment {
	private String orderId;
	
	public Payment(String orderId) {
		this.orderId=orderId;
	}
	
	
	
	public class Mode{
		private String paymentMode;
		
		public Mode(String paymentMode) {
			this.paymentMode=paymentMode;
		}
		
		public void displayMode() {
			System.out.println("OrderId: "+orderId);
			System.out.println("PaymentMode: "+paymentMode);
		}
	}
	
	public static void main(String[] args) {
		Payment newPayment=new Payment("MED1011");
		Payment.Mode mode=newPayment.new Mode("Debit Card");
		
		mode.displayMode();
	}

}
