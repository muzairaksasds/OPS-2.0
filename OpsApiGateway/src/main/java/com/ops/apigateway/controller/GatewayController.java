package com.ops.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

//    @GetMapping("/user")
//    public ResponseEntity<String> handleProtectedEndpoint(
//            @RequestHeader(name = "Authorization") String authorizationHeader) {
//
//        // Check if the Authorization header is prese nt and formatted correctly
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            // Extract the token from the Authorization header
//            String jwtToken = authorizationHeader.substring(7);
//
//            // Now you have the JWT token, you can pass it to the Auth Service or perform further processing
//            // For simplicity, just returning the token in this example
//            return ResponseEntity.ok("Received JWT Token: " + jwtToken);
//        } else {
//            // Authorization header is missing or not formatted correctly
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
//        }
//    }
}
