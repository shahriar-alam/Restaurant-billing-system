package GUI;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel panel1;
	private JTextField textField1;
	private JPasswordField passwordField1;
	Connection con = null;
	People p1 = null;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
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
	public Login() {
		setTitle("Log in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		JLabel label1 = new JLabel("User Name:");
		label1.setBounds(211, 104, 84, 84);
		panel1.add(label1);
		label1.setFont(new Font("Calibri", Font.BOLD, 17));
		label1.setForeground(Color.WHITE);
		label1.setBackground(Color.BLACK);
		
		JLabel label2 = new JLabel("Password:");
		label2.setBounds(215, 184, 73, 45);
		panel1.add(label2);
		label2.setForeground(Color.WHITE);
		label2.setBackground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 17));
		
		textField1 = new JTextField();
		textField1.setBounds(310, 123, 336, 45);
		panel1.add(textField1);
		textField1.setForeground(Color.BLACK);
		textField1.setBackground(Color.WHITE);
		textField1.setFont(new Font("Calibri", Font.PLAIN, 17));
		textField1.setColumns(10);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(310, 183, 336, 45);
		panel1.add(passwordField1);
		passwordField1.setForeground(Color.BLACK);
		passwordField1.setFont(new Font("Calibri", Font.PLAIN, 17));
		passwordField1.setEchoChar('*');
		
		JButton button1 = new JButton("Login");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{  
					con = DBCon.dbcon();  
					PreparedStatement stmt=con.prepareStatement("select * from employee where email=? and password=?");
					stmt.setString(1, textField1.getText());
					stmt.setString(2, passwordField1.getText());
					
					ResultSet rs = stmt.executeQuery();
					int count = 0;
					p1 = new People();
					while(rs.next()){
						p1.setID(rs.getInt("EID"));
						p1.setName(rs.getString("ENAME"));
						p1.setType(rs.getString("ETYPE"));
						p1.setEmail(rs.getString("EMAIL"));
						p1.setContact(rs.getInt("CONTACT"));
						p1.setPassword(rs.getString("PASSWORD"));
						p1.setAddress(rs.getString("ADDRESS"));
						p1.setSalary(rs.getInt("SALARY"));
						p1.setSecret(rs.getString("SECRET"));
						count++;
					}
					
					if(count>0){
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
					else{
						JOptionPane.showMessageDialog(null, "Invalid Username and Password");
					}
					rs.close();
					stmt.close();
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		button1.setBounds(333, 255, 117, 45);
		panel1.add(button1);
		button1.setBackground(Color.WHITE);
		button1.setForeground(Color.BLACK);
		button1.setFont(new Font("Calibri", Font.BOLD, 17));
		
		JButton button2 = new JButton("Forgot Password");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				PasswordRecover pr = new PasswordRecover();
				pr.setVisible(true);
			}
		});
		button2.setBounds(470, 255, 162, 45);
		panel1.add(button2);
		button2.setForeground(Color.BLACK);
		button2.setBackground(Color.WHITE);
		button2.setFont(new Font("Calibri", Font.BOLD, 17));
		
		JButton button3 = new JButton("Customer Signup");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerSignup cs = new CustomerSignup();
				cs.setVisible(true);
				dispose();
			}
		});
		button3.setBounds(402, 311, 172, 45);
		panel1.add(button3);
		button3.setBackground(Color.WHITE);
		button3.setForeground(Color.BLACK);
		button3.setFont(new Font("Calibri", Font.BOLD, 17));
		
		JLabel label3 = new JLabel("");
		label3.setBounds(11, 95, 190, 170);
		panel1.add(label3);
		label3.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\11218622_860905744002996_2919853945693511245_n.jpg"));
		
		JLabel labelBack = new JLabel("");
		labelBack.setBounds(0, 0, 684, 411);
		panel1.add(labelBack);
		labelBack.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
	}
}
