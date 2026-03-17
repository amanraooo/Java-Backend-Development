package com.cfd.bms.service;

import com.cfd.bms.dto.MovieDto;
import com.cfd.bms.dto.ScreenDto;
import com.cfd.bms.dto.ShowDto;
import com.cfd.bms.dto.TheaterDto;
import com.cfd.bms.exception.ResourceNotFoundException;
import com.cfd.bms.model.Movie;
import com.cfd.bms.model.Screen;
import com.cfd.bms.model.Show;
import com.cfd.bms.model.ShowSeat;
import com.cfd.bms.repository.MovieRepository;
import com.cfd.bms.repository.ScreenRepository;
import com.cfd.bms.repository.ShowRepository;
import com.cfd.bms.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

		Show savedShow = showRepository.save(show);

		List<ShowSeat> availableSeats =
				showSeatRepository.findByShowIdAndStatus(savedShow.getId(), "AVAILABLE");
		return


	}

	private ShowDto mapToDto(Show show, List<ShowSeat> availableSeats){
		ShowDto showDto = new ShowDto();
		showDto.setId(show.getId());
		showDto.setStartTime(show.getStartTime());
		showDto.setEndTime(show.getEndTime());

		showDto.setMovie(new MovieDto(
				show.getMovie().getId(),
				show.getMovie().getTitle(),
				show.getMovie().getDescription(),
				show.getMovie().getLanguage(),
				show.getMovie().getGenre(),
				show.getMovie().getDurationMins(),
				show.getMovie().getReleaseDate(),
				show.getMovie().getPosterUrl()
		));

		TheaterDto theaterDto=new TheaterDto(
				show.getScreen().getTheater().getId(),
				show.getScreen().getTheater().getName(),
				show.getScreen().getTheater().getAddress(),
				show.getScreen().getTheater().getCity(),
				show.getScreen().getTheater().getTotalScreens()
		);

		showDto.setScreen(new ScreenDto(
				show.getScreen().getId(),
				show.getScreen().getName(),
				show.getScreen().getTotalSeats(),
				theaterDto
		));


	}
}
