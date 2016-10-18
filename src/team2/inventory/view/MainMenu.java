import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JList;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Scrollbar;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Label;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainMenu extends JFrame{

	JPanel contentPane;
	private JTable table;
	private JTextField textField;
	/**
	 * Initialize the contents of the frame.
	 */
	public MainMenu() {
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 303);
		contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		JMenuBar menuBar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 0, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, menuBar, 409, SpringLayout.WEST, contentPane);
		contentPane.add(menuBar);
		
		JMenu mnNew = new JMenu("Add");
		menuBar.add(mnNew);
		
		JMenuItem mntmItem = new JMenuItem("Item");
		mntmItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewItemPopUp temp = new NewItemPopUp();
				temp.setVisible(true);
			}
		});
		mnNew.add(mntmItem);
		
		JMenuItem mntmPallet = new JMenuItem("Pallet");
		mntmPallet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NewPalletPopUp temp = new NewPalletPopUp();
					temp.setVisible(true);
				}
			});
		mnNew.add(mntmPallet);
		
		JMenuItem mntmExistingItem = new JMenuItem("Existing Item");
		mntmExistingItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddExistingItemPopUp temp = new AddExistingItemPopUp();
				temp.setVisible(true);
				
			}
		});
		mnNew.add(mntmExistingItem);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem menuItem = new JMenuItem("Item");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditItemPopUp temp = new EditItemPopUp();
				temp.setVisible(true);
			}
		});
		mnEdit.add(menuItem);
		
		JMenu mnSort = new JMenu("Sort By");
		menuBar.add(mnSort);
		
		JMenuItem mntmItemNam = new JMenuItem("Item Name");
		mnSort.add(mntmItemNam);
		
		JMenuItem mntmPalletNumber = new JMenuItem("Pallet Number");
		mnSort.add(mntmPalletNumber);
		
		JMenuItem mntmBarcode = new JMenuItem("Barcode ");
		mnSort.add(mntmBarcode);
		
		JMenuItem mntmLocation = new JMenuItem("Location");
		mnSort.add(mntmLocation);
		
		JMenuItem mntmShipment = new JMenuItem("Shipment");
		mnSort.add(mntmShipment);
		
		JMenu mnReport = new JMenu("Report");
		menuBar.add(mnReport);
		
		JMenuItem mntmGenerateReport = new JMenuItem("Generate Report");
		mnReport.add(mntmGenerateReport);
		
		JMenu mnSend = new JMenu("Send");
		menuBar.add(mnSend);
		
		JMenuItem mntmSendAGenerated = new JMenuItem("Send A Generated Report");
		mnSend.add(mntmSendAGenerated);
		
		JMenu mnLogout = new JMenu("Logout");
		menuBar.add(mnLogout);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnLogout.add(mntmLogout);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 58, SpringLayout.SOUTH, menuBar);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 35, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -48, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 366, SpringLayout.WEST, contentPane);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Items", "Pallets", "Locations", "Shipments"
			}
		));
		scrollPane.setViewportView(table);
		
		Label label = new Label("Search:");
		springLayout.putConstraint(SpringLayout.NORTH, label, 18, SpringLayout.SOUTH, menuBar);
		springLayout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, scrollPane);
		contentPane.add(label);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, label);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -18, SpringLayout.NORTH, scrollPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		springLayout.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, btnSearch);
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, label);
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, 0, SpringLayout.EAST, scrollPane);
		contentPane.add(btnSearch);
		
	}
}
