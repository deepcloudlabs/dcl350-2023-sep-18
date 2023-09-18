package com.example.imdb.controller;

import java.util.Collection;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@RestController
@RequestMapping("/movies")
@RequestScope
@CrossOrigin
@Validated
public class ImdbRestController {
	// dependency
	private final MovieService movieService;
	
	public ImdbRestController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping(params={"fromYear","toYear"})
	public Collection<Movie> getMoviesFromRange(@RequestParam int fromYear,@RequestParam int toYear){
		return movieService.findAllMoviesByYearRange(fromYear, toYear);
	}
}
