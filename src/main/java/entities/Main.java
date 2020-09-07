/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import facades.MovieFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(); 
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(new Movie("Batman Begins", "Zack Snyder", 2012));
            em.persist(new Movie("Batman The Dark Knight", "Zack Snyder", 2015));

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
