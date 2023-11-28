package com.ops.merchant.controller;

import org.springframework.web.bind.annotation.*;
import com.ops.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("/info")
    public String getInfo() {
        return merchantService.getInfo();
    }

}
