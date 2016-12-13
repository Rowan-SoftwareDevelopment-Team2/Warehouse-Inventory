package team2.inventory.controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import team2.inventory.model.Barcode;
import team2.inventory.model.Company;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
import team2.inventory.model.Location;
import team2.inventory.model.User;

/** Database update methods.
 * @author James A. Donnell Jr. */
public class Updater {

	public static void update(Connection connection, Barcode barcode) throws SQLException {
		String sqlQuery = "UPDATE `Barcode` SET `Barcode`='" + barcode.getBarcode() + "' WHERE `ID`=" + barcode.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void update(Connection connection, Company company) throws SQLException {
		String sqlQuery = "UPDATE `Company` SET `Name`='" + company.getName() + "', `Email`='" + company.getEmail() 
		+ "', `Phone`='" + company.getPhone() + "', `Address`='" + company.getAddress() + "' WHERE `ID`=" + company.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void update(Connection connection, Item item) throws SQLException {
		String sqlQuery = "UPDATE `Item` SET `Name`='" + item.getName() + "', `Manufacturer`='" + item.getManufacturer().getId() 
		+ "', `Barcode`='" + item.getBarcode().getId() + "', `Description`='" + item.getDescription() + "' WHERE `ID`=" + item.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void update(Connection connection, Location location) throws SQLException {
		String sqlQuery = "UPDATE `Location` SET `Description`='" + location.getDescription() + "', `Aisle`='" + location.getAisle() 
		+ "', `Row`='" + location.getRow() + "' WHERE `ID`=" + location.getId();
		Connector.getResultSet(connection, sqlQuery);
	}
	
	/** Moves Inventory to a new location.
	 * @param connection Database connection.
	 * @param inventory Inventory to move.
	 * @param location Location to move to.
	 * @throws SQLException Thrown on any SQL Error. */
	public static void moveInventory(Connection connection, Inventory inventory, Location location) throws SQLException {
		inventory.setLocation(location);
		update(connection, inventory);
		Map<Integer, Inventory> children = Query.getInventoryByParent(connection, inventory.getId());
		Iterator<Inventory> it = children.values().iterator();
		Inventory i = null;
		while(it.hasNext()){
			i = it.next();
			i.setLocation(location);
			update(connection, i);
		}
	}
	
	/** Ships inventory today.
	 * @param connection Database connection.
	 * @param inventory Inventory to ship.
	 * @throws SQLException Thrown on any SQL Error. */
	public static void shipInventory(Connection connection, Inventory inventory) throws SQLException {
		shipInventory(connection, inventory, new Date(new java.util.Date().getTime()));
	}

	/** Ships inventory on a given date.
	 * @param connection Database connection.
	 * @param inventory Inventory to ship.
	 * @param shipped Date to ship on.
	 * @throws SQLException Thrown on any SQL Error. */
	public static void shipInventory(Connection connection, Inventory inventory, Date shipped) throws SQLException {
		Map<Integer, Inventory> children = Query.getInventoryByParent(connection, inventory.getId());
		Iterator<Inventory> it = children.values().iterator();
		Inventory i = null;
		while(it.hasNext()){
			i = it.next();
			i.setShipped(shipped);
			i.setLocation(null);
			update(connection, i);
		}
		inventory.setLocation(null);
		inventory.setShipped(shipped);
		update(connection, inventory);
	}

	public static void update(Connection connection, User user) throws SQLException {
		String sqlQuery = "UPDATE `User` SET `Username`='" + user.getUsername() + "', `Password`='" + user.getHash() 
		+ "', `RealName`='" + user.getRealname() + "', `Privileges`='" + user.getPrivileges() + "' WHERE `ID`=" + user.getId();
		Connector.getResultSet(connection, sqlQuery);
	}

	public static void update(Connection connection, Inventory inventory) throws SQLException {
		String sql = "UPDATE `Inventory` SET `Item`=";
		sql += (inventory.getItem() == null) ? "NULL" : "'" + inventory.getItem().getId() + "'";
		sql += ", `Amount`=";
		sql += (inventory.getAmount() == 0) ? "NULL" : "'" + inventory.getAmount() + "'";
		sql += ", `Supplier`=";
		sql += (inventory.getSupplier() == null) ? "NULL" : "'" + inventory.getSupplier().getId() + "'";
		sql += ", `Type`=";
		sql += (inventory.getType() == 0) ? "NULL" : "'" + inventory.getType() + "'";
		sql += ", `Parent`=";
		sql += (inventory.getParent() == 0) ? "NULL" : "'" + inventory.getParent() + "'";
		sql += ", `Received`=";
		sql += (inventory.getReceived() == null) ? "NULL" : "'" + inventory.getReceived() + "'";
		sql += ", `Shipped`=";
		sql += (inventory.getShipped() == null) ? "NULL" : "'" + inventory.getShipped() + "'";
		sql += ", `Location`=";
		sql += (inventory.getLocation() == null) ? "NULL" : "'" + inventory.getLocation().getId() + "'";
		sql += ", `Barcode`=";
		sql += (inventory.getBarcode() == null) ? "NULL" : "'" + inventory.getBarcode().getId() + "'";
		sql += " WHERE `ID`=" + inventory.getId();
		Connector.getResultSet(connection, sql);
	}
}