package team2.inventory.model;

public class Barcode {

	private int id;
	private String barcode;

	public Barcode(int id, String barcode) {
		this.id = id;
		this.barcode = barcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public String toString() {
		return "[" + barcode + "]";
	}
}