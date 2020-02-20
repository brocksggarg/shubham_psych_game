package com.psych.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

}



@RestController
class Hello{
	String msg="";
	
	@RequestMapping("/hello")
	public String hello() {
		return msg;
	}
	@RequestMapping("/add/{msg}")
	public String addMsg(String m) {
		msg=m;
		return "added";
	}
}
