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

	public static void insertBarcode(Connection connection, Barcode barcode) throws SQLException {
		if (barcode.getId() == 0) {
			String sqlQuery = "INSERT INTO `Barcode` VALUES (NULL, '" + barcode.getBarcode() + "')";
			Connector.getResultSet(connection, sqlQuery);
		} else
			throw new SQLException("Barcode with ID cannot be inserted, utilize the update class.");
	}

	public static void insertCompany(Connection connection, Company company) throws SQLException {
		if (company.getId() == 0) {
			String sqlQuery = "INSERT INTO `Company` VALUES (NULL, '" + company.getName() + "', '" + company.getEmail() + "', '" + company.getPhone() + "', '" + company.getAddress() + "')";
			Connector.getResultSet(connection, sqlQuery);
		} else
			throw new SQLException("Company with ID cannot be inserted, utilize the update class.");
	}
	
	public static void insertItem(Connection connection, Item item) throws SQLException {
		if (item.getId() == 0) {
			String sqlQuery = "INSERT INTO `Item` VALUES (NULL, '" + item.getName() + "', '" + item.getManufacturer() + "', '" + item.getBarcode() + "', '" + item.getDescription() + "')";
			Connector.getResultSet(connection, sqlQuery);
		} else
			throw new SQLException("Item with ID cannot be inserted, utilize the update class.");
	}
	
	public static void insertLocation(Connection connection, Location location) throws SQLException {
		if (location.getId() == 0) {
			String sqlQuery = "INSERT INTO `Location` VALUES (NULL, '" + location.getDescription() + "', '" + location.getAisle() + "', '" + location.getRow() + "')";
			Connector.getResultSet(connection, sqlQuery);
		} else
			throw new SQLException("Location with ID cannot be inserted, utilize the update class.");
	}
	
	public static void insertUser(Connection connection, User user) throws SQLException {
		if (user.getId() == 0) {
			String sqlQuery = "INSERT INTO `User` VALUES (NULL, '" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getRealname() + "', '" + user.getPrivileges() + "')";
			Connector.getResultSet(connection, sqlQuery);
		} else
			throw new SQLException("User with ID cannot be inserted, utilize the update class.");
	}
	
	public static void insertInventory(Connection connection, Inventory inv) throws SQLException {
		if (inv.getId() == 0) {
			String sqlQuery = "INSERT INTO `Inventory` VALUES (NULL, ";
			sqlQuery += (inv.getItem() == 0) ? "NULL, " : "'" + inv.getItem() + "', ";
			sqlQuery += (inv.getAmount() == 0) ? "NULL, " : "'" + inv.getAmount() + "', ";
			sqlQuery += (inv.getSupplier() == 0) ? "NULL, " : "'" + inv.getSupplier() + "', ";
			sqlQuery += (inv.getType() == 0) ? "NULL, " : "'" + inv.getType() + "', ";
			sqlQuery += (inv.getParent() == 0) ? "NULL, " : "'" + inv.getParent() + "', ";
			sqlQuery += (inv.getReceived() == null) ? "NULL, " : "'" + inv.getReceived() + "', ";
			sqlQuery += (inv.getShipped() == null) ? "NULL, " : "'" + inv.getShipped() + "', ";
			sqlQuery += (inv.getLocation() == 0) ? "NULL, " : "'" + inv.getLocation() + "', ";
			sqlQuery += (inv.getBarcode() == 0) ? "NULL)" : "'" + inv.getBarcode() + "')";
			Connector.getResultSet(connection, sqlQuery);
		} else
			throw new SQLException("Inventory with ID cannot be inserted, utilize the update class.");
	}

}