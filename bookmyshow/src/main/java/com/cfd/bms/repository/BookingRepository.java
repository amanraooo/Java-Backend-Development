package com.cfd.bms.repository;

import com.cfd.bms.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingRepository, Long> {

	List<Booking> findByUserId(Long userId);

	Optional<Booking> findByBookingNumber(String bookingNumber);

	List<Booking> findByShowId(Long id);
}
