/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import entities.Movie;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class MovieDTO {
    private String name; 
    private int releaseYear; 
    private int id; 
    
    public MovieDTO(Movie movie){
        this.name = movie.getName(); 
        this.releaseYear = movie.getYear(); 
        this.id = movie.getId(); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
