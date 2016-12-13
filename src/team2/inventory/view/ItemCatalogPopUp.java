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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import team2.inventory.controller.database.Query;
import team2.inventory.controller.database.QueryInventoryExtender;
import team2.inventory.model.Item;
/** Creates a Popup to show the Item Catalog
 *  of the warehouse
 * @author Trevor Silva */
public class ItemCatalogPopUp extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	private static JTable table;
	
	public ItemCatalogPopUp(Connection connection) throws SQLException{
		/**************************************
		/	      Setup of the JPanel   	  /
		/									  /
		/*************************************/
		super("Item Catalog");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 378);
		contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
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
		searchField.setColumns(10);	
		
		/**************************************
		/	    Scrollpane for table		  /
		/									  /
		/*************************************/
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
	
		/**************************************
		/	    Buttons for the actions 	  /
		/									  /
		/*************************************/
		JButton btnEditItem = new JButton("Edit Item");
		btnEditItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//grab the selected item from the table
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			    int rowNumber = table.getSelectedRow();
			    String itemName = (String) dtm.getValueAt(rowNumber,0);
			    Item selected = null;
				try {
					selected = Query.getItemByName(connection, itemName, Query.getBarcodes(connection), Query.getCompanies(connection));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}    
				//pass the item to be updated
				EditItemPopUp temp = new EditItemPopUp(connection, selected);
				temp.setVisible(true);
				btnEditItem.setVisible(false);
			}
		});
		contentPane.add(btnEditItem);
		btnEditItem.setVisible(false);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//update the table based on search input
					updateTable(QueryInventoryExtender.searchBarItem(connection, searchField.getText()));
					btnEditItem.setVisible(false);
					searchField.setText("");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnSearch);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewItemPopUp temp = new NewItemPopUp(connection, false);
				temp.setVisible(true);
			}
		});
		contentPane.add(btnAddItem);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//updates entire table
					updateTable(Query.getItems(connection, Query.getBarcodes(connection), Query.getCompanies(connection)));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnUpdate);
		
		/**************************************
		/	   Table that displays items 	  /
		/									  /
		/*************************************/
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Items", "Manufacturer", "Barcode", "Description"
			}){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
		//ensures users don't tamper with cells
		public boolean isCellEditable(int row, int column) {return false;}});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//show edit button if cell is clicked
				btnEditItem.setVisible(true);
			}
		});	
		scrollPane.setViewportView(table);
		updateTable(Query.getItems(connection, Query.getBarcodes(connection), Query.getCompanies(connection)));
		
		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, lblSearch, 11, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.SOUTH, lblSearch, -20, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, lblSearch, -6, SpringLayout.WEST, searchField);
		springLayout.putConstraint(SpringLayout.WEST, lblSearch, 23, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.NORTH, searchField, 1, SpringLayout.NORTH, btnSearch);
		springLayout.putConstraint(SpringLayout.EAST, searchField, -6, SpringLayout.WEST, btnSearch);
		springLayout.putConstraint(SpringLayout.WEST, searchField, 90, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 10, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, -28, SpringLayout.EAST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 18, SpringLayout.SOUTH, btnSearch);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -39, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, btnSearch);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblSearch);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnAddItem, 6, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnAddItem, 0, SpringLayout.WEST, scrollPane);

		springLayout.putConstraint(SpringLayout.NORTH, btnEditItem, 6, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnEditItem, 6, SpringLayout.EAST, btnAddItem);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 6, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnUpdate, 0, SpringLayout.EAST, scrollPane);

		/**************************************
		/  Sets the default button to enter	  /
		/	  								  /
		/*************************************/
		getRootPane().setDefaultButton(btnSearch);
	}
	
	/**
	 * Updates table with given map from query
	 * @param Map<Integer, Item> map 
	 */
	protected static void updateTable(Map<Integer, Item> map){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Iterator<Item> it = map.values().iterator();
		Item i;
	    while (it.hasNext()) {
	    	i = it.next();
	    	Object[] row = { i.getName(), i.getManufacturer().getName(), i.getBarcode().getBarcode(), 
	    			i.getDescription()};
			model.addRow(row);
	    }
	}
}
