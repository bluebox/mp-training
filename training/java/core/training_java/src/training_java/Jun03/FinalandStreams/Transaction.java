package FinalandStreams;

public class Transaction {
	private int routingNumber;
	private int custId;
	private long transactionId;
	private double transactionAmount;
	
	public Transaction(int routingNumber,int custId,long transactionId,double transactionAmount) {
		this.custId=custId;
		this.routingNumber=routingNumber;
		this.transactionAmount
	}
	public int getRoutingNumber() {
		return routingNumber;
	}
	public void setRoutingNumber(int routingNumber) {
		this.routingNumber = routingNumber;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

}
