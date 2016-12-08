package team2.inventory.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import team2.inventory.controller.Report;
import team2.inventory.controller.StartupDriver;
import team2.inventory.controller.database.Query;
import team2.inventory.controller.database.QueryInventoryExtender;
import team2.inventory.model.Inventory;

public class CustomReportGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static String saveLocation = StartupDriver.saveLocation + "report\\report.csv";
	private static final String[] months = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	public CustomReportGUI(Connection connection) {
		super("Report Generator");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Verify folder exists, if not create it
		File mainDir = new File(saveLocation);
		if(!mainDir.exists())
			mainDir.mkdirs();

		JTabbedPane tabbedPane = new JTabbedPane();

		// Inventory Reports
		JPanel panel1 = getInventoryReportsPanel(connection);
		tabbedPane.addTab("Inventory Report", panel1);

		JPanel panel2 = getMiscReportsPanel(connection);
		tabbedPane.addTab("Miscellaneous Reports", panel2);

		add(tabbedPane);
		setMinimumSize(new Dimension(400, 400));
		pack();
		setVisible(true);
	}

	private JPanel getInventoryReportsPanel(Connection connection) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// Full inventory
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Full Inventory");
		JButton button1 = new JButton("Generate Report");
		button1.addActionListener(new InventoryButtonListener(connection, this, 1));
		panel1.add(label1);
		panel1.add(button1);

		// Received Before date
		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("Received Before");
		JLabel label2Month = new JLabel("Month:");
		JLabel label2Day = new JLabel("Day:");
		JLabel label2Year = new JLabel("Year:");
		JComboBox<String> comboBox2 = new JComboBox<String>(months);
		JTextField textField2Day = new JTextField(2);
		JTextField textField2Year = new JTextField(4);
		JButton button2 = new JButton("Generate Report");
		button2.addActionListener(new InventoryButtonListener(connection, this, 2, comboBox2, textField2Day, textField2Year));
		panel2.add(label2);
		panel2.add(label2Month);
		panel2.add(comboBox2);
		panel2.add(label2Day);
		panel2.add(textField2Day);
		panel2.add(label2Year);
		panel2.add(textField2Year);
		panel2.add(button2);

		// Received After date
		JPanel panel3 = new JPanel();
		JLabel label3 = new JLabel("Received After");
		JLabel label3Month = new JLabel("Month:");
		JLabel label3Day = new JLabel("Day:");
		JLabel label3Year = new JLabel("Year:");
		JComboBox<String> comboBox3 = new JComboBox<String>(months);
		JTextField textField3Day = new JTextField(2);
		JTextField textField3Year = new JTextField(4);
		JButton button3 = new JButton("Generate Report");
		button3.addActionListener(new InventoryButtonListener(connection, this, 3, comboBox3, textField3Day, textField3Year));
		panel3.add(label3);
		panel3.add(label3Month);
		panel3.add(comboBox3);
		panel3.add(label3Day);
		panel3.add(textField3Day);
		panel3.add(label3Year);
		panel3.add(textField3Year);
		panel3.add(button3);

		// Shipped Before date
		JPanel panel4 = new JPanel();
		JLabel label4 = new JLabel("Shipped Before");
		JLabel label4Month = new JLabel("Month:");
		JLabel label4Day = new JLabel("Day:");
		JLabel label4Year = new JLabel("Year:");
		JComboBox<String> comboBox4 = new JComboBox<String>(months);
		JTextField textField4Day = new JTextField(2);
		JTextField textField4Year = new JTextField(4);
		JButton button4 = new JButton("Generate Report");
		button4.addActionListener(new InventoryButtonListener(connection, this, 4, comboBox4, textField4Day, textField4Year));
		panel4.add(label4);
		panel4.add(label4Month);
		panel4.add(comboBox4);
		panel4.add(label4Day);
		panel4.add(textField4Day);
		panel4.add(label4Year);
		panel4.add(textField4Year);
		panel4.add(button4);

		// Shipped After date
		JPanel panel5 = new JPanel();
		JLabel label5 = new JLabel("Shipped After");
		JLabel label5Month = new JLabel("Month:");
		JLabel label5Day = new JLabel("Day:");
		JLabel label5Year = new JLabel("Year:");
		JComboBox<String> comboBox5 = new JComboBox<String>(months);
		JTextField textField5Day = new JTextField(2);
		JTextField textField5Year = new JTextField(4);
		JButton button5 = new JButton("Generate Report");
		button5.addActionListener(new InventoryButtonListener(connection, this, 5, comboBox5, textField5Day, textField5Year));
		panel5.add(label5);
		panel5.add(label5Month);
		panel5.add(comboBox5);
		panel5.add(label5Day);
		panel5.add(textField5Day);
		panel5.add(label5Year);
		panel5.add(textField5Year);
		panel5.add(button5);

		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		mainPanel.add(panel5);
		return mainPanel;
	}

	private JPanel getMiscReportsPanel(Connection connection) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel companyPanel = new JPanel();
		JButton companyButton = new JButton("Company Report");
		companyButton.addActionListener(new CompanyButtonListener(connection, this));
		companyPanel.add(companyButton);

		JPanel itemPanel = new JPanel();
		JButton itemButton = new JButton("Item Catalog Report");
		itemButton.addActionListener(new ItemButtonListener(connection, this));
		itemPanel.add(itemButton);

		mainPanel.add(companyPanel);
		mainPanel.add(itemPanel);
		return mainPanel;
	}

	private class CompanyButtonListener implements ActionListener {

		private CustomReportGUI frame;
		private Connection connection;

		public CompanyButtonListener(Connection connection, CustomReportGUI frame) {
			this.frame = frame;
			this.connection = connection;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				Report.generateCompanyReport(saveLocation, Query.getCompanies(connection));
				Report.openReport(saveLocation);
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
			}
			frame.dispose();
		}
	}

	private class ItemButtonListener implements ActionListener {

		private CustomReportGUI frame;
		private Connection connection;

		public ItemButtonListener(Connection connection, CustomReportGUI frame) {
			this.frame = frame;
			this.connection = connection;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				Report.generateItemReport(saveLocation, Query.getItems(connection, Query.getBarcodes(connection), Query.getCompanies(connection)));
				Report.openReport(saveLocation);
			} catch (IOException | SQLException e1) {
				e1.printStackTrace();
			}
			frame.dispose();
		}
	}

	private class InventoryButtonListener implements ActionListener {

		private CustomReportGUI frame;
		private Connection connection;
		private int selected;
		private JComboBox<String> month;
		private JTextField day, year;

		public InventoryButtonListener(Connection connection, CustomReportGUI frame, int selected) {
			this.frame = frame;
			this.connection = connection;
			this.selected = selected;
		}

		public InventoryButtonListener(Connection connection, CustomReportGUI frame, int selected, JComboBox<String> month, JTextField day, JTextField year) {
			this(connection, frame, selected);
			this.month = month;
			this.day = day;
			this.year = year;
		}

		public void actionPerformed(ActionEvent e) {
			Map<Integer, Inventory> query = new HashMap<Integer, Inventory>();
			try {
				switch (selected) {
				case 1:
					query = Query.getInventory(connection);
					break;
				case 2:
					String date2 = year.getText() + "-" + (month.getSelectedIndex()+1) + "-" + day.getText();
					query = QueryInventoryExtender.receivedBefore(connection, Date.valueOf(date2));
					break;
				case 3:
					String date3 = year.getText() + "-" + (month.getSelectedIndex()+1) + "-" + day.getText();
					query = QueryInventoryExtender.receivedAfter(connection, Date.valueOf(date3));
					break;
				case 4:
					String date4 = year.getText() + "-" + (month.getSelectedIndex()+1) + "-" + day.getText();
					query = QueryInventoryExtender.shippedBefore(connection, Date.valueOf(date4));
					break;
				case 5:
					String date5 = year.getText() + "-" + (month.getSelectedIndex()+1) + "-" + day.getText();
					query = QueryInventoryExtender.shippedAfter(connection, Date.valueOf(date5));
					break;
				}
			} catch (SQLException sql) {
				sql.printStackTrace();
			}

			try {
				Report.generateInventoryReport(saveLocation, query);
				Report.openReport(saveLocation);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			frame.dispose();
		}
	}
}