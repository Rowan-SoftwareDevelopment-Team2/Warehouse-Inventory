package team2.inventory.model;

import team2.inventory.controller.Login;

/** User class.
 * @author James A. Donnell Jr. */
public class User {
	
	/** User Information. */
	private int id, privileges;
	/** User Information. */
	private String username, hash, realname;

	public User(int id, String username, String password, String realname, int privilages) {
		this.id = id;
		this.privileges = privilages;
		this.username = username;
		this.hash = Login.hashPassword(password);
		this.realname = realname;
	}
	
	public User(int id, String username, String realname, int privilages) {
		this.id = id;
		this.privileges = privilages;
		this.username = username;
		this.realname = realname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrivileges() {
		return privileges;
	}

	public void setPrivileges(int privelages) {
		this.privileges = privelages;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHash() {
		return hash;
	}

	public void setPassword(String password) {
		this.hash = Login.hashPassword(password);
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public String toString() {
		return "[" + username + ", " + hash + ", " + realname + ", " + privileges + "]";
	}
}