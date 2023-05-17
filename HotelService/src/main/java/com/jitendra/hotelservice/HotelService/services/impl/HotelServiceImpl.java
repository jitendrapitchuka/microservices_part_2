package com.jitendra.hotelservice.HotelService.services.impl;

import com.jitendra.hotelservice.HotelService.entity.Hotel;
import com.jitendra.hotelservice.HotelService.exceptions.ResourceNotFoundException;
import com.jitendra.hotelservice.HotelService.repo.HotelRepo;
import com.jitendra.hotelservice.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    @Override
    public Hotel Create(Hotel hotel) {
        String hotelId=UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel id not found"));
    }
}
