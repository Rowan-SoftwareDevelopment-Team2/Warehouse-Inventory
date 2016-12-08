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


	public class ScanNewItemWindow extends JFrame{
		private JTextField textField;
		private JTextField textField_1;

		public ScanNewItemWindow() {

			 JPanel contentPane;
				
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setBounds(100, 100, 433, 224);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				SpringLayout springLayout = new SpringLayout();
				contentPane.setLayout(springLayout);
				
				JLabel lblNewItem = new JLabel("Scan New Item:");
				springLayout.putConstraint(SpringLayout.NORTH, lblNewItem, 10, SpringLayout.NORTH, contentPane);
				springLayout.putConstraint(SpringLayout.WEST, lblNewItem, 20, SpringLayout.WEST, contentPane);
				lblNewItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
				contentPane.add(lblNewItem);
				
				JLabel lblBarcode = new JLabel("Barcode:");
				springLayout.putConstraint(SpringLayout.NORTH, lblBarcode, 30, SpringLayout.SOUTH, lblNewItem);
				springLayout.putConstraint(SpringLayout.WEST, lblBarcode, 0, SpringLayout.WEST, lblNewItem);
				contentPane.add(lblBarcode);
				
				textField = new JTextField();
				springLayout.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, lblBarcode);
				springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblBarcode);
				springLayout.putConstraint(SpringLayout.EAST, textField, 166, SpringLayout.EAST, lblBarcode);
				contentPane.add(textField);
				textField.setColumns(10);
				
				JLabel lblDetails = new JLabel("Details:");
				springLayout.putConstraint(SpringLayout.NORTH, lblDetails, 42, SpringLayout.SOUTH, lblBarcode);
				springLayout.putConstraint(SpringLayout.WEST, lblDetails, 0, SpringLayout.WEST, lblNewItem);
				contentPane.add(lblDetails);
				
				textField_1 = new JTextField();
				springLayout.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblDetails);
				springLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
				springLayout.putConstraint(SpringLayout.EAST, textField_1, 173, SpringLayout.EAST, lblDetails);
				contentPane.add(textField_1);
				textField_1.setColumns(10);
				
				JButton btnEnter = new JButton("Enter");
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


