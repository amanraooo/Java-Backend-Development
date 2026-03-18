package com.cfd.bms.service;

import com.cfd.bms.dto.TheaterDto;
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
