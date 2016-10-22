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
	private static String hostname = "warehouse.jdweb.info";
	/** Database port. */
	private static String port = "3306";
	
	/** Creates a database connection.
	 * @param table Database table to create connection on.
	 * @param username Database username.
	 * @param password Database password.
	 * @return Connection
	 * @throws SQLException Error if issues with connection. */
	public static Connection createConnection(String table, String username, String password) throws SQLException {
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
}