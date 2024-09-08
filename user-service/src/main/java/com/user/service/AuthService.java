package com.user.service;


import com.user.dto.Login;
import com.user.dto.LoginResponse;
import com.user.enums.Role;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

@Autowired
    private  AuthenticationManager authenticationManager;




    public LoginResponse login(Login loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            User user = userRepository.findByUsername(loginDto.getUsername());
            String token = jwtUtils.generateToken(user, user.getRole());

            return LoginResponse.builder()
                    .token(token)
                    .username(user.getUsername())
                    .role(user.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }

    public  void signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

}
