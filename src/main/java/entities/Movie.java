package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Movie"),
//@NamedQuery(name = "Movie.getAll", query = "SELECT m from Movie m"),
//@NamedQuery(name = "Movie.getMovieById", query = "SELECT m from Movie m WHERE m.name LIKE :name"),
})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String director;
    private int releaseYear;

    
    public Movie() {
    }

    
    public Movie(String name, String director, int releaseYear) {
        this.name = name;
        this.director = director;
        this.releaseYear = releaseYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return releaseYear;
    }

    public void setYear(int year) {
        this.releaseYear = year;
    }

    public int getId() {
        return id;
        
    }

    public void setId(int id) {
        this.id = id;
    }

}
