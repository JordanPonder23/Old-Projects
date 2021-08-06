package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dbtouching.DAO;
import com.example.dbtouching.ServiceLayer;

public class ControllerLayer extends HttpServlet {
	protected ServiceLayer sl = new ServiceLayer();
	protected ArrayList<String> ticket = new ArrayList<String>();

	protected String signIn(HttpServletRequest request) {
		String pageDirectionResult = "exists";
		String nameEntered = request.getParameter("humanName");
		String id = request.getParameter("Eid");

		System.out.println("Username:  " + nameEntered);
		System.out.println("EmpireID:  " + id);
		System.out.println("=====================");
		ArrayList aL = new ArrayList();
		aL.add(nameEntered);
		aL.add(id);

		pageDirectionResult = sl.pass(aL);
		// System.out.println("page should: "+ pageDirectionResult);// exists, updated
		return pageDirectionResult;
	}

	protected String TestTicketParameters(String... params) {
		String rtrn = "true";

		for (String n : params) {
			if (n == "")
				rtrn = "short";
		}
		int expCheck = 0;
		try {
			expCheck = Integer.parseInt(params[1]);
			if (expCheck < 1) {
				rtrn = "neg";
			}
		} catch (Exception e) {
			rtrn = "NaN";
		}
		if (rtrn.equals("true")) {

			sl.FixParams(params);
		}
		return rtrn;
	}

	protected String ConfirmPasswordCheck(String p1, String p2, HttpSession session ) {
		String rtrn = "failure";
		if (p1.equals(p2)) {
			DAO dao = new DAO(); 
			
			dao.updatePassword(p1, session.getAttribute("id").toString());
			
			
			rtrn = "success";
		} else {

		}

		return rtrn;
	}

	protected String AdminSignin(HttpSession sesh, HttpServletRequest request) {
		String AdminUsername = request.getParameter("Username");
		String AdPassword = request.getParameter("Password");
		// getParameter

		System.out.println("--Controller layer--");
		System.out.println("Username: " + AdminUsername);
		System.out.println("AdPassword: " + AdPassword);

		ArrayList aL = new ArrayList();
		aL.add(AdminUsername);// 0
		aL.add(AdPassword);// 1

		// sl.pass(aL);
		String direction = "/ERSp1/resources/MainPage.html";
		String queried = sl.passAdminManager(AdminUsername, AdPassword, sesh);
		// ------
		if (queried.equalsIgnoreCase("Admin")) {
			System.out.println("Admin signed in ");
			direction = "/ERSp1/resources/AdminConsole.html";

		} else if (queried.equalsIgnoreCase("Employee")) {
			System.out.println("Employees cannot sign in here..  ");
		} else if (queried.equalsIgnoreCase("Manager")) {

			System.out.println("Finanace Manager/ overseer signed in ");
			direction = "/ERSp1/resources/SimpleManagement.html";
			// HttpSession session = request.getSession();
			// session.setAttribute("villain", "LoggedIn");
			// System.out.println("Session: "+ session);
		} else if (queried.equals("noRole")) {
			System.out.println("Something wrong happened ");
			direction = "/ERSp1/resources/MainPage.html";// needs to be replaced
			
		}
		return direction;
	}

	protected String addOverseer(HttpServletRequest request) {
		String rtrn = "failed";// initiate as failed.
		String firstName = request.getParameter("FirstName");
//		System.out.println(firstName); //-test-
		String lastName = request.getParameter("LastName");
//		System.out.println(lastName); //-test-
		String email = request.getParameter("Email");
//		System.out.println(email); //-test-
		String STID = request.getParameter("STID");
//		System.out.println(STID); //-test-
		ArrayList OcCheck = new ArrayList();
		OcCheck.add(firstName);
		OcCheck.add(lastName);
		OcCheck.add(email);
		OcCheck.add(STID);
		if (sl.rtrnOverseer(OcCheck).equalsIgnoreCase("Success")) {
			rtrn = "Success";// change value of return for the Http response
			System.out.println("WHY DOES THIS NOT WORK: " +rtrn);
		}
		return rtrn;
	}

