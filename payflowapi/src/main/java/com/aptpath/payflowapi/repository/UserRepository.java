package com.aptpath.payflowapi.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aptpath.payflowapi.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    boolean existsByRole(String role); // For checking if an ADMIN exists
    boolean existsByUsername(String username);  // This enables existsByUsername()
    boolean existsByEmail(String email);  
}

