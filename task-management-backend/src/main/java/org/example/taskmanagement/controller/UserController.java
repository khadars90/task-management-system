package org.example.taskmanagement.controller;

import org.example.taskmanagement.dto.ChangePasswordDTO;
import org.springframework.security.core.Authentication;
import org.example.taskmanagement.dto.ProfileResponseDTO;
import org.example.taskmanagement.entity.User;
import org.example.taskmanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

//    @PostMapping
//    public ResponseEntity<User> createUser(
//            @RequestBody User user
//    ) {
//
//        User savedUser =
//                userService.saveUser(user);
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(savedUser);
//    }

    // GET PROFILE
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDTO> getProfile(
            Authentication authentication
    ) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                userService.getProfile(email)
        );
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(

            Authentication authentication,

            @RequestBody ChangePasswordDTO request

    ) {

        String email = authentication.getName();

        userService.changePassword(email, request);

        return ResponseEntity.ok("Password Updated Successfully");

    }
}