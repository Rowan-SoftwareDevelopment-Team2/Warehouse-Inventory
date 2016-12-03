package team2.inventory.controller.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Database connector. By default utilizes MariaDB.
 * @author James A. Donnell Jr. */
public class Connector {

	/** MariaDB JDBC database prefix. */
	private static String prefix = "jdbc:mariadb://";
	/** Database hostname. */
	private static String defaultHostname = "warehouse.jdweb.info";
	/** Database port. */
	private static String defaultPort = "3306";
	
	/** Creates a database connection with default host and port.
	 * @param table Database table to create connection on.
	 * @param username Database username.
	 * @param password Database password.
	 * @return Connection
	 * @throws SQLException Error if issues with connection. */
	public static Connection createConnection(String table, String username, String password) throws SQLException {
		return createConnection(defaultHostname, defaultPort, table, username, password);
	}
	
	/** Creates a database connection.
	 * @param hostname Server hostname.
	 * @param port Server port.
	 * @param table Database table to create connection on.
	 * @param username Database username.
	 * @param password Database password.
	 * @return Connection
	 * @throws SQLException Error if issues with connection. */
	public static Connection createConnection(String hostname, String port, String table, String username, String password) throws SQLException {
		String connectionString = prefix + hostname + ":" + port + "/" + table + "?user=" + username + "&password=" + password;
		return DriverManager.getConnection(connectionString);
	}
	
	/** Generates a ResultSet from a supplied sqlQuery.
	 * @param connection Connection to generate SQL statements on.
	 * @param sqlQuery SQL statement/query to perform.
	 * @return ResultSet
	 * @throws SQLException Error on SQLException. */
	public static ResultSet getResultSet(Connection connection, String sqlQuery) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sqlQuery);
		statement.close();
		return result;
	}
	
	public static String getDefaultHostname() {
		return defaultHostname;
	}
	
	public static String getDefaultPort() {
		return defaultPort;
	}
}