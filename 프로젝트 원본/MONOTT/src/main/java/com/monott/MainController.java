package com.monott;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping("/")
	public String thymeleaf() {
		return "home";
	}
	
}
