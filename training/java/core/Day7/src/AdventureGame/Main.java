package AdventureGame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String locations = 
                "road,at the end of the road,W:hill,E:well house,S:valley,N:forest\n" +
                "hill,on top of hill with a view in all directions,N:forest,E:road\n" +
                "well house,inside a well house for a small spring,W:road,N:lake,S:stream\n" +
                "valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream\n" +
                "forest,at the edge of a thick dark forest,S:road,E:lake\n" +
                "lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house\n" +
                "stream,near a stream with a rocky bed,W:valley,N:well house\n";

        Game game = new Game(locations);
        game.play("road");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.startsWith("Q")) {
                System.out.println("**EXIT**");
                break;
            }
            game.move(input);
        }

        scanner.close();
    }
}
