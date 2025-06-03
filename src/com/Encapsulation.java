public class Encapsulation {
    public static void main(String[] args) {
        Capsule cap = new Capsule("Cold Act", 15);
//        cap.capsuleType = 10; // gives you error
        cap.getCapsuleType();
    }
}

class Capsule {
    private String capsuleType;
    private int noOfCapsules;

    Capsule(String name, int cnt) {
        capsuleType = name;
        noOfCapsules = cnt;
    }

    public void getCapsuleType() {
        System.out.println(capsuleType);
    }
}
