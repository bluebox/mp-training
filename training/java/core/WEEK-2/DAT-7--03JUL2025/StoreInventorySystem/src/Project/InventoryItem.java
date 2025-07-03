package Project;

public class InventoryItem {
    private final Product product;
    private int qtyTotal;
    private int qtyReserved;
    private int qtyReorder;
    private int qtyLow;
    private double salesPrice;

    public InventoryItem(Product product, int qtyTotal, int qtyReorder, int qtyLow, double salesPrice) {
        this.product = product;
        this.qtyTotal = qtyTotal;
        this.qtyReorder = qtyReorder;
        this.qtyLow = qtyLow;
        this.salesPrice = salesPrice;
    }

    public boolean reserveItem(int qty) {
        if (qty <= (qtyTotal - qtyReserved)) {
            qtyReserved += qty;
            return true;
        }
        return false;
    }

    public boolean releaseItem(int qty) {
        if (qty <= qtyReserved) {
            qtyReserved -= qty;
            return true;
        }
        return false;
    }

    public boolean sellItem(int qty) {
        if (qty <= qtyReserved) {
            qtyReserved -= qty;
            qtyTotal -= qty;
            return true;
        }
        return false;
    }

    public boolean placeInventoryOrder() {
        if (qtyTotal <= qtyLow) {
            qtyTotal += qtyReorder;
            return true;
        }
        return false;
    }

    public Product getProduct() { return product; }

    @Override
    public String toString() {
        return product + " | Stock: " + qtyTotal + ", Reserved: " + qtyReserved;
    }
}

