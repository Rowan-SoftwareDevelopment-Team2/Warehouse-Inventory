package team2.inventory.model;

/** Item class.
 * @author James A. Donnell Jr. */
public class Item {
	/** Item Information. */
	private int id;
	/** Item Information. */
	private Barcode barcode;
	/** Item Information. */
	private Company manufacturer;
	/** Item Information. */
	private String name, description;
	
	public Item(int id, String name, Company manufacturer, Barcode barcode, String description) {
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.barcode = barcode;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Company manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Barcode getBarcode() {
		return barcode;
	}

	public void setBarcode(Barcode barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "[" + name + ", " + manufacturer + ", " + barcode + ", " + description + "]";
	}
}