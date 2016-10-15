package team2.inventory.model;

/** Location class.
 * @author James A. Donnell Jr. */
public class Location {
	/** Location Information. */
	private int id, aisle, row;
	/** Location Information. */
	private String description;
	
	public Location(int id, String description, int aisle, int row) {
		this.id = id;
		this.description = description;
		this.aisle = aisle;
		this.row = row;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAisle() {
		return aisle;
	}

	public void setAisle(int aisle) {
		this.aisle = aisle;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "[" + description + ", " + aisle + ", " + row + "]";
	}
}