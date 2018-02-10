package com.javatpoint;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/amish")
public class HelloController {

	@RequestMapping("/hello/{country}/{username}")
	public ModelAndView hello(@PathVariable Map<String,String> pathVar){
		
		String country = pathVar.get("country");
		String name = pathVar.get("username");
		
		String message="Hello "+name +"! Welcome to " + country + " Spring MVC application";
		return new ModelAndView("hellopage","message",message);
	}
	
	@RequestMapping("/welcome")
	public ModelAndView welcome(){
		String message="Welcome to Spring MVC application";
		
		ModelAndView model = new ModelAndView("hellopage");
		model.addObject("message",message);
		return model;
}

}