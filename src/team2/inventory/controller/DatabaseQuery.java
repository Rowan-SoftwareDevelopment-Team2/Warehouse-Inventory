package team2.inventory.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import team2.inventory.model.Barcode;
import team2.inventory.model.Company;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
import team2.inventory.model.Location;
import team2.inventory.model.User;

/** Various query methods.
 * @author James A. Donnell Jr. */
public class DatabaseQuery {
	
	/* -----------------------------
	 *        Barcode Methods
	 * ----------------------------- */

	/** Retrieves all barcodes on database in a map of ID-to-Barcode.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Barcode> getBarcodes(Connection connection) throws SQLException {
		Map<Integer, Barcode> result = new HashMap<Integer, Barcode>();

		String sqlQuery = "SELECT * FROM `Barcode`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);

		while(resultSet.next()) {
			Barcode barcode = new Barcode(resultSet.getInt(1), resultSet.getString(2));
			result.put(resultSet.getInt(1), barcode);
		}

		return result;
	}
	
	/** Retrieves all barcodes on database in a map of ID-to-Barcode.
	 * @param connection Database connection.
	 * @param id ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Barcode> getBarcodesByID(Connection connection, int id) throws SQLException {
		Map<Integer, Barcode> result = new HashMap<Integer, Barcode>();

		String sqlQuery = "SELECT * FROM `Barcode` WHERE `Barcode`.`ID`=" + id;
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);

		while(resultSet.next()) {
			Barcode barcode = new Barcode(resultSet.getInt(1), resultSet.getString(2));
			result.put(resultSet.getInt(1), barcode);
		}

		return result;
	}
	
	/** Retrieves all barcodes on database in a map of ID-to-Barcode.
	 * @param connection Database connection.
	 * @param barcodeSearch Barcode to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Barcode> getBarcodesByBarcode(Connection connection, String barcodeSearch) throws SQLException {
		Map<Integer, Barcode> result = new HashMap<Integer, Barcode>();

		String sqlQuery = "SELECT * FROM `Barcode` WHERE `Barcode`.`Barcode` LIKE '%" + barcodeSearch + "%'";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);

		while(resultSet.next()) {
			Barcode barcode = new Barcode(resultSet.getInt(1), resultSet.getString(2));
			result.put(resultSet.getInt(1), barcode);
		}

		return result;
	}
	
	/* -----------------------------
	 *        Company Methods
	 * ----------------------------- */

	/** Retrieves all companies on database in a map of ID-to-Company.
	 * @param connection Database connection. 
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Company> getCompanies(Connection connection) throws SQLException {
		Map<Integer, Company> result = new HashMap<Integer, Company>();

		String sqlQuery = "SELECT * FROM `Company`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);

		while(resultSet.next()) {
			Company company = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			result.put(resultSet.getInt(1), company);
		}

		return result;
	}
	
	/* -----------------------------
	 *         Item Methods
	 * ----------------------------- */

	/** Retrieves all items on database in a map of ID-to-Item.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Item> getItems(Connection connection) throws SQLException {
		Map<Integer, Item> result = new HashMap<Integer, Item>();

		String sqlQuery = "SELECT * FROM `Item`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);

		while(resultSet.next()) {
			Item item = new Item(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5));
			result.put(resultSet.getInt(1), item);
		}

		return result;
	}
	
	/* -----------------------------
	 *        ItemType Methods
	 * ----------------------------- */

	/** Retrieves all item types on database in a map of ID-to-ItemType.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, String> getItemTypes(Connection connection) throws SQLException {
		Map<Integer, String> result = new HashMap<Integer, String>();

		String sqlQuery = "SELECT * FROM `ItemType`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);

		while(resultSet.next()) {
			result.put(resultSet.getInt(1), resultSet.getString(2));
		}

		return result;
	}
	
	/* -----------------------------
	 *      Location Methods
	 * ----------------------------- */

	/** Retrieves all locations on database in a map of ID-to-Location.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Location> getLocations(Connection connection) throws SQLException {
		Map<Integer, Location> result = new HashMap<Integer, Location>();

		String sqlQuery = "SELECT * FROM `Location`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);

		while(resultSet.next()) {
			Location location = new Location(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
			result.put(resultSet.getInt(1), location);
		}

		return result;
	}
	
	/* -----------------------------
	 *      Privileges Methods
	 * ----------------------------- */

	/** Retrieves all privileges on database in a map of ID-to-Privilege.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, String> getPrivileges(Connection connection) throws SQLException {
		Map<Integer, String> result = new HashMap<Integer, String>();

		String sqlQuery = "SELECT * FROM `Privilege`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);

		while(resultSet.next()) {
			result.put(resultSet.getInt(1), resultSet.getString(2));
		}

		return result;
	}
	
	/* -----------------------------
	 *          User Methods
	 * ----------------------------- */

	/** Retrieves all users on database in a map of ID-to-User.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, User> getUsers(Connection connection) throws SQLException {
		Map<Integer, User> result = new HashMap<Integer, User>();

		String sqlQuery = "SELECT * FROM `User`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);

		while(resultSet.next()) {
			User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
			result.put(resultSet.getInt(1), user);
		}

		return result;
	}
	
	/* -----------------------------
	 *      Inventory Methods
	 * ----------------------------- */
	
	public static Map<Integer, Inventory> getInventory(Connection connection) throws SQLException {
		Map<Integer, Inventory> result = new HashMap<Integer, Inventory>();
		
		String sqlQuery = "SELECT * FROM `Inventory`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		
		while(resultSet.next()) {
			Inventory inventory = new Inventory(resultSet.getInt(1), resultSet.getInt(2), 
					resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), 
					resultSet.getInt(6), resultSet.getDate(7), resultSet.getDate(8), 
					resultSet.getInt(9), resultSet.getInt(10));
			result.put(resultSet.getInt(1), inventory);
		}
		return result;
	}
	
}