	protected String addEmployee(HttpServletRequest request) {
		String rtrn = "failed";// initiate as failed.
		String firstName = request.getParameter("FirstName");
		System.out.println(firstName);
		String lastName = request.getParameter("LastName");
		System.out.println(lastName);
		String email = request.getParameter("Email");
		System.out.println(email);
		ArrayList OcCheck = new ArrayList();
		OcCheck.add(firstName);
		OcCheck.add(lastName);
		OcCheck.add(email);
		if (sl.rtrnEmployee(OcCheck).equals("Success")) {
			rtrn = "Success";// change value of return for the Http response
		}

		return rtrn;
	}
	public void logoutAdmin() {
		//admin lg
	}
	public String employeeSignIn(HttpServletRequest req) {
		String dflt = "failure";
		System.out.println("in controller layer");
		String username = req.getParameter("signName");
		String password = req.getParameter("passwordSign");
		if (sl.userlogin(username, password).equals("success")) {
			dflt = "success";
		}
		System.out.println("controller layer is passing back, " + dflt);
		return dflt;
	}

	protected String usernamePassword(HttpServletRequest req) {
		String valid = "";
		System.out.println("in controller layer");
		String id = req.getParameter("StormTrooperID");
		String myUsername = req.getParameter("myUsername");
		String password = req.getParameter("myPassword");
		String cPass = req.getParameter("password2");
		if (password.equals(cPass) & password != "" & cPass != "" & myUsername != "" & id != "") {
			// sl
			if (sl.UpdateUsername(id, myUsername, password).equals("success")) {
				// success html
				System.out.println("successfully updated");
				valid = "http://localhost:9007/ERSp1/resources/";
			} else {
				// fail html
				System.out.println("failed to update");
			}

		} else {
			// passwords do not equal
			valid = "http://localhost:9007/ERSp1/resources/FinishRegister.html";
			// http://localhost:9007/ERSp1/resources/FinishRegister.html
		}

		return valid;
	}

