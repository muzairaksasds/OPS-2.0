package com.ops.authentication.OPSAuthentication.controller;

// AuthController.java
import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.authentication.OPSAuthentication.dto.AuthRequestDto;
import com.ops.authentication.OPSAuthentication.dto.AuthResponseDto;
import com.ops.authentication.OPSAuthentication.model.User;
import com.ops.authentication.OPSAuthentication.service.AuthService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public User registerUser(@RequestBody String msisdn){
        return authService.createUser(msisdn);
    }

    @PostMapping("/generate")
    public AuthResponseDto generateSecretCodeAndJwt(@RequestBody AuthRequestDto authRequest) {
        String authRequestDtoJson = convertObjectToJson(authRequest);

        logger.info("Received request to generate secret code and JWT: {}", authRequestDtoJson);

        AuthResponseDto authResponseDto = authService.generateSecretCodeAndJwt(authRequest);
        String authResponseDtoJson = convertObjectToJson(authResponseDto);
        // Log the response
        logger.info("Generated response: {}", authResponseDtoJson);
        return authResponseDto;
    }
    private static String convertObjectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // Handle the exception appropriately based on your application's needs
            e.printStackTrace();
            return null;
        }
    }

    //this resource must be accessed if we have valid jwt token
   @GetMapping("/user")
    public ResponseEntity<String> getUserName(@RequestHeader("Authorization") String authorizationHeader) {

       logger.info("Received request to get user name with Authorization header: {}", authorizationHeader);

       // Check if the Authorization header is present and formatted correctly
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract the token from the Authorization header
            String token = authorizationHeader.substring(7);

            // Validate the JWT token
            if (authService.validateJwt(token)) {
                // Token is valid, you can proceed with the protected logic
                return ResponseEntity.ok("Access granted to secured endpoint");
            } else {
                // Token is invalid
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Jwt Token not present");
    }
}
