package com.spring.mvc.home;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping
	public String index(Map<String, Object> model){
		System.out.println("haizz");
		model.put("message", this.message);
		return "index";
	}
}
