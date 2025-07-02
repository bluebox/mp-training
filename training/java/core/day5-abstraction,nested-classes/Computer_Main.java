package day5;

public class Computer_Main {
    public static void main(String[] args) {
    	Computer.brand="Asus";
        Computer.USBPort usb = new Computer.USBPort(3);
        usb.connectDevice();
    }
}