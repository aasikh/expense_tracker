package com.example.expene_tracker.service;
import com.example.expene_tracker.dto.RequestDto;
import com.example.expene_tracker.dto.ResponseDto;
import com.example.expene_tracker.entity.User;
import com.example.expene_tracker.repository.UserRepositroy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


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
}
