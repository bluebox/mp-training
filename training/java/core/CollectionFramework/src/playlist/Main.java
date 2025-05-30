package playlist;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		
		ArrayList<Album> albums = new ArrayList<>();
		Album album = new Album("Premikudu","AR Rehman" );
		album.addsongs("O Cheliya",5.8);
		album.addsongs("Mukkala",7);
		album.addsongs("Andamaina Premarani",4.8);
		
		albums.add(album);
		
		for(var albm:albums)
		{
			System.out.println("albm :: "+albm);
		}
		

	}

}
