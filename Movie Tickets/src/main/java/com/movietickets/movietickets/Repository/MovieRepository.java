package com.movietickets.movietickets.Repository;

import com.movietickets.movietickets.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
