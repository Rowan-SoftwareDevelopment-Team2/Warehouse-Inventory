package team2.inventory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import team2.inventory.controller.Login.LoginException;
import team2.inventory.controller.database.Connector;
import team2.inventory.controller.database.Query;

/** Database testing driver.
 * @author James A. Donnell Jr. */
public class DatabaseTestDriver {

	/** Testing various database methods.
	 * @param args Database table, username and password on separate lines. 
	 * @throws LoginException 
	 * @throws IOException */
	public static void main(String[] args) throws LoginException, IOException {
		try {
			Connection connection = Connector.createConnection(args[0], args[1], args[2]);
			
			String filename = "F:\\Desktop\\test.csv";
			Report.generateInventoryReport(filename, Query.getInventory(connection));
			Report.openReport(filename);
			
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}