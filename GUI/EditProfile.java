package GUI;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditProfile extends JFrame {

	private JPanel panel1;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	Connection con = null;
	private JTextField textField6;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditProfile frame = new EditProfile();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
	public EditProfile(People p1) {
		setTitle("Edit Profile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		JLabel label1 = new JLabel("Name:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 15));
		label1.setBounds(43, 34, 69, 35);
		panel1.add(label1);
		
		JLabel label2 = new JLabel("Contact number:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(43, 80, 119, 39);
		panel1.add(label2);
		
		JLabel label3 = new JLabel("Email:");
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setForeground(Color.WHITE);
		label3.setBounds(43, 149, 69, 32);
		panel1.add(label3);
		
		JLabel label4 = new JLabel("Password:");
		label4.setForeground(Color.WHITE);
		label4.setFont(new Font("Calibri", Font.BOLD, 15));
		label4.setBounds(43, 209, 69, 36);
		panel1.add(label4);
		
		JLabel label5 = new JLabel("Address:");
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		label5.setForeground(Color.WHITE);
		label5.setBounds(43, 269, 85, 35);
		panel1.add(label5);
		
		JButton button1 = new JButton("Update");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("update employee set ename = ?, contact = ?, email = ?, password = ?, address = ?, secret = ? where eid = ?");
					stmt.setString(1, textField1.getText());
					stmt.setInt(2, Integer.parseInt(textField2.getText()));
					stmt.setString(3, textField3.getText());
					stmt.setString(4, textField4.getText());
					stmt.setString(5, textField5.getText());
					stmt.setString(6, textField6.getText());
					stmt.setInt(7, p1.getID());
					int i = stmt.executeUpdate();
					
					if(i>0){
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Please login again");
						Login ln = new Login();
						ln.setVisible(true);
						dispose();
					}
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Enter all the info correctly");
				}
			}
		});
		
		JLabel label7 = new JLabel("Secret Code:");
		label7.setFont(new Font("Calibri", Font.BOLD, 15));
		label7.setForeground(Color.WHITE);
		label7.setBounds(43, 337, 96, 28);
		panel1.add(label7);
		button1.setFont(new Font("Calibri", Font.PLAIN, 15));
		button1.setForeground(Color.BLACK);
		button1.setBackground(Color.WHITE);
		button1.setBounds(483, 105, 119, 57);
		panel1.add(button1);
		
		JButton button2 = new JButton("Close");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(p1.getType().equals("Admin")){
					AdminPanel ap = new AdminPanel(p1);
					ap.setVisible(true);
					dispose();
				}
				else if(p1.getType().equals("Employee")){
					UserPanel up = new UserPanel(p1);
					up.setVisible(true);
					dispose();
				}
				else {
					CustomerPanel cp = new CustomerPanel(p1);
					cp.setVisible(true);
					dispose();
				}
				
			}
		});
		button2.setForeground(Color.BLACK);
		button2.setFont(new Font("Calibri", Font.PLAIN, 15));
		button2.setBackground(Color.WHITE);
		button2.setBounds(483, 224, 119, 57);
		panel1.add(button2);
		
		textField1 = new JTextField(p1.getName());
		textField1.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField1.setBounds(166, 31, 220, 40);
		panel1.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField("0"+Integer.toString(p1.getContact()));
		textField2.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField2.setBounds(166, 88, 220, 40);
		panel1.add(textField2);
		textField2.setColumns(10);
		
		textField3 = new JTextField(p1.getEmail());
		textField3.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField3.setBounds(166, 146, 220, 40);
		panel1.add(textField3);
		textField3.setColumns(10);
		
		textField4 = new JTextField(p1.getPassword());
		textField4.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField4.setBounds(166, 208, 220, 40);
		panel1.add(textField4);
		textField4.setColumns(10);
		
		textField5 = new JTextField(p1.getAddress());
		textField5.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField5.setBounds(166, 267, 220, 40);
		panel1.add(textField5);
		textField5.setColumns(10);
		
		textField6 = new JTextField(p1.getSecret());
		textField6.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField6.setBounds(166, 332, 220, 40);
		panel1.add(textField6);
		textField6.setColumns(10);
		
		JLabel label6 = new JLabel("");
		label6.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		label6.setBounds(0, 0, 684, 411);
		panel1.add(label6);
	}
}
