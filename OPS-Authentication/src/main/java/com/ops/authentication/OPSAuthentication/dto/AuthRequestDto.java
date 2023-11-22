package com.ops.authentication.OPSAuthentication.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRequestDto {
    private String msisdn;
    private String appId;

}