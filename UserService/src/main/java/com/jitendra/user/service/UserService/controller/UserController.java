package com.jitendra.user.service.UserService.controller;

import com.jitendra.user.service.UserService.entity.User;
import com.jitendra.user.service.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private  Logger logger= LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
//    int retryCount=1;
    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
//    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String  userId){
        User user1=userService.getUser(userId);
//        logger.info("Retry count {}",retryCount);
//        retryCount++;
        return  ResponseEntity.ok(user1);
    }


    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
      //  logger.info("Fall back is executed because service is down",ex.getMessage());

        User user=User.builder().email("dummy@gmail.com").about("This is dummy email bcuse other services down")
                .name("dummy").userId("12345").build();
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUser();
        return  ResponseEntity.ok(users);
    }
}
