package com.ops.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableZuulProxy
public class OpsZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpsZuulApplication.class, args);
	}

}
