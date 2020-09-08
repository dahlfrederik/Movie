package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void deleteAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public MovieDTO getMovieById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createNamedQuery("Movie.getMovieById");
            query2.setParameter("id", id);
            Movie mov = (Movie) query2.getSingleResult();
            MovieDTO movDTO = new MovieDTO(mov);
            return movDTO;
        } finally {
            em.close();
        }
    }

    public List<MovieDTO> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query2 = em.createNamedQuery("Movie.getAll");

            List<Movie> movieList = query2.getResultList();
            List<MovieDTO> movieDTOList = new ArrayList();
            for (Movie movie : movieList) {
                MovieDTO movDTO = new MovieDTO(movie);
                movieDTOList.add(movDTO);
            }

            return movieDTOList;
        } finally {
            em.close();
        }
    }

    public MovieDTO getMovieByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Movie.getMovieByName");
            query.setParameter("name", name);
            Movie mov = (Movie) query.getSingleResult();
            MovieDTO movDTO = new MovieDTO(mov); 

            return movDTO;
        } finally {
            em.close();
        }
    }

    public long getMovieCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long movieCount = (long) em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return movieCount;
        } finally {
            em.close();
        }
    }
    
      public void populateDB(){
            EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Movie("Batman Begins", "Zack Snyder", 2012));
            em.persist(new Movie("Batman The Dark Knight", "Zack Snyder", 2015));
            em.persist(new Movie("Topgun", "Kelly McGills", 1986));
            em.persist(new Movie("Kill Bill","John",2003));
            em.persist(new Movie("Point Break", "John 2", 1991));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
