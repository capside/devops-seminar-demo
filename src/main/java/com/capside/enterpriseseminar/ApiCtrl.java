
package com.capside.enterpriseseminar;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ciberado
 */
@RestController
@RequestMapping("/")
public class ApiCtrl {
    
    private final StatisticsService statsService;

    @Autowired
    public ApiCtrl(StatisticsService statsService) {
        this.statsService = statsService;
    }
    
    @RequestMapping(value="/games/stats/{firstTeam:.*}-vs-{secondTeam:.*}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<StatisticsDTO> calculateStatistics(@PathVariable String firstTeam, @PathVariable String secondTeam) {
        Statistics stats = statsService.getStatsBetween(firstTeam, secondTeam);
        StatisticsDTO dto = new StatisticsDTO(stats);
        return new ResponseEntity<>(dto, stats == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
    
    @Data
    public static class StatisticsDTO {
        private final Statistics statistics;
    }
    
}
