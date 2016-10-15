package team2.inventory.model;

/** User class.
 * @author James A. Donnell Jr. */
public class User {
	
	/** User Information. */
	private int id, privelages;
	/** User Information. */
	private String username, password, realname;

	public User(int id, String username, String password, String realname, int privelages) {
		this.id = id;
		this.privelages = privelages;
		this.username = username;
		this.password = password;
		this.realname = realname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrivelages() {
		return privelages;
	}

	public void setPrivelages(int privelages) {
		this.privelages = privelages;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public String toString() {
		return "[" + username + ", " + password + ", " + realname + ", " + privelages + "]";
	}
}