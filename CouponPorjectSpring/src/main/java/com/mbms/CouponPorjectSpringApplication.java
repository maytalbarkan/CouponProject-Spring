package com.mbms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.mbms.login.Session;

@SpringBootApplication
@ComponentScan({ "com.mbms" })
public class CouponPorjectSpringApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(CouponPorjectSpringApplication.class, args);
		System.out.println("Starting SPRING maytal&maaytan project !!!!!!");

//		applicationContext.close();
	}

	
	@Bean
	public Map <String, Session>tokens(){
		return new HashMap<String,Session>();
	}
	}
	
	


