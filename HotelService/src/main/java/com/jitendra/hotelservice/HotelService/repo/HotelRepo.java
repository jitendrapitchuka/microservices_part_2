package com.jitendra.hotelservice.HotelService.repo;

import com.jitendra.hotelservice.HotelService.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel,String> {
}
