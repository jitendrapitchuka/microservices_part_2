package com.jitendra.hotelservice.HotelService.services;

import com.jitendra.hotelservice.HotelService.entity.Hotel;

import java.util.List;

public interface HotelService {
    public Hotel Create(Hotel hotel);
    public List<Hotel> getAll();

    public Hotel getHotel(String id);
}
