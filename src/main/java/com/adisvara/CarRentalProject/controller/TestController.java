package com.adisvara.CarRentalProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/public")
    public ResponseEntity<String> testPublicPost() {
        System.out.println("Public POST request received!");
        return ResponseEntity.ok("Public POST successful");
    }
    
    @GetMapping("/public")
    public ResponseEntity<String> testPublicGet() {
        System.out.println("Public GET request received!");
        return ResponseEntity.ok("Public GET successful");
    }
    
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> userAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("User authenticated: " + auth.getName());
        return ResponseEntity.ok("User Content.");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Admin authenticated: " + auth.getName());
        return ResponseEntity.ok("Admin Content.");
    }
}
