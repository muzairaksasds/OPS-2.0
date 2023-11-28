package com.ops.authentication.OPSAuthentication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String userType;
    String username;       // uuid
    String userpassword;   //random
    String apikey;  //random   similar to apikey
    String apisecret;    //msdin + random number
    String msisdn;
    String qrcode;     //  empty
    String signature ;

}
