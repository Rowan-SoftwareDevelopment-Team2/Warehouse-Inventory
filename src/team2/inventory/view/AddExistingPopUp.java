import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;


public class AddExistingPopUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public AddExistingPopUp() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 435, 137);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 30, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 142, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, -30, SpringLayout.SOUTH, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFilePath = new JLabel("File Path:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblFilePath, 4, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblFilePath, -56, SpringLayout.WEST, textField);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblFilePath, -6, SpringLayout.WEST, textField);
		lblFilePath.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblFilePath);
	}
}
