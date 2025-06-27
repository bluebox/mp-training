package day_1_june25_basics;

class PoundToKilogramConverter {
    public static void main(String[] args) {
        int numberOfPounds = 30;
        double conversionFactor = 0.45359237;
        double numberOfKilograms = numberOfPounds * conversionFactor;
        System.out.println(numberOfPounds +
                " pounds in kilograms is " +
                numberOfKilograms + " kilograms");
    }
}
