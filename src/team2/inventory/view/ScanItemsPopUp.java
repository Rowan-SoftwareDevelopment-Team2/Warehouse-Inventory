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

import team2.inventory.controller.database.Query;
import team2.inventory.model.Barcode;
import team2.inventory.model.Inventory;
import team2.inventory.model.Item;
/** Creates a main pop up for scanning items and
 *  inventory
 * @author Trevor Silva */
public class ScanItemsPopUp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static String barcode = "";
	private static boolean fromScanned;

	public ScanItemsPopUp(Connection connection) {
		/**************************************
		/	      Setup of the JPanel   	  /
		/									  /
		/*************************************/
		super("Scanner");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 373, 83);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
	
		/**************************************
		/	   JLabels for the contentPane	  /
		/									  /
		/*************************************/
		JLabel lblScan = new JLabel("Scan:");
		contentPane.add(lblScan);
		
		/**************************************
		/	      Fields for input			  /
		/									  /
		/*************************************/
		JTextField barcodeField = new JTextField();
		contentPane.add(barcodeField);
		barcodeField.setColumns(10);
		
		/**************************************
		/	    Buttons for the actions 	  /
		/									  /
		/*************************************/
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Barcode bcode = null;
				try{
					//grab the barcode from input
					barcode = barcodeField.getText(); 
					barcodeField.setText("");
					fromScanned = true;
					bcode = Query.getBarcodeByBarcode(connection, barcode);
					//grab inventory and check if it is a pallet
					Inventory palletInventory = Query.getInventoryByBarcode(connection, bcode.getId()).values().iterator().next();
					if(palletInventory.getType() == 2){
						if(palletInventory.getShipped() != null){
							//if the pallet is shipped display error
							JOptionPane.showMessageDialog(contentPane, "Pallet has been shipped", "Pallet Error", JOptionPane.ERROR_MESSAGE);
						}else{
							//otherwise ask if user wants to move pallet
							int option = JOptionPane.showConfirmDialog(contentPane, "Do you want to move pallet: " + bcode.getBarcode() + "?",
									"Move Pallet", JOptionPane.YES_NO_OPTION);
							if(option == JOptionPane.YES_OPTION){
								//if user wants to move pallet call MoveInventoryPopUp
								MoveInventoryPopUp temp = new MoveInventoryPopUp(connection,Query.getInventoryByBarcode(connection,
										bcode.getId()).values().iterator().next());
								temp.setVisible(true);
							}
						}
					}
				} catch (SQLException | NoSuchElementException e1){
					try{
						//check if barcode is an item and if so call pallet selection
						Item item =  Query.getItemByBarcode(connection, bcode);
						PalletSelectionPopUp temp = new PalletSelectionPopUp(connection, bcode, item);
						temp.setVisible(true);
					}catch(NullPointerException | NoSuchElementException | SQLException e2){
						//if barcode is not in the system ask if user would
						//like to add it 
						int option = JOptionPane.showConfirmDialog(contentPane,"Barcode " + barcodeField.getText() +
								" does not exist. Would you like to add to database?", "Add Item", JOptionPane.YES_NO_OPTION);
						if(option == JOptionPane.YES_OPTION){
							NewItemPopUp temp = new NewItemPopUp(connection, true);
							temp.setVisible(true);
						}
					} 
				} 
				fromScanned = false;
			}
		});
		contentPane.add(btnEnter);
		
		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, lblScan, 10, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblScan, 10, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, barcodeField, -3, SpringLayout.NORTH, lblScan);
		springLayout.putConstraint(SpringLayout.EAST, barcodeField, 235, SpringLayout.EAST, lblScan);
		springLayout.putConstraint(SpringLayout.WEST, barcodeField, 6, SpringLayout.EAST, lblScan);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnEnter, 0, SpringLayout.NORTH, barcodeField);
		springLayout.putConstraint(SpringLayout.WEST, btnEnter, 6, SpringLayout.EAST, barcodeField);
		
		/**************************************
		/  Sets the default button to enter	  /
		/	  								  /
		/*************************************/	
		getRootPane().setDefaultButton(btnEnter);
		
	}

	/**
	 * 
	 * @return boolean fromScanned if barcode was from the scanner
	 */
	protected static boolean isFromScanned(){
		return fromScanned;
	}
	
	/**
	 * 
	 * @return String barcode to place in subsequent fields
	 */
	protected static String getScannedBarcode(){
		return barcode;
	}
}
