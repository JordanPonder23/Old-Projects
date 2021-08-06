package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.FoodRepo;
import com.example.model.Food;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping(value="/justanything")
public class FoodController {
	@Autowired
	private FoodRepo foodRepo;
	
	public FoodController() {
	}
	
	@GetMapping(value="/getAllFood.app")
	//@RequestMapping(value="/getAllFood.app", method=RequestMethod.GET)
	public @ResponseBody List<Food> getAllFoods() {
		return foodRepo.selectAll();
	}
	
	//makes sure the parameter names and the "params" attributes have the same
	//	spelling
	@RequestMapping(value="/getFoodById.app", method=RequestMethod.POST,
			produces="application/json", params= {"id"})
	public ResponseEntity<Food> getFoodById( int id) {
		return new ResponseEntity<Food>(foodRepo.selectById(id),
				HttpStatus.I_AM_A_TEAPOT);
	}
	
	//easier than the method above (it achieves the same results)
	/*@PostMapping(value="/getFoodById.app")
	public @ResponseBody Food getFoodById(@RequestParam("id") int num) {
		return foodRepo.selectById(num);
	}*/
	
	@PostMapping(value="{num}/getFoodByUri.app")
	public @ResponseBody Food getFoodByUri(@PathVariable("num") int num) {
		return foodRepo.selectById(num);
	}
}
