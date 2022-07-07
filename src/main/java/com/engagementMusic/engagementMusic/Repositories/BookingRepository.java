package com.engagementMusic.engagementMusic.Repositories;

import com.engagementMusic.engagementMusic.Models.Agent;
import com.engagementMusic.engagementMusic.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
