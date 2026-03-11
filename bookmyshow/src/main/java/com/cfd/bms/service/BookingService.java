package com.cfd.bms.service;

import com.cfd.bms.dto.BookingDto;
import com.cfd.bms.dto.BookingRequestDto;
import com.cfd.bms.exception.ResourceNotFoundException;
import com.cfd.bms.repository.ShowRepository;
import com.cfd.bms.repository.UserRepository;
import com.cfd.bms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShowRepository showRepository;

	public  BookingDto createBooking(BookingRequestDto bookingRequest) {
		User user = userRepository.findById(bookingRequest.getUserId())
				.orElseThrow(()-> new ResourceNotFoundException("User Not Found"));

		Show show = showRepository.findById(bookingRequest.getShowId())
				.orElseThrow(()-> new ResourceNotFoundException(("Show not found")));

	}
}
