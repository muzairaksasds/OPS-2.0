package com.ops.authentication.OPSAuthentication.controller;

// AuthController.java
import com.ops.authentication.OPSAuthentication.dto.AuthRequestDto;
import com.ops.authentication.OPSAuthentication.dto.AuthResponseDto;
import com.ops.authentication.OPSAuthentication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/generate")
    public AuthResponseDto generateSecretCodeAndJwt(@RequestBody AuthRequestDto authRequest) {

        return authService.generateSecretCodeAndJwt(authRequest);
    }

    //this resource must be accessed if we have valid jwt token
   @GetMapping("/user")
    public ResponseEntity<String> getUserName(@RequestHeader("Authorization") String authorizationHeader) {
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
