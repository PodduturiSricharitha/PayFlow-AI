package com.aptpath.payflowapi.controller;

import com.aptpath.payflowapi.service.AuthService;
import com.aptpath.payflowapi.dto.AuthResponse;
import com.aptpath.payflowapi.dto.LoginDTO;
import com.aptpath.payflowapi.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthService adminService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AdminDTO adminDTO) {
        adminService.registerAdmin(adminDTO);
        return ResponseEntity.ok("Admin registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponse message = adminService.loginAdmin(loginDTO);
        return ResponseEntity.ok(message);
    }
}
