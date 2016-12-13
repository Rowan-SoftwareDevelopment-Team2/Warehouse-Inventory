package team2.inventory.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import team2.inventory.controller.database.Connector;

/** Database Configuration Settings Basic GUI.
 * @author James A. Donnell Jr. */
public class ConfigGUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
	private boolean done = false;
	
	public ConfigGUI(File file) {
		super("Database settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		JLabel hostLabel = new JLabel("Host:");
		JLabel portLabel = new JLabel("Port:");
		JLabel tableLabel = new JLabel("Table:");
		JLabel userLabel = new JLabel("Username:");
		JLabel passLabel = new JLabel("Password:");
		JTextField hostText = new JTextField(Connector.getDefaultHostname(), 20);
		JTextField portText = new JTextField(Connector.getDefaultPort(), 5);
		JTextField tableText = new JTextField(20);
		JTextField userText = new JTextField(20);
		JPasswordField passText = new JPasswordField(20);
		
		panel.add(hostLabel);
		panel.add(hostText);
		panel.add(portLabel);
		panel.add(portText);
		panel.add(tableLabel);
		panel.add(tableText);
		panel.add(userLabel);
		panel.add(userText);
		panel.add(passLabel);
		panel.add(passText);
		
		JButton button = new JButton("Save");
		button.addActionListener(new CustomButtonListener(hostText, portText, tableText, userText, passText, file, this));
		panel.add(button);
		
		add(panel);
		pack();
		setVisible(true);
	}
	
	public boolean isDone() {
		return done;
	}
	
	private class CustomButtonListener implements ActionListener {

		private JTextField hostText, portText, tableText, userText;
		private JPasswordField passText;
		private File file;
		private ConfigGUI configGUI;
		
		public CustomButtonListener(JTextField hostText, JTextField portText, JTextField tableText, JTextField userText, JPasswordField passText, File file, ConfigGUI configGUI) {
			this.hostText = hostText;
			this.portText = portText;
			this.tableText = tableText;
			this.userText = userText;
			this.passText = passText;
			this.file = file;
			this.configGUI = configGUI;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(hostText.getText() + "\n");
				bw.write(portText.getText() + "\n");
				bw.write(tableText.getText() + "\n");
				bw.write(userText.getText() + "\n");
				bw.write(new String(passText.getPassword()));
				bw.close();
				done = true;
				configGUI.dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}