package com.zoft.solutions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableTransactionManagement
public class ZoftsolutionApplication {


	public static void main(String[] args) {
		SpringApplication.run(ZoftsolutionApplication.class, args);
	}

}
