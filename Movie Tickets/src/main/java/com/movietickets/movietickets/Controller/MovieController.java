package com.movietickets.movietickets.Controller;

import com.movietickets.movietickets.Model.Movie;
import com.movietickets.movietickets.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "admin")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

//    Movie Retrieve
    @RequestMapping(value = "movies")
    private String movies(Model model)
    {
        model.addAttribute("title", "Movies");
        model.addAttribute("movieList", movieRepository.findAll());
        return "AdminFeatures/Movies";
    }

//    Add New Movie
    @RequestMapping(value = "addmovie", method = RequestMethod.GET)
    private String addMovie(Model model)
    {
        model.addAttribute("movie", new Movie());
        model.addAttribute("title", "Add Movie");
        return "AdminFeatures/AddMovie";
    }
    @RequestMapping(value = "addmovie", method = RequestMethod.POST)
    private String processAddMovie(@ModelAttribute @Valid Movie movie, Errors error, Model model)
    {
        if(error.hasErrors())
        {
            model.addAttribute("title", "Add Movie");
            return "AdminFeatures/AddMovie";
        }
        movieRepository.save(movie);

        return "redirect:/admin/movies";
    }

//    Movie Details
    @RequestMapping(value = "movieDetails/{movieId}", method = RequestMethod.GET)
    private String movieDetails(@PathVariable int movieId, Model model)
    {
        model.addAttribute("title", "Movie Details");
        Movie movie = movieRepository.getOne(movieId);
        model.addAttribute("movie", movie);
        return "AdminFeatures/MovieDetails";
    }

//    Movie Delete
    @RequestMapping(value = "movieDelete/{movieId}")
    private String deleteMovie(@PathVariable int movieId)
    {
        movieRepository.deleteById(movieId);
        return "redirect:/admin/movies";
    }

//    Modify of Edit Movie
    @RequestMapping(value = "movieEdit/{movieId}", method = RequestMethod.GET)
    private String modifyMovie(@PathVariable int movieId, Model model)
    {
        model.addAttribute("title","Modify Movie");
        Movie movie = movieRepository.getOne(movieId);
        model.addAttribute("movie", movie);
        return "AdminFeatures/EditMovie";
    }
    @RequestMapping(value = "movieEdit/{movieId}", method = RequestMethod.POST)
    private String processModifyMovie(@ModelAttribute @Valid Movie movie, Errors errors, @PathVariable int movieId, Model model)
    {
        if(errors.hasErrors())
        {
            model.addAttribute("title","Modify Movie");
            return "AdminFeatures/EditMovie";
        }
        movie.setId(movieId);
        movieRepository.save(movie);
        return "redirect:/admin/movies";
    }

}
