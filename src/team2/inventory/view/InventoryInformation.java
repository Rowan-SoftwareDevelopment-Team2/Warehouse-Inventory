package team2.inventory.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Calendar;
import java.util.NoSuchElementException;

import team2.inventory.controller.database.Inserter;
import team2.inventory.controller.database.Query;
import team2.inventory.controller.database.Updater;
import team2.inventory.model.Barcode;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
import team2.inventory.model.Location;
/** Creates a Popup asking for Inventory Information
 * @author Trevor Silva */
public class InventoryInformation extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField aisleField;
	private JTextField rowField;
	private JTextField locationField;
	
	public InventoryInformation(Connection connection, Barcode barcode, Item item, int palletChoice, Inventory inventory){
		/**************************************
		/	      Setup of the JPanel   	  /
		/									  /
		/*************************************/
		super("Inventory Informtation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 195);
		contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		/**************************************
		/	   JLabels for the contentPane	  /
		/									  /
		/*************************************/
		JLabel lblHowMany = new JLabel("How many of the specific item would you like?");
		contentPane.add(lblHowMany);
		
		/**************************************
		/	      Fields for input			  /
		/									  /
		/*************************************/
		JTextField howManyField = new JTextField();
		contentPane.add(howManyField);
		howManyField.setColumns(10);
		
		//if a new pallet is being created other fields are shown
		if(palletChoice == 1){
			/**************************************
			/	   JLabels for the contentPane	  /
			/									  /
			/*************************************/
			JLabel lblAisle = new JLabel("Aisle:");
			contentPane.add(lblAisle);
		
			JLabel lblRow = new JLabel("Row:");
			contentPane.add(lblRow);
			
			JLabel lblSelectLocation = new JLabel("Select Location:");
			contentPane.add(lblSelectLocation);
		
			/**************************************
			/	      Fields for input			  /
			/									  /
			/*************************************/
			locationField = new JTextField();
			contentPane.add(locationField);
			locationField.setColumns(10);
		
			aisleField = new JTextField();
			contentPane.add(aisleField);
			aisleField.setColumns(10);
		
			rowField = new JTextField();
			rowField.setColumns(10);
			contentPane.add(rowField);
			
			/**************************************
			/	  Layout constraints for all 	  /
			/	  components based on location	  /
			/*************************************/
			springLayout.putConstraint(SpringLayout.EAST, howManyField, 0, SpringLayout.EAST, lblRow);
			springLayout.putConstraint(SpringLayout.WEST, howManyField, 0, SpringLayout.WEST, locationField);

			springLayout.putConstraint(SpringLayout.NORTH, lblSelectLocation, 51, SpringLayout.SOUTH, lblHowMany);
			springLayout.putConstraint(SpringLayout.SOUTH, lblSelectLocation, -6, SpringLayout.NORTH, lblAisle);
			springLayout.putConstraint(SpringLayout.EAST, lblSelectLocation, -249, SpringLayout.EAST, contentPane);
		    springLayout.putConstraint(SpringLayout.WEST, lblSelectLocation, 14, SpringLayout.WEST, contentPane);

			springLayout.putConstraint(SpringLayout.SOUTH, locationField, -61, SpringLayout.SOUTH, contentPane);
			springLayout.putConstraint(SpringLayout.EAST, locationField, 0, SpringLayout.EAST, rowField);
			springLayout.putConstraint(SpringLayout.WEST, locationField, 6, SpringLayout.EAST, lblSelectLocation);
			
			springLayout.putConstraint(SpringLayout.NORTH, lblAisle, -3, SpringLayout.NORTH, lblRow);
			springLayout.putConstraint(SpringLayout.EAST, lblAisle, 0, SpringLayout.EAST, lblSelectLocation);
			springLayout.putConstraint(SpringLayout.WEST, lblAisle, 64, SpringLayout.WEST, contentPane);

			springLayout.putConstraint(SpringLayout.NORTH, aisleField, -3, SpringLayout.NORTH, lblRow);
			springLayout.putConstraint(SpringLayout.EAST, aisleField, -40, SpringLayout.WEST, lblRow);
			springLayout.putConstraint(SpringLayout.WEST, aisleField, 6, SpringLayout.EAST, lblAisle);
			
			springLayout.putConstraint(SpringLayout.NORTH, lblRow, 104, SpringLayout.NORTH, contentPane);
			springLayout.putConstraint(SpringLayout.EAST, lblRow, -6, SpringLayout.WEST, rowField);
			springLayout.putConstraint(SpringLayout.WEST, lblRow, 193, SpringLayout.WEST, contentPane);
			
			springLayout.putConstraint(SpringLayout.NORTH, rowField, 6, SpringLayout.SOUTH, locationField);
			springLayout.putConstraint(SpringLayout.EAST, rowField, -81, SpringLayout.EAST, contentPane);
			springLayout.putConstraint(SpringLayout.WEST, rowField, 232, SpringLayout.WEST, contentPane);
						
		}
		
		/**************************************
		/	    Buttons for the actions 	  /
		/									  /
		/*************************************/
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory itemInventory = null;
				boolean showError = false;
				String message = "";
				try {
					//Checks to make sure no information from the fields
					//is invalid.
					if(!howManyField.getText().matches("\\d+") || palletChoice == 1 && !aisleField.getText().matches("\\d+")  
							|| palletChoice == 1 && !rowField.getText().matches("\\d+")){
						showError = true;
						message += "A value entered was null or invalid\n";
					}else if( palletChoice == 1 && Integer.parseInt(aisleField.getText()) > 10000){
						showError = true;
						message += "Aisle number can't be  less than 1 or greater than 10000\n";
					}else if(palletChoice == 1 && Integer.parseInt(rowField.getText()) > 10000){
						showError = true;
						message += "Row number can't be  less than 1 or greater than 10000\n";
					}
					if(palletChoice == 1 && locationField.getText().equals("")){
						showError = true;
						message += "Location can't be null\n";
					}
					try{
						//Last check to ensure that the location is not in use
						if(palletChoice == 1){
							Query.getLocationByAisleRow(connection, Integer.parseInt(aisleField.getText()),Integer.parseInt(rowField.getText()));
							showError = true;
							message += "Location already in use";	
						}
						//if any of the above checks are tripped show a dialog and
						//relaunch InventoryInformation
						if(showError){
							JOptionPane.showMessageDialog(contentPane, message, "Error", JOptionPane.ERROR_MESSAGE);
							dispose();
							InventoryInformation temp = new InventoryInformation(connection, barcode, item, palletChoice, inventory);
							temp.setVisible(true);
						} 
						//When adding to existing pallet, add the create itemInventory to 
						//the passed field: inventory
						else{
							itemInventory = new Inventory(0,item, Integer.parseInt(howManyField.getText()), item.getManufacturer(), 1,
									inventory.getId(), new java.sql.Date(Calendar.getInstance().getTime().getTime()), null,
									inventory.getLocation(), null);
							Updater.update(connection, inventory);
							Inserter.insert(connection, itemInventory);
						}
					} catch (NoSuchElementException | NumberFormatException e2){
						//Last error check for adding a new pallet
						if(showError){
							JOptionPane.showMessageDialog(contentPane, message, "Error", JOptionPane.ERROR_MESSAGE);
							dispose();
							InventoryInformation temp = new InventoryInformation(connection, barcode, item, palletChoice, inventory);
							temp.setVisible(true);
						}
						//if the input passes all tests, all a new pallet
						//and add the new itemInventory to that pallet
						else{
							Inventory i = new Inventory(0, null, 1, null, 2, 0,new java.sql.Date(Calendar.getInstance().getTime().getTime()),
									null, null, null);
							try {
								Inserter.insert(connection, i);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							Location l = new Location(0, locationField.getText() + ":" + Integer.parseInt(aisleField.getText())
									+ ":"  + Integer.parseInt(rowField.getText()), Integer.parseInt(aisleField.getText()),
									Integer.parseInt(rowField.getText()));
							itemInventory = new Inventory(0,item, Integer.parseInt(howManyField.getText()), item.getManufacturer(), 1,
									i.getId(), new java.sql.Date(Calendar.getInstance().getTime().getTime()), null, l, null);
							Inserter.insert(connection, l);
							i.setLocation(l);
							Updater.update(connection, i);
							Inserter.insert(connection, itemInventory);
						}
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		contentPane.add(btnEnter);
		
		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.SOUTH, lblHowMany, -6, SpringLayout.NORTH, howManyField);
		springLayout.putConstraint(SpringLayout.EAST, lblHowMany, -55, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblHowMany, 39, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, howManyField, 30, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, howManyField, 238, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, howManyField, 101, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnEnter, -10, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, btnEnter, -10, SpringLayout.EAST, contentPane);
		
		/**************************************
		/  Sets the default button to enter	  /
		/	  								  /
		/*************************************/
		getRootPane().setDefaultButton(btnEnter);
	}
}
