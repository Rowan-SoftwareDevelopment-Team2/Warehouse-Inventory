package team2.inventory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.print.PrintException;

import team2.inventory.controller.Login.LoginException;
import team2.inventory.controller.database.Connector;
import team2.inventory.controller.database.Query;

/** Database testing driver.
 * @author James A. Donnell Jr. */
public class DatabaseTestDriver {

	/** Testing various database methods.
	 * @param args Database table, username and password on separate lines. 
	 * @throws LoginException 
	 * @throws IOException 
	 * @throws PrintException */
	public static void main(String[] args) throws LoginException, IOException, PrintException {
		try {
			Connection connection = Connector.createConnection(args[0], args[1], args[2]);

			// How to create a new pallet Inventory
			// This will create a pallet, create a unique ID, generate a barcode, and save the barcode PDF for later printing.
			// Inventory inventory = new Inventory(0, null, 0, null, 2, 0, null, null, Query.getLocationByDescription(connection, "Dock"), null);
			// Inserter.insert(connection, inventory);


			// How to create a new item Inventory
			// Creates inventory of Item (id 40) from supplier JD Supply (id of 10)
			// Barcode stays null, barcode should be found in Item, not inventory, for item ItemTypes.
			// Received date set, shipped date null for now until it is shipped.
			// Parent is null, change to a pallet Inventory ID if desired to place in a pallet.
			// Inventory inventory = new Inventory(0, Query.getItemByID(connection, 40),
			//		999, Query.getCompanyByID(connection, 10), 1, 0, Date.valueOf("2016-11-28"), null, Query.getLocationByDescription(connection, "Dock"), null);
			// Inserter.insert(connection, inventory);


			// How to generate a report
			// Use any Query, send it to Report.generate???Report()
			// Open it using Report.openReport()
			// Example 1
			// String filename = "F:\\Desktop\\test.csv";
			// Report.generateInventoryReport(filename, QueryInventoryExtender.itemsWithinPallet(connection));
			// Report.openReport(filename);
			//
			// Example 2
			// String filename2 = "F:\\Desktop\\test2.csv";
			// Report.generateInventoryReport(filename2, Query.getInventory(connection));
			// Report.openReport(filename2);


			// Move inventory to location
			// Inventory inventory = Query.getInventoryByID(connection, 30);
			// Location location = Query.getLocationByID(connection, 13);
			// Updater.moveInventory(connection, inventory, location);


			// Ship Inventory
			// Inventory inventory = Query.getInventoryByID(connection, 27);
			// Updater.shipInventory(connection, inventory);


			// Search bar
			// Use this method for your search bar.
			// System.out.println(QueryInventoryExtender.searchBar(connection, "Angel"));

			
			// Print a barcode
			// Inventory pallet = QueryInventoryExtender.palletsOnly(connection).values().iterator().next();
			// BarcodeGenImage.generateBarcode(pallet); // only used for testing, should be generated locally if inserted.
			// BarcodeGenImage.printBarcode(pallet);
			
			System.out.println(Query.getInventory(connection));

			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}