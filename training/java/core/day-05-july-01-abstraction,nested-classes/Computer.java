package day5;

class Computer {
    public static String brand = "Dell";

    static class USBPort {
        private int portNumber;

        public USBPort(int portNumber) {
            this.portNumber = portNumber;
        }

        public void connectDevice() {
            System.out.println("Connecting to port " + portNumber + " on " + brand + " computer.");
        }
    }
}
