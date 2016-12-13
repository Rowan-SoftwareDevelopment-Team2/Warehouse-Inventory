package team2.inventory.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import team2.inventory.controller.database.Inserter;
import team2.inventory.controller.database.Query;
import team2.inventory.controller.database.Remover;
import team2.inventory.controller.database.Updater;
import team2.inventory.model.Inventory;
import team2.inventory.model.Location;
/** Creates a Popup to move pallet to new
 *  location
 * @author Trevor Silva */
public class MoveInventoryPopUp extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField locationField;
	private JTextField aisleField;
	private JTextField rowField;
	
	MoveInventoryPopUp(Connection connection, Inventory inventory){
		/**************************************
		/	      Setup of the JPanel   	  /
		/									  /
		/*************************************/
		super("Move Inventory");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 426, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		/**************************************
		/	   JLabels for the contentPane	  /
		/									  /
		/*************************************/
		JLabel lblSelectNewLocation = new JLabel("Select New Location:");
		contentPane.add(lblSelectNewLocation);	
		
		JLabel lblAisle = new JLabel("Aisle:");
		contentPane.add(lblAisle);
		
		JLabel lblRow = new JLabel("Row:");
		contentPane.add(lblRow);
		
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
		contentPane.add(rowField);
		rowField.setColumns(10);

		/**************************************
		/	    Buttons for the actions 	  /
		/									  /
		/*************************************/
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean showError = false;
				String message = "";
				//checks to ensure input is positive numbers
				if(!aisleField.getText().matches("\\d+") || !rowField.getText().matches("\\d+")){
					showError = true;
					message += "A value entered was null or invalid\n";
				}
				//checks to ensure the aisle number stays reasonable
				else if(Integer.parseInt(aisleField.getText()) > 10000){
					showError = true;
					message += "Aisle number can't be  less than 1 or greater than 10000\n";
				}
				//checks to ensure the row number stays reasonable
				else if(Integer.parseInt(rowField.getText()) > 10000){
					showError = true;
					message += "Row number can't be  less than 1 or greater than 10000\n";
				}
				//checks to ensure location description isn't null
				if(locationField.getText().equals("")){
					showError = true;
					message += "Location can't be null\n";
				}
				try{
					//if there are no errors check to make sure the location
					//is not taken
					if(!showError){
						Query.getLocationByAisleRow(connection, Integer.parseInt(aisleField.getText()),Integer.parseInt(rowField.getText()));
						showError = true;
						message += "Location already in use";
					}
					//if error display message
					if(showError){
						JOptionPane.showMessageDialog(contentPane, message, "Error", JOptionPane.ERROR_MESSAGE);
						dispose();
						MoveInventoryPopUp temp = new MoveInventoryPopUp(connection, inventory);
						temp.setVisible(true);
					}
				} catch(SQLException | NoSuchElementException e1){
					//if no errors are thrown move selected pallet to new location
					//and remove old pallet
					try {
						Location old = inventory.getLocation();
						Location l = new Location(0, locationField.getText() + ":" + Integer.parseInt(aisleField.getText())
								+ ":"  + Integer.parseInt(rowField.getText()), Integer.parseInt(aisleField.getText()),
								Integer.parseInt(rowField.getText()));
						Inserter.insert(connection, l);
						Updater.moveInventory(connection, inventory, l);
						Remover.remove(connection, old);
					} catch (SQLException | NumberFormatException e) {
						e.printStackTrace();
					}
				}
				dispose();
			}
		});
		contentPane.add(btnEnter);
		
		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectNewLocation, 13, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, lblSelectNewLocation, -263, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectNewLocation, 10, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.SOUTH, lblAisle, -13, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, lblAisle, -285, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblAisle, 70, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.SOUTH, lblRow, -13, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, lblRow, -6, SpringLayout.WEST, rowField);
		springLayout.putConstraint(SpringLayout.WEST, lblRow, 189, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.NORTH, locationField, 10, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, locationField, -118, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, locationField, 6, SpringLayout.EAST, lblSelectNewLocation);
		
		springLayout.putConstraint(SpringLayout.SOUTH, aisleField, -10, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, aisleField, -32, SpringLayout.WEST, lblRow);
		springLayout.putConstraint(SpringLayout.WEST, aisleField, 6, SpringLayout.EAST, lblAisle);

		springLayout.putConstraint(SpringLayout.SOUTH, rowField, -10, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, rowField, -118, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, rowField, 246, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnEnter, -8, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, btnEnter, -10, SpringLayout.EAST, contentPane);
		
		/**************************************
		/  Sets the default button to enter	  /
		/	  								  /
		/*************************************/
		getRootPane().setDefaultButton(btnEnter);
	}
}
