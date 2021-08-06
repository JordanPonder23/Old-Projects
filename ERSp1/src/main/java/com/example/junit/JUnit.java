package com.example.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.dbtouching.DAO;
import com.example.dbtouching.TicketWriter;

public class JUnit {
	@Test
	public void testGetEmployeeById() {

		DAO dao = new DAO();
	
		assertEquals("null" ,dao.loginUser(80, "trevin"));		
	}
}
