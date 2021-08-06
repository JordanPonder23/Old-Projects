package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("weDontNeedANameForOurBeanIfWeDontWant")
public class ViewController {
	@RequestMapping(value="/next.app", method=RequestMethod.POST)
	public String getPage() {
		//return "nextpage.html"; //this is to forward
		//return "redirect:/nextpage.html"; //this is to redirect
		//return "redirect:https://www.google.com/";
		return "age";
	}
}
