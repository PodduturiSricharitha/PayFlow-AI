package com.aptpath.payflowapi.controller;

import com.aptpath.payflowapi.service.AuthService;
import com.aptpath.payflowapi.service.EmployeeService;
import com.aptpath.payflowapi.dto.AuthResponse;
import com.aptpath.payflowapi.dto.EmployeeDTO;
import com.aptpath.payflowapi.dto.LoginDTO;
import com.aptpath.payflowapi.dto.ManagerDTO;
import com.aptpath.payflowapi.dto.ResetPasswordDTO;
import com.aptpath.payflowapi.entity.Employee;
import com.aptpath.payflowapi.entity.User;
import com.aptpath.payflowapi.repository.UserRepository;
import com.aptpath.payflowapi.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/admin/register")
    public ResponseEntity<String> register(@Valid @RequestBody AdminDTO adminDTO) {
        userService.registerAdmin(adminDTO);
        return ResponseEntity.ok("Admin registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponse message = userService.login(loginDTO);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<String> create(@Valid @RequestBody ManagerDTO managerDTO) {
        userService.createManager(managerDTO);
        return ResponseEntity.ok("User created successfully");
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO dto) {
    	String message = userService.resetPassword(dto);
        return ResponseEntity.ok(message);
    }
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO dto) {
        Employee employee = employeeService.createEmployee(dto);
        return ResponseEntity.ok(employee);
    }
}
