public class Inventoryitem {
    

    private Product product;
    private int qtyTotal;
    private int qtyReserved;
    private int qtyReorder;
    private int qtylow;
    private int salesprice;
    
    public Inventoryitem(Product product, int qtyTotal, int qtyReserved, int qtyReorder, int qtylow, int salesprice) {
        this.product = product;
        this.qtyTotal = qtyTotal;
        this.qtyReserved = qtyReserved;
        this.qtyReorder = qtyReorder;
        this.qtylow = qtylow;
        this.salesprice = salesprice;
    }
    void reserveitem(int qtyReserved){  
        setQtyReserved(qtyReserved);

    }
    void releaseitem(){
        setQtyReorder(qtyReorder-1);

    }
    void sellitem(){
        setQtyTotal(qtyTotal-1);
    }
    void placeInventoryOrder(int qty){
        setQtyTotal(qtyTotal+qty);

    }
    public Product getProduct() {
        return product;
    }
    public int getQtyReorder() {
        return qtyReorder;
    }
    public int getQtyReserved() {
        return qtyReserved;
    }
    public int getQtyTotal() {
        return qtyTotal;
    }
    public int getQtylow() {
        return qtylow;
    }
    public int getSalesprice() {
        return salesprice;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setQtyReorder(int qtyReorder) {
        this.qtyReorder = qtyReorder;
    }
    public void setQtyReserved(int qtyReserved) {
        this.qtyReserved = qtyReserved;
    }
    public void setQtyTotal(int qtyTotal) {
        this.qtyTotal = qtyTotal;
    }
    public void setQtylow(int qtylow) {
        this.qtylow = qtylow;
    }
    public void setSalesprice(int salesprice) {
        this.salesprice = salesprice;
    }
}
