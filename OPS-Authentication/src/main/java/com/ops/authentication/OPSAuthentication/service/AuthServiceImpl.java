package com.ops.authentication.OPSAuthentication.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.authentication.OPSAuthentication.Repository.AuthRequestRepository;
import com.ops.authentication.OPSAuthentication.dto.AuthRequestDto;
import com.ops.authentication.OPSAuthentication.dto.AuthResponseDto;
import com.ops.authentication.OPSAuthentication.model.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRequestRepository authRequestRepository;

    @Override
    public AuthResponseDto generateSecretCodeAndJwt(AuthRequestDto authRequestDto) {
        // Validate and process the request
        validateRequest(authRequestDto);


        authRequestRepository.save(new ObjectMapper().convertValue(authRequestDto, AuthRequest.class));

        // Generate secret code using MD5 hash
        String secretCode = generateSecretCode(authRequestDto);

        // Generate JWT
        String jwt = generateJwt(authRequestDto, secretCode);

        return new AuthResponseDto(jwt);
    }

    //jwt validation
    @Value("${jwt.secret}") // Load the secret key from application.properties or application.yml
    private String secretKey;

    public boolean validateJwt(String token) {
        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes()) // Use the same secret key for decoding
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(token);
            System.out.println(secretKey);


            System.out.println("Decoded MSISDN: " + claims.get("msisdn"));
            System.out.println("Decoded AppID: " + claims.get("appid"));
            System.out.println("Decoded Secret Code: " + claims.get("secretCode"));
            // Additional validation logic if needed
            // For example, you can check claims.getExpiration() for expiration time

            return true;
        } catch (Exception e) {
            // Token validation failed
            e.printStackTrace(); // Log the exception or handle it as needed
            return false;
        }
    }


    private void validateRequest(AuthRequestDto authRequest) {
        // Add your validation logic here
        // For example, check if MSISDN and APPID are not empty
    }

    private String generateSecretCode(AuthRequestDto authRequest) {
        // Combine MSISDN and APPID and generate MD5 hash
        String dataToHash = authRequest.getMsisdn() + authRequest.getAppId();
        return DigestUtils.md5DigestAsHex(dataToHash.getBytes());
    }

    private String generateJwt(AuthRequestDto authRequest, String secretCode) {

        // Convert the secret key string to bytes
        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

        Date expiration = new Date(System.currentTimeMillis() + 3600000); // 1 hour
        return Jwts.builder()
                .setSubject("JWT Token")
                .claim("msisdn", authRequest.getMsisdn())
                .claim("appid", authRequest.getAppId())
                .claim("secretCode", secretCode)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKeyBytes)
                .compact();
    }

}
