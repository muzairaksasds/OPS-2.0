package com.ops.authentication.OPSAuthentication.service;

import com.ops.authentication.OPSAuthentication.dto.AuthRequestDto;
import com.ops.authentication.OPSAuthentication.dto.AuthResponseDto;
import com.ops.authentication.OPSAuthentication.model.response.UserResponse;
import com.ops.authentication.OPSAuthentication.model.request.UserRequest;

import java.security.NoSuchAlgorithmException;


public interface AuthService {
    AuthResponseDto generateSecretCodeAndJwt(AuthRequestDto authRequest);
    boolean validateJwt(String token);

    UserResponse createUser(UserRequest userRequest) throws NoSuchAlgorithmException;
}
