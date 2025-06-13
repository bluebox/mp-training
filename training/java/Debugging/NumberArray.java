package Debugging;

public class NumberArray {

    private final int[] array;
    private  int count;

    public NumberArray() {

        this.array = new int[10];
        this.count=0;
    }
    public void addNumber(int a){
        array[count]=a;
        count++;
    }
    public int[] getArray(){
        return  array;
    }
}
