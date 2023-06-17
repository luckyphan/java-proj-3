package com.javaunit3.springmvc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class MovieApp {
    public static void main(String[] args) {
//System.out.println("print properly");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MovieApp.class);
        //isnt IoC, can change implementation for bean name, but relies directly on Spring , gets rid fof purpose as DI container
        //inject Movie object
        BestMovieService bestMovieService = applicationContext.getBean("bestMovieService", BestMovieService.class);

        Movie bestMovie = bestMovieService.getBestMovie();

        System.out.println("Title: "+bestMovie.getTitle());
        System.out.println("Maturity Rating: "+bestMovie.getMaturityRating());
        System.out.println("Genre: "+bestMovie.getGenre());
    }
}