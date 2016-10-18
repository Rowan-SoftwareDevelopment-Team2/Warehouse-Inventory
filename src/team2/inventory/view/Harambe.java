import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.SpringLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Harambe {

	private JFrame beginFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Harambe window = new Harambe();
					window.beginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Harambe() {
		initializeBeginFrame();
	}

	/**
	 * Initialize the contents of the beginFrame.
	 */
	private void initializeBeginFrame() {
		beginFrame = new JFrame();
		beginFrame.setBounds(100, 100, 450, 300);
		beginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		beginFrame.getContentPane().setLayout(springLayout);
		
		JButton btnAddFileOf = new JButton("Add File of Users");
		btnAddFileOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddExistingPopUp temp = new AddExistingPopUp();
				temp.setVisible(true);
			}
		});
		
		springLayout.putConstraint(SpringLayout.WEST, btnAddFileOf, 76, SpringLayout.WEST, beginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddFileOf, -66, SpringLayout.SOUTH, beginFrame.getContentPane());
		beginFrame.getContentPane().add(btnAddFileOf);
		
		JButton btnLoginAsExisting = new JButton("Login as Existing User");
		springLayout.putConstraint(SpringLayout.NORTH, btnLoginAsExisting, 0, SpringLayout.NORTH, btnAddFileOf);
		springLayout.putConstraint(SpringLayout.EAST, btnLoginAsExisting, -52, SpringLayout.EAST, beginFrame.getContentPane());
		beginFrame.getContentPane().add(btnLoginAsExisting);
		
		btnLoginAsExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddLoginPopUp temp = new AddLoginPopUp();
				temp.setVisible(true);
				beginFrame.dispose();
			}
		});
		
		JLabel lblWarehouseInventorySystem = new JLabel("Warehouse Inventory System");
		springLayout.putConstraint(SpringLayout.NORTH, lblWarehouseInventorySystem, 78, SpringLayout.NORTH, beginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblWarehouseInventorySystem, -160, SpringLayout.SOUTH, beginFrame.getContentPane());
		lblWarehouseInventorySystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarehouseInventorySystem.setAlignmentX(Component.CENTER_ALIGNMENT);
		springLayout.putConstraint(SpringLayout.WEST, lblWarehouseInventorySystem, 0, SpringLayout.WEST, beginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblWarehouseInventorySystem, 0, SpringLayout.EAST, beginFrame.getContentPane());
		lblWarehouseInventorySystem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		beginFrame.getContentPane().add(lblWarehouseInventorySystem);
	}
}
