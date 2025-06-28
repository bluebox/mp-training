public class Printer {
    private int tonerLevel;

    public Printer(int initialTonerLevel) {
        if (initialTonerLevel >= 0 && initialTonerLevel <= 100) {
            this.tonerLevel = initialTonerLevel;
        } else {
            this.tonerLevel = -1;
        }
    }

    public int addToner(int tonerAmount) {
        if (tonerAmount <= 0 || tonerAmount > 100) {
            return -1; 
        }

        if (this.tonerLevel + tonerAmount > 100) {
            return -1; 
        }

        this.tonerLevel += tonerAmount;
        return this.tonerLevel;
    }

    public int getTonerLevel() {
        return tonerLevel;
    }
}
