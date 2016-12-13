package team2.inventory.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class WelcomeScreen {
	
	private static JFrame beginFrame;
	
	public WelcomeScreen(Connection connection){

		/**************************************
		/	Basic setup of the login screen	  /
		/									  /
		/*************************************/
		beginFrame = new JFrame();
		beginFrame.setTitle("Welcome");
		beginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		beginFrame.getContentPane().setLayout(springLayout);
		beginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		/**************************************
		/	   JLabels for the contentPane	  /
		/									  /
		/*************************************/
		JLabel lblWIMS = new JLabel("Warehouse Inventory Management System");
		lblWIMS.setFont(new Font("Tahoma", Font.PLAIN, 54));
		lblWIMS.setHorizontalAlignment(SwingConstants.CENTER);
		lblWIMS.setAlignmentX(Component.CENTER_ALIGNMENT);
		beginFrame.getContentPane().add(lblWIMS);
		
		JButton btnLoginAsExisting = new JButton("Login as Existing User");
		btnLoginAsExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPopUp temp = null;
				try {
					temp = new LoginPopUp(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				temp.setVisible(true);
			}
		});
		beginFrame.getContentPane().add(btnLoginAsExisting);
		
		
		/**************************************
		/	  Layout constraints for all 	  /
		/	  components based on location	  /
		/*************************************/
		springLayout.putConstraint(SpringLayout.NORTH, lblWIMS, 0, SpringLayout.NORTH, beginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblWIMS, 0, SpringLayout.SOUTH, beginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblWIMS, 0, SpringLayout.EAST, beginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblWIMS, 0, SpringLayout.WEST, beginFrame.getContentPane());

		springLayout.putConstraint(SpringLayout.SOUTH, btnLoginAsExisting, -127, SpringLayout.SOUTH, beginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLoginAsExisting, -400, SpringLayout.EAST, beginFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnLoginAsExisting, 400, SpringLayout.WEST, beginFrame.getContentPane());
		

		beginFrame.getRootPane().setDefaultButton(btnLoginAsExisting);
		beginFrame.setVisible(true);
	}
	
	public static void close(){
		beginFrame.dispose();
	}
}
