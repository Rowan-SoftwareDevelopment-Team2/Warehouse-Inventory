package team2.inventory.controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import team2.inventory.model.Barcode;
import team2.inventory.model.Company;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
import team2.inventory.model.Location;
import team2.inventory.model.User;

/** Database query methods.
 * @author James A. Donnell Jr. */
public class Query {

	/* -----------------------------
	 *        Barcode Methods
	 * ----------------------------- */

	/** Retrieves all barcodes on database in a map of ID-to-Barcode.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Barcode> getBarcodes(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Barcode`";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toBarcodeMap(resultSet);
	}

	/** Retrieves a Barcode on database by ID.
	 * @param connection Database connection.
	 * @param id ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Barcode */
	public static Barcode getBarcodeByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `Barcode` WHERE `Barcode`.`ID`=" + id;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toBarcodeMap(resultSet).get(id);
	}
	
	/** Retrieves a Barcode on database by barcode string.
	 * @param connection Database connection.
	 * @param barcodeSearch Barcode to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Barcode */
	public static Barcode getBarcodeByBarcode(Connection connection, String barcodeSearch) throws SQLException {
		return getBarcodesByBarcode(connection, barcodeSearch).values().iterator().next();
	}

	/** Retrieves all barcodes on database in a map of ID-to-Barcode.
	 * @param connection Database connection.
	 * @param barcodeSearch Barcode to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Barcode> getBarcodesByBarcode(Connection connection, String barcodeSearch) throws SQLException {
		String sqlQuery = "SELECT * FROM `Barcode` WHERE `Barcode`.`Barcode` LIKE '%" + barcodeSearch + "%'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toBarcodeMap(resultSet);
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
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toCompanyMap(resultSet);
	}

	/** Retrieves all companies on database with a specific Name in a map of ID-to-Company.
	 * @param connection Database connection.
	 * @param name Company name to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Company> getCompaniesByName(Connection connection, String name) throws SQLException {
		String sqlQuery = "SELECT * FROM `Company` WHERE `Company`.`Name` LIKE '%" + name + "%'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toCompanyMap(resultSet);
	}
	
	/** Retrieves all companies on database with a specific Email in a map of ID-to-Company.
	 * @param connection Database connection.
	 * @param email Company email to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Company> getCompaniesByEmail(Connection connection, String email) throws SQLException {
		String sqlQuery = "SELECT * FROM `Company` WHERE `Company`.`Email` LIKE '%" + email + "%'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toCompanyMap(resultSet);
	}
	
	/** Retrieves all companies on database with a specific Phone in a map of ID-to-Company.
	 * @param connection Database connection.
	 * @param phone Company phone to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Company> getCompaniesByPhone(Connection connection, String phone) throws SQLException {
		String sqlQuery = "SELECT * FROM `Company` WHERE `Company`.`Phone` LIKE '%" + phone + "%'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toCompanyMap(resultSet);
	}
	
	/** Retrieves all companies on database with a specific Address in a map of ID-to-Company.
	 * @param connection Database connection.
	 * @param address Company address to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Company> getCompaniesByAddress(Connection connection, String address) throws SQLException {
		String sqlQuery = "SELECT * FROM `Company` WHERE `Company`.`Address` LIKE '%" + address + "%'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toCompanyMap(resultSet);
	}
	
	/** Retrieves a single Company on database based on ID.
	 * @param connection Database connection. 
	 * @param id ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Company */
	public static Company getCompanyByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `Company` WHERE `Company`.`ID`=" + id;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toCompanyMap(resultSet).get(id);
	}
	
	/** Retrieves a single Company on database based on Name.
	 * @param connection Database connection. 
	 * @param name Company name to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Company */
	public static Company getCompanyByName(Connection connection, String name) throws SQLException {
		return getCompaniesByName(connection, name).values().iterator().next();
	}
	
	/** Retrieves a single Company on database based on E-mail.
	 * @param connection Database connection. 
	 * @param email Company e-mail to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Company */
	public static Company getCompanyByEmail(Connection connection, String email) throws SQLException {
		return getCompaniesByEmail(connection, email).values().iterator().next();
	}
	
	/** Retrieves a single Company on database based on Phone number.
	 * @param connection Database connection. 
	 * @param phone Company phone to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Company */
	public static Company getCompanyByPhone(Connection connection, String phone) throws SQLException {
		return getCompaniesByPhone(connection, phone).values().iterator().next();
	}
	
	/** Retrieves a single Company on database based on Address.
	 * @param connection Database connection. 
	 * @param address Company address to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Company */
	public static Company getCompanyByAddress(Connection connection, String address) throws SQLException {
		return getCompaniesByAddress(connection, address).values().iterator().next();
	}
	
	/** Retrieves the last Company inserted on database.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Company */
	public static Company getCompanyLastInserted(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Company` WHERE `Company`.`ID` = LAST_INSERT_ID()";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toCompanyMap(resultSet).values().iterator().next();
	}

	/* -----------------------------
	 *         Item Methods
	 * ----------------------------- */

	/** Retrieves all items on database in a map of ID-to-Item.
	 * @param connection Database connection.
	 * @param barcodeMap Current Integer-to-Barcode map.
	 * @param companyMap Current Integer-to-Company map.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Item> getItems(Connection connection, Map<Integer, Barcode> barcodeMap, Map<Integer, Company> companyMap) throws SQLException {
		String sqlQuery = "SELECT * FROM `Item`";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toItemMap(resultSet, barcodeMap, companyMap);
	}
	
	/** Retrieves all items on database with a specific Name in a map of ID-to-Item.
	 * @param connection Database connection.
	 * @param name Item name to search for.
	 * @param barcodeMap Current Integer-to-Barcode map.
	 * @param companyMap Current Integer-to-Company map.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Item> getItemsByName(Connection connection, String name, Map<Integer, Barcode> barcodeMap, Map<Integer, Company> companyMap) throws SQLException {
		String sqlQuery = "SELECT * FROM `Item` WHERE `Item`.`Name` LIKE '%" + name + "%'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toItemMap(resultSet, barcodeMap, companyMap);
	}
	
	/** Retrieves all items on database with a specific Manufacturer in a map of ID-to-Item.
	 * @param connection Database connection.
	 * @param manufacturerID Manufacturer ID to search for.
	 * @param barcodeMap Current Integer-to-Barcode map.
	 * @param companyMap Current Integer-to-Company map.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Item> getItemsByManufacturer(Connection connection, int manufacturerID, Map<Integer, Barcode> barcodeMap, Map<Integer, Company> companyMap) throws SQLException {
		String sqlQuery = "SELECT * FROM `Item` WHERE `Item`.`Manufacturer`='" + manufacturerID + "'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toItemMap(resultSet, barcodeMap, companyMap);
	}
	
	/** Retrieves all items on database with a specific Barcode in a map of ID-to-Item.
	 * @param connection Database connection.
	 * @param barcodeID Barcode ID to search for.
	 * @param barcodeMap Current Integer-to-Barcode map.
	 * @param companyMap Current Integer-to-Company map.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Item> getItemsByBarcode(Connection connection, int barcodeID, Map<Integer, Barcode> barcodeMap, Map<Integer, Company> companyMap) throws SQLException {
		String sqlQuery = "SELECT * FROM `Item` WHERE `Item`.`Barcode`='" + barcodeID + "'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toItemMap(resultSet, barcodeMap, companyMap);
	}
	
	/** Retrieves all items on database with a specific Description in a map of ID-to-Item.
	 * @param connection Database connection.
	 * @param description Item description to search for.
	 * @param barcodeMap Current Integer-to-Barcode map.
	 * @param companyMap Current Integer-to-Company map.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Item> getItemsByDescription(Connection connection, String description, Map<Integer, Barcode> barcodeMap, Map<Integer, Company> companyMap) throws SQLException {
		String sqlQuery = "SELECT * FROM `Item` WHERE `Item`.`Description` LIKE '%" + description + "%'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toItemMap(resultSet, barcodeMap, companyMap);
	}

	/** Retrieves a single item on database in a map of ID-to-Item based on ID.
	 * @param connection Database connection.
	 * @param id ID to search for.
	 * @param barcodeMap Current Integer-to-Barcode map.
	 * @param companyMap Current Integer-to-Company map.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Item */
	public static Item getItemByID(Connection connection, int id, Map<Integer, Barcode> barcodeMap, Map<Integer, Company> companyMap) throws SQLException {
		String sqlQuery = "SELECT * FROM `Item` WHERE `Item`.`ID`=" + id;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toItemMap(resultSet, barcodeMap, companyMap).get(id);
	}
	
	/** Helper method to retrieve a single item on database in a map of ID-to-Item based on ID.
	 * Internally generates Barcode and Company maps.
	 * @param connection Database connection.
	 * @param id ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Item */
	public static Item getItemByID(Connection connection, int id) throws SQLException {
		return getItemByID(connection, id, Query.getBarcodes(connection), Query.getCompanies(connection));
	}
	
	/** Retrieves a single item on database in a map of ID-to-Item based on Name.
	 * @param connection Database connection.
	 * @param name Item name to search for.
	 * @param barcodeMap Current Integer-to-Barcode map.
	 * @param companyMap Current Integer-to-Company map.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Item getItemByName(Connection connection, String name, Map<Integer, Barcode> barcodeMap, Map<Integer, Company> companyMap) throws SQLException {
		return getItemsByName(connection, name, barcodeMap, companyMap).values().iterator().next();
	}
	
	/** Retrieves a single item on database in a map of ID-to-Item based on Description.
	 * @param connection Database connection.
	 * @param description Item description to search for.
	 * @param barcodeMap Current Integer-to-Barcode map.
	 * @param companyMap Current Integer-to-Company map.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Item getItemByDescription(Connection connection, String description, Map<Integer, Barcode> barcodeMap, Map<Integer, Company> companyMap) throws SQLException {
		return getItemsByDescription(connection, description, barcodeMap, companyMap).values().iterator().next();
	}
	
	/** Retrieves the last Item inserted on database.
	 * @param connection Database connection.
	 * @param barcodeMap Current Integer-to-Barcode map.
	 * @param companyMap Current Integer-to-Company map.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Item */
	public static Item getItemLastInserted(Connection connection, Map<Integer, Barcode> barcodeMap, Map<Integer, Company> companyMap) throws SQLException {
		String sqlQuery = "SELECT * FROM `Item` WHERE `Item`.`ID` = LAST_INSERT_ID()";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toItemMap(resultSet, barcodeMap, companyMap).values().iterator().next();
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
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toItemTypeMap(resultSet);
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
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toLocationMap(resultSet);
	}
	
	/** Retrieves all locations on database with a specific Description in a map of ID-to-Location.
	 * @param connection Database connection.
	 * @param description Location description to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Location> getLocationsByDescription(Connection connection, String description) throws SQLException {
		String sqlQuery = "SELECT * FROM `Location` WHERE `Location`.`Description` LIKE '%" + description + "%'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toLocationMap(resultSet);
	}

	/** Retrieves a single location on database in a map of ID-to-Location based on ID.
	 * @param connection Database connection.
	 * @param id ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Location */
	public static Location getLocationByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `Location` WHERE `Location`.`ID`=" + id;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toLocationMap(resultSet).get(id);
	}
	
	/** Retrieves a single location on database in a map of ID-to-Location based on Description.
	 * @param connection Database connection.
	 * @param description Location description to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Location */
	public static Location getLocationByDescription(Connection connection, String description) throws SQLException {
		return getLocationsByDescription(connection, description).values().iterator().next();
	}
	
	/** Retrieves a single location on database in a map of ID-to-Location based on Aisle and Row.
	 * @param connection Database connection.
	 * @param aisle Location aisle to search for.
	 * @param row Location row to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Location */
	public static Location getLocationByAisleRow(Connection connection, int aisle, int row) throws SQLException {
		String sqlQuery = "SELECT * FROM `Location` WHERE `Location`.`Aisle`=" + aisle + " AND `Location`.`Row`=" + row;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toLocationMap(resultSet).values().iterator().next();
	}
	
	/** Retrieves the last Location inserted on database.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Location */
	public static Location getLocationLastInserted(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Location` WHERE `Location`.`ID` = LAST_INSERT_ID()";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toLocationMap(resultSet).values().iterator().next();
	}
	
	/** Retrieves the highest aisle number on database.
	 * @throws SQLException Database connection.
	 * @param connection
	 * @return int */
	public static int getMaxAisle(Connection connection) throws SQLException {
		String sqlQuery = "SELECT MAX(`Aisle`) FROM `Location`";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.getMaxResult(resultSet);
	}
	
	/** Retrieves the highest row number on database.
	 * @throws SQLException Database connection.
	 * @param connection
	 * @return int */
	public static int getMaxRow(Connection connection) throws SQLException {
		String sqlQuery = "SELECT MAX(`Row`) FROM `Location`";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.getMaxResult(resultSet);
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
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toPrivilegesMap(resultSet);
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
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toUserMap(resultSet);
	}
	
	/** Retrieves all users on database with a specific Real Name in a map of ID-to-User.
	 * @param connection Database connection.
	 * @param realName User real name to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, User> getUsersByRealName(Connection connection, String realName) throws SQLException {
		String sqlQuery = "SELECT * FROM `User` WHERE `User`.`RealName` LIKE '%" + realName + "%'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toUserMap(resultSet);
	}
	
	/** Retrieves all users on database with a specific Privilege in a map of ID-to-User.
	 * @param connection Database connection.
	 * @param privilegeID User privilege to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, User> getUsersByPrivilege(Connection connection, int privilegeID) throws SQLException {
		String sqlQuery = "SELECT * FROM `User` WHERE `User`.`Privileges`=" + privilegeID;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toUserMap(resultSet);
	}

	/** Retrieves a single user on database in a map of ID-to-User based on ID.
	 * @param connection Database connection.
	 * @param id ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return User */
	public static User getUserByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `User` WHERE `User`.`ID`=" + id;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toUserMap(resultSet).get(id);
	}
	
	/** Retrieves a single user on database in a map of ID-to-User based on Real Name.
	 * @param connection Database connection.
	 * @param realName User's real name to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return User */
	public static User getUserByRealName(Connection connection, String realName) throws SQLException {
		return getUsersByRealName(connection, realName).values().iterator().next();
	}
	
	/** Retrieves a single user on database in a map of ID-to-User based on username.
	 * @param connection Database connection.
	 * @param username Username to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return User */
	public static User getUserByUsername(Connection connection, String username) throws SQLException {
		String sqlQuery = "SELECT * FROM `User` WHERE `User`.`Username`='" + username + "'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toUserMap(resultSet).values().iterator().next();
	}
	
	/** Retrieves the last User inserted on database.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return User */
	public static User getUserLastInserted(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `User` WHERE `User`.`ID` = LAST_INSERT_ID()";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return ResultSetParser.toUserMap(resultSet).values().iterator().next();
	}

	/* -----------------------------
	 *      Inventory Methods
	 * ----------------------------- */
	
	/** Retrieves all Inventory entries on the database in a map of ID-to-Inventory.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Inventory> getInventory(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory`";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	/** Retrieves all Inventory entries on the database of a specified Item in a map of ID-to-Inventory.
	 * @param connection Database connection.
	 * @param itemID Item ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Inventory> getInventoryByItem(Connection connection, int itemID) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Item`=" + itemID;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	/** Retrieves all Inventory entries on the database of a specified Supplier in a map of ID-to-Inventory.
	 * @param connection Database connection.
	 * @param supplierID Company ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Inventory> getInventoryBySupplier(Connection connection, int supplierID) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Supplier`=" + supplierID;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	/** Retrieves all Inventory entries on the database of a specified ItemType in a map of ID-to-Inventory.
	 * @param connection Database connection.
	 * @param itemTypeID ItemType ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Inventory> getInventoryByItemType(Connection connection, int itemTypeID) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Type`=" + itemTypeID;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	/** Retrieves all Inventory entries on the database of a specified Parent in a map of ID-to-Inventory.
	 * @param connection Database connection.
	 * @param parentID Parent ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Inventory> getInventoryByParent(Connection connection, int parentID) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Parent`=" + parentID;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	/** Retrieves all Inventory entries on the database of a specified Received Date in a map of ID-to-Inventory.
	 * @param connection Database connection.
	 * @param received Received date to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Inventory> getInventoryByReceived(Connection connection, Date received) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Received`='" + received + "'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	/** Retrieves all Inventory entries on the database of a specified Shipped Date in a map of ID-to-Inventory.
	 * @param connection Database connection.
	 * @param shipped Shipped date to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Inventory> getInventoryByShipped(Connection connection, Date shipped) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Shipped`='" + shipped + "'";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	/** Retrieves all Inventory entries on the database of a specified Location in a map of ID-to-Inventory.
	 * @param connection Database connection.
	 * @param locationID Location ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Inventory> getInventoryByLocation(Connection connection, int locationID) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Location`=" + locationID;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}
	
	/** Retrieves all Inventory entries on the database of a specified Barcode in a map of ID-to-Inventory.
	 * @param connection Database connection.
	 * @param barcodeID Barcode ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Map<Integer, Inventory> getInventoryByBarcode(Connection connection, int barcodeID) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`Barcode`=" + barcodeID;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet);
	}

	/** Retrieves a single Inventory entry on the database based on ID.
	 * @param connection Database connection.
	 * @param id Inventory ID to search for.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Map */
	public static Inventory getInventoryByID(Connection connection, int id) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`ID`=" + id;
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet).get(id);
	}
	
	/** Retrieves the last Inventory inserted on database.
	 * @param connection Database connection.
	 * @throws SQLException Thrown on any SQL Error.
	 * @return Inventory */
	public static Inventory getInventoryLastInserted(Connection connection) throws SQLException {
		String sqlQuery = "SELECT * FROM `Inventory` WHERE `Inventory`.`ID` = LAST_INSERT_ID()";
		ResultSet resultSet = Connector.getResultSet(connection, sqlQuery);
		return Helper.toInventoryMap(connection, resultSet).values().iterator().next();
	}
	
	/** Query helper class.
	 * @author James A. Donnell Jr. */
	protected static class Helper {
		/** Generates all requirements to instantiate Inventory objects from database data.
		 * @param connection Database connection.
		 * @param resultSet ResultSet generated from Inventory table query.
		 * @throws SQLException Thrown on any SQL Error.
		 * @return Map */
		protected static Map<Integer, Inventory> toInventoryMap(Connection connection, ResultSet resultSet) throws SQLException {
			Map<Integer, Barcode> barcodeMap = getBarcodes(connection);
			Map<Integer, Company> companyMap = getCompanies(connection);
			Map<Integer, Item> itemMap = getItems(connection, barcodeMap, companyMap);
			Map<Integer, Location> locationMap = getLocations(connection);
			return ResultSetParser.toInventoryMap(resultSet, itemMap, companyMap, locationMap, barcodeMap);
		}
	}
}