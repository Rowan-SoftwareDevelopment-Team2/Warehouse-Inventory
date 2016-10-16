package team2.inventory.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import team2.inventory.model.Company;
import team2.inventory.model.Item;
import team2.inventory.model.Location;
import team2.inventory.model.User;

/** Tests basic database functionality for MariaDB.
 * @author James A. Donnell Jr. */
public class DatabaseConnectorTest {

	/** MariaDB JDBC database prefix. */
	private static String prefix = "jdbc:mariadb://";
	/** Database hostname. */
	private static String hostname = "warehouse.jdweb.info";
	/** Database port. */
	private static String port = "3306";
	/** Working database name. */
	private static String database = "";
	/** Database user. */
	private static String user = "";
	/** Database password. */
	private static String password = "";

	/** Generated database string for connection. */
	private static String connectionString = prefix + hostname + ":" + port + "/" + database + "?user=" + user + "&password=" + password;

	/** Testing all tables in database.
	 * @param args Not utilized. */
	public static void main(String[] args) {
		try {
			System.out.println("All Barcodes:\n" + getBarcodesTest() + "\n");
			System.out.println("All Companies:\n" + getCompaniesTest() + "\n");
			System.out.println("All Items:\n" + getItemsTest() + "\n");
			System.out.println("All Item Types:\n" + getItemTypesTest() + "\n");
			System.out.println("All Locations:\n" + getLocationsTest() + "\n");
			System.out.println("All Privileges:\n" + getPrivilegeTest() + "\n");
			System.out.println("All Users:\n" + getUsersTest() + "\n");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/** Retrieves all barcodes on database in a map of ID-Barcode.
	 * @return Map */
	public static Map<Integer, Integer> getBarcodesTest() throws SQLException {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		Connection connection = DriverManager.getConnection(connectionString);
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `Barcode`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			result.put(resultSet.getInt(1), resultSet.getInt(2));
		}

		stmt.close();
		connection.close();

		return result;
	}

	/** Retrieves all companies on database in a map of ID-Company.
	 * @return Map */
	public static Map<Integer, Company> getCompaniesTest() throws SQLException {
		Map<Integer, Company> result = new HashMap<Integer, Company>();

		Connection connection = DriverManager.getConnection(connectionString);
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `Company`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			Company company = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			result.put(resultSet.getInt(1), company);
		}

		stmt.close();
		connection.close();

		return result;
	}

	/** Retrieves all items on database in a map of ID-Item.
	 * @return Map */
	public static Map<Integer, Item> getItemsTest() throws SQLException {
		Map<Integer, Item> result = new HashMap<Integer, Item>();

		Connection connection = DriverManager.getConnection(connectionString);
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `Item`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			Item item = new Item(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5));
			result.put(resultSet.getInt(1), item);
		}

		stmt.close();
		connection.close();

		return result;
	}

	/** Retrieves all item types on database in a map of ID-ItemType.
	 * @return Map */
	public static Map<Integer, String> getItemTypesTest() throws SQLException {
		Map<Integer, String> result = new HashMap<Integer, String>();

		Connection connection = DriverManager.getConnection(connectionString);
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `ItemType`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			result.put(resultSet.getInt(1), resultSet.getString(2));
		}

		stmt.close();
		connection.close();

		return result;
	}

	/** Retrieves all locations on database in a map of ID-Location.
	 * @return Map */
	public static Map<Integer, Location> getLocationsTest() throws SQLException {
		Map<Integer, Location> result = new HashMap<Integer, Location>();

		Connection connection = DriverManager.getConnection(connectionString);
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `Location`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			Location location = new Location(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
			result.put(resultSet.getInt(1), location);
		}

		stmt.close();
		connection.close();

		return result;
	}

	/** Retrieves all privileges on database in a map of ID-Privilege.
	 * @return Map */
	public static Map<Integer, String> getPrivilegeTest() throws SQLException {
		Map<Integer, String> result = new HashMap<Integer, String>();

		Connection connection = DriverManager.getConnection(connectionString);
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `Privilege`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			result.put(resultSet.getInt(1), resultSet.getString(2));
		}

		stmt.close();
		connection.close();

		return result;
	}

	/** Retrieves all users on database in a map of ID-User.
	 * @return Map */
	public static Map<Integer, User> getUsersTest() throws SQLException {
		Map<Integer, User> result = new HashMap<Integer, User>();

		Connection connection = DriverManager.getConnection(connectionString);
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `User`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
			result.put(resultSet.getInt(1), user);
		}

		stmt.close();
		connection.close();

		return result;
	}
}