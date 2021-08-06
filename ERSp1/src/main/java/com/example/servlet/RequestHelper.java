package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dbtouching.DAO;
import com.example.dbtouching.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestHelper extends HttpServlet {
	protected ControllerLayer cl = new ControllerLayer();// we need to call control layer
	protected DAO dao = new DAO();
	protected String additionalScripts = "<link rel=\"stylesheet\"\r\n"// additional bootstrap scripts for better
			// styling
			+ "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\r\n"
			+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\r\n"
			+ "	crossorigin=\"anonymous\">\r\n" + "<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\r\n"
			+ "	integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\r\n"
			+ "	crossorigin=\"anonymous\"></script>\r\n" + "<script\r\n"
			+ "	src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"\r\n"
			+ "	integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\"\r\n"
			+ "	crossorigin=\"anonymous\"></script>\r\n" + "<script\r\n"
			+ "	src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"\r\n"
			+ "	integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\"\r\n"
			+ "	crossorigin=\"anonymous\"></script>";
	String additionalScripts2 = "<link rel=\"stylesheet\"\r\n" // additional bootstrap scripts for better
			// styling
			+ "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\r\n"
			+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\r\n"
			+ "	crossorigin=\"anonymous\">\r\n" + "<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\r\n"
			+ "	integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\r\n"
			+ "	crossorigin=\"anonymous\"></script>\r\n" + "<script\r\n"
			+ "	src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"\r\n"
			+ "	integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\"\r\n"
			+ "	crossorigin=\"anonymous\"></script>\r\n" + "<script\r\n"
			+ "	src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"\r\n"
			+ "	integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\"\r\n"
			+ "	crossorigin=\"anonymous\"></script>";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		System.out.println("inside requestHelper");
		String controllerDefinition = req.getRequestURI();
		// set response for redirect--
		PrintWriter out = res.getWriter();
		// ----
		String rtrnURL = "/ERSp1/resources/MainPage.html";
		switch (controllerDefinition) {
		case "/ERSp1/resources/here.ms": // here ='s sign in form 'action'
			
			session.setAttribute("id", req.getParameter("Eid"));
			System.out.println("my ID "+req.getParameter("Eid"));
			String pageChange = "exists";
			pageChange = cl.signIn(req); // passing in 'humanName' and 'password'
			if (pageChange.equals("updated")) {
				// update rest of user info..
				String additionalScripts = "<link rel=\"stylesheet\"\r\n"// additional bootstrap scripts for better
																			// styling
						+ "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\r\n"
						+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\r\n"
						+ "	crossorigin=\"anonymous\">\r\n"
						+ "<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\r\n"
						+ "	integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\r\n"
						+ "	crossorigin=\"anonymous\"></script>\r\n" + "<script\r\n"
						+ "	src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"\r\n"
						+ "	integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\"\r\n"
						+ "	crossorigin=\"anonymous\"></script>\r\n" + "<script\r\n"
						+ "	src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"\r\n"
						+ "	integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\"\r\n"
						+ "	crossorigin=\"anonymous\"></script>";
				out.println("<html><head>" + additionalScripts
						+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\'results1\'>"
						+ " <br><br> <h1> Create Password </h1> <form  method='Post' id='myform' action='createPassword.ms' style='margin-left: 200px;'> <br> <input type='text' name='Password1'\r\n"
						+ "										placeholder='Create Password' />  <br> <input type='text' name='Password2'\r\n"
						+ "										placeholder='Confirm Password' />  <input type=\"submit\" value='Submit' class=\"btn btn-secondary btn-sm\"\r\n"
						+ "							id=\"EmptyForm2\" >" + "</input> </form></div> </body></html>");
			} else {
				anyRedirection(res, "http://localhost:9007/ERSp1/resources/index.html");
			}

			break;
		case "/ERSp1/resources/createPassword.ms":
				//fnny
			String entry1 = req.getParameter("Password1");
			String entry2 = req.getParameter("Password2");
			String attemptCredEntry = cl.ConfirmPasswordCheck(entry1, entry2, session);
			if (attemptCredEntry.equals("failure")) {
				String additionalScripts = "<link rel=\"stylesheet\"\r\n"// additional bootstrap scripts for better
																			// styling
						+ "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\r\n"
						+ "	integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\"\r\n"
						+ "	crossorigin=\"anonymous\">\r\n"
						+ "<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\r\n"
						+ "	integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\r\n"
						+ "	crossorigin=\"anonymous\"></script>\r\n" + "<script\r\n"
						+ "	src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"\r\n"
						+ "	integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\"\r\n"
						+ "	crossorigin=\"anonymous\"></script>\r\n" + "<script\r\n"
						+ "	src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"\r\n"
						+ "	integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\"\r\n"
						+ "	crossorigin=\"anonymous\"></script>";
				out.println("<html><head>" + additionalScripts
						+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\'results1\'>"
						+ " <br><br> <h1> Create Password  </h1><br> <h4> *Enter matching credentials- </h4> <form  method='Post' id='myform' action='createPassword.ms' style='margin-left: 200px;'> <br> <input type='password' name='Password1'\r\n"
						+ "										placeholder='Create Password' />  <br> <input type='password' name='Password2'\r\n"
						+ "										placeholder='Confirm Password' />  <input type=\"submit\" value='Submit' class=\"btn btn-secondary btn-sm\"\r\n"
						+ "							id=\"EmptyForm2\" >" + "</input> </form></div> </body></html>");
			} else {
				// change page back upon success-
				anyRedirection(res, "http://localhost:9007/ERSp1/resources/index.html");
			}

			break;
		case "/ERSp1/resources/sendTicket.ms":

			String type = req.getParameter("expenseType");
			String exp = req.getParameter("Expense");
			String desc = req.getParameter("desc");
			String nameID = req.getParameter("nameID");
//			System.out.println("ticket: " + type + ", " + exp + ", " + desc);
//			System.out.println("user: " + session.getAttribute("id") + ", " + session.getAttribute("Password"));
//			System.out.println("the user :  " +nameID); //--test successful
			String passFail = cl.TestTicketParameters(type, exp, desc, nameID);
			if (passFail.equalsIgnoreCase("short")) {
				out.println("<html><head>" + additionalScripts
						+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3 style=\"color: white;\"> **Not enough form data was entered"
						+ " </h3><br><br> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
						+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/EmployeeConsole.html'\">"
						+ " RETURN</button></div> </body></html>");
				System.out.println("not enough info");
			} else if (passFail.equalsIgnoreCase("neg")) {
				out.println("<html><head>" + additionalScripts
						+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3 style=\"color: white;\"> **No negative values please."
						+ " </h3><br><br> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
						+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/EmployeeConsole.html'\">"
						+ " RETURN</button></div> </body></html>");
				System.out.println("too low of a value");
			} else if (passFail.equalsIgnoreCase("NaN")) {
				out.println("<html><head>" + additionalScripts
						+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3 style=\"color: white;\"> **Value must be at least $1.00"
						+ " </h3><br><br> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
						+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/EmployeeConsole.html'\">"
						+ "RETURN </button></div> </body></html>");
				System.out.println("number needs to be entered in the expense box");
			} else if (passFail.equalsIgnoreCase("true")) {
				System.out.println("ticket creation successful");
				out.println("<html><head>" + additionalScripts
						+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3 style=\"color: white;\"> Ticket has been successfully Submitted"
						+ "<br> </h3><br><br><br/> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
						+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/EmployeeConsole.html'\">"
						+ "RETURN </button></div> </body></html>");
			}

			break;
		case "/ERSp1/resources/SignInEmployee.ms":
			//////// HERE

			if (cl.employeeSignIn(req).equals("success")) {
				System.out.println("user logged in successfullly... but we ain't got no page right now.. ");
				// --first create session --
				String username = req.getParameter("signName");
				String password = req.getParameter("passwordSign");
				session.setAttribute("id", username);
				session.setAttribute("Password", password);
				ServiceLayer sl = new ServiceLayer();
				sl.createEmpSession(username, password, session);

				// --second
				anyRedirection(res, "http://localhost:9007/ERSp1/resources/EmployeeConsole.html");
			} else {
				System.out.println("login of employee was unsuccessful");
				anyRedirection(res, "http://localhost:9007/ERSp1/resources/index.html");
			}

			break;
		case "/ERSp1/resources/grabSesh.ms":
			//// HERE session
			session.getAttribute("first_name");
			res.getWriter().write(new ObjectMapper().writeValueAsString(session.getAttribute("first_name") + " "
					+ session.getAttribute("last_name") + " (" + session.getAttribute("userid") + ")"));
			break;
		case "/ERSp1/resources/myTickestSee.ms":
			// no data to manipulate.. sending array of objects straight to js
			// employeeconsole
			ArrayList al = dao.retrieveUserTickets();
			res.getWriter().write(new ObjectMapper().writeValueAsString(al));
			// ^^ returns a list of ALL tickets currently in database to be manipulated
			// through js
			break;
		case "/ERSp1/resources/mngmnt.ms":

			String direct = cl.AdminSignin(session, req);
			anyRedirection(res, direct);
			break;
		case "/ERSp1/resources/viewAllTickets.ms":
			dao.viewAllTickets(req, res);

			break;
		case "/ERSp1/resources/Approve.ms":
			String ticketNo = req.getParameter("newOS");
			String denyApprove = req.getParameter("Botton");
			
			if(denyApprove.equalsIgnoreCase("Approve")) {
			String result = dao.ApproveThisTicket(ticketNo);
			if(result.equals("failure")) {
				anyRedirection(res, "http://localhost:9007/ERSp1/resources/SimpleManagement.html");
			}else {
				out.println("<html><head>" + additionalScripts2
						+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3 style=\"margin-right:30px; \"> <b>"
						+ "Ticket, "+ result+" </h3><br><br> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
						+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/SimpleManagement.html'\">"
						+  "RETURN</button></div> </body></html>");
			}}else if(denyApprove.equalsIgnoreCase("Deny")){
			String successful=	dao.denyThisTicket(ticketNo); 
			if(successful.equals("failure")) {
				anyRedirection(res, "http://localhost:9007/ERSp1/resources/SimpleManagement.html");
			}else {
				out.println("<html><head>" + additionalScripts2
						+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3 style=\"margin-right:30px; \"> <b>"
						+ "Ticket, "+ successful+" </h3><br><br> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
						+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/SimpleManagement.html'\">"
						+  "RETURN</button></div> </body></html>");}
			}
			break;
		case "/ERSp1/resources/selfAssign.ms":
			System.out.println("made it");
			System.out.println(req.getParameter("newOS"));
			System.out.println(req.getParameter("Deny"));
			System.out.println(req.getParameter("Approve"));
			break;
		case "/ERSp1/resources/myWork.ms":
			String loggedUser = session.getAttribute("userid").toString();
			dao.grabStuff(res, loggedUser);
			break;
		case "/ERSp1/resources/logout.ms":
			session.invalidate();

			anyRedirection(res, "http://localhost:9007/ERSp1/resources/index.html");
			break;
		case "/ERSp1/resources/DeleteUserEntry.ms":
			System.out.println("reachedTHIS");
			break;
		case "/ERSp1/resources/updateTicket.ms":
			String red1 = "<html><head>" + additionalScripts
					+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3 style=\"color: white;\">Updated Resolver and Amount for this ticket      -<b>"
					+ " </b></h3><br><br> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
					+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/AdminConsole.html'\">"
					+ "RETURN</button></div> </body></html>";
			String red2 = "<html><head>" + additionalScripts
					+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3 style=\"color: white;\">Updated Resolver for this ticket   -<b>"
					+ " </b></h3><br><br> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
					+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/AdminConsole.html'\">"
					+ "RETURN</button></div> </body></html>";
			String red3 = "<html><head>" + additionalScripts
					+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3 style=\"color: white;\">Updated amount for this ticket   -<b>"
					+ " </b></h3><br><br> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
					+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/AdminConsole.html'\">"
					+ "RETURN</button></div> </body></html>";	
			
			String redirect = dao.updateTicket(req, res);
			if (redirect.equalsIgnoreCase("both")) {
				out.println(red1);
			} else if (redirect.equalsIgnoreCase("resolver")) {
				out.println(red2);
			} else if (redirect.equalsIgnoreCase("amount")) {
				out.println(red3);
			}
//beginning
			break;
		case "/ERSp1/resources/returnResolvers.ms":
			dao.returnResolvers(req, res);
			// resolver list returned to page thorugh here..
			break;
		case "/ERSp1/resources/pullEmailList.ms":
			System.out.println("pulling email list");
			// DAO dao = new DAO(); //not super secure but... on a time budget..
			dao.pullUnLoggedUsernamePasswords(req, res);
			// javier
			break;
		case "/ERSp1/resources/adminLogout.ms":
			System.out.println("logout admin");
			session.invalidate();
			anyRedirection(res, "http://localhost:9007/ERSp1/resources/Management.html");
			break;
		case "/ERSp1/resources/updateNewUser.ms":

			anyRedirection(res, cl.usernamePassword(req));
			break;
		case "/ERSp1/resources/home.ms":
			anyRedirection(res, "http://localhost:9007/ERSp1/resources/MainPage.html");
			break;
		case "/ERSp1/resources/redirectRegister.ms":
			anyRedirection(res, "http://localhost:9007/ERSp1/resources/index.html");// customer login--
			break;
		case "/ERSp1/resources/searchByTicket.ms":
			cl.AdminConsoleByNum(req.getParameter("Number"), req, res);

			break;
		case "/ERSp1/resources/createOverseer.ms":
			String AddDOrNot = cl.addOverseer(req); // returns failed or success
			// PrintWriter out = res.getWriter();
			String btnCnt = "";
			if (AddDOrNot.equalsIgnoreCase("failed")) {
				AddDOrNot = "This employee either does not exist or already exists with the role of <i>(Financial) Manager</i>";
				btnCnt = "Ok";
			} else if (AddDOrNot.equalsIgnoreCase("Success")) {
				AddDOrNot = "Successfully promoted!";
				btnCnt = "Return";
			}
			System.out.println("----generate overseer related prompt ------ ");
			out.println("<html><head>" + additionalScripts
					+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3> <b>"
					+ AddDOrNot + " </b></h3><br><br> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
					+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/AdminConsole.html'\">"
					+ btnCnt + "</button></div> </body></html>");
			break;
		case "/ERSp1/resources/enterEmployee.ms":
			String AddDOrNot2 = cl.addEmployee(req); // returns failed or success
			// PrintWriter out2 = res.getWriter();
			String btnCnt2 = "";
			if (AddDOrNot2.equals("failed")) {
				AddDOrNot2 = "Employee could not be created (press 'ok' and fill out refill the form)";
				btnCnt2 = "Ok";
			} else if (AddDOrNot2.equals("Success")) {
				AddDOrNot2 = "Employee Successfully entered!";
				btnCnt2 = "Return";
			}
			System.out.println("----generate employee related prompt ------ ");

			out.println("<html><head>" + additionalScripts2
					+ "<link rel='stylesheet' type='text/css' href='css/tempStyle.css'><title>PromotionPrompt</title></head><body> <div id=\"results\"><h3 style=\"margin-right:30px; \"> <b>"
					+ AddDOrNot2 + " </b></h3><br><br> <button type=\"button\" class=\"btn btn-secondary btn-sm\"\r\n"
					+ "							id=\"EmptyForm2\" onclick=\"document.location.href='/ERSp1/resources/AdminConsole.html'\">"
					+ btnCnt2 + "</button></div> </body></html>");
			break;
		default:
//			return "/ERSp1/resources/MainPage.html";
		}
	}

	public void anyRedirection(HttpServletResponse response, String Direct) {
		response.setContentType("text/html");
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", Direct);
		// direct == "/yourProjectName/resources/nextPage.html":
//		res.setHeader("Location", "AdminConsole.html");
	}
}
