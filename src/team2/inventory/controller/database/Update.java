package team2.inventory.controller.database;

import java.sql.Connection;
import java.sql.SQLException;

import team2.inventory.model.Barcode;
import team2.inventory.model.Company;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
import team2.inventory.model.Location;
import team2.inventory.model.User;

public class Update {

	public static void updateBarcode(Connection connection, Barcode barcode) throws SQLException {
		String sqlQuery = "UPDATE `Barcode` SET `Barcode`='" + barcode.getBarcode() + "' WHERE `ID`=" + barcode.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void updateCompany(Connection connection, Company company) throws SQLException {
		String sqlQuery = "UPDATE `Company` SET `Name`='" + company.getName() + "', `Email`='" + company.getEmail() 
		+ "', `Phone`='" + company.getPhone() + "', `Address`='" + company.getAddress() + "' WHERE `ID`=" + company.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void updateItem(Connection connection, Item item) throws SQLException {
		String sqlQuery = "UPDATE `Item` SET `Name`='" + item.getName() + "', `Manufacturer`='" + item.getManufacturer() 
		+ "', `Barcode`='" + item.getBarcode() + "', `Description`='" + item.getDescription() + "' WHERE `ID`=" + item.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void updateLocation(Connection connection, Location location) throws SQLException {
		String sqlQuery = "UPDATE `Location` SET `Description`='" + location.getDescription() + "', `Aisle`='" + location.getAisle() 
		+ "', `Row`='" + location.getRow() + "' WHERE `ID`=" + location.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void updateUser(Connection connection, User user) throws SQLException {
		String sqlQuery = "UPDATE `User` SET `Username`='" + user.getUsername() + "', `Password`='" + user.getPassword() 
		+ "', `RealName`='" + user.getRealname() + "', `Privileges`='" + user.getPrivileges() + "' WHERE `ID`=" + user.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void updateInventory(Connection connection, Inventory inventory) throws SQLException {
		String sql = "UPDATE `Inventory` SET `Item`=";
		sql += (inventory.getItem() == 0) ? "NULL" : "'" + inventory.getItem() + "'";
		sql += ", `Amount`=";
		sql += (inventory.getAmount() == 0) ? "NULL" : "'" + inventory.getAmount() + "'";
		sql += ", `Supplier`=";
		sql += (inventory.getSupplier() == 0) ? "NULL" : "'" + inventory.getSupplier() + "'";
		sql += ", `Type`=";
		sql += (inventory.getType() == 0) ? "NULL" : "'" + inventory.getType() + "'";
		sql += ", `Parent`=";
		sql += (inventory.getParent() == 0) ? "NULL" : "'" + inventory.getParent() + "'";
		sql += ", `Received`=";
		sql += (inventory.getReceived() == null) ? "NULL" : "'" + inventory.getReceived() + "'";
		sql += ", `Shipped`=";
		sql += (inventory.getShipped() == null) ? "NULL" : "'" + inventory.getShipped() + "'";
		sql += ", `Location`=";
		sql += (inventory.getLocation() == 0) ? "NULL" : "'" + inventory.getLocation() + "'";
		sql += ", `Barcode`=";
		sql += (inventory.getBarcode() == 0) ? "NULL" : "'" + inventory.getBarcode() + "'";
		sql += " WHERE `ID`=" + inventory.getId();
		Connector.getResultSet(connection, sql);
	}
}