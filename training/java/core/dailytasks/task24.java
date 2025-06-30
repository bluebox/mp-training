public class task24 {
    
    private double firstNumber;
    private double secondNumber;

    public double getFirstNumber() {
        return firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }


    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

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

    
    public double getMultiplicationResult() {
        return firstNumber * secondNumber;
    }

    
    public double getDivisionResult() {
        if (secondNumber == 0) {
            return 0;
        }
        return firstNumber / secondNumber;
    }
}
