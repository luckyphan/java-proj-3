package com.javaunit3.springmvc;

import com.javaunit3.springmvc.model.MovieEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

// the pages are returned in proper methods wrapped in quotes
//@Controller (autodetect implementation classes) is used with @RequestMapping for request handling methods
@Controller
public class MovieController {
    @Autowired
    private BestMovieService bestMovieService;
    //set by bean for SessionFactory
    @Autowired
    private SessionFactory sessionFactory;

    //the endpoint of / would return the index page
    @RequestMapping("/")
    public String getIndexPage(){
        return "index";
    }
    //Spring model parameter so whichever bean is passed as best movie
    //The Model is the container that contains data of the application
    //its placed in the Controller
    //data is in forms of Objects, Strings, db info
    @RequestMapping("/bestMovie")
    public String getBestMoviePage(Model model) {
        model.addAttribute("BestMovie", bestMovieService.getBestMovie().getTitle());
        return "bestMovie";
    }
    //the endpoint of voteForBestMovieForm would return the voteForBestMovie page
    @RequestMapping("/voteForBestMovieForm")
    public String voteForBestMovieFormPage(){
        return "voteForBestMovie";
    }
    //the endpoint of voteForBestMovie would return the voteForBestMovie page
    //HttpServletRequest enables methods to access parameters of request
    //in this case we are grabbing the movieTitle parameter from the request and setting it to BestMovieVote in
    //voteForBestMovie
    //**is model just the page reference variable?
    //Model is an object that exposes a new object to view template
    @RequestMapping("/voteForBestMovie")
    public String voteForBestMovie(HttpServletRequest req, Model model){
        String movieTitle= req.getParameter("movieTitle");
        model.addAttribute("BestMovieVote",movieTitle);
        return "voteForBestMovie";
    }
    //the endpoint of addMovieForm would return the addMovie page
    @RequestMapping("/addMovieForm")
    public String addMovieForm(){
        return "addMovie";
    }
    //the endpoint of addMovie would return the addMovie page
    @RequestMapping("/addMovie")
    public String addMovie(HttpServletRequest req, Model model){
        String movieTitle = req.getParameter("movieTitle");
        String maturityRating = req.getParameter("maturityRating");
        String genre = req.getParameter("genre");

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(movieTitle);
        movieEntity.setMaturityRating(maturityRating);
        movieEntity.setGenre(genre);
        //the SessionFactory is long persistence
        //Session object is short persistence to enable CRUD operations
        Session session = sessionFactory.getCurrentSession();
        //begin, save, and commit
        session.beginTransaction();
        session.save(movieEntity);
        session.getTransaction().commit();

        return "addMovie";
    }
}
