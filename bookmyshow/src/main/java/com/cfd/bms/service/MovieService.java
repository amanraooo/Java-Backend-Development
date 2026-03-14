package com.cfd.bms.service;

import com.cfd.bms.dto.MovieDto;
import com.cfd.bms.exception.ResourceNotFoundException;
import com.cfd.bms.model.Movie;
import com.cfd.bms.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	public MovieDto createMovie(MovieDto movieDto){
		Movie movie = mapToEntity(movieDto);
		Movie saveMovie = movieRepository.save(movie);
		return mapToDto(saveMovie);
	}

	public MovieDto getMovieById(Long id){
		Movie movie = movieRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Movie not Found"));
		return mapToDto(movie);
	}

	public List<MovieDto> getAllMovies(){
		List<Movie> movies = movieRepository.findAll();
		return movies.stream()
				.map(this::mapToDto)
				.collect(Collectors.toList());

	}
	private MovieDto mapToDto(Movie movie)
	{
		MovieDto movieDto=new MovieDto();
		movieDto.setId(movie.getId());
		movieDto.setTitle(movie.getTitle());
		movieDto.setDescription(movie.getDescription());
		movieDto.setLanguage(movie.getLanguage());
		movieDto.setGenre(movie.getGenre());
		movieDto.setDurationMins(movie.getDurationMins());
		movieDto.setReleaseDate(movie.getReleaseDate());
		movieDto.setPosterUrl(movie.getPosterUrl());
		return movieDto;
	}

	public Movie mapToEntity(MovieDto movieDto)
	{
		Movie movie=new Movie();
		movie.setTitle(movieDto.getTitle());
		movie.setDescription(movieDto.getDescription());
		movie.setLanguage(movieDto.getLanguage());
		movie.setGenre(movieDto.getGenre());
		movie.setDurationMins(movieDto.getDurationMins());
		movie.setReleaseDate(movieDto.getReleaseDate());
		movie.setPosterUrl(movieDto.getPosterUrl());
		return movie;
	}
}
