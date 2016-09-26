package com.capside.enterpriseseminar;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ciberado
 */
@Data @AllArgsConstructor @NoArgsConstructor
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
