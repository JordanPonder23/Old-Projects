package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Food;
import com.example.repo.FoodDao;

@Controller("OurController")
public class FoodController {
	
	@Autowired
	private FoodDao foodDao;
	
	@GetMapping("/food.app")
	public @ResponseBody Food findFood() {
		return foodDao.findByCalories(278.0);
		//return foodDao.findByDishName("Filet Mignon");
	}
	
	@GetMapping("/allfood.app")
	public @ResponseBody List<Food> findAllFood() {
		//return (List<Food>) foodDao.findAll();
		return foodDao.findByOrderByDishName();
	}
}
