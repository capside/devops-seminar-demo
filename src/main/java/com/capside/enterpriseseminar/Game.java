package com.capside.enterpriseseminar;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ciberado
 */
@Entity 
@Table(name = "games")
@Data @AllArgsConstructor @NoArgsConstructor
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
    
}
