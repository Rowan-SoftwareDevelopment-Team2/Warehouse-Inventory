package team2.inventory.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import team2.inventory.controller.database.Query;
import team2.inventory.model.User;

public class Login {

	protected static User login(Connection connection, String username, String password) throws SQLException, LoginException {
		String hashed = hashPassword(password);
		try {
			User user = Query.getUserByUsername(connection, username);
			if (!(user.getPassword().equals(hashed))) {
				throw new LoginException("Password is incorrect.");
			}
			return user;
		}
		catch (NoSuchElementException e) {
			throw new LoginException("Username does not exist.");
		}
	}
	
	private static String hashPassword(String original) {
		return original;
	}
	
	@SuppressWarnings("serial")
	public static class LoginException extends Exception {
		public LoginException(String message) {
			super(message);
		}
	}
}