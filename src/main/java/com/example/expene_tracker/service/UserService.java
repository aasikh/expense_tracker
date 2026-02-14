package com.example.expene_tracker.service;
import com.example.expene_tracker.entity.User;
import com.example.expene_tracker.repository.UserRepositroy;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepositroy userRepositroy;

    public UserService(UserRepositroy userRepositroy) {
        this.userRepositroy = userRepositroy;
    }

    public User register(User user) {

        if (userRepositroy.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email " + user.getEmail() + " is already taken!");
        }

        return userRepositroy.save(user);
    }
}
