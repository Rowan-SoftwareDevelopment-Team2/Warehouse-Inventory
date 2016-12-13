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
import team2.inventory.model.Barcode;
import team2.inventory.model.Company;
import team2.inventory.model.Item;
/** Creates a Popup to add a new item to the database
 * @author Trevor Silva */
public class NewItemPopUp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JLabel lblSelectedCompany ;
	private SelectCompanyPopUp temp;

	public NewItemPopUp(Connection connection, boolean fromScanner) {
		/**************************************
		/	      Setup of the JPanel   	  /
		/									  /
		/*************************************/
		super("New Item");
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
		
		JLabel lblManufacturer = new JLabel("Manufacturer:");
		contentPane.add(lblManufacturer);
		
		JLabel lblSelected = new JLabel("Selected:");
		contentPane.add(lblSelected);
		
		lblSelectedCompany = new JLabel("None");
		contentPane.add(lblSelectedCompany);
		
		JLabel lblDescription = new JLabel("Description:");
		contentPane.add(lblDescription);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		contentPane.add(lblBarcode);

		/**************************************
		/	      Fields for input			  /
		/									  /
		/*************************************/
		JTextField nameField = new JTextField();
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JTextField descriptionField = new JTextField();
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);
		
		JTextField barcodeField = new JTextField();
		if(ScanItemsPopUp.isFromScanned()){
			//sets field to scanned barcode
			barcodeField.setText(ScanItemsPopUp.getScannedBarcode());
		}
		contentPane.add(barcodeField);
		barcodeField.setColumns(10);
		
		/**************************************
		/	    Buttons for the actions 	  /
		/									  /
		/*************************************/
		JButton btnSelectCompany = new JButton("Select Company");
		btnSelectCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creates a new popup to select a company
				temp = new SelectCompanyPopUp(connection, true);
				temp.setVisible(true);
			}
		});
		contentPane.add(btnSelectCompany);
		
		JButton btnEnter = new JButton("Enter");
		contentPane.add(btnEnter);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//grabs all item info from the input fields
				String itemName, itemDescription, itemBarcode;
				itemName = nameField.getText();
				itemDescription = descriptionField.getText();
				itemBarcode = barcodeField.getText();
				Barcode barcode = new Barcode(0, itemBarcode);
				Company company;
				Item item;
				try {
					//checks to ensure that all input is valid
					if(!itemBarcode.equals("") && !lblSelectedCompany.getText().equals("None") 
							&& !itemDescription.equals("") && !itemName.equals("")){
						//if all are valid insert information to database
						//and add a new item
						Inserter.insert(connection, barcode);
						company = temp.getChosenCompany();
						item = new Item(0,itemName,company, barcode, itemDescription);
						Inserter.insert(connection, item);
					} else{
						//throw an exception if there is an invalid input
						throw new NullPointerException();
					}
					if(fromScanner){
						//if the item is being created from the scanner stage
						//it will immediately call PalletSelection
						dispose();
						PalletSelectionPopUp temp = new PalletSelectionPopUp(connection, barcode, item);
						temp.setVisible(true);
					}
				} catch (SQLException | NullPointerException e1) {
					//Message to show error if any input is invalid
					JOptionPane.showMessageDialog(contentPane, "A field was left as null", "Null Pointer Exception", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					NewItemPopUp temp = new NewItemPopUp(connection, fromScanner);
					dispose();
					temp.setVisible(true);
				}
				dispose();
			}
		});
		
		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 33, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblName, 20, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.SOUTH, lblManufacturer, -56, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblManufacturer, 0, SpringLayout.WEST, lblName);

		springLayout.putConstraint(SpringLayout.NORTH, lblSelected, 0, SpringLayout.NORTH, btnEnter);
		springLayout.putConstraint(SpringLayout.EAST, lblSelected, 0, SpringLayout.EAST, lblManufacturer);

		springLayout.putConstraint(SpringLayout.NORTH, lblSelectedCompany, 0, SpringLayout.NORTH, lblSelected);
		springLayout.putConstraint(SpringLayout.EAST, lblSelectedCompany, -37, SpringLayout.WEST, btnEnter);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectedCompany, 6, SpringLayout.EAST, lblSelected);

		springLayout.putConstraint(SpringLayout.NORTH, lblDescription, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblDescription, 240, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblBarcode, 0, SpringLayout.NORTH, lblManufacturer);
		springLayout.putConstraint(SpringLayout.WEST, lblBarcode, 0, SpringLayout.WEST, lblDescription);
		
		springLayout.putConstraint(SpringLayout.NORTH, nameField, -3, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.EAST, nameField, -6, SpringLayout.WEST, lblDescription);
		springLayout.putConstraint(SpringLayout.WEST, nameField, 11, SpringLayout.EAST, lblName);
		
		springLayout.putConstraint(SpringLayout.NORTH, descriptionField, -3, SpringLayout.NORTH, lblDescription);
		springLayout.putConstraint(SpringLayout.EAST, descriptionField, 0, SpringLayout.EAST, btnEnter);
		springLayout.putConstraint(SpringLayout.WEST, descriptionField, 6, SpringLayout.EAST, lblDescription);

		springLayout.putConstraint(SpringLayout.NORTH, barcodeField, -3, SpringLayout.NORTH, lblManufacturer);
		springLayout.putConstraint(SpringLayout.EAST, barcodeField, 0, SpringLayout.EAST, descriptionField);
		springLayout.putConstraint(SpringLayout.WEST, barcodeField, 0, SpringLayout.WEST, descriptionField);
		
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
	 * Updates the selected company label to show
	 * the user selection
	 * @param company
	 */
	protected static void updateSelected(Company company){
		String companyName = company.getName().toString();
		lblSelectedCompany.setText(companyName);
	}
}
