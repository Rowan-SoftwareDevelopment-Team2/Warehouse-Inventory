package team2.inventory.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import team2.inventory.controller.database.Query;
import team2.inventory.model.Company;
/** Creates a Popup asking to select a company
 * @author Trevor Silva */
public class SelectCompanyPopUp extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTable table;
	private static boolean isSelected;
	private Company chosenCompany;
	private JPanel contentPane;
	
	public SelectCompanyPopUp(Connection connection, boolean isNewItem){
		/**************************************
		/	      Setup of the JPanel   	  /
		/									  /
		/*************************************/
		super("Select Company");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 761, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		isSelected = false;
		
		/**************************************
		/	      Scrollpane for the          /
		/             table                   /
		/									  /
		/*************************************/
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		/**************************************
		/	      Table of items         	  /
		/									  /
		/*************************************/
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Email", "Phone", "Address"
			}){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			//ensure that users can't tamper with table
		public boolean isCellEditable(int row, int column) {return false;}});
		scrollPane.setViewportView(table);
		updateTable(connection);
		
		/**************************************
		/	    Buttons for the actions 	  /
		/									  /
		/*************************************/
		JButton btnAddNewCompany = new JButton("Add New Company");
		btnAddNewCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creates a new company popup
				NewCompanyPopUp temp = new NewCompanyPopUp(connection);
				temp.setVisible(true);
			}
		});
		contentPane.add(btnAddNewCompany);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			    int rowNumber = table.getSelectedRow();
			    //check if there is a selected row 
			    if(rowNumber != -1){
			    	String companyName = (String) dtm.getValueAt(rowNumber,0);
				    Company selected = null;
				    //try to pass the selected company to the correct popup
				    //either new or edit
					try {
						selected = Query.getCompanyByName(connection, companyName);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}    
				    setChosenCompany(selected);
				    if(isNewItem == true)
				    	NewItemPopUp.updateSelected(selected);
				    else
				    	EditItemPopUp.updateSelected(selected);
				    dispose();
			    }else{
			    	//if there is no row selected and select button is clicked
			    	//an error message will show
			    	JOptionPane.showMessageDialog(contentPane, "You must select a company", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
			    	SelectCompanyPopUp temp = new SelectCompanyPopUp(connection, isNewItem);
			    	dispose();
			    	temp.setVisible(true);
			    }
			    
			}
		});
		contentPane.add(btnSelect);

		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 5, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -9, SpringLayout.NORTH, btnSelect);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, contentPane);

		springLayout.putConstraint(SpringLayout.SOUTH, btnSelect, 0, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, btnSelect, 0, SpringLayout.EAST, contentPane);

		springLayout.putConstraint(SpringLayout.SOUTH, btnAddNewCompany, 0, SpringLayout.SOUTH, btnSelect);
		springLayout.putConstraint(SpringLayout.WEST, btnAddNewCompany, 0, SpringLayout.WEST, contentPane);
		

	}
	
	/**
	 * Get the selected company
	 * @return Company chosenCompany
	 */
	protected Company getChosenCompany() {
		return chosenCompany;
	}

	/**
	 * Set the chosenCompany
	 * @param chosenCompany
	 */
	protected void setChosenCompany(Company chosenCompany) {
		this.chosenCompany = chosenCompany;
	}
	
	/**
	 * Checks if there was a selection made
	 * @return boolean isSelected 
	 */
	protected static boolean isSelected(){
		return isSelected;
	}
	
	/**
	 * updates company table
	 * @param connection
	 */
	protected static void updateTable(Connection connection){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Map<Integer, Company> companies = null;
		try {
			companies = Query.getCompanies(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Iterator<Company> it = companies.values().iterator();
	    while (it.hasNext()) {
	        Company c = it.next();
	        Object[] row = { c.getName(), c.getEmail(),c.getPhone(), c.getAddress()};
		    model.addRow(row);
	    }
	}
}
