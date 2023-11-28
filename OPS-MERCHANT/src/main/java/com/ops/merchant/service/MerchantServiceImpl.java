package com.ops.merchant.service;

import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Override
    public String getInfo() {
        System.out.println("I am merchant get request");
        return "Merchant Service - Version 1.0";
    }


}
