enum Type{
    CHECKING,
    SAVINGS,
    OTHER
}
public class BankAccount {
    

    private Type type;
    private int balance;
    public BankAccount(Type type, int balance) {
        this.type = type;
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }
    public Type getType() {
         return type;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void setType(Type type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "BankAccount [type=" + type + ", balance=" + balance + "]";
    }
    
    


}
