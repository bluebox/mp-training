public class Regularexp {
    public static void main(String[] args) {
        String sentence="how are you Hello, World";
        if(sentence.contains("Hello, World")){
            System.out.println("contains Hello World");
        }
        String newsentence="How are you.";
        if(newsentence.matches("[A-Z][a-z].*\\.")){
            System.out.println("matched");
        }
        else{
            System.out.println("not matched");
        }
        String newSentence="This is bad!";
        if(newSentence.matches("[A-Z].+[?!.]")){
            System.out.println("new Sentence matched");
        }
        else{
            System.out.println("new Sentence not matched");

        }

       
    }



}
