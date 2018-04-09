package com.movietickets.movietickets.APIService;

import com.movietickets.movietickets.Model.Movie;
import com.movietickets.movietickets.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "api")
public class APIServiceController {

    @Autowired
    private MovieRepository movieRepository;
//    For All Data
    @RequestMapping(value = "allmovies", method = RequestMethod.GET)
    @ResponseBody
    private List<Movie> getAllMoviesAPI()
    {
        return movieRepository.findAll();
    }

//  For Single Data
    @RequestMapping(value = "movie/{movieId}")
    @ResponseBody
    private Movie getMovieAPI(@PathVariable int movieId)
    {
        return movieRepository.getOne(movieId);
    }
}
