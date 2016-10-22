package team2.inventory.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sqlQuery = "SELECT * FROM `Barcode`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toBarcodeMap(resultSet);
	}
	
	/** Retrieves all barcodes on database in a map of ID-to-Barcode.
	 * @param connection Database connection.
	 * @param id ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Barcode getBarcodeByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `Barcode` WHERE `Barcode`.`ID`=" + id;
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toBarcodeMap(resultSet).get(id);
	}
	
	/** Retrieves all barcodes on database in a map of ID-to-Barcode.
	 * @param connection Database connection.
	 * @param barcodeSearch Barcode to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Barcode> getBarcodesByBarcode(Connection connection, String barcodeSearch) throws SQLException {
		String sqlQuery = "SELECT * FROM `Barcode` WHERE `Barcode`.`Barcode` LIKE '%" + barcodeSearch + "%'";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toBarcodeMap(resultSet);
	}
	
	/* -----------------------------
	 *        Company Methods
	 * ----------------------------- */

	/** Retrieves all companies on database in a map of ID-to-Company.
	 * @param connection Database connection. 
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Company> getCompanies(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Company`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toCompanyMap(resultSet);
	}
	
	/** Retrieves all companies on database in a map of ID-to-Company.
	 * @param connection Database connection. 
	 * @param ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Company */
	public static Company getCompanyByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `Company` WHERE `Company`.`ID`=" + id;
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toCompanyMap(resultSet).get(id);
	}
	
	/* -----------------------------
	 *         Item Methods
	 * ----------------------------- */

	/** Retrieves all items on database in a map of ID-to-Item.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Item> getItems(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Item`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toItemMap(resultSet);
	}
	
	/** Retrieves all items on database in a map of ID-to-Item.
	 * @param connection Database connection.
	 * @param ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Item getItemByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `Item` WHERE `Item`.`ID`=" + id;
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toItemMap(resultSet).get(id);
	}
	
	/* -----------------------------
	 *        ItemType Methods
	 * ----------------------------- */

	/** Retrieves all item types on database in a map of ID-to-ItemType.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, String> getItemTypes(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `ItemType`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toItemTypeMap(resultSet);
	}
	
	/* -----------------------------
	 *      Location Methods
	 * ----------------------------- */

	/** Retrieves all locations on database in a map of ID-to-Location.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Location> getLocations(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Location`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toLocationMap(resultSet);
	}
	
	/** Retrieves all locations on database in a map of ID-to-Location.
	 * @param connection Database connection.
	 * @param ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Location getLocationByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `Location` WHERE `Location`.`ID`=" + id;
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toLocationMap(resultSet).get(id);
	}
	
	/* -----------------------------
	 *      Privileges Methods
	 * ----------------------------- */

	/** Retrieves all privileges on database in a map of ID-to-Privilege.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, String> getPrivileges(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Privilege`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toPrivilegesMap(resultSet);
	}
	
	/* -----------------------------
	 *          User Methods
	 * ----------------------------- */

	/** Retrieves all users on database in a map of ID-to-User.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, User> getUsers(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `User`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toUserMap(resultSet);
	}
	
	/** Retrieves all users on database in a map of ID-to-User.
	 * @param connection Database connection.
	 * @param ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static User getUserByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `User` WHERE `User`.`ID`=" + id;
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toUserMap(resultSet).get(id);
	}
	
	/* -----------------------------
	 *      Inventory Methods
	 * ----------------------------- */
	
	public static Map<Integer, Inventory> getInventory(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory`";
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toInventoryMap(resultSet);
	}
	
	public static Inventory getInventoryByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`ID`=" + id;
		ResultSet resultSet = DatabaseConnector.getResultSet(connection, sqlQuery);
		return DatabaseResultSetParser.toInventoryMap(resultSet).get(id);
	}
}