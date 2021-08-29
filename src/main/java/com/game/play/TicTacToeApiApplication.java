package com.game.play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TicTacToeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicTacToeApiApplication.class, args);
	}

}
