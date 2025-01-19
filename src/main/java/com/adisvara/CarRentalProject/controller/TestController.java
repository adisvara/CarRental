package com.adisvara.CarRentalProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/test")
    public ResponseEntity<String> testPost() {
        System.out.println("POST request received!");
        return ResponseEntity.ok("POST successful");
    }
    @GetMapping("/test")
    public ResponseEntity<String> testGet() {
        System.out.println("Get request received!");
        return ResponseEntity.ok("Get successful");
    }
}
