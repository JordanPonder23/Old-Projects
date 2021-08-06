package com.example.ajax;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.SuperVillain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DannyBoyController {

	/*	This method is an example of a controller. 
	 *		It returns a JSON.
	 */
	public static void dannyFinder(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException {
		SuperVillain danny = new SuperVillain("Danny Boy", "Technopath", 280_000);
		response.getWriter().write(new ObjectMapper().writeValueAsString(danny));
	}
}
