package team2.inventory.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import team2.inventory.controller.database.*;
import team2.inventory.model.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.SQLException;
/** Allows user to edit items
 * @author Trevor Silva */
public class EditItemPopUp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private static JLabel lblSelectedCompany;

	public EditItemPopUp(Connection connection, Item item) {
		/**************************************
		/	     Setup of the JPanel		  /
		/									  /
		/*************************************/
		super("Edit Item: " + item.getName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 669, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		/**************************************
		/	   JLabels for the contentPane	  /
		/									  /
		/*************************************/
		JLabel lblName = new JLabel("New Name:");
		contentPane.add(lblName);
		
		JLabel lblManufacturer = new JLabel("New Manufacturer:");
		contentPane.add(lblManufacturer);
		
		JLabel lblSelected = new JLabel("Selected: ");
		contentPane.add(lblSelected);		
		
		lblSelectedCompany = new JLabel("None");
		contentPane.add(lblSelectedCompany);
		
		JLabel lblDescription = new JLabel("New Description:");
		contentPane.add(lblDescription);
		
		JLabel lblNewBarcode = new JLabel("New Barcode:");
		contentPane.add(lblNewBarcode);
		
		/**************************************
		/	      Fields for input			  /
		/									  /
		/*************************************/
		JTextField newNameField = new JTextField();
		contentPane.add(newNameField);
		newNameField.setColumns(10);
		
		JTextField newDescriptionField = new JTextField();
		contentPane.add(newDescriptionField);
		newDescriptionField.setColumns(10);
		
		JTextField newBarcodeField = new JTextField();
		contentPane.add(newBarcodeField);
		newBarcodeField.setColumns(10);
		
		/**************************************
		/	    Buttons for the actions 	  /
		/									  /
		/*************************************/
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Grabs info from text boxes
				String itemName, itemDescription, itemBarcode;
				itemName = newNameField.getText();
				itemDescription = newDescriptionField.getText();
				itemBarcode = newBarcodeField.getText();
				try {
					//Checks for nulls then adds updated item to database
					if(!itemName.equals("")){
						item.setName(itemName);
					}if(!lblSelectedCompany.getText().equals("None")){
						Company c = Query.getCompanyByName(connection, lblSelectedCompany.getText());
						item.setManufacturer(c);
					}if(!itemDescription.equals("")){
						item.setDescription(itemDescription);
					}if(!itemBarcode.equals("")){
						Barcode b = new Barcode(0, itemBarcode);
						Inserter.insert(connection, b);
						item.setBarcode(b);
					}
					Updater.update(connection, item);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				setVisible(false);
				//updates the item table based off of the new edit
				try {
					ItemCatalogPopUp.updateTable(Query.getItems(connection, Query.getBarcodes(connection), Query.getCompanies(connection)));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnEnter);
		
		
		JButton btnSelectCompany = new JButton("Select Company");
		btnSelectCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//opens select company pop up
				SelectCompanyPopUp temp = new SelectCompanyPopUp(connection, false);
				temp.setVisible(true);
			}
		});
		contentPane.add(btnSelectCompany);

		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 27, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.SOUTH, lblName, -63, SpringLayout.NORTH, lblManufacturer);
		springLayout.putConstraint(SpringLayout.EAST, lblName, -530, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblName, 20, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.NORTH, lblManufacturer, 113, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblManufacturer, 20, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.NORTH, lblSelected, 4, SpringLayout.NORTH, btnEnter);
		springLayout.putConstraint(SpringLayout.EAST, lblSelected, -6, SpringLayout.WEST, lblSelectedCompany);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectedCompany, 4, SpringLayout.NORTH, btnEnter);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectedCompany, 119, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.NORTH, lblDescription, 4, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.EAST, lblDescription, -6, SpringLayout.WEST, newDescriptionField);
		springLayout.putConstraint(SpringLayout.WEST, lblDescription, 312, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblNewBarcode, 0, SpringLayout.NORTH, lblManufacturer);
		springLayout.putConstraint(SpringLayout.EAST, lblNewBarcode, -8, SpringLayout.WEST, newBarcodeField);
		springLayout.putConstraint(SpringLayout.WEST, lblNewBarcode, 0, SpringLayout.WEST, lblDescription);
		
		springLayout.putConstraint(SpringLayout.NORTH, newNameField, 1, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.EAST, newNameField, -25, SpringLayout.WEST, lblDescription);
		springLayout.putConstraint(SpringLayout.WEST, newNameField, 6, SpringLayout.EAST, lblName);

		springLayout.putConstraint(SpringLayout.NORTH, newDescriptionField, 1, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.EAST, newDescriptionField, -11, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, newDescriptionField, 433, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.NORTH, newBarcodeField, -3, SpringLayout.NORTH, lblManufacturer);
		springLayout.putConstraint(SpringLayout.EAST, newBarcodeField, -10, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, newBarcodeField, 429, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.NORTH, btnSelectCompany, -4, SpringLayout.NORTH, lblManufacturer);
		springLayout.putConstraint(SpringLayout.WEST, btnSelectCompany, 6, SpringLayout.EAST, lblManufacturer);	
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnEnter, -10, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, btnEnter, -25, SpringLayout.EAST, contentPane);
		
		/**************************************
		/  Sets the default button to enter	  /
		/	  								  /
		/*************************************/
		getRootPane().setDefaultButton(btnEnter);
	}
	/**
	 * updates the selected company to a lblSelectedCompany
	 * @param Company company
	 */
	protected static void updateSelected(Company company){
		String companyName = company.getName().toString();
		lblSelectedCompany.setText(companyName);
	}
}