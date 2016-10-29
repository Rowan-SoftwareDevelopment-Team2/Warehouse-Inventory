package team2.inventory.controller.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import team2.inventory.model.Barcode;
import team2.inventory.model.Company;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
import team2.inventory.model.Location;
import team2.inventory.model.User;

/** Database ResultSet Parser.
 * @author James A. Donnell Jr. */
public class ResultSetParser {

	protected static Map<Integer, Barcode> toBarcodeMap(ResultSet resultSet) throws SQLException {
		Map<Integer, Barcode> result = new HashMap<Integer, Barcode>();
		while(resultSet.next()) {
			Barcode barcode = new Barcode(resultSet.getInt(1), resultSet.getString(2));
			result.put(resultSet.getInt(1), barcode);
		}
		return result;
	}

	protected static Map<Integer, Company> toCompanyMap(ResultSet resultSet) throws SQLException {
		Map<Integer, Company> result = new HashMap<Integer, Company>();
		while(resultSet.next()) {
			Company company = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			result.put(resultSet.getInt(1), company);
		}
		return result;
	}

	protected static Map<Integer, Item> toItemMap(ResultSet resultSet) throws SQLException {
		Map<Integer, Item> result = new HashMap<Integer, Item>();
		while(resultSet.next()) {
			Item item = new Item(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5));
			result.put(resultSet.getInt(1), item);
		}
		return result;
	}

	protected static Map<Integer, String> toItemTypeMap(ResultSet resultSet) throws SQLException {
		Map<Integer, String> result = new HashMap<Integer, String>();
		while(resultSet.next()) {
			result.put(resultSet.getInt(1), resultSet.getString(2));
		}
		return result;
	}

	protected static Map<Integer, Location> toLocationMap(ResultSet resultSet) throws SQLException {
		Map<Integer, Location> result = new HashMap<Integer, Location>();
		while(resultSet.next()) {
			Location location = new Location(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
			result.put(resultSet.getInt(1), location);
		}
		return result;
	}

	protected static Map<Integer, String> toPrivilegesMap(ResultSet resultSet) throws SQLException {
		Map<Integer, String> result = new HashMap<Integer, String>();
		while(resultSet.next()) {
			result.put(resultSet.getInt(1), resultSet.getString(2));
		}
		return result;
	}
	
	protected static Map<Integer, User> toUserMap(ResultSet resultSet) throws SQLException {
		Map<Integer, User> result = new HashMap<Integer, User>();
		while(resultSet.next()) {
			User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
			result.put(resultSet.getInt(1), user);
		}
		return result;
	}
	
	protected static Map<Integer, Inventory> toInventoryMap(ResultSet resultSet) throws SQLException {
		Map<Integer, Inventory> result = new HashMap<Integer, Inventory>();
		while(resultSet.next()) {
			Inventory inventory = new Inventory(resultSet.getInt(1), resultSet.getInt(2), 
					resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), 
					resultSet.getInt(6), resultSet.getDate(7), resultSet.getDate(8), 
					resultSet.getInt(9), resultSet.getInt(10));
			result.put(resultSet.getInt(1), inventory);
		}
		return result;
	}
}