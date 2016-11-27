package team2.inventory.controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import team2.inventory.controller.database.Query.Helper;
import team2.inventory.model.Inventory;

public class QueryInventoryExtender {

	public static Map<Integer, Inventory> merge(Map<Integer, Inventory> first, Map<Integer, Inventory> second) {
		Map<Integer, Inventory> result = new HashMap<Integer, Inventory>(first);
		result.putAll(second);
		return result;
	}
	
	public static Map<Integer, Inventory> receivedBetween(Connection connection, Date begin, Date end) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Received` BETWEEN '" + begin + "' AND '" + end +"'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	public static Map<Integer, Inventory> receivedBefore(Connection connection, Date date) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Received` < '" + date + "'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	public static Map<Integer, Inventory> receivedAfter(Connection connection, Date date) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Received` > '" + date + "'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	public static Map<Integer, Inventory> shippedBetween(Connection connection, Date begin, Date end) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Shipped` BETWEEN '" + begin + "' AND '" + end +"'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	public static Map<Integer, Inventory> shippedBefore(Connection connection, Date date) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Shipped` < '" + date + "'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	public static Map<Integer, Inventory> shippedAfter(Connection connection, Date date) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Shipped` > '" + date + "'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	public static Map<Integer, Inventory> shipped(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Shipped` IS NOT NULL";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	public static Map<Integer, Inventory> notShipped(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Shipped` IS NULL";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	public static Map<Integer, Inventory> itemsOnly(Connection connection) throws SQLException {
		return Query.getInventoryByItemType(connection, 1);
	}
	
	public static Map<Integer, Inventory> palletsOnly(Connection connection) throws SQLException {
		return Query.getInventoryByItemType(connection, 2);
	}
	
	public static Map<Integer, Inventory> itemsWithinPallet(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Parent` IS NOT NULL";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	public static Map<Integer, Inventory> itemsNotWithinPallet(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Parent` IS NULL";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
}