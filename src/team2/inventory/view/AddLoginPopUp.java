import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;


public class AddLoginPopUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Initialize the contents of the frame.
	 */
	public AddLoginPopUp() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 433, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout(springLayout);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 65, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, -266, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -106, SpringLayout.EAST, getContentPane());
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 105, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 168, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -106, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -20, SpringLayout.NORTH, textField_1);
		textField_1.setColumns(10);
		getContentPane().add(textField_1);
		
		JLabel label = new JLabel("Warehouse Inventory System");
		springLayout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label, 85, SpringLayout.WEST, getContentPane());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setAlignmentX(0.5f);
		getContentPane().add(label);
		
		JLabel lblUsername = new JLabel("Username:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblUsername, 0, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -6, SpringLayout.WEST, textField);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblPassword, 0, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, -6, SpringLayout.WEST, textField_1);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 6, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, -143, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu temp = new MainMenu();
				setVisible(false);
				temp.setVisible(true);
			}
		});
	}

}
