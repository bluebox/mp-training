public class SimpleCalc {
    private double firstNum;
    private double secNum;

    public double getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(double firstNum) {
        this.firstNum = firstNum;
    }

    public double getSecNum() {
        return secNum;
    }

    public void setSecNum(double secNum) {
        this.secNum = secNum;
    }
    public double getAddition(){
        return firstNum+secNum;
    }

    public double getSub(){
        return firstNum-secNum;
    }

    public double getProd(){
        return firstNum*secNum;
    }

    public double getRem(){
        return firstNum%secNum;
    }

    public double getDiv(){
        return firstNum/secNum;
    }
}
