package com.jitendra.hotelservice.HotelService.controller;

import com.jitendra.hotelservice.HotelService.entity.Hotel;
import com.jitendra.hotelservice.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody  Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.Create(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> GetHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId));
    }


    @GetMapping
    public ResponseEntity<List<Hotel>> GetAllHotel(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
    }


}
