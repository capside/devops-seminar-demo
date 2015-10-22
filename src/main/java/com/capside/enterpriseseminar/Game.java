package com.capside.enterpriseseminar;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ciberado
 */
@Entity 
@Table(name = "games")
@Data 
public class Game implements Serializable {
    @Id @GeneratedValue
    private int id;
    private String home;
    private String visitor;
    @Column(name="theyear")
    private int year;
    @Column(name="scorehome")
    private int scoreHome;
    @Column(name="scorevisitor")
    private int scoreVisitor;

    public Game() {
    }

    public Game(int id, String home, String visitor, int year, int scoreHome, int scoreVisitor) {
        this.id = id;
        this.home = home;
        this.visitor = visitor;
        this.year = year;
        this.scoreHome = scoreHome;
        this.scoreVisitor = scoreVisitor;
    }
    
    
    
}
