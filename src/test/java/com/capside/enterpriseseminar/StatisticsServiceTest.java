package com.capside.enterpriseseminar;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author ciberado
 */
public class StatisticsServiceTest {

    private StatisticsService serv;
    private FakeGameRepository gameRepo;

    @Before
    public void init() {
        gameRepo = new FakeGameRepository();
        serv = new StatisticsService(gameRepo);
    }

    @Test
    public void countWinsAddingHomePlusVisitors() {
        Statistics stats = serv.getStatsBetween("Alice", "Bob");
        
        assertEquals(2, stats.getDraws());
        assertEquals(3, stats.getFirstTeamWins());
        assertEquals(2, stats.getSecondTeamWins());
    }

}
