package com.ops.authentication.OPSAuthentication.service;

import com.ops.authentication.OPSAuthentication.dto.AuthRequestDto;
import com.ops.authentication.OPSAuthentication.dto.AuthResponseDto;
import com.ops.authentication.OPSAuthentication.model.User;
import jakarta.servlet.http.HttpServletRequest;


public interface AuthService {
    AuthResponseDto generateSecretCodeAndJwt(AuthRequestDto authRequest);
    boolean validateJwt(String token);

    User createUser(String msisdn);
}
