package com.ops.authentication.OPSAuthentication.service;

import com.ops.authentication.OPSAuthentication.dto.AuthRequestDto;
import com.ops.authentication.OPSAuthentication.dto.AuthResponseDto;


public interface AuthService {
    AuthResponseDto generateSecretCodeAndJwt(AuthRequestDto authRequest);

    boolean validateJwt(String token);
}
