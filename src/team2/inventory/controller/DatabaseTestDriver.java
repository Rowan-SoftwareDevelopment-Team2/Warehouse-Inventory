package team2.inventory.controller;

import java.sql.Connection;
import java.sql.SQLException;

import team2.inventory.controller.database.Connector;
import team2.inventory.controller.database.Inserter;
import team2.inventory.controller.database.Query;
import team2.inventory.controller.database.Remover;
import team2.inventory.model.Location;

/** Database testing driver.
 * @author James A. Donnell Jr. */
public class DatabaseTestDriver {

	/** Testing various database methods.
	 * @param args Database table, username and password on separate lines. */
	public static void main(String[] args) {
		try {
			Connection connection = Connector.createConnection(args[0], args[1], args[2]);
			
			Location test = new Location(0, "Test item", 10, 20);
			
			System.out.println(Query.getLocations(connection));
			Inserter.insert(connection, test);
			System.out.println(Query.getLocations(connection));
			Remover.remove(connection, test);
			System.out.println(Query.getLocations(connection));
			
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private static void allTests(Connection connection) throws SQLException {
		System.out.println("All Barcodes:\n" + Query.getBarcodes(connection) + "\n");
		System.out.println("All Companies:\n" + Query.getCompanies(connection) + "\n");
		System.out.println("All Items:\n" + Query.getItems(connection, Query.getBarcodes(connection), Query.getCompanies(connection)) + "\n");
		System.out.println("All Item Types:\n" + Query.getItemTypes(connection) + "\n");
		System.out.println("All Locations:\n" + Query.getLocations(connection) + "\n");
		System.out.println("All Privileges:\n" + Query.getPrivileges(connection) + "\n");
		System.out.println("All Users:\n" + Query.getUsers(connection) + "\n");
		System.out.println("All Inventory:\n" + Query.getInventory(connection) + "\n");
	}
}