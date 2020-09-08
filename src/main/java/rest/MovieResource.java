package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("function")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MovieFacade FACADE =  MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("byid/{id}")
    public String movieById(@PathParam("id") int id) {
        try{
            MovieFacade mf = MovieFacade.getFacadeExample(EMF); 
            MovieDTO mov = mf.getMovieById(id); 
                            
            return GSON.toJson(mov);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The id " + id + " is not in the database and therefore this program cannot show you the result";
            return GSON.toJson(errorString);
        }
    }
        
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("all")
    public String allEmployees() {
        try{
            MovieFacade mf = MovieFacade.getFacadeExample(EMF); 
            List<MovieDTO> movieList = mf.getAllMovies(); 
            return GSON.toJson(movieList);
        }catch (javax.persistence.NoResultException e) {
            String errorString = "The function is either not working or there might not be any data in the database. IT HAS BEEN CONTACTED";
            return GSON.toJson(errorString);
        }
       
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("byname/{name}")
    public String movieByName(@PathParam("name") String name) {
        try{
            MovieFacade mf = MovieFacade.getFacadeExample(EMF); 
            MovieDTO mov = mf.getMovieByName(name);
                            
            return GSON.toJson(mov);
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The name " + name + " is not in the database and therefore this program cannot show you the result";
            return GSON.toJson(errorString);
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("count")
    public String count() {
        try{
            MovieFacade mf = MovieFacade.getFacadeExample(EMF); 
            long count = mf.getMovieCount(); 
                            
             return "{\"count\":"+count+"}";
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The count cannot be done in the database and therefore this program cannot show you the result";
            return GSON.toJson(errorString);
        }
    }
    
     @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("populate")
    public String populate() {
        try{
            MovieFacade mf = MovieFacade.getFacadeExample(EMF); 
            mf.populateDB();
                            
            return GSON.toJson("The DB is populated, see /all for more");
        } catch (javax.persistence.NoResultException e) {
            String errorString = "The count cannot be done in the database and therefore this program cannot show you the result";
            return GSON.toJson(errorString);
        }
    }
   
}
