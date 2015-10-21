package com.capside.enterpriseseminar;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ciberado
 */
@Repository
public class GameRepositoryImpl implements GameRepository {

    private final EntityManager em;

    @Autowired
    public GameRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    
    
    @Override
    public List<Game> findGames(String home, String visitor) {
        List<Game> games = (List<Game>) em.createQuery(
                "select g from Game g where g.home = :home and g.visitor = :visitor")
                .setParameter("home", home)
                .setParameter("visitor", visitor)
                .getResultList();
        
        return games;
    }
    
}
