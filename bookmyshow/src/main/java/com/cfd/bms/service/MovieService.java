package com.cfd.bms.service;

import com.cfd.bms.dto.MovieDto;
import com.cfd.bms.model.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

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
