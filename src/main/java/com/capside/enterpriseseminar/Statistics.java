package com.capside.enterpriseseminar;

import java.util.List;
import lombok.Data;

/**
 *
 * @author ciberado
 */
@Data
public class Statistics {
    private String firstTeam;
    private int firstTeamWins;
    private String secondTeam;
    private int secondTeamWins;
    private int draws;
    private List<Game> games;

    void incFirstTeamWins() {
        firstTeamWins++;
    }

    void incDraws() {
        draws++;
    }

    void incSecondTeamWins() {
        secondTeamWins++;
    }
    
    public int getTotalGames() {
        return games.size();
    }
}
