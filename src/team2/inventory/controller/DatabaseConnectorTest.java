package team2.inventory.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import team2.inventory.model.Barcode;
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

	/** Testing all tables in database.
	 * @param args Not utilized. */
	public static void main(String[] args) {
		try {
			Connection connection = createConnection(args[0], args[1], args[2]);
			
			System.out.println("All Barcodes:\n" + getBarcodesTest(connection) + "\n");
			System.out.println("All Companies:\n" + getCompaniesTest(connection) + "\n");
			System.out.println("All Items:\n" + getItemsTest(connection) + "\n");
			System.out.println("All Item Types:\n" + getItemTypesTest(connection) + "\n");
			System.out.println("All Locations:\n" + getLocationsTest(connection) + "\n");
			System.out.println("All Privileges:\n" + getPrivilegeTest(connection) + "\n");
			System.out.println("All Users:\n" + getUsersTest(connection) + "\n");
			
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static Connection createConnection(String table, String username, String password) throws SQLException {
		String connectionString = prefix + hostname + ":" + port + "/" + table + "?user=" + username + "&password=" + password;
		return DriverManager.getConnection(connectionString);
	}

	/** Retrieves all barcodes on database in a map of ID-Barcode.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Barcode> getBarcodesTest(Connection connection) throws SQLException {
		Map<Integer, Barcode> result = new HashMap<Integer, Barcode>();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `Barcode`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			Barcode barcode = new Barcode(resultSet.getInt(1), resultSet.getString(2));
			result.put(resultSet.getInt(1), barcode);
		}

		stmt.close();
		return result;
	}

	/** Retrieves all companies on database in a map of ID-Company.
	 * @param connection Database connection. 
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Company> getCompaniesTest(Connection connection) throws SQLException {
		Map<Integer, Company> result = new HashMap<Integer, Company>();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `Company`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			Company company = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			result.put(resultSet.getInt(1), company);
		}

		stmt.close();
		return result;
	}

	/** Retrieves all items on database in a map of ID-Item.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Item> getItemsTest(Connection connection) throws SQLException {
		Map<Integer, Item> result = new HashMap<Integer, Item>();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `Item`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			Item item = new Item(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5));
			result.put(resultSet.getInt(1), item);
		}

		stmt.close();
		return result;
	}

	/** Retrieves all item types on database in a map of ID-ItemType.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, String> getItemTypesTest(Connection connection) throws SQLException {
		Map<Integer, String> result = new HashMap<Integer, String>();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `ItemType`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			result.put(resultSet.getInt(1), resultSet.getString(2));
		}

		stmt.close();
		return result;
	}

	/** Retrieves all locations on database in a map of ID-Location.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Location> getLocationsTest(Connection connection) throws SQLException {
		Map<Integer, Location> result = new HashMap<Integer, Location>();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `Location`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			Location location = new Location(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
			result.put(resultSet.getInt(1), location);
		}

		stmt.close();
		return result;
	}

	/** Retrieves all privileges on database in a map of ID-Privilege.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, String> getPrivilegeTest(Connection connection) throws SQLException {
		Map<Integer, String> result = new HashMap<Integer, String>();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `Privilege`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			result.put(resultSet.getInt(1), resultSet.getString(2));
		}

		stmt.close();
		return result;
	}

	/** Retrieves all users on database in a map of ID-User.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, User> getUsersTest(Connection connection) throws SQLException {
		Map<Integer, User> result = new HashMap<Integer, User>();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SELECT * FROM `User`");

		while(stmt.getResultSet().next()) {
			ResultSet resultSet = stmt.getResultSet();
			User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
			result.put(resultSet.getInt(1), user);
		}

		stmt.close();
		return result;
	}
}