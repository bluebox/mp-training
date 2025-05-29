package Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

class Song {
    private String title;
    private double duration;

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "title: " + title + ", duration: " + duration;
    }
}

class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration) {
        if (findSong(title) != null) {
            return false;
        }
        songs.add(new Song(title, duration));
        return true;
    }

    private Song findSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                return song;
            }
        }
        return null;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        int index = trackNumber - 1;
        if (index >= 0 && index < songs.size()) {
            playList.add(songs.get(index));
            return true;
        }
        return false;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        Song song = findSong(title);
        if (song != null) {
            playList.add(song);
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Album album = new Album("Greatest Hits", "Various Artists");

        // Adding songs
        album.addSong("Song One", 4.25);
        album.addSong("Song Two", 3.50);
        album.addSong("Song Three", 5.10);

        // Creating a playlist
        LinkedList<Song> playList = new LinkedList<>();

        // Adding songs to the playlist
        album.addToPlayList("Song Two", playList);
        album.addToPlayList(1, playList);
        album.addToPlayList("Song Four", playList); // Will not be added (not found)

        // Displaying the playlist
        System.out.println("Playlist:");
        for (Song song : playList) {
            System.out.println(song);
        }

        // Optionally play through the playlist
        System.out.println("\nPlaying playlist:");
        play(playList);
    }

    public static void play(LinkedList<Song> playList) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean goingForward = true;
        ListIterator<Song> listIterator = playList.listIterator();

        if (playList.isEmpty()) {
            System.out.println("No songs in playlist.");
            return;
        } else {
            System.out.println("Now playing: " + listIterator.next());
        }

        while (!quit) {
            System.out.println("\nEnter action: 0 - quit, 1 - next, 2 - previous, 3 - replay");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if (!goingForward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing: " + listIterator.next());
                    } else {
                        System.out.println("End of playlist.");
                        goingForward = false;
                    }
                    break;
                case 2:
                    if (goingForward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing: " + listIterator.previous());
                    } else {
                        System.out.println("Start of playlist.");
                        goingForward = true;
                    }
                    break;
                case 3:
                    if (goingForward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Replaying: " + listIterator.previous());
                            goingForward = false;
                        } else {
                            System.out.println("Start of playlist.");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println("Replaying: " + listIterator.next());
                            goingForward = true;
                        } else {
                            System.out.println("End of playlist.");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid action.");
            }
        }
    }
}
