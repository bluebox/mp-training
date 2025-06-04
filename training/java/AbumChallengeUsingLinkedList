public class AbumChallengeUsingLinkedList{
    public static  void main(String[] args){
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
for(Song item:album.songs){
    System.out.print(item);
}

    }
}


public class Album{
    private String artist;
    private String name;
    public ArrayList<Song>  songs;
    public Album(String artist,String name){
        this.artist=artist;
        this.name=name;
    }
    public static boolean addSong(String titleOfSong,double duration){
        Song newSong=new Song(titleOfSong,duration);
        if(songs.contains(newSong)){
            System.out.println("Song is already present in the Playlist!!!");
            return false;
        }
        for(var item:songs){
            if(item.name==newSong.name){
                System.out.println("Song is already present in the Playlist with this name!!!");
            return false;
            }
        }
        ListIterator<Song> iter=songs.listIterator();
        if(songs.size()==0){songs.add(newSong);
        return true;}
        while(iter.hasNext());
        iter.add(newSong);
        return true;
        

    }
    public static Song findSong(String titleOfSong){
        for(var item:songs){
            if(item.name==titleOfSong){
                System.out.println("Song is present in the Playlist with this name!!!");
            return item;
            }
        }
        return null;

    }
    public static boolean addToPlayList(int songNumber,LinkedList<Song> playList){
        try{
            playList.add(Song.get(songNumber+1));
            return true;
        }
        catch{return false;}
    }
    public static boolean addToPlayList(String songName,LinkedList<Song> playList){
        for(var item:songs){
            if(item.name==songName){
                playList.add(Song.get(songNumber+1));
            return true;
            }
        }
        return false;
    }
}

public record Song(String name,double duration){
    public String getTitle(){
        return name;
    }
    public double getduration(){
        return duration;
    }
    public String toString(){
        return name+"song has"+duration +"of duration.";
    }
}