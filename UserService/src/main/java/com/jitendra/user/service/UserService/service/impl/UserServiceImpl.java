package com.jitendra.user.service.UserService.service.impl;

import com.jitendra.user.service.UserService.entity.User;
import com.jitendra.user.service.UserService.exception.ResourceNotFoundException;
import com.jitendra.user.service.UserService.repo.UserRepo;
import com.jitendra.user.service.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
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
        return userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id not found"));
    }


}
