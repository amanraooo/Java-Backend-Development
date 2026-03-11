package com.cfd.bms.service;

import com.cfd.bms.dto.BookingDto;
import com.cfd.bms.dto.BookingRequestDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	public static BookingDto createBooking(@Valid BookingRequestDto bookingRequest) {
	}
}
