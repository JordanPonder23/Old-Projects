package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dbtouching.ServiceLayer;

public class MasterServlet extends HttpServlet{
	 public MasterServlet() {
	        super();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String controllerDefinition = request.getRequestURI();
			System.out.println("get uri: " +controllerDefinition);
			StringBuilder checkRes = new StringBuilder(controllerDefinition); 
			String ResInq = checkRes.substring(10, controllerDefinition.length());
			System.out.println("here "+ ResInq);
			if(ResInq.equals("Restore")) {
				System.out.println("worked");
			}		
			//response.getWriter().append("Served at: ").append(request.getContextPath());
			StringBuilder dissectUser = new StringBuilder(controllerDefinition); 
			int shortIndex = 17; //the user is at least 17 characters deep '/ERSp1/resources/HERE'
			char uno ='k';//initialized as anything
			String iD ="";
			do {
				
			uno = dissectUser.charAt(shortIndex);
			if(uno =='D')
				break;
			shortIndex++; 
			iD = iD+uno;
			}while(uno != 'D');
			System.out.println(iD);
			String extractedUri = dissectUser.replace(17, shortIndex-1, "").toString();
			//System.out.println("extracted: " + extractedUri); //-test-
			//I actually don't even know why I extracted right now but I might need it later.. 
			ServiceLayer sl = new ServiceLayer(); 
//			HttpSession session = request.getSession();
			sl.PassId(iD );
			//----User Recovery--			
			
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			//---has to be done before control layer
			String controllerDefinition = request.getRequestURI();
			System.out.println("get uri: " +controllerDefinition);
			StringBuilder checkRes = new StringBuilder(controllerDefinition); 
			String ResInq = checkRes.substring(10, controllerDefinition.length());
			System.out.println("here "+ ResInq);
			if(ResInq.equals("Restore")) {
				System.out.println("worked");
			}	
			//---------
			RequestHelper rh = new RequestHelper(); 
			rh.doPost(request, response);
		}
}
