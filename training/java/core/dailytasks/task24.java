public class task24 {
    
    private double firstNumber;
    private double secondNumber;

    public double getFirstNumber() {
        return firstNumber;
    }

    // Method to return the value of secondNumber
    public double getSecondNumber() {
        return secondNumber;
    }

    // Method to set the value of firstNumber
    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    // Method to set the value of secondNumber
    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    // Method to return the result of adding firstNumber and secondNumber
    public double getAdditionResult() {
        return firstNumber + secondNumber;
    }

    // Method to return the result of subtracting secondNumber from firstNumber
    public double getSubtractionResult() {
        return firstNumber - secondNumber;
    }

    // Method to return the result of multiplying firstNumber and secondNumber
    public double getMultiplicationResult() {
        return firstNumber * secondNumber;
    }

    // Method to return the result of dividing firstNumber by secondNumber
    // Returns 0 if secondNumber is 0
    public double getDivisionResult() {
        if (secondNumber == 0) {
            return 0;
        }
        return firstNumber / secondNumber;
    }
}