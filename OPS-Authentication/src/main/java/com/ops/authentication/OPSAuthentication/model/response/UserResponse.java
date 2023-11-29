package com.ops.authentication.OPSAuthentication.model.response;

import jakarta.persistence.*;
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
    //these will be uncommented later
   // String qrcode;     //  empty
    //String signature ;
    //@Transient
    //String userJwt;

}
