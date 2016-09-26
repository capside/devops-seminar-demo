package com.capside.enterpriseseminar;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author ciberado
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoAPIWithMockServiceIT {

    @Autowired
    private TestRestTemplate restTemplate;
    
    @MockBean
    private StatisticsService statisticsService;
    
    
    public DemoAPIWithMockServiceIT() {
    }
    
    @Before
    public void setup() {
        final String firstTeamName = "R. Madrid";
        final String secondTeamName = "Barcelona";
        
        Game[] games = {
            new Game(1, firstTeamName, secondTeamName, 1999, 3, 1),
            new Game(2, firstTeamName, secondTeamName, 1999, 5, 1),
            new Game(3, secondTeamName, firstTeamName, 1999, 2, 4)
        };
        
        given(
            this.statisticsService.getStatsBetween(firstTeamName, secondTeamName)
        ).willReturn(
            new Statistics(firstTeamName, 2, secondTeamName, 1, 0, Arrays.asList(games)));
        
        given(
            this.statisticsService.getStatsBetween(secondTeamName, firstTeamName)
        ).willReturn(
            new Statistics(secondTeamName, 1, firstTeamName, 2, 0, Arrays.asList(games)));
    }

    @Test
    public void testGetStatisticsWithDTO() {
        final String localTeamName = "R. Madrid";
        final String visitorTeamName = "Barcelona";
        ResponseEntity<ApiCtrl.StatisticsDTO> response = 
                restTemplate.exchange("/games/stats/{firstTeam:.*}-vs-{secondTeam:.*}",
                        HttpMethod.GET, null, ApiCtrl.StatisticsDTO.class, localTeamName, visitorTeamName);
        
        assertEquals("Invocation was a success.", HttpStatus.OK, response.getStatusCode());
        assertEquals("First team wins:", 2, response.getBody().getStatistics().getFirstTeamWins());
        assertEquals("Second team wins:", 1, response.getBody().getStatistics().getSecondTeamWins());
   }
    
    
}
