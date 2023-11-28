package com.ops.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = "com.ops.merchant")
public class OpsMerchantApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpsMerchantApplication.class, args);
	}

}
