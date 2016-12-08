package team2.inventory.controller.database;

import java.sql.Connection;
import java.sql.SQLException;

import team2.inventory.model.Barcode;
import team2.inventory.model.Company;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
import team2.inventory.model.Location;
import team2.inventory.model.User;

/** Database remove methods.
 * @author James A. Donnell Jr. */
public class Remover {

	public static void remove(Connection connection, Barcode barcode) throws SQLException {
		String sqlQuery = "DELETE FROM `Barcode` WHERE `ID`=" + barcode.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void remove(Connection connection, Company company) throws SQLException {
		String sqlQuery = "DELETE FROM `Company` WHERE `ID`=" + company.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void remove(Connection connection, Item item) throws SQLException {
		String sqlQuery = "DELETE FROM `Item` WHERE `ID`=" + item.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void remove(Connection connection, Location location) throws SQLException {
		String sqlQuery = "DELETE FROM `Location` WHERE `ID`=" + location.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void remove(Connection connection, User user) throws SQLException {
		String sqlQuery = "DELETE FROM `User` WHERE `ID`=" + user.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void remove(Connection connection, Inventory inventory) throws SQLException {
		String sqlQuery = "DELETE FROM `Inventory` WHERE `ID`=" + inventory.getId();
		Connector.getResultSet(connection, sqlQuery);
	}
	
}