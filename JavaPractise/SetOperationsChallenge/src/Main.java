public class Main {
    public static void main(String[] args) {
        System.out.println("All Tasks:");
        TaskData.getTasks("all").forEach(System.out::println);

        System.out.println("Akash's Tasks:");
        TaskData.getTasks("Akash").forEach(System.out::println);

        System.out.println("B's Tasks:");
        TaskData.getTasks("B").forEach(System.out::println);
    }
}