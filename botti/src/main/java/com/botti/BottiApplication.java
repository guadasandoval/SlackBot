package com.botti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BottiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BottiApplication.class, args);
	}

}
