import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AddExistingItemPopUp extends JFrame{

	JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	
	public AddExistingItemPopUp() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 303);
		contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 58, SpringLayout.SOUTH, contentPane);
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
		springLayout.putConstraint(SpringLayout.NORTH, label, 71, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, label, 36, SpringLayout.WEST, contentPane);
		contentPane.add(label);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 93, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -18, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, textField, -114, SpringLayout.EAST, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		springLayout.putConstraint(SpringLayout.SOUTH, btnSearch, 0, SpringLayout.SOUTH, label);
		contentPane.add(btnSearch);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, btnSearch, 6, SpringLayout.EAST, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 93, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_1, 0, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Add Existing Item:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 23, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, scrollPane);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, textArea, 6, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, textArea, 112, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, textArea, 0, SpringLayout.EAST, scrollPane);
		contentPane.add(textArea);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 26, SpringLayout.SOUTH, textArea);
		springLayout.putConstraint(SpringLayout.WEST, btnAdd, 158, SpringLayout.WEST, contentPane);
		contentPane.add(btnAdd);
	}
}
