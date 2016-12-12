package team2.inventory.controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import team2.inventory.controller.database.Query.Helper;
import team2.inventory.model.Barcode;
import team2.inventory.model.Company;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;

/** Database extended query methods. Used for Inventory table mostly.
 * @author James A. Donnell Jr. */
public class QueryInventoryExtender {

	/** Merges two Inventory Maps together.
	 * @param first First map merge.
	 * @param second Second map to merge.
	 * @return Map */
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
	
	/** For Inventory Search Bar. Searches item names and company names.
	 * @param connection Database connection.
	 * @param searchString String to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Inventory> searchBar(Connection connection, String searchString) throws SQLException {
		return merge(searchBarByItem(connection, searchString), searchBarByCompany(connection, searchString));
	}
	
	/** Sub-method for Inventory Search bar. Searches item names.
	 * @param connection Database connection.
	 * @param searchString String to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	private static Map<Integer, Inventory> searchBarByItem(Connection connection, String searchString) throws SQLException {
		Map<Integer, Item> itemMap = Query.getItemsByName(connection, searchString, Query.getBarcodes(connection), Query.getCompanies(connection));
		return searchBarByItemMap(connection, itemMap);
	}
	
	/** Sub-method for Inventory Search bar. Searches company names.
	 * @param connection Database connection.
	 * @param searchString String to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	private static Map<Integer, Inventory> searchBarByCompany(Connection connection, String searchString) throws SQLException {
		Map<Integer, Company> companyMap = Query.getCompaniesByName(connection, searchString);
		Map<Integer, Item> itemMap = new HashMap<Integer, Item>();
		
		Map<Integer, Barcode> tempBarcodeMap = Query.getBarcodes(connection);
		Map<Integer, Company> tempCompanyMap = Query.getCompanies(connection);
		for(Company company : companyMap.values())
			itemMap.putAll(Query.getItemsByManufacturer(connection, company.getId(), tempBarcodeMap, tempCompanyMap));
		
		return searchBarByItemMap(connection, itemMap);
	}
	
	/** Helper for Inventory Search bar. Searches for Inventory of certain Items.
	 * @param connection Database connection.
	 * @param itemMap Map of items to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	private static Map<Integer, Inventory> searchBarByItemMap(Connection connection, Map<Integer, Item> itemMap) throws SQLException {
		Iterator<Item> it = itemMap.values().iterator();
		
		if(!it.hasNext())
			return new HashMap<Integer, Inventory>();
		
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Item`=" + it.next().getId();
		while(it.hasNext())
			sqlQuery += " OR `Inventory`.`Item`=" + it.next().getId();
		
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	public static Map<Integer, Item> searchBarItem(Connection connection, String searchString) throws SQLException {
		Map<Integer, Barcode> tempBarcodeMap = Query.getBarcodes(connection);
		Map<Integer, Company> tempCompanyMap = Query.getCompanies(connection);
		
		Map<Integer, Item> result = new HashMap<Integer, Item>();
		result.putAll(Query.getItemsByName(connection, searchString, tempBarcodeMap, tempCompanyMap));
		result.putAll(Query.getItemsByDescription(connection, searchString, tempBarcodeMap, tempCompanyMap));
		return result;
	}
	
}