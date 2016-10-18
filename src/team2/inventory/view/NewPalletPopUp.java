import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;


public class NewPalletPopUp extends JFrame{

	public NewPalletPopUp() {

		 JPanel contentPane;
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 433, 224);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			SpringLayout springLayout = new SpringLayout();
			contentPane.setLayout(springLayout);
			
			JLabel lblNewItem = new JLabel("New Pallet:");
			springLayout.putConstraint(SpringLayout.NORTH, lblNewItem, 10, SpringLayout.NORTH, contentPane);
			springLayout.putConstraint(SpringLayout.WEST, lblNewItem, 20, SpringLayout.WEST, contentPane);
			lblNewItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPane.add(lblNewItem);
			
			JButton btnAddExistingItem = new JButton("Add Existing Item");
			btnAddExistingItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddExistingItemPopUp temp = new AddExistingItemPopUp();
					temp.setVisible(true);
				}
			});
			springLayout.putConstraint(SpringLayout.NORTH, btnAddExistingItem, 24, SpringLayout.SOUTH, lblNewItem);
			springLayout.putConstraint(SpringLayout.WEST, btnAddExistingItem, 0, SpringLayout.WEST, lblNewItem);
			contentPane.add(btnAddExistingItem);
			
			JButton btnScanANew = new JButton("Scan a New Item");
			btnScanANew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ScanNewItemWindow temp = new ScanNewItemWindow();
					temp.setVisible(true);
				}
			});
			springLayout.putConstraint(SpringLayout.NORTH, btnScanANew, 29, SpringLayout.SOUTH, btnAddExistingItem);
			springLayout.putConstraint(SpringLayout.WEST, btnScanANew, 0, SpringLayout.WEST, lblNewItem);
			springLayout.putConstraint(SpringLayout.EAST, btnScanANew, 0, SpringLayout.EAST, btnAddExistingItem);
			contentPane.add(btnScanANew);
			
			JLabel lblCurrentItemsIn = new JLabel("Current Items In Pallet:");
			springLayout.putConstraint(SpringLayout.NORTH, lblCurrentItemsIn, 34, SpringLayout.NORTH, contentPane);
			springLayout.putConstraint(SpringLayout.EAST, lblCurrentItemsIn, -54, SpringLayout.EAST, contentPane);
			contentPane.add(lblCurrentItemsIn);
			
			JList list = new JList();
			springLayout.putConstraint(SpringLayout.NORTH, list, -17, SpringLayout.SOUTH, contentPane);
			springLayout.putConstraint(SpringLayout.WEST, list, -160, SpringLayout.EAST, contentPane);
			springLayout.putConstraint(SpringLayout.SOUTH, list, -116, SpringLayout.SOUTH, contentPane);
			springLayout.putConstraint(SpringLayout.EAST, list, -43, SpringLayout.EAST, contentPane);
			contentPane.add(list);
			
			JButton btnEnter = new JButton("Enter ");
			btnEnter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			springLayout.putConstraint(SpringLayout.SOUTH, btnEnter, -10, SpringLayout.SOUTH, contentPane);
			springLayout.putConstraint(SpringLayout.EAST, btnEnter, -10, SpringLayout.EAST, contentPane);
			contentPane.add(btnEnter);
		}
	}

