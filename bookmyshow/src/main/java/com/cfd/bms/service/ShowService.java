package com.cfd.bms.service;

import com.cfd.bms.dto.ShowDto;
import com.cfd.bms.exception.ResourceNotFoundException;
import com.cfd.bms.model.Movie;
import com.cfd.bms.model.Screen;
import com.cfd.bms.model.Show;
import com.cfd.bms.repository.MovieRepository;
import com.cfd.bms.repository.ScreenRepository;
import com.cfd.bms.repository.ShowRepository;
import com.cfd.bms.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ShowService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ScreenRepository screenRepository;

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private ShowSeatRepository showSeatRepository;


	public ShowDto createShow(ShowDto showDto){
		Show show = new Show();

		Movie movie = movieRepository.findById(ShowDto.getMovie().getId())
				.orElseThrow(()-> new ResourceNotFoundException("Movie Not Found"));

		Screen screen = screenRepository.findById(ShowDto.getScreen().getId())
				.orElseThrow(()-> new ResourceNotFoundException("Screen Not Found"));

		show.setMovie(movie);
		show.setScreen(screen);
		show.setStartTime(showDto.getStartTime());
		show.setEndTime(showDto.getEndTime());

		Show saveShow = showRepository.save(show);



	}
}
