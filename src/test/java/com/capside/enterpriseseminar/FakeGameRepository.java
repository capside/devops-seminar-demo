package com.capside.enterpriseseminar;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ciberado
 */
class FakeGameRepository implements GameRepository {

    private final List<Game> games = new ArrayList<>();
    
    public FakeGameRepository() {
        // Alice vs Bob -> draw 2, win alice 3, win bob 3 
        games.add(new Game(1, "Alice", "Bob", 2015, 0, 0));
        games.add(new Game(1, "Alice", "Bob", 2015, 1, 0));
        games.add(new Game(1, "Alice", "Bob", 2015, 0, 1));
        games.add(new Game(1, "Alice", "Bob", 2015, 5, 0));
        games.add(new Game(1, "Bob", "Alice", 2015, 3, 0));
        games.add(new Game(1, "Bob", "Alice", 2015, 3, 3));
        games.add(new Game(1, "Bob", "Alice", 2015, 1, 3));
        games.add(new Game(1, "Alice", "Charlie", 2015, 1, 3));
        games.add(new Game(1, "Bob", "Charlie", 2015, 1, 3));
        games.add(new Game(1, "Charlie", "Dave", 2015, 1, 3));        
    }
    
    
    @Override
    public List<Game> findGames(String home, String visitor) {
        List<Game> results = new ArrayList<>();
        for (Game game : games) {
            if (game.getHome().equals(home) && game.getVisitor().equals(visitor)) {
                results.add(game);
            }
        }
        return results;
    }
    
}
