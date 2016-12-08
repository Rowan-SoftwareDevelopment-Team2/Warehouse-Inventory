import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NewItemPopUp extends JFrame{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public NewItemPopUp() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		JLabel lblNewItem = new JLabel("New Item:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewItem, 10, SpringLayout.NORTH, contentPane);
		springLayout.putConstraint(SpringLayout.WEST, lblNewItem, 20, SpringLayout.WEST, contentPane);
		lblNewItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewItem);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		springLayout.putConstraint(SpringLayout.WEST, lblBarcode, 0, SpringLayout.WEST, lblNewItem);
		contentPane.add(lblBarcode);
		
		JLabel lblManufacturer = new JLabel("Manufacturer:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblBarcode, -16, SpringLayout.NORTH, lblManufacturer);
		springLayout.putConstraint(SpringLayout.WEST, lblManufacturer, 0, SpringLayout.WEST, lblNewItem);
		contentPane.add(lblManufacturer);
		
		JLabel lblSupplier = new JLabel("Supplier:");
		springLayout.putConstraint(SpringLayout.WEST, lblSupplier, 0, SpringLayout.WEST, lblNewItem);
		contentPane.add(lblSupplier);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblBarcode, -1, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, lblBarcode, -30, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField, 114, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 53, SpringLayout.NORTH, contentPane);
		textField.setText("");
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 14, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.NORTH, lblManufacturer, 3, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 12, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, lblSupplier, 3, SpringLayout.NORTH, textField_2);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDateArrived = new JLabel("Date Arrived:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDateArrived, 4, SpringLayout.NORTH, lblBarcode);
		contentPane.add(lblDateArrived);
		
		textField_3 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField_3, 314, SpringLayout.WEST, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, lblDateArrived, -6, SpringLayout.WEST, textField_3);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_3, 0, SpringLayout.SOUTH, lblBarcode);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		springLayout.putConstraint(SpringLayout.WEST, lblDescription, 0, SpringLayout.WEST, lblDateArrived);
		springLayout.putConstraint(SpringLayout.SOUTH, lblDescription, -59, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblDescription);
		
		textField_4 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_4, -3, SpringLayout.NORTH, lblDescription);
		springLayout.putConstraint(SpringLayout.WEST, textField_4, 0, SpringLayout.WEST, textField_3);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		springLayout.putConstraint(SpringLayout.SOUTH, btnEnter, -10, SpringLayout.SOUTH, contentPane);
		springLayout.putConstraint(SpringLayout.EAST, btnEnter, -25, SpringLayout.EAST, contentPane);
		contentPane.add(btnEnter);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

}
