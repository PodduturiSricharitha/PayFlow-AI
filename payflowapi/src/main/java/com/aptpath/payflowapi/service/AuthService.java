package com.aptpath.payflowapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aptpath.payflowapi.entity.User;
import com.aptpath.payflowapi.repository.UserRepository;
import com.aptpath.payflowapi.util.JwtUtil;

import jakarta.validation.Valid;

import com.aptpath.payflowapi.dto.AdminDTO;
import com.aptpath.payflowapi.dto.AuthResponse;
import com.aptpath.payflowapi.dto.LoginDTO;
import com.aptpath.payflowapi.dto.RegisterRequest;
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;

    public void registerAdmin(AdminDTO adminDTO) {
        if (userRepository.existsByUsername(adminDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        if (userRepository.existsByEmail(adminDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
        if (userRepository.existsByContactNumber(adminDTO.getContactNumber())) {
        	throw new ResponseStatusException(HttpStatus.CONFLICT, "Contact number already in use");
        }

        User user = new User();
        user.setUsername(adminDTO.getUsername());
        user.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        user.setEmail(adminDTO.getEmail());
        user.setRole("ADMIN");
        user.setContactNumber(adminDTO.getContactNumber());

        userRepository.save(user);
    }

    public AuthResponse loginAdmin(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        } 
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        System.out.println("Token "+ token);

        return new AuthResponse(token);
    }
   
}