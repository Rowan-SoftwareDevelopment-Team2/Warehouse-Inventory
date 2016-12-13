package team2.inventory.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import team2.inventory.controller.Login;
import team2.inventory.controller.database.Inserter;
import team2.inventory.controller.database.Query;
import team2.inventory.model.User;
/** Create a New User Pop Up.
 * @author Trevor Silva */
public class CreateNewUserPopUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private JPanel contentPane;
		
	public CreateNewUserPopUp(Connection connection) {
		/**************************************
		/	      Setup of the JPanel	 	  /
		/									  /
		/*************************************/
		super("Create New User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 578, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		/**************************************
		/	   JLabels for the contentPane	  /
		/									  /
		/*************************************/
		JLabel lblRealName = new JLabel("Real Name:");
		contentPane.add(lblRealName);
		
		JLabel lblNewUsername = new JLabel("New Username:");
		contentPane.add(lblNewUsername);
		
		JLabel lblNewPassword = new JLabel("New Password:");
		contentPane.add(lblNewPassword);

		JLabel lblgreaterThan = new JLabel("(Greater than 8 Characters)");
		contentPane.add(lblgreaterThan);
			
		/**************************************
		/	  Fields for the contentPane	  /
		/									  /
		/*************************************/
		JTextField realnameField = new JTextField();
		contentPane.add(realnameField);
		realnameField.setColumns(10);
		
		JTextField usernameField = new JTextField();
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JPasswordField passwordField = new JPasswordField();
		contentPane.add(passwordField);
		
		/**************************************
		/     Buttons for the contentPane     /
		/									  /
		/*************************************/
		JRadioButton rdbtnManager = new JRadioButton("Manager");
		
		JRadioButton rdbtnNonmanager = new JRadioButton("Non-Manager");
		
		ButtonGroup btnGrpUsers = new ButtonGroup();
		btnGrpUsers.add(rdbtnManager);
		btnGrpUsers.add(rdbtnNonmanager);
		rdbtnNonmanager.setSelected(true);
		contentPane.add(rdbtnManager);
		contentPane.add(rdbtnNonmanager);
		
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User newUser;
				try {
					//Checks to makes sure fields meet requirements
					if(!usernameField.equals("") && !realnameField.equals("") && !passwordField.equals("") 
							&& passwordField.getPassword().length > 7){
						//Checks if username already exists
						Query.getUserByUsername(connection, usernameField.getText());
						//if not throw error and retry creation
						JOptionPane.showMessageDialog(contentPane, "Username already exists", "Creation Error", JOptionPane.ERROR_MESSAGE);
						CreateNewUserPopUp temp = new CreateNewUserPopUp(connection);
						dispose();
						temp.setVisible(true);
					} else{
						throw new NullPointerException();
					}
				} catch (SQLException | NoSuchElementException e1) {
					//Creates new user based off of input
					if(rdbtnNonmanager.isSelected()){
						newUser = new User(0, usernameField.getText(),
								Login.hashPassword(new String(passwordField.getPassword())), realnameField.getText(), 0);
					}else{
						newUser = new User(0, usernameField.getText(),
								Login.hashPassword(new String(passwordField.getPassword())), realnameField.getText(), 1);
					}
					//inserts new User to table
					try {
						Inserter.insert(connection, newUser);
						dispose();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				} catch(NullPointerException e2){
					//Last check to ensure fields aren't null
					JOptionPane.showMessageDialog(contentPane, "A field is null", "Creation Error", JOptionPane.ERROR_MESSAGE);
					CreateNewUserPopUp temp = new CreateNewUserPopUp(connection);
					dispose();
					temp.setVisible(true);
				}
			}
		});
		contentPane.add(btnCreate);
		
		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, lblRealName, 3, SpringLayout.NORTH, realnameField);
		springLayout.putConstraint(SpringLayout.EAST, lblRealName, 0, SpringLayout.EAST, lblNewUsername);
		springLayout.putConstraint(SpringLayout.WEST, lblRealName, 10, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblNewUsername, 3, SpringLayout.NORTH, usernameField);
		springLayout.putConstraint(SpringLayout.EAST, lblNewUsername, -434, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblNewUsername, 10, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblNewPassword, 55, SpringLayout.SOUTH, lblNewUsername);
		springLayout.putConstraint(SpringLayout.EAST, lblNewPassword, -434, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblNewPassword, 10, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblgreaterThan, 6, SpringLayout.SOUTH, lblNewPassword);
		springLayout.putConstraint(SpringLayout.WEST, lblgreaterThan, 0, SpringLayout.WEST, lblNewUsername);

		springLayout.putConstraint(SpringLayout.NORTH, realnameField, 27, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, realnameField, -125, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, realnameField, 151, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.SOUTH, usernameField, -123, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, usernameField, -125, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, usernameField, 151, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 49, SpringLayout.SOUTH, usernameField);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, usernameField);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -125, SpringLayout.EAST, contentPane);

		springLayout.putConstraint(SpringLayout.NORTH, rdbtnManager, -1, SpringLayout.NORTH, btnCreate);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnManager, -115, SpringLayout.WEST, rdbtnNonmanager);
		
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNonmanager, 0, SpringLayout.NORTH, btnCreate);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnNonmanager, 0, SpringLayout.EAST, passwordField);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnCreate, 210, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCreate, -10, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, btnCreate, -10, SpringLayout.EAST, contentPane);
		
		/**************************************
		/	     Sets Default Button     	  /
		/									  /
		/*************************************/
		getRootPane().setDefaultButton(btnCreate);
	}
}