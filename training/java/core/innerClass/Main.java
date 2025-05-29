import java.util.*;

public class Main {
    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {
        
        Album album = new Album("One Hot Minute", "Red Hot Chili Peppers");
        album.addSong("Warped", 5.04);
        album.addSong("Aeroplane", 4.45);
        album.addSong("Deep Kick", 6.33);
        album.addSong("My Friends", 4.02);
        album.addSong("Coffee Shop", 3.08);
        album.addSong("Pea", 1.47);
        album.addSong("One Big Mob", 6.02);
        album.addSong("Walkabout", 5.07);
        album.addSong("Tearjerker", 4.19);
        album.addSong("One Hot Minute", 6.23);
        album.addSong("Falling into Grace", 3.48);
        album.addSong("Shallow Be Thy Game", 4.33);
        album.addSong("Transcending", 5.46);
        albums.add(album);

        album = new Album("Californication", "Red Hot Chili Peppers");
        album.addSong("Around the World", 3.58);
        album.addSong("Parallel Universe", 4.30);
        album.addSong("Scar Tissue", 3.37);
        album.addSong("Otherside", 4.15);
        album.addSong("Get on Top", 3.18);
        album.addSong("Californication", 5.21);
        album.addSong("Easily", 3.51);
        album.addSong("Porcelain", 2.43);
        album.addSong("Emit Remmus", 4.00);
        album.addSong("I Like Dirt", 2.37);
        album.addSong("This Velvet Glove", 3.45);
        album.addSong("Savior", 4.52);
        album.addSong("Purple Stain", 4.13);
        album.addSong("Right on Time", 1.52);
        album.addSong("Road Trippin'", 3.25);
        albums.add(album);

        LinkedList<Song> playList = new LinkedList<>();
        albums.get(0).addToPlayList("My Friends", playList);
        albums.get(0).addToPlayList("Aeroplane", playList);
        albums.get(0).addToPlayList("Dark Road", playList);
        albums.get(0).addToPlayList(9, playList);
        albums.get(1).addToPlayList(7, playList);
        albums.get(1).addToPlayList(3, playList);
        albums.get(1).addToPlayList(1, playList);
        albums.get(1).addToPlayList(17, playList);

        play(playList);
    }

    private static void play(LinkedList<Song> playlist) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playlist.listIterator();
        if (playlist.size() == 0) {
            System.out.println("No songs in playlist");
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Quitting the simulator.");
                    quit = true;
                    break;
                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString());
                    } else {
                        System.out.println("We have reached the end of the playlist.");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing : " + listIterator.previous().toString());
                        forward = true;
                    } else {
                        System.out.println("We are at the start of the playlist.");
                    }
                    break;
                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Now playing : " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the playlist.");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println("Now replaying : " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the playlist.");
                        }
                    }
                    break;
                case 4:
                    printList(playlist);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if (playlist.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing : " + listIterator.next());
                        } else if (listIterator.hasPrevious()) {
                            System.out.println("Now playing : " + listIterator.previous());
                        }
                    }
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Available actions:\nPress");
        System.out.println("( 0 ) - to quit the simulator\n" +
                "( 1 ) - to play the next song\n" +
                "( 2 ) - to play the previous song\n" +
                "( 3 ) - to replay the current song\n" +
                "( 4 ) - list all songs in playlist\n" +
                "( 5 ) - print all available action options.\n" +
                "( 6 ) - to remove current song from the playlist");
    }

    private static void printList(LinkedList<Song> playlist) {
        Iterator<Song> iterator = playlist.iterator();
        System.out.println("=============================");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("=============================");
    }
}