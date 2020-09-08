package facades;

import dto.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private Movie r1, r2; 

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        r1 = new Movie("Batman begins","Zack Snyder",2012);
        r2 = new Movie("Batman The Dark Knight","Zack Snyder",2014);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(r1);
            em.persist(r2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetAllMovies() {
        MovieFacade mf = MovieFacade.getFacadeExample(emf);
        int result = mf.getAllMovies().size(); 
        int expected = 2; 
        assertEquals(expected, expected, "Expects two rows in the database");
    }
    
    @Test
    public void testGetMovieByID() {
        MovieDTO m1 = new MovieDTO(r1); 
        MovieDTO movie = facade.getMovieById(m1.getId());
        assertEquals("Batman begins",movie.getName());
        
       }
    
    
     @Test
        public void testGetMovieByName() {
        MovieFacade cf = MovieFacade.getFacadeExample(emf);
        String name = "Batman begins";
        String expResult = "Batman begins";
        String result = cf.getMovieByName(name).getName();

        assertEquals(expResult, result);
    }
}
