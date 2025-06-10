import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;

public class AbumChallengeUsingLinkedList{
    public static  void main(String[] args){
        ArrayList<Album> albums = new ArrayList<>();

        Album album = new Album("Stormbringer", "Deep Purple");
album.addSong("Stormbringer", 4.6);
album.addSong("Love don't mean a thing", 4.22);
album.addSong("Holy man", 4.3);
album.addSong("Hold on", 5.6);
album.addSong("Lady double dealer", 3.21);
album.addSong("You can't do it right", 6.23);
album.addSong("High ball shooter", 4.27);
album.addSong("The gypsy", 4.2);
album.addSong("Soldier of fortune", 3.13);
albums.add(album);
 
album = new Album("For those about to rock", "AC/DC");
album.addSong("For those about to rock", 5.44);
album.addSong("I put the finger on you", 3.25);
album.addSong("Lets go", 3.45);
album.addSong("Inject the venom", 3.33);
album.addSong("Snowballed", 4.51);
album.addSong("Evil walks", 3.45);
album.addSong("C.O.D.", 5.25);
album.addSong("Breaking the rules", 5.32);
album.addSong("Night of the long knives", 5.12);
albums.add(album);
for(Album albumName:albums){
    for(Song item:album.songs){
    System.out.println(item);
}

    
}
System.out.println("-----------------------------------");
LinkedList<Song> playList = new LinkedList<Song>();
albums.get(0).addToPlayList("You can't do it right", playList);
albums.get(0).addToPlayList("Holy man", playList);
albums.get(0).addToPlayList("Speed king", playList);  
System.out.println(albums.get(0).addToPlayList(9, playList));
System.out.println(albums.get(1).addToPlayList(3, playList));
System.out.println(albums.get(1).addToPlayList(2, playList));
System.out.println(albums.get(1).addToPlayList(24, playList));  


    }
}


public class Album{
    private String artist;
    private String name;
     public ArrayList<Song> songs = new ArrayList<>();
    public Album(String artist,String name){
        this.artist=artist;
        this.name=name;
        

    }
    public boolean addSong(String titleOfSong,double duration){
        Song newSong=new Song(titleOfSong,duration);
        if(songs.contains(newSong)){
            System.out.println("Song is already present in the Playlist!!!");
            return false;
        }
        for(Song item:songs){
            if(item.getName().equalsIgnoreCase(newSong.getName())){
                System.out.println("Song is already present in the Playlist with this name!!!");
            return false;
            }
        }
        
        if(songs.size()==0){songs.add(newSong);
        return true;}
        
        songs.add(newSong);
        return true;
        

    }
    public  Song findSong(String titleOfSong){
        for(var item:songs){
            if(item.getName().equalsIgnoreCase(titleOfSong)){
                System.out.println("Song is present in the Playlist with this name!!!");
            return item;
            }
        }
        return null;

    }
    public  boolean addToPlayList(int songNumber,LinkedList<Song> playList){
        try{
            
            playList.add(songs.get(songNumber+1));
            return true;
        }
        catch(Throwable t){return false;}
    }
    public boolean addToPlayList(String songName,LinkedList<Song> playList){
        for(var item:songs){
            if(item.getName().equalsIgnoreCase(songName)){
                playList.add(item);
            return true;
            }
        }
        return false;
    }
}

public record Song(String name,double duration){
    public String getName(){
        return name;
    }
    public double getduration(){
        return duration;
    }
    public String toString(){
        return name+"::"+duration ;
    }
}