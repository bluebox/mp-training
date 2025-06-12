package dev.lpa;

import dev.lpa.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class SongQuery {

    public static void main(String[] args) {

        try (
                EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                        "dev.lpa.music");
                EntityManager em = emf.createEntityManager();
        ) {

            String dashedString = "-".repeat(19);
            String word = "Storm";
            var matches = getMatchedSongs(em, "%"+word+"%");
            System.out.printf("%-30s %-65s %s%n","Artist", "Album", "Song Title");
            System.out.printf("%1$-30s %1$-65s %1$s%n", dashedString);

            matches.forEach(a -> {
                String artistName = a.getArtistName();
                a.getAlbums().forEach(b -> {
                    String albumName = b.getAlbumName();
                    b.getPlayList().forEach(s -> {
                        String songTitle = s.getSongTitle();
                        if (songTitle.contains(word)) {
                            System.out.printf("%-30s %-65s %s%n",
                                    artistName, albumName, songTitle);
                        }
                    });
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Artist> getMatchedSongs(EntityManager em, String matchedValue) {

        String jpql = "SELECT a FROM Artist a JOIN albums album join playList p" +
                " WHERE p.songTitle LIKE ?1 ";
        var query = em.createQuery(jpql, Artist.class);
        query.setParameter(1, matchedValue);
        return query.getResultList();
    }
}
