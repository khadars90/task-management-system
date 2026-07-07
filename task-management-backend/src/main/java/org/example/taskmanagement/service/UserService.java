package org.example.taskmanagement.service;

import org.example.taskmanagement.dto.ChangePasswordDTO;
import org.example.taskmanagement.dto.LoginRequestDTO;
import org.example.taskmanagement.dto.ProfileResponseDTO;
import org.example.taskmanagement.dto.RegisterRequestDTO;
import org.example.taskmanagement.entity.User;
import org.example.taskmanagement.repository.UserRepository;
import org.example.taskmanagement.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User saveUser(
            User user
    ) {
    user.setPassword(
            passwordEncoder.encode(
            user.getPassword()
            )
            );
    //System.out.println(passwordEncoder.encode("123456"));

        return userRepository.save(user);
    }


    public String login(
            LoginRequestDTO request
    ) {

        User user = userRepository.findByEmail(
                request.getEmail()
        ).orElseThrow(
                () -> new RuntimeException(
                        "User not found"
                )
        );

        boolean isValid =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!isValid) {
            throw new RuntimeException(
                    "Invalid password"
            );
        }

        return jwtService.generateToken(
                user.getEmail()
        );
    }

    public void register(RegisterRequestDTO request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public ProfileResponseDTO getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return new ProfileResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public void changePassword(
            String email,
            ChangePasswordDTO request
    ) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword()
        )) {

            throw new RuntimeException("Current password is incorrect");
        }

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );

        userRepository.save(user);
    }


}