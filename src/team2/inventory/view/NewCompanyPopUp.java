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
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import team2.inventory.controller.database.Inserter;
import team2.inventory.model.Company;

public class NewCompanyPopUp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public NewCompanyPopUp(Connection connection) {
		super("Add New Company");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		/**************************************
		/	   JLabels for the contentPane	  /
		/									  /
		/*************************************/
		JLabel lblName = new JLabel("Name:");
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email:");
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone:");
		contentPane.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address:");
		contentPane.add(lblAddress);

		
		JTextField nameField = new JTextField();
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JTextField emailField = new JTextField();
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		JTextField phoneField = new JTextField();
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		JTextField addressField = new JTextField();
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String companyName, companyEmail, companyPhone, companyAddress;
				companyName = nameField.getText();
				companyEmail = emailField.getText();
				companyPhone = phoneField.getText();
				companyAddress = addressField.getText();
				Company company;
				try {
					if(!companyName.equals("") && !companyEmail.equals("") 
							&& !companyPhone.equals("") && !companyAddress.equals("")){
						company = new Company(0,companyName, companyEmail, companyPhone, companyAddress);
						Inserter.insert(connection, company);
						SelectCompanyPopUp.updateTable(connection);
					} else{
						throw new NullPointerException();
					}
				} catch (SQLException | NullPointerException e1) {
					JOptionPane.showMessageDialog(contentPane, "A field was left as null", "Null Pointer Exception", JOptionPane.ERROR_MESSAGE);
					NewCompanyPopUp temp = new NewCompanyPopUp(connection);
					dispose();
					temp.setVisible(true);
				}
				dispose();
			}
		});
		contentPane.add(btnEnter);
		
		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 32, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblName, 10, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 53, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblEmail, 0, SpringLayout.WEST, lblName);

		springLayout.putConstraint(SpringLayout.NORTH, lblPhone, 0, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, lblPhone, 0, SpringLayout.WEST, lblAddress);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblAddress, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblAddress, 19, SpringLayout.EAST, nameField);
		
		springLayout.putConstraint(SpringLayout.NORTH, nameField, -3, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, nameField, 19, SpringLayout.EAST, lblName);
		springLayout.putConstraint(SpringLayout.EAST, nameField, -252, SpringLayout.EAST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, emailField, -3, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.EAST, emailField, -19, SpringLayout.WEST, lblPhone);
		springLayout.putConstraint(SpringLayout.WEST, emailField, 0, SpringLayout.WEST, nameField);

		springLayout.putConstraint(SpringLayout.NORTH, phoneField, -3, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, phoneField, 25, SpringLayout.EAST, lblPhone);
		springLayout.putConstraint(SpringLayout.EAST, phoneField, -31, SpringLayout.EAST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, addressField, -3, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, addressField, 0, SpringLayout.WEST, phoneField);
		springLayout.putConstraint(SpringLayout.EAST, addressField, -31, SpringLayout.EAST, contentPane);
		
		springLayout.putConstraint(SpringLayout.EAST, btnEnter, 0, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.SOUTH, btnEnter, 0, SpringLayout.SOUTH, contentPane);
		
		getRootPane().setDefaultButton(btnEnter);
	}
}
