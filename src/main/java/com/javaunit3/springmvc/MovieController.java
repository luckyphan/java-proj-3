package com.javaunit3.springmvc;

import com.javaunit3.springmvc.model.MovieEntity;
import com.javaunit3.springmvc.model.VoteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    public String getBestMoviePage(Model model)
    {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();
        movieEntityList.sort(Comparator.comparing(movieEntity -> movieEntity.getVotes().size()));

        MovieEntity movieWithMostVotes = movieEntityList.get(movieEntityList.size() - 1);
        List<String> voterNames = new ArrayList<>();

        for (VoteEntity vote: movieWithMostVotes.getVotes())
        {
            voterNames.add(vote.getVoterName());
        }

        String voterNamesList = String.join(",", voterNames);

        model.addAttribute("bestMovie", movieWithMostVotes.getTitle());
        model.addAttribute("bestMovieVoters", voterNamesList);

        session.getTransaction().commit();

        return "bestMovie";
    }
    //the endpoint of voteForBestMovieForm would return the voteForBestMovie page
    @RequestMapping("/voteForBestMovieForm")
    public String voteForBestMovieFormPage(Model model){
        //get list of all movie entities in DB and populate "movies"attribute in model
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        //saves the object of the Query from the MovieEntity to the list (convert to list)
        List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();
        //returns the currently active transaction/creates one and commit tells DB to perform all required
        //consistency checks and persist changes permanently
        session.getTransaction().commit();
        model.addAttribute("movies", movieEntityList);
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
//        String movieTitle= req.getParameter("movieTitle");
//        model.addAttribute("BestMovieVote",movieTitle);
        String voterName = req.getParameter("voterName");
        String movieId = req.getParameter("movieId");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        MovieEntity movieEntity = (MovieEntity) session.get(MovieEntity.class,Integer.parseInt(movieId));
        VoteEntity newVote = new VoteEntity();
        newVote.setVoterName(voterName);
        movieEntity.addVote(newVote);

        session.update(movieEntity);
        session.getTransaction().commit();

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