	protected void AdminConsoleByNum(String id, HttpServletRequest req, HttpServletResponse res) {
		boolean skip = false; // default failures as usual- should be anybodies password this time..

		ticket = sl.returnRowByTicket(id);
		// ----ticket part of page printout
		String measureSpace = "";
		String space2 = "";
		String point2 = "-";
		String space1 = "";
		String space = "";
		String point = "-";
		try {
			measureSpace = ticket.get(3).toString() + ticket.get(4).toString();
			space1 = ticket.get(0);
			for (int k = space1.length(); k < 9; k++) {
				space2 = space2 + point2;
			}
			for (int i = 28; i > measureSpace.length(); i--) {
				space = space + point;
			}
		} catch (IndexOutOfBoundsException e) {
			skip = true;
		}
		if (skip == false) {
			String ticketPart = "<div id='TicketQuery'><a href='http://localhost:9007/ERSp1/resources/AdminConsole.html' style='float: right; margin-right: 20px; '>(x)</a>\r\n"
					+ "			<br>\r\n"
					+ "				==================================================================================\r\n"
					+ "	<br/>		=-Ticket#-=-Expense Amount-===-Submitted On-=====-Submitted By-========-Description-===-Status-==-Resolver-=\r\n"
					+ "				\r\n" + "<br> ------ " + id + " ----- " + ticket.get(0) + " ----" + space2
					+ "----- " + ticket.get(1) + " ---- " + ticket.get(3) + "(" + ticket.get(4) + ")" + " -" + space
					+ " <button onclick='showDesc()' id='desc1' style='position: absolute;'>Description</button> ---------------------"
					+ ticket.get(5) + " ------- " + ticket.get(7)
					+ "				________________________________________________________________________________________________________________________________________\r\n"
					+

					"<br>"
					+ "<div style='background: rgba(200, 54, 54, 0.5); z-index: 1000; padding: 10px; display:none;' id='description'>Expense Description: "
					+ ticket.get(2) + " </div>" + "			</div>";
			// --
			String pageOverlay = "<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>Empire ERS Administration Console</title>\r\n"
					+ "<script type=\"text/javascript\" src=\"javascripts/table.js\">\r\n" + "	\r\n" + "</script>\r\n"
					+ "<link rel=\"stylesheet type=\" text/css\" href=\"css/darthAdmin.css\">\r\n"
					+ "<link rel=\"stylesheet\"\r\n"
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
					+ "	crossorigin=\"anonymous\"></script>\r\n" + "<!-- 	my javascript file ---v-v--v -->\r\n"
					+ "<!-- <script type=\"text/javascript\" src=\"./javascripts/AdminConsoleJs.js\"></script> -->\r\n"
					+ "<!-- View Button -->\r\n" + "<!-- <script type=\"text/javascript\" -->\r\n"
					+ "<!-- 	src=\"./javascripts/ViewAllTicketsJavaScript.js\"></script> -->\r\n" + "</head>\r\n"
					+ "<div style='background: rgba(200, 54, 54, 0.5); z-index: 1000; '> \r\n"
					+ "<body style='background-image: linear-gradient(to right, rgba(0, 0, 0, .5), white, #800000, rgba(0, 0, 0, .78));'>\r\n"
					+ "	<div>\r\n" + "\r\n" + "		<div class='container-fluid' id='darthAdmin'\r\n"
					+ "			style='padding-left: 40px; margin-left: 20px;'>\r\n"
					+ "			Welcome, Darth Admin<br> <img src=\"css/friendlypalp.jpg\"\r\n"
					+ "				alt='Boss picture' id='boss'> <span id='quotepalp'> <i>\r\n"
					+ "					`` We love our employees who do not disapoint us! `` <span> <b>~\r\n"
					+ "							Palpatine</b></span>\r\n" + "			</i></span>\r\n"
					+ "		</div>\r\n" + "		\r\n" + ticketPart
					+ "		<div style='z-index: -1; position: relative; background: #808080; margin: 40px; border: 1.75px outset; border-color: #800000; border-radius: 90px;'>\r\n"
					+ "			<div class=\"container\"\r\n"
					+ "				style='background: #FFF8DC; padding: 20px; border: 2px inset; border-color: #808080; border-radius: 5px;'>\r\n"
					+ "				<div class='row'>\r\n" + "					<div class='col'>\r\n" + "\r\n"
					+ "						<form method='Post' action='searchByTicket.ms' id='loginFormAdmin'>\r\n"
					+ "							<p>Search Ticket by Number</p>\r\n"
					+ "							<input type='text' name='Number'\r\n"
					+ "								placeholder='Enter Ticket Number' /> <br>\r\n"
					+ "							<button type='submit' class='btn btn-primary btn-lg active'\r\n"
					+ "								id='buttonAdjustcolor'>Search</button>\r\n"
					+ "						</form>\r\n" + "						<br>\r\n"
					+ "					</div>\r\n" + "					<div class=\"col-6\">\r\n"
					+ "						<!-- 					<form method=\"Post\" action=\"viewAllTickets.ms\" id=\"loginFormAdmin\"> -->\r\n"
					+ "						<p>View Un-assigned Tickets</p>\r\n"
					+ "						<button type='submit' class='btn btn-primary btn-lg active'\r\n"
					+ "							id='buttonAdjustcolor1' aria-pressed='true'\r\n"
					+ "							onclick='revealTable()'>View</button>\r\n"
					+ "						<br>\r\n"
					+ "						<div class='container' id='ticketsDisplay'>\r\n"
					+ "							<div class='row'>\r\n"
					+ "								<div class='col-sm'>\r\n"
					+ "									<div class='col'>\r\n"
					+ "										<div>\r\n"
					+ "											<table>\r\n"
					+ "												<thead>\r\n"
					+ "													<tr>\r\n"
					+ "														<th class='ticketHed'>TicketID</th>\r\n"
					+ "														<th class='ticketHed'>Status</th>\r\n"
					+ "														<th class='ticketHed' id='extraInch'>Details</th>\r\n"
					+ "													</tr>\r\n"
					+ "												</thead>\r\n"
					+ "												<tbody id='AllTicketsView'>\r\n" + "\r\n"
					+ "												</tbody>\r\n"
					+ "											</table>\r\n"
					+ "										</div>\r\n" + "									</div>\r\n"
					+ "								</div>\r\n"
					+ "								<div class='col-sm'>\r\n"
					+ "									<div class='col'>\r\n"
					+ "										<div id='ticketDetails'\r\n"
					+ "											style=''display: none; background: silver; padding: 10px; width: 290px;'>\r\n"
					+ "\r\n" + "											<div id='ticketTitle'></div>\r\n"
					+ "											<div id='ticketSubmitted'></div>\r\n"
					+ "											<div>---------</div>\r\n"
					+ "											<div id='ticketOwnerAndAmount'></div>\r\n"
					+ "											<div>---------</div>\r\n"
					+ "											<div id='ResolverAndStatus'></div>\r\n"
					+ "											<button id='close' onclick='closeTicket()'>Close</button>\r\n"
					+ "											<button id='update' onclick='updateTicket()'>Update</button>\r\n"
					+ "											<form method='Post' action='updateTicket.ms' id='changeForm'\r\n"
					+ "												style='display: none;'>\r\n"
					+ "												<input type='text' name='hiddenTikID' id='TikID'> </input>\r\n"
					+ "												Change Reimbursement Amount: <input type='text'\r\n"
					+ "													name='expAmnt' placeholder='$0.00'> <br>\r\n"
					+ "												Assign a different Overseer: <input type='text' name='newOS'\r\n"
					+ "													placeholder='Overseer EmployeeID'> <input\r\n"
					+ "													type='submit' value='Submit'>\r\n"
					+ "											</form>\r\n" + "\r\n"
					+ "										</div>\r\n" + "									</div>\r\n"
					+ "								</div>\r\n"
					+ "								<div class='col-sm'>\r\n"
					+ "									<div class='col' id='resolversShow'>\r\n"
					+ "										<button onclick='populateResolvers()' id='resolversButton'\r\n"
					+ "											style='background: red; color: black; border-radius: 5px; font-family: impact;'>See\r\n"
					+ "											Available Resolvers</button>\r\n"
					+ "									</div>\r\n" + "								</div>\r\n"
					+ "							</div>\r\n" + "					\r\n"
					+ "						</div>\r\n" + "\r\n" + "					</div>\r\n"
					+ "					<div class='row'>\r\n" + "						<div class='col'>\r\n"
					+ "							<form method='Post' action='searchByTrooper.ms'\r\n"
					+ "								id='loginFormAdmin'>\r\n"
					+ "								<p>Search ticket by Stormtrooper</p>\r\n"
					+ "								<input type='text' name='Number' placeholder='Enter EmpID' /> <br>\r\n"
					+ "								<input type='submit' value='Search'\r\n"
					+ "									class='btn btn-primary btn-lg active' \r\n"
					+ "							role='button'\r\n"
					+ "									aria-pressed='true' id='buttonAdjustcolor2' />\r\n"
					+ "							</form>\r\n" + "						</div>\r\n"
					+ "						<div class='col-5'>(results)</div>\r\n"
					+ "						<div class='col'>(Resolvers)</div>\r\n" + "					</div>\r\n"
					+ "					<div class='row'>\r\n" + "						<div class='col'>\r\n"
					+ "							<form method='Post' action='searchByResolver.ms'\r\n"
					+ "								id='loginFormAdmin'>\r\n"
					+ "								<p>Search ticket by resolver</p>\r\n"
					+ "								<input type='text' name='Number'\r\n"
					+ "									placeholder='Enter the Overseer`s EmpID' /> <br> <input\r\n"
					+ "									type='submit' value='Search'\r\n"
					+ "									class='btn btn-primary btn-lg active' role='button'\r\n"
					+ "									aria-pressed='true' id='buttonAdjustcolor3' />\r\n"
					+ "							</form>\r\n" + "						</div>\r\n"
					+ "						<div class='col-5'>(results)</div>\r\n"
					+ "						<div class='col'>(Resolvers)</div>\r\n" + "					</div>\r\n"
					+ "				</div>\r\n" + "				<div class='container'\r\n"
					+ "					style='background: #FFF8DC; padding: 20px; border: 2px inset; border-color: #808080; border-radius: 5px;'>\r\n"
					+ "\r\n" + "\r\n" + "					<div class='container'>\r\n"
					+ "						<div class='row'>\r\n"
					+ "							<div class='col-sm'>\r\n"
					+ "								<button type='button' class='btn btn-primary btn-lg active'\r\n"
					+ "									id='OverseerCreate' onclick='ShowOverseerForm()'>Hire\r\n"
					+ "									New Overseer</button>\r\n"
					+ "								<button type='button' class='btn btn-secondary btn-sm'\r\n"
					+ "									id='EmptyForm' onclick='hideOverseerForm()''>Cancel\r\n"
					+ "									Promotion</button>\r\n"
					+ "								<div id='OverseerForm'>\r\n"
					+ "									<form method='Post' action='createOverseer.ms'\r\n"
					+ "										id='OverseerFormRl'>\r\n"
					+ "										<br>\r\n" + "										<p>\r\n"
					+ "											<b>Fill out the Below form hit hire to commit a new\r\n"
					+ "												Overseer to service!</b>\r\n"
					+ "										</p>\r\n" + "										<p>\r\n"
					+ "											<i> (~The Overseer chosen must already exist in our\r\n"
					+ "												system as an employee~) </i>\r\n"
					+ "										</p>\r\n"
					+ "										<input type='text' name='FirstName'\r\n"
					+ "											placeholder='Enter Overseer`s first name' /><br> <br>\r\n"
					+ "										<input type='text' name='LastName'\r\n"
					+ "											placeholder='Enter Overseer`s last name' /><br> <br>\r\n"
					+ "										<input type='text' name='Email'\r\n"
					+ "											placeholder='Enter Overseer`s email' /><br> <br>\r\n"
					+ "										<p>\r\n"
					+ "											<b> If you have the Storm Trooper ID, you need only enter\r\n"
					+ "												this value to hire them as a Financial Overseer</b>\r\n"
					+ "										</p>\r\n"
					+ "										<input type='text' name='STID' placeholder='Enter the `STID`' />\r\n"
					+ "										<br> <br> <input type='submit'\r\n"
					+ "											value='Promote Overseer '\r\n"
					+ "											class='btn btn-primary btn-lg active' id='buttonAdjustcolor'\r\n"
					+ "											role='button' aria-pressed='true' />\r\n"
					+ "									</form>\r\n" + "								</div>\r\n"
					+ "							</div>\r\n" + "							<div class='col-sm'>\r\n"
					+ "								<button type='button' class='btn btn-primary btn-lg active'\r\n"
					+ "									id='EmpCreate' onclick='showEmployeeForm()'>Manually\r\n"
					+ "									Enter Employee</button>\r\n"
					+ "								<div id='EmployeeForm'></div>\r\n" + "\r\n"
					+ "								<button type='button' class='btn btn-secondary btn-sm'\r\n"
					+ "									id='EmptyForm2' onclick='hideEmployeeForm()'>Cancel\r\n"
					+ "									Employee Entry</button>\r\n"
					+ "								<div id='EmployeeForm1'>\r\n"
					+ "									<form method='Post' action='enterEmployee.ms'\r\n"
					+ "										id='EmployeeFormID'>\r\n"
					+ "										<br>\r\n" + "										<p>\r\n"
					+ "											<b>New StormTroopers ( or other Empire employees) need to\r\n"
					+ "												entered into our Reimbursement system.</b>\r\n"
					+ "										</p>\r\n"
					+ "										<input type='text' name='FirstName'\r\n"
					+ "											placeholder='Enter Employee's first name' /><br> <br>\r\n"
					+ "										<input type='text' name='LastName'\r\n"
					+ "											placeholder='Enter Employee`s last name' /><br> <br>\r\n"
					+ "										<input type='text' name='Email'\r\n"
					+ "											placeholder='Enter Employee`s email' /><br> <br> <br>\r\n"
					+ "										<br> <input type='submit' value='Enter Employee'\r\n"
					+ "											class='btn btn-primary btn-lg active' id='buttonAdjustcolor2'\r\n"
					+ "											role='button' aria-pressed='true' />\r\n"
					+ "									</form>\r\n" + "								</div>\r\n"
					+ "\r\n" + "							</div>\r\n" + "						</div>\r\n"
					+ "					</div>\r\n" + "				</div>\r\n"
					+ "				<div class='container' id='Notice'\r\n"
					+ "					style='background: #FFF8DC; padding: 20px; border: 2px inset; border-color: #808080; border-radius: 5px;'>\r\n"
					+ "					<h2>Emails to send:</h2>\r\n" + "					<br>\r\n"
					+ "					<button type='button' class='btn btn-primary btn-lg active'\r\n"
					+ "						id='Reveal' onclick='UserList()'>Reveal Workload</button>\r\n"
					+ "					<br>\r\n" + "					<div id='LstUsers'>\r\n" + "\r\n" + "\r\n"
					+ "						<div class='row'>\r\n" + "							<div class='col'>\r\n"
					+ "								<ul id='addHere'>\r\n" + "								</ul>\r\n"
					+ "							</div>\r\n" + "							<div class='col'>\r\n"
					+ "								<ul id='addHere2'>\r\n" + "								</ul>\r\n"
					+ "							</div>\r\n" + "						</div>\r\n" + "\r\n"
					+ "					</div>\r\n" + "				</div>\r\n" + "			</div>\r\n"
					+ "		</div>\r\n" + "		</div>\r\n" + "</body>\r\n" + "</html>";

			try {
				PrintWriter out = res.getWriter();
				out.println(pageOverlay);
			} catch (IOException e) {

			}

		} else {
			res.setContentType("text/html");
			res.setStatus(res.SC_MOVED_TEMPORARILY);
			res.setHeader("Location", "http://localhost:9007/ERSp1/resources/AdminConsole.html");
		}
	}

}
