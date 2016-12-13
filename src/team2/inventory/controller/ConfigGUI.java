package team2.inventory.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
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
		JLabel databaseLabel = new JLabel("Database:");
		JLabel userLabel = new JLabel("Username:");
		JLabel passLabel = new JLabel("Password:");
		JTextField hostText = new JTextField(Connector.getDefaultHostname(), 20);
		JTextField portText = new JTextField(Connector.getDefaultPort(), 5);
		JTextField databaseText = new JTextField(20);
		JTextField userText = new JTextField(20);
		JTextField passText = new JTextField(20);
		
		panel.add(hostLabel);
		panel.add(hostText);
		panel.add(portLabel);
		panel.add(portText);
		panel.add(databaseLabel);
		panel.add(databaseText);
		panel.add(userLabel);
		panel.add(userText);
		panel.add(passLabel);
		panel.add(passText);
		
		JButton button = new JButton("Save");
		button.addActionListener(new CustomButtonListener(hostText, portText, databaseText, userText, passText, file, this));
		panel.add(button);
		
		add(panel);
		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
	}
	
	public boolean isDone() {
		return done;
	}
	
	private class CustomButtonListener implements ActionListener {

		private JTextField hostText, portText, databaseText, userText, passText;
		private File file;
		private ConfigGUI configGUI;
		
		public CustomButtonListener(JTextField hostText, JTextField portText, JTextField databaseText, JTextField userText, JTextField passText, File file, ConfigGUI configGUI) {
			this.hostText = hostText;
			this.portText = portText;
			this.databaseText = databaseText;
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
				bw.write(databaseText.getText() + "\n");
				bw.write(userText.getText() + "\n");
				bw.write(passText.getText());
				bw.close();
				done = true;
				configGUI.dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}