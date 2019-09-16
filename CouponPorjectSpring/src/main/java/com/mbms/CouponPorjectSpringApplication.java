package com.mbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.mbms" })
public class CouponPorjectSpringApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(CouponPorjectSpringApplication.class, args);
		System.out.println("Starting SPRING maytal&maaytan project !!!!!!");

//		applicationContext.close();

	}
	
	}


