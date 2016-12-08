package team2.inventory.controller;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import team2.inventory.controller.database.Query;
import team2.inventory.model.User;

/** Login class.
 * @author James A. Donnell Jr. */
public class Login {

	protected static User login(Connection connection, String username, String password) throws SQLException, LoginException {
		String hashed = hashPassword(password);
		try {
			User user = Query.getUserByUsername(connection, username);
			if (!(user.getHash().equals(hashed)))
				throw new LoginException("Password is incorrect.");
			return user;
		} catch (NoSuchElementException e) {
			throw new LoginException("Username does not exist.");
		}
	}
	
	public static String hashPassword(String original) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(original.getBytes(Charset.forName("UTF-8")));
			byte byteData[] = md.digest();
			//convert the byte to hex format
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1)
	   	     		hexString.append('0');
	   	     	hexString.append(hex);
	    	}
	    	return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// Should never happen.
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("serial")
	public static class LoginException extends Exception {
		public LoginException(String message) {
			super(message);
		}
	}
}