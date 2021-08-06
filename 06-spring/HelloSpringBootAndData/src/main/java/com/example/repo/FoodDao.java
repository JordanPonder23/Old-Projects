package com.example.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Food;

public interface FoodDao extends CrudRepository<Food, Integer> {
	public Food findByDishName(String dishName);
	//public Food findByDishNameAndCalories
	public Food findByCalories(Double calories);
	public List<Food> findByOrderByDishName();
	public List<Food> findByOrderByDishNameDesc();
}
