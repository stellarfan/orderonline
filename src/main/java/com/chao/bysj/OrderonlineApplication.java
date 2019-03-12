package com.chao.bysj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class OrderonlineApplication {
	@RequestMapping(value = {"","/index","/user/index"})
	public String index(){
		return "index";
	}

	@RequestMapping(value = "/about")
	public String about(){
		return "about";
	}

	@RequestMapping(value = "/help")
	public String help(){
		return "help";
	}

	@RequestMapping(value = "/contact")
	public String contact(){
		return "contact";
	}

	@RequestMapping(value = "/icons")
	public String icons(){
		return "icons";
	}

	@RequestMapping(value = "/codes")
	public String codes(){
		return "codes";
	}

	@RequestMapping(value = "/offers")
	public String offers(){
		return "offers";
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderonlineApplication.class, args);
	}
}
