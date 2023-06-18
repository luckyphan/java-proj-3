package com.javaunit3.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BestMovieService {
//    @Autowired same objective of injection by using constructor and setter
    private Movie movie;

    //constructor injection
//    @Autowired
//    public BestMovieService(Movie movie) {
//        this.movie = movie;
//    }

    //since creating the TitanicMovie same type as BatmanMovie, doesnt know which bean to use and throws error
    //constructor that defaults the bean id to TitanicMovie
    @Autowired
    public BestMovieService(@Qualifier("titanicMovie") Movie movie) {
        this.movie = movie;
    }

    public Movie getBestMovie(){
        return movie;
    }

    //setter injection
//    @Autowired
//    public void setMovie(Movie movie) {
//        this.movie = movie;
//    }

}
