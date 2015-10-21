package com.capside.enterpriseseminar;

import java.util.List;

/**
 *
 * @author ciberado
 */

public interface GameRepository {
    
    List<Game> findGames(String home, String visitor);
    
}
