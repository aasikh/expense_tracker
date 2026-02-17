package com.example.expene_tracker.service;
import com.example.expene_tracker.dto.RequestDto;
import com.example.expene_tracker.dto.ResponseDto;
import com.example.expene_tracker.entity.User;
import com.example.expene_tracker.repository.UserRepositroy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepositroy userRepositroy;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepositroy userRepositroy,  BCryptPasswordEncoder passwordEncoder) {
       this.passwordEncoder = passwordEncoder;
        this.userRepositroy = userRepositroy;
    }

    public ResponseDto register(RequestDto requestDto) {


User user = new User();
user.setName(requestDto.getName());
user.setEmail(requestDto.getEmail());
user.setPassword(requestDto.getPassword());
user.setPassword(passwordEncoder.encode(user.getPassword()));
User savedUser = userRepositroy.save(user);

ResponseDto response = new ResponseDto();
response.setId(savedUser.getId());
response.setName(savedUser.getName());
response.setEmail(savedUser.getEmail());

return  response;

    }
    public ResponseDto login(RequestDto requestDto) {
        Optional<User> userBox = userRepositroy.findByEmail(requestDto.getEmail());
        if (userBox.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userBox.get();
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Passwords don't match");
        }
        ResponseDto response = new ResponseDto();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

      return response;
    }

}
