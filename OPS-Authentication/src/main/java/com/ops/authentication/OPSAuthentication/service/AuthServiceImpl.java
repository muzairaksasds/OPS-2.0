package com.ops.authentication.OPSAuthentication.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.authentication.OPSAuthentication.Repository.AuthRequestRepository;
import com.ops.authentication.OPSAuthentication.dto.AuthRequestDto;
import com.ops.authentication.OPSAuthentication.dto.AuthResponseDto;
import com.ops.authentication.OPSAuthentication.model.AuthRequest;
import com.ops.authentication.OPSAuthentication.model.UserResponse;
import com.ops.authentication.OPSAuthentication.model.UserRequest;
import com.ops.authentication.OPSAuthentication.utility.ApiKeyGenerator;
import com.ops.authentication.OPSAuthentication.utility.PasswordGenerator;
import com.ops.authentication.OPSAuthentication.utility.SignatureGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRequestRepository authRequestRepository;

    @Override
    public AuthResponseDto generateSecretCodeAndJwt(AuthRequestDto authRequestDto) {
        // Validate and process the request
        validateRequest(authRequestDto);


        authRequestRepository.save(new ObjectMapper().convertValue(authRequestDto, AuthRequest.class));
        System.out.println("Hi");

        // Generate secret code using MD5 hash
        String secretCode = generateSecretCode(authRequestDto);

        // Generate JWT
        String jwt = generateJwt(authRequestDto, secretCode);
        System.out.println(jwt);

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

    public String generateRandomUUID(String prefix,int maxLength) {

        // Append a prefix and a UUID to create a random username
       String fullUUID = prefix + UUID.randomUUID().toString().replace("-", "");
        return fullUUID.substring(0, Math.min(fullUUID.length(), maxLength));

    }

    @Override
    public UserResponse createUser(UserRequest userRequest) throws NoSuchAlgorithmException {
//        String userType;
//        String username;       // uuid
//        String userpassword;   //random
//        String apikey;  //random   similar to apikey
//        String apisecret;    //msdin + random number
//        String msisdn;
//        String qrcode;     //  empty
//        String signature ;
        UserResponse user = new UserResponse();
        user.setUserType("Merchant");
        user.setUsername(generateRandomUUID("", 8));
        user.setUserpassword(PasswordGenerator.generateRandomPassword(10));
        user.setApikey(ApiKeyGenerator.generateRandomApiKey());
        user.setApisecret(generateRandomUUID( SignatureGenerator.generateMD5Hash(userRequest.getMsisdn()), 8));
        user.setMsisdn(userRequest.getMsisdn());
        user.setQrcode("");

        String signature = SignatureGenerator.generateSignature(user.getUserType(), user.getUsername(),
                user.getUserpassword(), user.getApikey(), user.getApisecret(), userRequest.getMsisdn(), user.getQrcode());
        user.setSignature(signature);
        //System.out.println(signature);
        return user;

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
