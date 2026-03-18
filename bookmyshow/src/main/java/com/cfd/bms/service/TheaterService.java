package com.cfd.bms.service;

import com.cfd.bms.dto.TheaterDto;
import com.cfd.bms.exception.ResourceNotFoundException;
import com.cfd.bms.model.Theater;
import com.cfd.bms.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;

	public TheaterDto createTheater(TheaterDto theaterDto){
		Theater theater = mapToEntity(theaterDto);
		Theater savedTheater = theaterRepository.save(theater);
		return mapToDto(savedTheater);
	}

	public TheaterDto getTheaterById(Long id){
		Theater theater = theaterRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Theater not found with id  "+ id));

		return mapToDto(theater);

	}

	private TheaterDto mapToDto(Theater theater) {
		TheaterDto theaterDto = new TheaterDto();
		theaterDto.setId(theater.getId());
		theaterDto.setName(theater.getName());
		theaterDto.setCity(theater.getCity());
		theaterDto.setAddress(theater.getAddress());
		theaterDto.setTotalScreens(theater.getTotalScreens());
		theaterDto.setTotalScreens(theater.getTotalScreens());
		return theaterDto;
	}

	private Theater mapToEntity(TheaterDto theaterDto) {
		Theater theater = new Theater();
		theater.setId(theaterDto.getId());
		theater.setAddress(theaterDto.getAddress());
		theater.setCity(theaterDto.getCity());
		theater.setTotalScreens(theaterDto.getTotalScreens());
		return theater;
	}
}
