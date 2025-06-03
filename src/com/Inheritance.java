public class Inheritance {
    public static void main(String[] args) {
        Human human1 = new Human("Mourya", 21);
        Teacher teacher1 = new Teacher("Yash", 26, "CS", 100000);
        Human teacher2 = new Teacher("Yaswanth", 25, "Maths", 50000);
        System.out.println(teacher1);
        System.out.println(teacher2);
        System.out.println(human1);
    }
}
