package team2.inventory.model;

import java.sql.Date;

/** Inventory class.
 * @author James A. Donnell Jr. */
public class Inventory {
	private int id, item, amount, supplier, 
	type, parent, location, barcode;
	private Date shipped, received;

	/** Inventory 
	 * @param id Inventory line item ID.
	 * @param item Item of line item.
	 * @param amount Amount.
	 * @param supplier Item supplier.
	 * @param type Type of line item.
	 * @param parent Parent.
	 * @param received Date received.
	 * @param shipped Date shipped.
	 * @param location Location in warehouse
	 * @param barcode Barcode of pallet. */
	public Inventory(int id, int item, int amount, int supplier, int type, int parent, Date received, Date shipped, int location, int barcode) {
		this.id = id;
		this.item = item;
		this.amount = amount;
		this.supplier = supplier;
		this.type = type;
		this.parent = parent;
		this.location = location;
		this.barcode = barcode;
		this.shipped = shipped;
		this.received = received;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getSupplier() {
		return supplier;
	}

	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public Date getShipped() {
		return shipped;
	}

	public void setShipped(Date shipped) {
		this.shipped = shipped;
	}

	public Date getReceived() {
		return received;
	}

	public void setReceived(Date received) {
		this.received = received;
	}

	public String toString() {
		return "[" + id + ", " + item + ", " + amount + ", " + supplier + ", "
				+ type + ", " + parent + ", " + received + ", " 
				+ shipped + ", " + location + ", " + barcode + "]";
	}
}