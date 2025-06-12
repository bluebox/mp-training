package jpachallenges;

import javax.persistence.*;
import java.util.List;

public class JpaChallengeApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SongPU");
        EntityManager em = emf.createEntityManager();
        
        try {
            // Create test data
            em.getTransaction().begin();
            
            Artist artist1 = new Artist("The Beatles");
            Artist artist2 = new Artist("Pink Floyd");
            em.persist(artist1);
            em.persist(artist2);
            
            Album album1 = new Album("Abbey Road");
            Album album2 = new Album("The Dark Side of the Moon");
            em.persist(album1);
            em.persist(album2);
            
            Song song1 = new Song("Come Together", artist1, album1);
            Song song2 = new Song("Here Comes the Sun", artist1, album1);
            Song song3 = new Song("Money", artist2, album2);
            em.persist(song1);
            em.persist(song2);
            em.persist(song3);
            
            em.getTransaction().commit();
            
            // Test the queries
            SongQuery songQuery = new SongQuery(em);
            
            System.out.println("JPQL Results:");
            List<Object[]> jpqlResults = songQuery.findSongsWithArtistAndAlbumJpql();
            printResults(jpqlResults);
            
            System.out.println("\nCriteria API Results:");
            List<Object[]> criteriaResults = songQuery.findSongsWithArtistAndAlbumCriteria();
            printResults(criteriaResults);
            
        } finally {
            em.close();
            emf.close();
        }
    }
    
    private static void printResults(List<Object[]> results) {
        for (Object[] row : results) {
            System.out.printf("Song: %-20s Artist: %-15s Album: %s%n", 
                row[0], row[1], row[2]);
        }
    }
}
