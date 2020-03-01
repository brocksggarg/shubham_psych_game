package com.psych.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psych.game.model.C;
import com.psych.game.repositories.CRepository;

@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

}



@RestController
class Hello{
	String msg="";
	
	@Autowired
	CRepository cr;
	
	@RequestMapping("/hello")
	public String hello() {
		C c=new C();
		c.setId(12);
		c.setName("ss");
		c.setAlais("s");
		c.setEmail("sss");
		cr.save(c);
		return msg;
	}
	@RequestMapping("/add/{msg}")
	public String addMsg(@PathVariable String msg) {
		this.msg=msg;
		return "added";
	}
}
