package corejavaday_Two;

public class SwitchChallenge {

    public static void main(String[] args){

        Character ch='L';
        switch (ch){
            case 'A':System.out.println("Able");
                break;
            case 'B':System.out.println("Baker");
                break;
            case 'C':System.out.println("Charlie");
                break;
            case 'D':System.out.println("Dog");
                break;
            case 'E':System.out.println("Easy");
                break;
            default:
                System.out.println("letter "+ch+" not found");
        }
    }
}
