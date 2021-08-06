package com.example.ajax;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.SuperVillain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AsterController {

	/*	This method is an example of a controller. 
	 *		It returns a JSON.
	 */
	public static void asterFinder(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		SuperVillain aster = new SuperVillain("Aster", "Fire breath", 100_000);
		response.getWriter().write(new ObjectMapper().writeValueAsString(aster));
	}
}
