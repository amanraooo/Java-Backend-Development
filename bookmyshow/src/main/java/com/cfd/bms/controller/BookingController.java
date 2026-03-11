package com.cfd.bms.controller;

import com.cfd.bms.dto.BookingDto;
import com.cfd.bms.dto.BookingRequestDto;
import com.cfd.bms.repository.BookingRepository;
import com.cfd.bms.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingRequestDto bookingRequest){
		return  new ResponseEntity<>(BookingService.createBooking(bookingRequest), HttpStatus.CREATED);
	}
}
