package team2.inventory.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import team2.inventory.controller.Login;
import team2.inventory.controller.Login.LoginException;
import team2.inventory.controller.database.Query;
/** Creates a Login screen to access system
 * @author Trevor Silva */
public class LoginPopUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public LoginPopUp(Connection connection) throws SQLException {
		super("System Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 433, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		/**************************************
		/	   JLabels for the contentPane	  /
		/									  /
		/*************************************/
		JLabel lblUsername = new JLabel("Username:");
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		contentPane.add(lblPassword);
		
		/**************************************
		/	      Fields for input			  /
		/									  /
		/*************************************/
		JTextField usernameField = new JTextField();
		usernameField.setColumns(10);
		contentPane.add(usernameField);
		
		JPasswordField passwordField = new JPasswordField();
		contentPane.add(passwordField);
		
		/**************************************
		/	    Buttons for the actions 	  /
		/									  /
		/*************************************/
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					//attempts to login with given username and hash of password
					try {
						Login.login(connection, usernameField.getText(),Login.hashPassword(new String(passwordField.getPassword())));
						MainMenu temp = new MainMenu(connection, Query.getUserByUsername(connection, usernameField.getText()));
						dispose();
						WelcomeScreen.close();
						temp.setVisible(true);
					} catch (SQLException e) {
						e.printStackTrace();
					}catch (LoginException e){
						//if fails show message
						JOptionPane.showMessageDialog(null,e.getMessage(), "Login Error",JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		contentPane.add(btnLogin);

		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.SOUTH, lblUsername, -38, SpringLayout.NORTH, lblPassword);
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 0, SpringLayout.WEST, lblPassword);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 98, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, -29, SpringLayout.WEST, passwordField);

		springLayout.putConstraint(SpringLayout.NORTH, usernameField, -2, SpringLayout.NORTH, lblUsername);
		springLayout.putConstraint(SpringLayout.EAST, usernameField, -101, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, usernameField, 0, SpringLayout.WEST, passwordField);

		springLayout.putConstraint(SpringLayout.NORTH, passwordField, -2, SpringLayout.NORTH, lblPassword);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -101, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 141, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, -10, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.SOUTH, btnLogin, -10, SpringLayout.SOUTH, contentPane);
		
		/**************************************
		/  Sets the default button to enter	  /
		/	  								  /
		/*************************************/
		getRootPane().setDefaultButton(btnLogin);
	}
}
