package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("OurController")
public class OurController {
	
	@GetMapping("/tester.app")
	public @ResponseBody ResponseEntity<String> getStuff(){
		return new ResponseEntity<>("Stuff has been received!!!", HttpStatus.OK);
	}
}
