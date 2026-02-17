package com.example.expene_tracker.controller;

import com.example.expene_tracker.dto.RequestDto;
import com.example.expene_tracker.dto.ResponseDto;
import com.example.expene_tracker.service.UserService;
import jakarta.validation.Valid;
import com.example.expene_tracker.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseDto register(@Valid @RequestBody RequestDto requestDto) {

        return userService.register(requestDto);
    }

    @PostMapping("/login")
    public ResponseDto login(@Valid @RequestBody RequestDto requestDto) {
        return userService.login(requestDto);

    }
// THIS IS UNFINISHED WORK FOR REGISTRATION
}
