package com.ezinne.Bookingapp.repositories;

import com.ezinne.Bookingapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
