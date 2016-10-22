package team2.inventory.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import team2.inventory.model.Barcode;
import team2.inventory.model.Company;
import team2.inventory.model.Item;
import team2.inventory.model.Location;
import team2.inventory.model.User;

/** Various query methods.
 * @author James A. Donnell Jr. */
public class DatabaseQuery {
	
	/* -----------------------------
	 *        Barcode Methods
	 * ----------------------------- */

	/** Retrieves all barcodes on database in a map of ID-Barcode.
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
	
	/* -----------------------------
	 *        Company Methods
	 * ----------------------------- */

	/** Retrieves all companies on database in a map of ID-Company.
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

	/** Retrieves all items on database in a map of ID-Item.
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

	/** Retrieves all item types on database in a map of ID-ItemType.
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

	/** Retrieves all locations on database in a map of ID-Location.
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

	/** Retrieves all privileges on database in a map of ID-Privilege.
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

	/** Retrieves all users on database in a map of ID-User.
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
}