package com.cfd.bms.service;

import com.cfd.bms.dto.BookingDto;
import com.cfd.bms.dto.BookingRequestDto;
import com.cfd.bms.exception.ResourceNotFoundException;
import com.cfd.bms.exception.SeatUnavailableException;
import com.cfd.bms.repository.BookingRepository;
import com.cfd.bms.repository.ShowRepository;
import com.cfd.bms.repository.ShowSeatRepository;
import com.cfd.bms.repository.UserRepository;
import com.cfd.bms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class BookingService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private ShowSeatRepository showSeatRepository;

	@Autowired
	private BookingRepository bookingRepository;

	public  BookingDto createBooking(BookingRequestDto bookingRequest) {
		User user = userRepository.findById(bookingRequest.getUserId())
				.orElseThrow(()-> new ResourceNotFoundException("User Not Found"));

		Show show = showRepository.findById(bookingRequest.getShowId())
				.orElseThrow(()-> new ResourceNotFoundException(("Show not found")));

		List<ShowSeat> selectedSeats = showSeatRepository.findAllById(bookingRequest.getSeatIds());

		for(ShowSeat seat: selectedSeats){
			if(!"AVAILABLE".equals(seat.getStatus())){
				throw new SeatUnavailableException("Seat "+seat.getSeat().getSeatNumber()+" is not Available");
			}

			//if even one seat is available
			seat.setStatus("LOCKED");
		}
		showSeatRepository.saveAll(selectedSeats);

		Double totalAmount = selectedSeats.stream()
				.mapToDouble(ShowSeat::getPrice)
				.sum();

		Payment payment = new Payment();
		payment.setAmount(totalAmount);
		payment.setPaymentTime(LocalDateTime.now());
		payment.setPaymentMethod(bookingRequest.getPaymentMethod());
		payment.setTransactionId(UUID.randomUUID().toString());


		Booking booking = new Booking();
		booking.setUser(user);
		booking.setShow(show);
		booking.setBookingTime(LocalDateTime.now());
		booking.setStatus("CONFIRMED");
		booking.setTotalAmount(totalAmount);
		booking.setBookingNumber(UUID.randomUUID().toString());
		booking.setPayment(payment);

		Booking saveBooking = bookingRepository.save(booking);

		selectedSeats.forEach(seat->
				{
					seat.getStatus("BOOKED");
					seat.setBooking(saveBooking);
				}
				);
		showSeatRepository.saveAll(selectedSeats);
	}
}
