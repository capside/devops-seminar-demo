package com.capside.enterpriseseminar;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ciberado
 */
@Service
public class StatisticsService {
    
    private final GameRepository gameRepo;

    @Autowired
    public StatisticsService(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }
    
    
    public Statistics getStatsBetween(String firstTeam, String secondTeam) {
        if (firstTeam.equals(secondTeam) == true) {
            throw new IllegalArgumentException("Two different teams needed.");
        }
        List<Game> games1 = gameRepo.findGames(firstTeam, secondTeam);
        List<Game> games2 = gameRepo.findGames(secondTeam, firstTeam);
        List<Game> games = new ArrayList<>();
        games.addAll(games1);
        games.addAll(games2);
        if (games.isEmpty() == true) {
            return null;
        }
        Statistics stats = new Statistics();
        stats.setFirstTeam(firstTeam); 
        stats.setSecondTeam(secondTeam);
        stats.setGames(games);
        for (Game game : games) {
            if (game.getScoreHome() == game.getScoreVisitor()) {
                stats.incDraws();
                continue;
            }
            if (game.getHome().equals(firstTeam) && (game.getScoreHome() > game.getScoreVisitor())) {
                stats.incFirstTeamWins();
                continue;
            }
            if (game.getVisitor().equals(firstTeam) && (game.getScoreVisitor() > game.getScoreHome())) {
                stats.incFirstTeamWins();
                continue;
            }
            if (game.getHome().equals(secondTeam) && (game.getScoreHome() > game.getScoreVisitor())) {
                stats.incSecondTeamWins();
                continue;
            }
            if (game.getVisitor().equals(secondTeam) && (game.getScoreVisitor() > game.getScoreHome())) {
                stats.incSecondTeamWins();
                continue;
            }
        }
        return stats;
    }
    
}
