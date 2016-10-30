package team2.inventory.controller.database;

import java.sql.Connection;
import java.sql.SQLException;

import team2.inventory.model.Barcode;
import team2.inventory.model.Company;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
import team2.inventory.model.Location;
import team2.inventory.model.User;

/** Database insert methods.
 * Constraints:
 * 		- To be used only when duplicates are not currently in database
 * 		- ID of class must be 0
 * @author James A. Donnell Jr. */
public class Insert {

	private static String error = " with an ID cannot be inserted. Please use the Update class for updating or set the ID to 0 for inserting new data.";

	public static void insert(Connection connection, Barcode barcode) throws SQLException {
		if (barcode.getId() != 0)
			throw new SQLException("Barcode" + error);
		String sqlQuery = "INSERT INTO `Barcode` VALUES (NULL, '" + barcode.getBarcode() + "')";
		Connector.getResultSet(connection, sqlQuery);
		barcode.setId(Query.getBarcodeByBarcode(connection, barcode.getBarcode()).getId());

	}

	public static void insert(Connection connection, Company company) throws SQLException {
		if (company.getId() != 0)
			throw new SQLException("Company" + error);
		String sqlQuery = "INSERT INTO `Company` VALUES (NULL, '" + company.getName() + "', '" + company.getEmail() + "', '" + company.getPhone() + "', '" + company.getAddress() + "')";
		Connector.getResultSet(connection, sqlQuery);
		company.setId(Query.getCompanyLastInserted(connection).getId());
	}

	public static void insert(Connection connection, Item item) throws SQLException {
		if (item.getId() != 0)
			throw new SQLException("Item" + error);
		String sqlQuery = "INSERT INTO `Item` VALUES (NULL, '" + item.getName() + "', '" + item.getManufacturer() + "', '" + item.getBarcode() + "', '" + item.getDescription() + "')";
		Connector.getResultSet(connection, sqlQuery);
		item.setId(Query.getItemLastInserted(connection).getId());
	}

	public static void insert(Connection connection, Location location) throws SQLException {
		if (location.getId() != 0)
			throw new SQLException("Location" + error);
		String sqlQuery = "INSERT INTO `Location` VALUES (NULL, '" + location.getDescription() + "', '" + location.getAisle() + "', '" + location.getRow() + "')";
		Connector.getResultSet(connection, sqlQuery);
		location.setId(Query.getLocationLastInserted(connection).getId());
	}

	public static void insert(Connection connection, User user) throws SQLException {
		if (user.getId() != 0)
			throw new SQLException("User" + error);
		String sqlQuery = "INSERT INTO `User` VALUES (NULL, '" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getRealname() + "', '" + user.getPrivileges() + "')";
		Connector.getResultSet(connection, sqlQuery);
		user.setId(Query.getUserLastInserted(connection).getId());
	}

	public static void insert(Connection connection, Inventory inventory) throws SQLException {
		if (inventory.getId() != 0)
			throw new SQLException("Inventory" + error);
		String sqlQuery = "INSERT INTO `Inventory` VALUES (NULL, ";
		sqlQuery += (inventory.getItem() == 0) ? "NULL, " : "'" + inventory.getItem() + "', ";
		sqlQuery += (inventory.getAmount() == 0) ? "NULL, " : "'" + inventory.getAmount() + "', ";
		sqlQuery += (inventory.getSupplier() == 0) ? "NULL, " : "'" + inventory.getSupplier() + "', ";
		sqlQuery += (inventory.getType() == 0) ? "NULL, " : "'" + inventory.getType() + "', ";
		sqlQuery += (inventory.getParent() == 0) ? "NULL, " : "'" + inventory.getParent() + "', ";
		sqlQuery += (inventory.getReceived() == null) ? "NULL, " : "'" + inventory.getReceived() + "', ";
		sqlQuery += (inventory.getShipped() == null) ? "NULL, " : "'" + inventory.getShipped() + "', ";
		sqlQuery += (inventory.getLocation() == 0) ? "NULL, " : "'" + inventory.getLocation() + "', ";
		sqlQuery += (inventory.getBarcode() == 0) ? "NULL)" : "'" + inventory.getBarcode() + "')";
		Connector.getResultSet(connection, sqlQuery);
		inventory.setId(Query.getInventoryLastInserted(connection).getId());
	}

}