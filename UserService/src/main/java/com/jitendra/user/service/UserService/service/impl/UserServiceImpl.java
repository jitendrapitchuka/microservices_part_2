package com.jitendra.user.service.UserService.service.impl;

import com.jitendra.user.service.UserService.entity.Hotel;
import com.jitendra.user.service.UserService.entity.Rating;
import com.jitendra.user.service.UserService.entity.User;
import com.jitendra.user.service.UserService.exception.ResourceNotFoundException;
import com.jitendra.user.service.UserService.externals.services.HotelService;
import com.jitendra.user.service.UserService.repo.UserRepo;
import com.jitendra.user.service.UserService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public User saveUser(User user) {
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {

        User user= userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id not found"));

        Rating[] ratingsOfUser = restTemplate
                .getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);
        logger.info(ratingsOfUser.toString());

        List<Rating>ratings=Arrays.stream(ratingsOfUser).toList();

       List<Rating>ratingList = ratings.stream().map(rating -> {
//            Hotel hotel=restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
        Hotel hotel=hotelService.getHotel(rating.getHotelId());

        rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }


}
