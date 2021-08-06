package ACTUAL.bankApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnection {
	//=================CONNECTIONSTRING=============================================\
	static String url= "jdbc:oracle:thin:@jponder23.cv1rmjxwr5fp.us-east-2.rds.amazonaws.com:1521:orcl";

	private static String username="myusfdb";

	private static String password= "#3Marine33";	
	//---------------------------------------------------------------/
	private String ConnectionStringInstanceSuperLongVariableName;
	
	public String getCnctString() {
		return ConnectionStringInstanceSuperLongVariableName;
	}
	public void setCnctString(String connectionStringInstanceSuperLongVariableName) {
		ConnectionStringInstanceSuperLongVariableName = connectionStringInstanceSuperLongVariableName;
	}
	//-----/
	
	public static void inPutcustomer(String url, String id, String namingAttmept) {

		try(Connection conn =
				DriverManager.getConnection(url, id, namingAttmept))
		{
			String sql = "INSERT INTO CustomerTable(Cid, First_name) "+
					"VALUES('"+"custIDgen.NEXTVAL"+"', '" + "Billy"+ "' )";
			
			//==-test--===
			Statement statement = conn.createStatement();
			int numOfRowsChanged = statement.executeUpdate(sql);
			System.out.println("The # of rows changed: "+ numOfRowsChanged);
			//--test--/
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}


}
