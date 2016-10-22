package team2.inventory.controller;

import java.sql.Connection;
import java.sql.SQLException;

/** Database testing driver.
 * @author James A. Donnell Jr. */
public class DatabaseTestDriver {

	/** Testing all tables in database.
	 * @param args Database table, username and password on seperate lines. */
	public static void main(String[] args) {
		try {
			Connection connection = DatabaseConnector.createConnection(args[0], args[1], args[2]);
			allTests(connection);
			
			System.out.println(DatabaseQuery.getBarcodesByID(connection, 1));
			System.out.println(DatabaseQuery.getBarcodesByBarcode(connection, "ABCDEFG"));
			System.out.println(DatabaseQuery.getInventory(connection));
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	private static void allTests(Connection connection) throws SQLException {
		System.out.println("All Barcodes:\n" + DatabaseQuery.getBarcodes(connection) + "\n");
		System.out.println("All Companies:\n" + DatabaseQuery.getCompanies(connection) + "\n");
		System.out.println("All Items:\n" + DatabaseQuery.getItems(connection) + "\n");
		System.out.println("All Item Types:\n" + DatabaseQuery.getItemTypes(connection) + "\n");
		System.out.println("All Locations:\n" + DatabaseQuery.getLocations(connection) + "\n");
		System.out.println("All Privileges:\n" + DatabaseQuery.getPrivileges(connection) + "\n");
		System.out.println("All Users:\n" + DatabaseQuery.getUsers(connection) + "\n");
	}
}