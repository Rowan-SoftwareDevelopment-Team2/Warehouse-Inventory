package team2.inventory.controller.database;

import java.sql.Connection;
import java.sql.SQLException;

import team2.inventory.controller.BarcodeGenImage;
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
public class Inserter {

	private static String customError = " with an ID cannot be inserted. Please use the Update class for updating or set the ID to 0 for inserting new data.";

	public static void insert(Connection connection, Barcode barcode) throws SQLException {
		if (barcode.getId() != 0)
			throw new SQLException("Barcode" + customError);
		String sqlQuery = "INSERT INTO `Barcode` VALUES (NULL, '" + barcode.getBarcode() + "')";
		Connector.getResultSet(connection, sqlQuery);
		barcode.setId(Query.getBarcodeByBarcode(connection, barcode.getBarcode()).getId());

	}

	public static void insert(Connection connection, Company company) throws SQLException {
		if (company.getId() != 0)
			throw new SQLException("Company" + customError);
		String sqlQuery = "INSERT INTO `Company` VALUES (NULL, '" + company.getName() + "', '" + company.getEmail() + "', '" + company.getPhone() + "', '" + company.getAddress() + "')";
		Connector.getResultSet(connection, sqlQuery);
		company.setId(Query.getCompanyLastInserted(connection).getId());
	}

	public static void insert(Connection connection, Item item) throws SQLException {
		if (item.getId() != 0)
			throw new SQLException("Item" + customError);
		String sqlQuery = "INSERT INTO `Item` VALUES (NULL, '" + item.getName() + "', '" + item.getManufacturer().getId() + "', '" + item.getBarcode().getId() + "', '" + item.getDescription() + "')";
		Connector.getResultSet(connection, sqlQuery);
		item.setId(Query.getItemLastInserted(connection, Query.getBarcodes(connection), Query.getCompanies(connection)).getId());
	}

	public static void insert(Connection connection, Location location) throws SQLException {
		if (location.getId() != 0)
			throw new SQLException("Location" + customError);
		String sqlQuery = "INSERT INTO `Location` VALUES (NULL, '" + location.getDescription() + "', '" + location.getAisle() + "', '" + location.getRow() + "')";
		Connector.getResultSet(connection, sqlQuery);
		location.setId(Query.getLocationLastInserted(connection).getId());
	}

	public static void insert(Connection connection, User user) throws SQLException {
		if (user.getId() != 0)
			throw new SQLException("User" + customError);
		String sqlQuery = "INSERT INTO `User` VALUES (NULL, '" + user.getUsername() + "', '" + user.getHash() + "', '" + user.getRealname() + "', '" + user.getPrivileges() + "')";
		Connector.getResultSet(connection, sqlQuery);
		user.setId(Query.getUserLastInserted(connection).getId());
	}

	public static void insert(Connection connection, Inventory inventory) throws SQLException {
		if (inventory.getId() != 0)
			throw new SQLException("Inventory" + customError);
		String sqlQuery = "INSERT INTO `Inventory` VALUES (NULL, ";
		sqlQuery += (inventory.getItem() == null) ? "NULL, " : "'" + inventory.getItem().getId() + "', ";
		sqlQuery += (inventory.getAmount() == 0) ? "NULL, " : "'" + inventory.getAmount() + "', ";
		sqlQuery += (inventory.getSupplier() == null) ? "NULL, " : "'" + inventory.getSupplier().getId() + "', ";
		sqlQuery += (inventory.getType() == 0) ? "NULL, " : "'" + inventory.getType() + "', ";
		sqlQuery += (inventory.getParent() == 0) ? "NULL, " : "'" + inventory.getParent() + "', ";
		sqlQuery += (inventory.getReceived() == null) ? "NULL, " : "'" + inventory.getReceived() + "', ";
		sqlQuery += (inventory.getShipped() == null) ? "NULL, " : "'" + inventory.getShipped() + "', ";
		sqlQuery += (inventory.getLocation() == null) ? "NULL, " : "'" + inventory.getLocation().getId() + "', ";
		sqlQuery += (inventory.getBarcode() == null) ? "NULL)" : "'" + inventory.getBarcode().getId() + "')";
		Connector.getResultSet(connection, sqlQuery);
		inventory.setId(Query.getInventoryLastInserted(connection).getId());

		if(inventory.getType()==2) {
			// Create barcode and barcode image.
			Barcode barcode = new Barcode(0, BarcodeGenImage.generateBarcode(inventory));
			Inserter.insert(connection, barcode);
			
			// Update database with new barcode.
			inventory.setBarcode(barcode);
			Updater.update(connection, inventory);
		}
	}

}