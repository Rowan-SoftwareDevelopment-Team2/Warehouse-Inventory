package team2.inventory.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import team2.inventory.controller.database.Query;
import team2.inventory.controller.database.QueryInventoryExtender;
import team2.inventory.controller.database.Updater;
import team2.inventory.model.Inventory;
import team2.inventory.model.User;
/** Main menu for all options in system
 * @author Trevor Silva */
public class MainMenu extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public MainMenu(Connection connection, User user) {
		/**************************************
		/	      Setup of the JPanel   	  /
		/									  /
		/*************************************/
		super("Main Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);

		/**************************************
		/	      MenuBar options based   	  /
		/		    on user privs	     	  /
		/*************************************/
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar);
		
		if(user.getPrivileges() == 1){
			JMenu mnNew = new JMenu("Add");
			menuBar.add(mnNew);
			
			JMenuItem mntmUser = new JMenuItem("User");
			mntmUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//allows managers to add new users
					CreateNewUserPopUp temp = new CreateNewUserPopUp(connection);
					temp.setVisible(true);
				}
			});
			mnNew.add(mntmUser);
			
			JMenu mnReport = new JMenu("Report");
			menuBar.add(mnReport);
			
			JMenuItem mntmGenerateReport = new JMenuItem("Generate Report");
			mntmGenerateReport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//alows managers to create reports
					CustomReportGUI temp = new CustomReportGUI(connection);
					temp.setVisible(true);
				}
			});
			mnReport.add(mntmGenerateReport);
		}
		
		/**************************************
		/	   JLabels for the contentPane	  /
		/									  /
		/*************************************/
		JLabel lblSearch = new JLabel("Search:");
		contentPane.add(lblSearch);
		
		/**************************************
		/	      Fields for input			  /
		/									  /
		/*************************************/
		JTextField searchField = new JTextField();
		contentPane.add(searchField);
	
		/**************************************
		/	    Scrollpane for table		  /
		/									  /
		/*************************************/
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		//need to create button before table
		//to avoid showing error
		JButton btnShip = new JButton("Ship");
		contentPane.add(btnShip);
		btnShip.setVisible(false);
		
		/**************************************
		/	      Table that shows all  	  /
		/			inventories				  /
		/*************************************/
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Name", "Amount", "Supplier", "Location:Aisle:Row", "Received", "Shipped", "Barcode"
			}){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
			//ensures users can't tamper with the table information
			public boolean isCellEditable(int row, int column) {return false;}});
		table.addMouseListener(new MouseAdapter() {
			@Override
			//if a cell is selected allow the inventory to be shipped
			public void mouseClicked(MouseEvent arg0) {
				btnShip.setVisible(true);
			}
		});
		scrollPane.setViewportView(table);
		//update table upon creation
		try {
			updateTable(QueryInventoryExtender.getOrderedList(connection), table);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		/**************************************
		/	    Buttons for the actions 	  /
		/									  /
		/*************************************/
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//updates entire table of inventories
					updateTable(QueryInventoryExtender.getOrderedList(connection), table);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnUpdate);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//updates table based on search field and resets ship button
					updateTable(QueryInventoryExtender.searchBar(connection, searchField.getText()), table);
					btnShip.setVisible(false);
					//clear field
					searchField.setText("");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnSearch);
		
		JButton btnScanItems = new JButton("Scan Items");
		btnScanItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//opens scanning window
				ScanItemsPopUp temp = new ScanItemsPopUp(connection);
				temp.setVisible(true);
			}
		});
		contentPane.add(btnScanItems);
		
		JButton btnItemCatalog = new JButton("Item Catalog");
		btnItemCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//attempts to open Item catalog
				ItemCatalogPopUp temp;
				try {
					temp = new ItemCatalogPopUp(connection);
					temp.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnItemCatalog);
		
		
		btnShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//grabs inventory based on it's unique barcode
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			    int rowNumber = table.getSelectedRow();
			    Inventory selected = null;
			    boolean showError = false;
				String message = "";
				try {
					selected = Query.getInventoryByBarcode(connection, Query.getBarcodeByBarcode(connection,
							dtm.getValueAt(rowNumber, 6).toString()).getId()).values().iterator().next();
					//checks to make sure Inventory is not already shipped
					if(selected.getLocation() == null){
						message += "Already Shipped \n";
						showError = true;
					}
					//throws exception if triggered
					if(showError)
						throw new Exception(); 
					else{
						//if successful ship the pallet
						Updater.shipInventory(connection, selected);
						updateTable(QueryInventoryExtender.getOrderedList(connection), table);
					}
				} catch (Exception e1) {
					//checks to make sure only ships pallets
					if(selected == null)
						message += "Can't Ship non Pallet Items";
					//show error message 
					JOptionPane.showMessageDialog(contentPane, message, "Shipping Error", JOptionPane.ERROR_MESSAGE);
				}
				//reset button after shipping
				btnShip.setVisible(false);
			}
		});
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//logs out of system
				try {
					connection.close();
					System.exit(0);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnLogout);
		
		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, menuBar, 1354, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 0, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 28, SpringLayout.SOUTH, lblSearch);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -33, SpringLayout.NORTH, btnItemCatalog);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -44, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 51, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblSearch, 3, SpringLayout.NORTH, searchField);
		springLayout.putConstraint(SpringLayout.EAST, lblSearch, -20, SpringLayout.WEST, searchField);
		springLayout.putConstraint(SpringLayout.WEST, lblSearch, -68, SpringLayout.WEST, searchField);
		
		springLayout.putConstraint(SpringLayout.NORTH, searchField, 17, SpringLayout.SOUTH, menuBar);
		springLayout.putConstraint(SpringLayout.EAST, searchField, -372, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, searchField, 372, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, -1, SpringLayout.NORTH, searchField);
		springLayout.putConstraint(SpringLayout.WEST, btnSearch, 26, SpringLayout.EAST, searchField);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnScanItems, -4, SpringLayout.NORTH, lblSearch);
		springLayout.putConstraint(SpringLayout.EAST, btnScanItems, 0, SpringLayout.EAST, btnLogout);
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnItemCatalog, -10, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, btnItemCatalog, 10, SpringLayout.WEST, contentPane);
	
		springLayout.putConstraint(SpringLayout.NORTH, btnShip, 0, SpringLayout.NORTH, btnItemCatalog);
		springLayout.putConstraint(SpringLayout.WEST, btnShip, 23, SpringLayout.EAST, btnItemCatalog);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnLogout, 0, SpringLayout.NORTH, btnItemCatalog);
		springLayout.putConstraint(SpringLayout.EAST, btnLogout, -10, SpringLayout.EAST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, -4, SpringLayout.NORTH, lblSearch);
		springLayout.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.WEST, btnItemCatalog);
		
		/**************************************
		/  Sets the default button to search  /
		/	  								  /
		/*************************************/
		getRootPane().setDefaultButton(btnSearch);
	}
	
	/**
	 * Updates table with given map from query and table
	 * @param Map<Item, Inventory> map
	 * @param JTable table
	 */
	private static void updateTable(Map<Integer, Inventory> map, JTable table){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Iterator<Inventory> it = map.values().iterator();
		while(it.hasNext()){
			Inventory i = (Inventory) it.next();
			if(i == null){
				Object[] nullRow = {"","","","","","",""};
				model.addRow(nullRow);
			}else{
				Object[] row = {(i.getItem()==null)?"Pallet: " :i.getItem().getName(),i.getAmount(),
		        		(i.getSupplier()==null)?"":i.getSupplier().getName(), (i.getLocation()==null)?"":i.getLocation().getDescription(),
		        		(i.getReceived()==null)?"":i.getReceived().toString(), (i.getShipped()==null)?"":i.getShipped().toString(), 
		        	    (i.getBarcode()==null)?i.getItem().getBarcode().getBarcode(): i.getBarcode().getBarcode()};
				model.addRow(row);
			}
		}
	}
}
