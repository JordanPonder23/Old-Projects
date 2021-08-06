package com.example.dbtouching;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class ServiceLayer {
	protected DAO dao = new DAO();

	public String passAdminManager(String username, String password, HttpSession sesh) {
		String credvalue = dao.queryRole(username, password, sesh);

		return credvalue;
	}

	public String pass(ArrayList Object) {
		String updateOr = "exists"; // default is failure
//		System.out.println("service layer--- ");
//		System.out.println("username: " + Object.get(0));
//		System.out.println("eID: " + Object.get(1));

		String user = dao.loginUser(Object.get(0), Object.get(1));
		if (user.equals("null")) {
			System.out.println("update null ");
			// dao call to update..
			System.out.println("RUNNING AN UPDATE --");
			dao.updateUserStuff(Object.get(0).toString(), Object.get(1).toString());
			updateOr = "updated";
		} else {
			System.out.println("username exists already");
		}
		return updateOr;// passes back either 'null' or 'updated'
	}

	public String rtrnOverseer(ArrayList OverseerCriteria) {
		String assumeFailure = "failure";
		System.out.println(" checking list: " + OverseerCriteria.get(3));
		if (OverseerCriteria.get(3) == "") {
//			System.out.println(" sid not present: " +OverseerCriteria.get(3));// -test-
			assumeFailure = dao.promote(OverseerCriteria.get(0).toString(), OverseerCriteria.get(1).toString(),
					OverseerCriteria.get(2).toString());
		} else if (OverseerCriteria.get(3) != null) {
//			System.out.println(" STID: " +OverseerCriteria.get(3));// -test-
			assumeFailure = dao.promote(OverseerCriteria.get(3).toString());

		} else {
			System.out.println("something else checklist");
		}

		return assumeFailure;
	}

	public void PassId(String iD) {
		// HttpSession ses
		dao.DeleteEmployee(iD);

	}

	public void FixParams(String[] valueArray) {
		StringBuilder idNameStrip = new StringBuilder(valueArray[3]);
		int Paraneth = idNameStrip.indexOf("(");
		String subName = idNameStrip.substring(0, Paraneth);
		System.out.println("sl------------------");
		System.out.println("subname is : " + subName);
		String subId = idNameStrip.substring(Paraneth + 1, valueArray[3].length() - 1);
		System.out.println("sub ID: " + subId);
		dao.ticketGenerate(valueArray[0], valueArray[1], valueArray[2], subName, subId);
	}

	public void createEmpSession(String username, String password, HttpSession sesh) {
		// we only need to query for the rest of the information then add it to our
		// session
		UsersToInstantiateThemselves user = dao.setUserSession(username, password);// returns user info for setting
																					// session attributes, for later
																					// consumption -
		// session--changes--v--v
		sesh.setAttribute("first_name", user.getFirstName());
		sesh.setAttribute("last_name", user.getLastName());
		sesh.setAttribute("userid", user.getStormTrooperID());
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=");
		System.out.println("the session object now holds...");
		System.out.println("firstname: " + sesh.getAttribute("first_name"));
		System.out.println("lastName: " + sesh.getAttribute("last_name"));
	}

	public String userlogin(String username, String password) {
		System.out.println("------------service layer");
		System.out.println("passing: " + username + "   " + password);
		System.out.println("------------service layer");
		String dflt = "failure";

		if (dao.EmployeeLogin(username, password).equals("success")) {
			dflt = "success";
		}
		System.out.println("service layer is passing back, " + dflt);
		return dflt;
	}

	public String rtrnEmployee(ArrayList EmployeeCriteria) {
		String assumeFailure = "failure";
		if (EmployeeCriteria.get(0) != "" & EmployeeCriteria.get(1) != "" & EmployeeCriteria.get(2) != "") {// the rest
																											// should,
																											// at least,
																											// be full
			assumeFailure = dao.enterEmployee(EmployeeCriteria.get(0).toString(), EmployeeCriteria.get(1).toString(),
					EmployeeCriteria.get(2).toString());
		}

		return assumeFailure;
	}

	public String UpdateUsername(String id, String username, String password) {
		System.out.println("in service layer");
		String successfail = "";

		if (dao.updateNewEmployee(id, username, password).equals("success")) {
			successfail = "success";
		} else {
			successfail = "fail";
		}

		return successfail;
	}

	public ArrayList<String> returnRowByTicket(String id) {

		ArrayList<String> returnedRow = new ArrayList<String>();
		returnedRow = dao.QueryTicketByNumber(id);

		return returnedRow;
	}
//	public ArrayList<String> returnTicketsByEmpID(String empID){
//		this will return a filtered query of multiple tickets -- a little past mvp right now.. 
//		return ;
//	}

}
