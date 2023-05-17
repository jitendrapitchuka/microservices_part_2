package com.jitendra.user.service.UserService.repo;

import com.jitendra.user.service.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {

}
