public class BANK {
    private String ac_no;
    private double acc_bal;
    private String custor_name;
    private String email;
    private String ph_no;

    public String getAc_no() {
        return ac_no;
    }

    public double getAcc_bal() {
        return acc_bal;
    }

    public String getCustor_name() {
        return custor_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPh_no() {
        return ph_no;
    }

    public void setAc_no(String ac_no) {
        this.ac_no = ac_no;
    }

    public void setAcc_bal(double acc_bal) {
        this.acc_bal = acc_bal;
    }

    public void setCustor_name(String custor_name) {
        this.custor_name = custor_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            acc_bal += amount;
            System.out.println("Deposited: " + amount + ". New balance: " + acc_bal);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && acc_bal - amount >= 0) {
            acc_bal -= amount;
            System.out.println("Withdrew: " + amount + ". New balance: " + acc_bal);
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else {
            System.out.println("Insufficient funds. Withdrawal denied.");
        }
    }
}