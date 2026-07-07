package org.example.taskmanagement.controller;

import org.example.taskmanagement.dto.LoginRequestDTO;
import org.example.taskmanagement.dto.RegisterRequestDTO;
import org.example.taskmanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequestDTO request
    ) {

        return ResponseEntity.ok(
                userService.login(request)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO request){
        userService.register(request);
        return ResponseEntity.ok("User Registered Successfully");
    }
}