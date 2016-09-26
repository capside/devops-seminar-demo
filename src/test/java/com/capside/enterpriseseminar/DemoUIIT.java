/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capside.enterpriseseminar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ciberado
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")   // Beware: this profile will use a local database
public class DemoAPIIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;
    
    
    public DemoAPIIT() {
    }
    
    @Test
    public void testGetStatisticsWithDTO() {
        final String localTeamName = "R. Madrid";
        final String visitorTeamName = "Barcelona";
        ResponseEntity<ApiCtrl.StatisticsDTO> response = 
                restTemplate.exchange("/games/stats/{firstTeam:.*}-vs-{secondTeam:.*}",
                        HttpMethod.GET, null, ApiCtrl.StatisticsDTO.class, localTeamName, visitorTeamName);
        
        assertEquals("Invocation was a success.", HttpStatus.OK, response.getStatusCode());
        assertEquals("Local team", localTeamName, response.getBody().getStatistics().getFirstTeam());
        assertEquals("Visitor team", visitorTeamName, response.getBody().getStatistics().getSecondTeam());
   }

    @Test
    public void testGetStatisticsWithJSON() throws IOException {
        final String localTeamName = "R. Madrid";
        final String visitorTeamName = "Barcelona";
        ResponseEntity<String> response = 
                restTemplate.exchange("/games/stats/{firstTeam:.*}-vs-{secondTeam:.*}",
                        HttpMethod.GET, null, String.class, localTeamName, visitorTeamName);
        JsonNode json = mapper.readTree(response.getBody());
        assertEquals("Invocation was a success.", HttpStatus.OK, response.getStatusCode());
        assertEquals("Local team", localTeamName, json.findValue("statistics").findValue("firstTeam").asText());
        assertEquals("Visitor team", visitorTeamName, json.findValue("statistics").findValue("secondTeam").asText());
   }

    
    
    
    
    
    
    
}
