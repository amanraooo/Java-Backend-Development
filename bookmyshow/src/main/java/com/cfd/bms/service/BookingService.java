package com.cfd.bms.service;

import com.cfd.bms.dto.*;
import com.cfd.bms.exception.ResourceNotFoundException;
import com.cfd.bms.exception.SeatUnavailableException;
import com.cfd.bms.repository.BookingRepository;
import com.cfd.bms.repository.ShowRepository;
import com.cfd.bms.repository.ShowSeatRepository;
import com.cfd.bms.repository.UserRepository;
import com.cfd.bms.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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

	@Transactional
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
					seat.setStatus("BOOKED");
					seat.setBooking(saveBooking);
				}
				);
		showSeatRepository.saveAll(selectedSeats);
		return mapToBookingDto(saveBooking,selectedSeats);
	}

	private BookingDto mapToBookingDto(Booking booking, List<ShowSeat> seats)
	{
		BookingDto bookingDto = new BookingDto();
		bookingDto.setId(booking.getId());
		bookingDto.setBookingNumber(booking.getBookingNumber());
		bookingDto.setBookingTime(booking.getBookingTime());
		bookingDto.setStatus(booking.getStatus());
		bookingDto.setTotalAmount(booking.getTotalAmount());

		// User
		UserDto userDto = new UserDto();
		userDto.setId(booking.getUser().getId());
		userDto.setName(booking.getUser().getName());
		userDto.setEmail(booking.getUser().getEmail());
		userDto.setPhoneNumber(booking.getUser().getPhoneNumber());
		bookingDto.setUser(userDto);

		// Show
		ShowDto showDto = new ShowDto();
		showDto.setId(booking.getShow().getId());
		showDto.setStartTime(booking.getShow().getStartTime());
		showDto.setEndTime(booking.getShow().getEndTime());

		// Movie
		MovieDto movieDto = new MovieDto();
		movieDto.setId(booking.getShow().getMovie().getId());
		movieDto.setTitle(booking.getShow().getMovie().getTitle());
		movieDto.setDescription(booking.getShow().getMovie().getDescription());
		movieDto.setLanguage(booking.getShow().getMovie().getLanguage());
		movieDto.setGenre(booking.getShow().getMovie().getGenre());
		movieDto.setDurationMins(booking.getShow().getMovie().getDurationMins());
		movieDto.setReleaseDate(booking.getShow().getMovie().getReleaseDate());
		movieDto.setPosterUrl(booking.getShow().getMovie().getPosterUrl());
		showDto.setMovie(movieDto);

		// Screen
		ScreenDto screenDto = new ScreenDto();
		screenDto.setId(booking.getShow().getScreen().getId());
		screenDto.setName(booking.getShow().getScreen().getName());
		screenDto.setTotalSeats(booking.getShow().getScreen().getTotalSeats());

		// Theater
		TheaterDto theaterDto = new TheaterDto();
		theaterDto.setId(booking.getShow().getScreen().getTheater().getId());
		theaterDto.setName(booking.getShow().getScreen().getTheater().getName());
		theaterDto.setAddress(booking.getShow().getScreen().getTheater().getAddress());
		theaterDto.setCity(booking.getShow().getScreen().getTheater().getCity());
		theaterDto.setTotalScreens(booking.getShow().getScreen().getTheater().getTotalScreens());

		screenDto.setTheater(theaterDto);
		showDto.setScreen(screenDto);
		bookingDto.setShow(showDto);

		// Seats
		List<ShowSeatDto> seatDtos = seats.stream()
				.map(seat -> {
					ShowSeatDto seatDto = new ShowSeatDto();
					seatDto.setId(seat.getId());
					seatDto.setStatus(seat.getStatus());
					seatDto.setPrice(seat.getPrice());

					SeatDto baseSeatDto = new SeatDto();
					baseSeatDto.setId(seat.getSeat().getId());
					baseSeatDto.setSeatNumber(seat.getSeat().getSeatNumber());
					baseSeatDto.setSeatType(seat.getSeat().getSeatType());
					baseSeatDto.setBasePrice(seat.getSeat().getBasePrice());

					seatDto.setSeat(baseSeatDto);
					return seatDto;
				})
				.collect(Collectors.toList());

		bookingDto.setSeats(seatDtos);

		// Payment
		if (booking.getPayment() != null)
		{
			PaymentDto paymentDto = new PaymentDto();
			paymentDto.setId(booking.getPayment().getId());
			paymentDto.setAmount(booking.getPayment().getAmount());
			paymentDto.setPaymentMethod(booking.getPayment().getPaymentMethod());
			paymentDto.setPaymentTime(booking.getPayment().getPaymentTime());
			paymentDto.setStatus(booking.getPayment().getStatus());
			paymentDto.setTransactionId(booking.getPayment().getTransactionId());

			bookingDto.setPayment(paymentDto);
		}

		return bookingDto;
	}
}
