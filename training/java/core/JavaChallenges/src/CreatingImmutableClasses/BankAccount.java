package CreatingImmutableClasses;

public class BankAccount {
	final String type;
	final double dollar;

	public BankAccount(String type, double dollar) {
		this.type = type;
		this.dollar = dollar;
	}

	public String toString() {
		return "%s %f".format(type, dollar);
	}
}