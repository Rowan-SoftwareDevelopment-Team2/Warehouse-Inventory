package team2.inventory.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import team2.inventory.controller.database.Query;
import team2.inventory.controller.database.QueryInventoryExtender;
import team2.inventory.model.Barcode;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
/** Creates a Popup asking if inventory
 *  wants to be added to a new pallet or an existing one
 * @author Trevor Silva */
public class PalletSelectionPopUp extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	PalletSelectionPopUp(Connection connection, Barcode barcode, Item item){
		/**************************************
		/	      Setup of the JPanel   	  /
		/									  /
		/*************************************/
		super("Pallet Selection");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 373, 126);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		/**************************************
		/	   JLabels for the contentPane	  /
		/									  /
		/*************************************/
		JLabel lblPalletBarcode = new JLabel("Pallet Barcode:");
		contentPane.add(lblPalletBarcode);
		lblPalletBarcode.setVisible(false);	
		
		/**************************************
		/	      Fields for input			  /
		/									  /
		/*************************************/
		JTextField barcodeField = new JTextField();
		contentPane.add(barcodeField);
		barcodeField.setColumns(10);
		barcodeField.setVisible(false);
		
		/**************************************
		/	    Buttons for the actions 	  /
		/									  /
		/*************************************/
		JButton btnNewPallet = new JButton("New Pallet");
		btnNewPallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if inventory wants to be added to a new pallet,
				//call the popup
				InventoryInformation temp = new InventoryInformation(connection, barcode, item, 1, null);
				temp.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewPallet);
		
		//enter button for use in btnExistingPallet
		JButton btnEnter = new JButton("Enter");
		
		JButton btnExistingPallet = new JButton("Existing Pallet");
		btnExistingPallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//sets information for scanning an existing pallet
				//barcode to visible
				lblPalletBarcode.setVisible(true);
				btnEnter.setVisible(true);
				barcodeField.setVisible(true);
				barcodeField.requestFocus();
				getRootPane().setDefaultButton(btnEnter);
			}
		});
		contentPane.add(btnExistingPallet);		
	
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				InventoryInformation temp = null;
				try {
					//grab the pallet by the barcode 
					Iterator<Inventory> it = Query.getInventoryByBarcode(connection, QueryInventoryExtender.palletsOnly(connection),
							Query.getBarcodeByBarcode(connection, barcodeField.getText()).getId()).values().iterator();
					Inventory inventory = it.next();
					//check to make sure it isn't a pallet that is shipped
					if(inventory.getShipped() != null){
						//show error if it is
						JOptionPane.showMessageDialog(contentPane, "Pallet Barcode has been shipped", "Pallet Error", JOptionPane.ERROR_MESSAGE);
					} else{
						//otherwise pass that pallet to InventoryInformation
						temp = new InventoryInformation(connection, barcode, item, 2, inventory);
						temp.setVisible(true);
						dispose();
					}
				} catch (SQLException | NoSuchElementException e1) {
					//if pallet barcode doesn't exist show error and redo
					JOptionPane.showMessageDialog(contentPane, "Pallet Barcode is not valid", "Error", JOptionPane.ERROR_MESSAGE);
				}
				//redo scanning of pallet barcode
				btnExistingPallet.doClick();
			}
		});
		contentPane.add(btnEnter);
		btnEnter.setVisible(false);

		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, lblPalletBarcode, 4, SpringLayout.NORTH, btnEnter);
		springLayout.putConstraint(SpringLayout.WEST, lblPalletBarcode, 21, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.NORTH, barcodeField, -3, SpringLayout.NORTH, lblPalletBarcode);
		springLayout.putConstraint(SpringLayout.EAST, barcodeField, -22, SpringLayout.WEST, btnEnter);
		springLayout.putConstraint(SpringLayout.WEST, barcodeField, 6, SpringLayout.EAST, lblPalletBarcode);

		springLayout.putConstraint(SpringLayout.NORTH, btnNewPallet, 0, SpringLayout.NORTH, btnExistingPallet);
		springLayout.putConstraint(SpringLayout.WEST, btnNewPallet, 59, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnExistingPallet, 10, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, btnExistingPallet, -45, SpringLayout.EAST, contentPane);
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnEnter, -1, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, btnEnter, 0, SpringLayout.EAST, contentPane);
	}
}
