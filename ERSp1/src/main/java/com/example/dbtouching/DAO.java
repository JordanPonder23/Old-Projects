package com.example.dbtouching;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.log.loggy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class DAO {
	private static String url = "jdbc:oracle:thin:@jponder23.cv1rmjxwr5fp.us-east-2.rds.amazonaws.com:1521:orcl";

	private static String username = "myusfdb";

	private static String password = "#3Marine33";
	static {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

	}

	// --==========================\
	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		DAO.url = url;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		DAO.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		DAO.password = password;
	}
	
	public String grabStuff(HttpServletResponse res, String thisUser) {
		String nm= "";
		System.out.println("this were requesting tickets for's id is "+ thisUser);
		ArrayList al = new ArrayList(); 
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String myTicketQuery ="select * from ersticket where resolverid =?";
			PreparedStatement pstmt = conn.prepareStatement(myTicketQuery);
			pstmt.setString(1, thisUser);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				TicketWriter tik = new TicketWriter(); 
				tik.setAmount(rs.getString("ramount"));
				tik.setDateSubmitted(rs.getString("submitted"));
				tik.setExpenseDescription((rs.getString("description")));
				tik.setSubmitterName(rs.getString("employee"));
				tik.setResolverID((rs.getString("resolverid")));
				tik.setTicketID(rs.getString("ticketid"));
				String setType = rs.getString("type");
				System.out.println("type "+ setType);
				if(setType ==null) {
					//skip
				}else {
				
				if(setType.equals("1")) {
					setType = "Psychological";
				}else if(setType.equals("2"))
					setType  = "Choke-Related";
				else if(setType.equals("3"))
					setType ="food/Shelter";
				else if(setType.equals("4"))
					setType = "Kinds of Equipment/ Misc";
				else if(setType.equals("5"))
					setType ="Incident..... shutup";
				
				tik.setType(setType);
				}
					
				al.add(tik);
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			res.getWriter().write(new ObjectMapper().writeValueAsString(al));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nm;
	}
	// --/
	public String loginUser(Object EID, Object username) {
	//	System.out.println("inside DAO---");
		//	System.out.println("passing inselect query, "+ username);
		String Username = "null";

		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String sql = "select * from anyUser where userid =?"; // query is correct..
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username.toString());	

			ResultSet rs = pstmt.executeQuery();
			System.out.println(" resultset returned for initial select "+rs);// test- but it will return some sort of empty result set even if the
									// credentials are incorrect
			while (rs.next()) {
				try {
				Username = rs.getString("Username").toString();
				loggy.logger.info("User logged in successfully ");// 				TLOG
				} catch(NullPointerException n) {
					loggy.logger.fatal("SQL Exception- did not return any usernames from DB");
				}		
			}
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.logger.fatal("Login Check Failure - failed to log user in (sql connection not successful)");
		}
		System.out.println("PASSED FROM FIRST SELECT STATEMENT: " +Username);
		return Username;
	}
	public void updatePassword(String updatePassword, String atUser) {
		System.out.println("inside updater");
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			System.out.println(atUser);
			System.out.println(updatePassword);
		String sql ="update anyuser set userpassword = ? where userid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, updatePassword);	
		pstmt.setString(2, atUser);	
		pstmt.executeQuery();
		loggy.logger.info("User updated..");// 				TLOG
		
		}catch (SQLException e) {
//			e.printStackTrace();
			
			loggy.logger.fatal("error in updating the password.. "); // 				TLOG
			
		}	
		
		
	}
	public String ApproveThisTicket(String tikNo) {
		String failed ="failed";
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String sql ="update ersticket set currentstatus = 'Approved' where ticketid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tikNo);	
			pstmt.executeQuery();
			failed=tikNo +" approved successfully!";
		}catch (SQLException e) {
//			e.printStackTrace();
			loggy.logger.fatal("There was an issue updating this ticket..");// 				TLOG
			
		}		
		return failed; 
	}
	public String denyThisTicket(String tikNo) {
		String dflt="failure";
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String sql ="update ersticket set currentstatus = 'Denied' where ticketid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tikNo);	
			pstmt.executeQuery();
			dflt=tikNo +" denied successfully!";
			loggy.logger.info("Update -- Ticket #"+tikNo+" denied successfully.. ");
		}catch (SQLException e) {
//			e.printStackTrace();
			loggy.logger.fatal(" Update Error -- Connection denied could deny.. ");// 				TLOG
			
		}			
		
		return  dflt;
	}
	
	protected void updateUserStuff(String username, String eid) {
		//System.out.println("update user------------DAO");
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
		//	System.out.println("EID: " + eid);
		//	System.out.println("username: " + username);
			String sql = "update anyuser set username = ? where userid =?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, username);
			pstmt1.setString(2, eid);
			pstmt1.executeQuery();
			loggy.logger.info("Update -- Updated user Username, of user #"+eid+", to "+username);
		} catch (SQLException e) {
			//e.printStackTrace();
			loggy.logger.fatal("Update Failure -- Failed to give a username and password to user..  ");// 				TLOG
		}
	}

	protected String EmployeeLogin(String username, String password) {
		String dflt = "failure";
		String id ="";
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String returnUserRole = "select userrole from anyuser where username = ? AND userpassword =?";
			PreparedStatement pstmt1 = conn.prepareStatement(returnUserRole);
			pstmt1.setString(1, username);
			pstmt1.setString(2, password);
			ResultSet rs = pstmt1.executeQuery();
			System.out.println("while rs.next right here -v-v-v");
			while (rs.next()) {
				String userrole = rs.getString("userrole");
				id = rs.getString("userid");
				System.out.println("userrole: " + userrole);
				if (userrole.equals("Employee")) {
					dflt = "success";
					loggy.logger.info(" Login success -- for username: " + username);
				}
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			loggy.logger.fatal("Login Error -- Could not login user #"+id+"");
	
		}
		System.out.println("dao is passing back, " + dflt);
		return dflt;
	}

	protected void DeleteEmployee(String id) {
		// HttpSession sesh
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String saveUserBySession = "select * from anyuser where userid =?";
			PreparedStatement pstmt1 = conn.prepareStatement(saveUserBySession);
			pstmt1.setString(1, id);
			ResultSet rs = pstmt1.executeQuery();
			loggy.logger.info(" Delete -- " );
			String deleteById = "delete from anyuser where userid=?";
			PreparedStatement pstmt = conn.prepareStatement(deleteById);
			pstmt.setString(1, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
		//	e.printStackTrace();
			loggy.logger.fatal(" Delete Error - failed to delete user.. ");
		}
	}

	protected String promote(String... values) {
		String assumedFalse = "failed";
		int numberOfValues = 0;
		for (String s : values) {
			numberOfValues++;
		}
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			if (numberOfValues == 3) {
				numberOfValues = 1;

				String updateSQL = "update anyuser set userrole = 'manager' where FIRST_NAME = ? and LAST_NAME =? and EMAIL = ? ";
				PreparedStatement pstmt = conn.prepareStatement(updateSQL);
				
				for (String s : values) {
					pstmt.setString(numberOfValues, s);
					numberOfValues++;
					
				}
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					assumedFalse = "Success";
					loggy.logger.info(" Promote Error -- " );
				}
				
			} else if (numberOfValues == 1) {
				String updateSQL = "update anyuser set userrole = 'manager' where userid = ? ";
				PreparedStatement pstmt = conn.prepareStatement(updateSQL);

				pstmt.setString(1, values[0]);
				ResultSet rs = pstmt.executeQuery();
				assumedFalse = "Success";
			}

		} catch (SQLException e) {
		//	e.printStackTrace();
			loggy.logger.fatal(" Promote Error -- Could not promote user - ");
		}
		return assumedFalse;
	}

	protected String enterEmployee(String... values) {
		String assumedFalse = "failed";
		int numberOfValues = 1;
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String enterEmployee = "insert into anyuser (USERID, FIRST_NAME, last_name, email,userrole ) values(anyUserID.nextval,?,?,?,'Employee')";
			PreparedStatement pstmt = conn.prepareStatement(enterEmployee);
			for (String s : values) {
				pstmt.setString(numberOfValues, s);
				System.out.println("incriment" + numberOfValues);
				numberOfValues++;
				loggy.logger.info(" Enter an employee -- "  );
			}
			int rs = pstmt.executeUpdate();
			if (rs == 1) {
				assumedFalse = "Success";
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			loggy.logger.fatal("Error - Could not enter this employee");
		}

		return assumedFalse;
	}

	public void viewAllTickets(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("reaching view all tickets DAO? ");
		res.setContentType("application/json");
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String sqlSelect = "select * from (select * from ersTicket where resolved is null order by currentstatus) order by submitted";
			ResultSet rs = conn.prepareStatement(sqlSelect).executeQuery();
			ArrayList ticketObjects = new ArrayList();

			int index = 0;
			while (rs.next()) {
				TicketWriter tempTicket = new TicketWriter();
				String ticketID = rs.getString("ticketid");
				String ramount = rs.getString("ramount");
				
				String SubmittedDate = rs.getString("Submitted");
				String Description = rs.getString("Description");
				String employeeName = rs.getString("Employee");
				String StormID = rs.getString("submitterid");
				String status = rs.getString("currentstatus");
				String resolverID = rs.getString("resolverID");
				tempTicket.setTicketID(ticketID);
				System.out.println("getting ticket id:  " + tempTicket.getTicketID());
				tempTicket.setAmount(ramount);
				tempTicket.setDateSubmitted(SubmittedDate);
				tempTicket.setExpenseDescription(Description);
				tempTicket.setSubmitterName(employeeName);
				tempTicket.setSubmitterID(StormID);
				tempTicket.setResolverID(resolverID);
				tempTicket.setStatus(status);
				System.out.println("ticket id from object " + tempTicket.getTicketID());
				ticketObjects.add(tempTicket);
				System.out.println(
						"something hopefully ticket id from arraylist : " + ticketObjects.get(index) + " at, " + index);
				index = index + 1;
			}
			try {
				// for(Object u : ticketObjects) {
				// System.out.println("this is the object reference put in mapper: "+ u);
				res.getWriter().write(new ObjectMapper().writeValueAsString(ticketObjects));
				// }
			} catch (JsonProcessingException e) {
				// wat
			//	e.printStackTrace();
				loggy.logger.fatal(" Error viewing tikcets- could not view the tickets because output from DB was not valid"  );
			} catch (IOException e) {
				loggy.logger.fatal(" Error viewing tikcets- could not view the tickets because output from DB was not valid"  );
				//e.printStackTrace();
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			loggy.logger.fatal(" Error viewing tikcets- could not view the tickets "  );
		}
	}

	public void returnResolvers(HttpServletRequest req, HttpServletResponse res) {
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String selectQuery = "select * from anyuser where userrole = 'manager' ";
			ResultSet rs = conn.prepareStatement(selectQuery).executeQuery();

			ArrayList listResolvers = new ArrayList<>();
			
			while (rs.next()) {
				Resolvers overmanres = new Resolvers();
				overmanres.setUsername(rs.getString("username"));
				overmanres.setFirstname(rs.getString("first_name"));
				overmanres.setLastname(rs.getString("last_name"));
				overmanres.setEmail(rs.getString("email"));
				listResolvers.add(overmanres);
				loggy.logger.info(" Resolvers successfully returned -- ");
			}
			try {
				res.getWriter().write(new ObjectMapper().writeValueAsString(listResolvers));
			} catch (JsonProcessingException e) {
				loggy.logger.fatal(" Error viewing tikcets- could not view the tickets because output from DB was not valid"  );
				e.printStackTrace();
			} catch (IOException e) {
				loggy.logger.fatal(" Error viewing tikcets- could not view the tickets because output from DB was not valid"  );
				//e.printStackTrace();
			} // should return all the resolvers/ managers/ overseers-
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String updateTicket(HttpServletRequest req, HttpServletResponse res) {
		String updateSuccess= "failed";
		String expChange = req.getParameter("expAmnt");
		System.out.println("Expense change entered: " + expChange); // test-
		String newOS = req.getParameter("newOS");
		System.out.println("ID of new overseer: " + newOS);
		String tikID = req.getParameter("hiddenTikID");
		System.out.println("for Ticket: " + tikID);
		StringBuilder sb = new StringBuilder(tikID);
		String newSt = sb.substring(17, sb.length() - 4);
		System.out.println("trimmed: " + newSt);
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			if (expChange != "" & newOS != "") {
				String updateSQL = "update ersticket set ramount = ?, resolverid =?, currentstatus = 'working' where ticketid =?";
				PreparedStatement pstmt = conn.prepareStatement(updateSQL);
				pstmt.setString(1, expChange);
				pstmt.setString(2, newOS);
				pstmt.setString(3, newSt);
				pstmt.executeQuery();
				updateSuccess= "both";

			} else if (expChange != "") {

				System.out.println("chaning amount");
				String updateSQL = "update ersticket set ramount = ?, currentstatus = 'working' where ticketid =?";
				PreparedStatement pstmt = conn.prepareStatement(updateSQL);
				pstmt.setString(1, expChange);
				pstmt.setString(2, newSt);
				pstmt.executeQuery();
				updateSuccess ="amount";

			} else if (newOS != "") {
				String checkOs = "select userrole from anyuser where userid = ?";
				PreparedStatement pstmt1 = conn.prepareStatement(checkOs);
				pstmt1.setString(1, newOS);
				ResultSet rs = pstmt1.executeQuery();
				while (rs.next()) {
					if (rs.getString("userrole").equalsIgnoreCase("manager")) {
						System.out.println("chaning overseer");
						String updateSQL = "update ersticket set resolverid = ?, currentstatus = 'working' where ticketid =?";
						PreparedStatement pstmt = conn.prepareStatement(updateSQL);
						pstmt.setString(1, newOS);
						pstmt.setString(2, newSt);
						pstmt.executeQuery();
						System.out.println("manager for this ticket updated");
						updateSuccess ="resolver";
					} else {
						System.out.println("didn't update");
					}
				}

			} else {
				// nothing
			}

		} catch (SQLException e) {
			loggy.logger.fatal("Update Error -- Ticket #"+tikID+" could not be updated - ");
		}
		return updateSuccess;

	}

	public UsersToInstantiateThemselves setUserSession(String username, String Password) {
		UsersToInstantiateThemselves user = new UsersToInstantiateThemselves();
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String retrieveUserInfo = "select * from anyuser where username = ? AND userpassword =?";
			PreparedStatement pstmt = conn.prepareStatement(retrieveUserInfo);
			pstmt.setString(1, username);
			pstmt.setString(2, Password);
			ResultSet userI = pstmt.executeQuery();

			while (userI.next()) {
				user.setFirstName(userI.getString("first_name"));
				user.setLastName(userI.getString("last_name"));
				user.setStormTrooperID(userI.getString("userid"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public void ticketGenerate(String... input) {
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String enterTicket = "insert into ersticket (ticketid, ramount, submitted, description, employee, submitterid, currentstatus, type) "
					+ "values(ticketnumber.nextval, ?, current_timestamp, ?, ?, ?, 'new',? )";
			PreparedStatement pstmt = conn.prepareStatement(enterTicket);

			pstmt.setString(1, input[1]);
			pstmt.setString(2, input[2]);
			pstmt.setString(3, input[3]);
			pstmt.setString(4, input[4]);
			pstmt.setString(5, input[0]);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void pullUnLoggedUsernamePasswords(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("application/json");

		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String findNonUser = "select * from anyuser where username is null and userpassword is null order by userid desc";
			PreparedStatement pstmt = conn.prepareStatement(findNonUser);
			ResultSet rs = pstmt.executeQuery();
			ArrayList listOfObject = new ArrayList();

			while (rs.next()) {
				String FirstNameInstance = rs.getString("first_name");
				String LastNameInstance = rs.getString("last_name");
				String emailInstance = rs.getString("email");
				UsersToInstantiateThemselves users = new UsersToInstantiateThemselves();
				users.setStormTrooperID(rs.getString("userid"));
				System.out.println(users.getStormTrooperID());
				users.setFirstName(FirstNameInstance);
				users.setLastName(LastNameInstance);
				users.setEmail(emailInstance);

				listOfObject.add(users);
			}
			try {

				res.getWriter().write(new ObjectMapper().writeValueAsString(listOfObject));

			} catch (JsonProcessingException e) {
				// wat
				e.printStackTrace();
			} catch (IOException e) {
				// huh
				e.printStackTrace();
			}

		} catch (SQLException e) {
			loggy.logger.fatal("Update Error -- Could not find users that do not have passowrd or username set up yet..  ");
		}

	}
	protected String queryRole(String user, String pass, HttpSession sesh) {
		String df ="noRole";
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
		String level = "select * from anyuser where username =? and userpassword=?";
		PreparedStatement pstmt = conn.prepareStatement(level);
		pstmt.setString(1, user);
		pstmt.setString(2, pass);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			 df = rs.getString("userrole");
			 sesh.setAttribute("username", user);
			 sesh.setAttribute("userpassword", pass);
			 sesh.setAttribute("first_name", rs.getString("first_name"));
			 sesh.setAttribute("last_name", rs.getString("last_name")); 
			 sesh.setAttribute("userid", rs.getString("userid"));
		}
		
		}catch (SQLException e) {
			loggy.logger.fatal("Login error - Could not connect to database to return a user role   ");

		}
		return df;
	}
	
	public ArrayList retrieveUserTickets() {
		ArrayList<TicketWriter> al = new ArrayList();
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String allTicketsFirst = "select * from ersticket order by ticketid desc";
			PreparedStatement pstmt = conn.prepareStatement(allTicketsFirst);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TicketWriter tw = new TicketWriter();
				tw.setTicketID(rs.getString("ticketid"));
				tw.setAmount(rs.getString("ramount"));
				tw.setDateSubmitted(rs.getString("submitted"));
				tw.setExpenseDescription(rs.getString("description"));
				tw.setSubmitterID(rs.getString("submitterid"));
				tw.setResolverID(rs.getString("resolverid"));
				tw.setStatus(rs.getString("currentstatus"));
				tw.setType(rs.getString("type"));
				tw.setSubmitterName(rs.getString("employee"));
				al.add(tw);
			}

		} catch (SQLException e) {
		//	e.printStackTrace();
			loggy.logger.fatal("Error -- Could not retrieve user tickets..  ");		
		}
		return al;
	}

	protected String updateNewEmployee(String id, String username, String password) {
		System.out.println("in dao");
		String defaltSuccess = "success";
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String newUserLoginQuery = "update anyuser set username =?,userpassword=? where userid= ? ";
			PreparedStatement pstmt = conn.prepareStatement(newUserLoginQuery);
			pstmt.setString(1, username);
			System.out.println(username);
			pstmt.setString(2, password);
			System.out.println(password);
			pstmt.setString(3, id);
			System.out.println(id);
			pstmt.executeQuery();
			System.out.println("query ran");

		} catch (SQLException e) {
			e.printStackTrace();
			defaltSuccess = "fail";
			loggy.logger.fatal("Update Error -- Could not update new employees ");	
		}

		return defaltSuccess;
	}

	protected ArrayList<String> QueryTicketByNumber(String id) {
		ArrayList<String> TicketInfo = new ArrayList<String>();
		try (Connection conn = DriverManager.getConnection(url, DAO.getUsername(), DAO.getPassword())) {
			String row = "select * from ersticket where ticketid =?";
			PreparedStatement pstmt = conn.prepareStatement(row);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TicketInfo.add(rs.getString("ramount"));

				String AdjustTime = rs.getString("submitted");
				StringBuilder sb = new StringBuilder(AdjustTime);
				String timeAdjusted = sb.substring(0, 16);
				TicketInfo.add(timeAdjusted);
				TicketInfo.add(rs.getString("description"));
				TicketInfo.add(rs.getString("employee"));
				TicketInfo.add(rs.getString("submitterid"));
				TicketInfo.add(rs.getString("currentstatus"));
				TicketInfo.add(id);
				TicketInfo.add(rs.getString("resolverid"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			loggy.logger.fatal("Ticket Query Error -- Could not find ticket #" +id +" because could not connect to database..");	
		}

		return TicketInfo;
	}
}
