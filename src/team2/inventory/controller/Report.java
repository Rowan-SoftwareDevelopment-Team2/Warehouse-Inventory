package team2.inventory.controller;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import team2.inventory.model.Company;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
import team2.inventory.model.Location;

public class Report {

	private static Charset utf = Charset.forName("UTF-8");
	private static String delim = ",";

	public static void generateItemReport(String filename, Map<Integer, Item> map) throws IOException {
		BufferedWriter bw = Files.newBufferedWriter(Paths.get(filename), utf);
		bw.write("id,Name,Manufacturer,Barcode,Description");
		for(Item item : map.values()) {
			bw.newLine();
			bw.write(item.getId() + delim + item.getName() + delim + item.getManufacturer().getName()
					+ delim + item.getBarcode().getBarcode() + delim + item.getDescription());
		}
		bw.close();
	}

	public static void generateLocationReport(String filename, Map<Integer, Location> map) throws IOException {
		BufferedWriter bw = Files.newBufferedWriter(Paths.get(filename), utf);
		bw.write("id,Description,Aisle,Row");
		for(Location location : map.values()) {
			bw.newLine();
			bw.write(location.getId() + delim + location.getDescription() + delim + location.getAisle() + delim + location.getRow());
		}
		bw.close();
	}

	public static void generateCompanyReport(String filename, Map<Integer, Company> map) throws IOException {
		BufferedWriter bw = Files.newBufferedWriter(Paths.get(filename), utf);
		bw.write("id,Name,Email,Phone,Address");
		for(Company company : map.values()) {
			bw.newLine();
			bw.write(company.getId() + delim + company.getName() + delim + company.getEmail() 
			+ delim + company.getPhone() + delim + company.getAddress().replace(delim, " ").replace("\n", " "));
		}
		bw.close();
	}

	public static void generateInventoryReport(String filename, Map<Integer, Inventory> map) throws IOException {
		BufferedWriter bw = Files.newBufferedWriter(Paths.get(filename), utf);
		bw.write("id,Item,Amount,Supplier,Type,Parent,Location,Barcode,Received,Shipped");
		for(Inventory i : map.values()) {
			String item = "", amount = "", supplier = "", type = "", parent = "", location = "", barcode = "", received = "", shipped = "";

			if(i.getItem()!=null)
				item = i.getItem().getName();
			amount = replaceZero(i.getAmount());
			if(i.getSupplier()!=null)
				supplier = i.getSupplier().getName();
			type = replaceType(i.getType());
			parent = replaceZero(i.getParent());
			if(i.getLocation()!=null)
				location = i.getLocation().getDescription();
			if(i.getBarcode()!=null)
				barcode = i.getBarcode().getBarcode();
			if(i.getReceived()!=null)
				received = i.getReceived().toString();
			if(i.getShipped()!=null)
				shipped = i.getShipped().toString();


			bw.newLine();
			bw.write(i.getId() + delim + item + delim + amount
					+ delim + supplier + delim + type + delim + parent
					+ delim + location + delim + barcode + delim + received
					+ delim + shipped);
		}
		bw.close();
	}

	private static String replaceType(int original) {
		return (original==2) ? "Pallet" : "Item";
	}

	private static String replaceZero(int original) {
		return (original==0) ? "" : original+"";
	}
	
	public static void openReport(String filename) throws IOException {
		Desktop.getDesktop().open(new File(filename));
	}
}