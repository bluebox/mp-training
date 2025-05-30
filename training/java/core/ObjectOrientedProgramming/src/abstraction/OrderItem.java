package abstraction;

public record OrderItem(int qty,Product1 product) {
	@Override
    public String toString() {
		product().showDetails();
        return "Qunatity :: " + qty();
    }
}
