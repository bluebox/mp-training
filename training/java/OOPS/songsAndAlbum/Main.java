package songsAndAlbum;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		
		ArrayList <Album> albums= new ArrayList<>();
		Album album=new Album("stormbringer","Deep Pruple");
		album.addSong("strombriger",4.6 );
		album.addSong("Love don't mean a thing",4.22 );
		album.addSong("holy man",4.3 );
		album.addSong("hold on",5.6 );
		album.addSong("stromb",4.6 );
		albums.add(album);
		album=new Album("bstormbringer","Deep Pruple");
		album.addSong("bstrombriger",4.6 );
		album.addSong("bLove don't mean a thing",4.22 );
		album.addSong("bholy man",4.3 );
		album.addSong("bhold on",5.6 );
		album.addSong("bstromb",4.6 );
		albums.add(album);

	}

}
