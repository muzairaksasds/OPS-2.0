package com.ops.merchant.controller;

import com.ops.merchant.model.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @GetMapping("/info")
    public String getInfo() {
        System.out.println("I am merchant get request");
        return "Merchant Service - Version 1.0";
    }

    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order) {
        // Handle order processing logic
        System.out.println(order.toString());
        return "Order placed successfully";
    }
}
