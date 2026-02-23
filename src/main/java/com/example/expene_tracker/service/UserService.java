package com.example.expene_tracker.service;
import com.example.expene_tracker.dto.RequestDto;
import com.example.expene_tracker.dto.ResponseDto;
import com.example.expene_tracker.entity.User;
import com.example.expene_tracker.repository.UserRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.expene_tracker.util.JwtUtil;

import java.util.Optional;


@Service
public class UserService {
    private final JwtUtil jwtUtil;

    private final UserRepositroy userRepositroy;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepositroy userRepositroy,  BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
       this.passwordEncoder = passwordEncoder;
        this.userRepositroy = userRepositroy;
        this.jwtUtil = jwtUtil;
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
     // this step check the email in db
        Optional<User> userBox = userRepositroy.findByEmail(requestDto.getEmail());
        // if email is not exists in db return this
        if (userBox.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        // this step is for if email is exists in db and then extract and store a seprate varible where we can easily extract info
        User user = userBox.get();
        //  now we check the request send password and db store pasword is not matches return invalid credential
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Passwords don't match");
        }
        //
        ResponseDto response = new ResponseDto();
        String token = jwtUtil.genrateToken(user);
        response.setMessage("You are Successfully Login");
        response.setId(user.getId());
        response.setToken(token);

      return response;
    }

}
