package com.javaunit3.springmvc.model;

import javax.persistence.*;
import java.util.List;

//@Entity is a persistent object that stores records in DB (user, product, data the app needs to persistently retrieve
@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id// the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //is using special identity columns in DB to generate value of insertion
    @Column(name = "movie_id") //indicates to add column
    private Integer id;
    //one to many relationship that creates the object for injection
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "movie_id")
    private List<VoteEntity> votes;

    public List<VoteEntity> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteEntity> votes) {
        this.votes = votes;
    }

    public void addVote(VoteEntity vote)
    {
        this.votes.add(vote);
    }

    @Column(name = "title")
    private String title;

    @Column(name = "maturity_rating")
    private String maturityRating;

    @Column(name = "genre")
    private String genre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }



}
