package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class signup extends JFrame {

	private JPanel panel1;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JPasswordField passwordField1;
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
//					signup frame = new signup();
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
	public signup(People p1) {
		setTitle("Sign up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField1.setBounds(372, 11, 300, 35);
		panel1.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField2.setBounds(372, 57, 140, 35);
		panel1.add(textField2);
		textField2.setColumns(10);
		
		textField3 = new JTextField();
		textField3.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField3.setBounds(372, 103, 300, 35);
		panel1.add(textField3);
		textField3.setColumns(10);
		
		textField4 = new JTextField();
		textField4.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField4.setBounds(372, 149, 300, 35);
		panel1.add(textField4);
		textField4.setColumns(10);
		
		textField5 = new JTextField();
		textField5.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField5.setBounds(372, 241, 300, 35);
		panel1.add(textField5);
		textField5.setColumns(10);
		
		textField6 = new JTextField();
		textField6.setBounds(372, 287, 300, 35);
		panel1.add(textField6);
		textField6.setColumns(10);
		
		passwordField1 = new JPasswordField();
		passwordField1.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordField1.setBounds(372, 195, 300, 35);
		panel1.add(passwordField1);
		
		String array1[] = {"gmail.com", "yahoo.com", "outlook.com"};
		JComboBox comboBox1 = new JComboBox(array1);
		comboBox1.setForeground(Color.BLACK);
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setFont(new Font("Calibri", Font.PLAIN, 18));
		comboBox1.setBounds(549, 57, 123, 35);
		panel1.add(comboBox1);
		
		JRadioButton radio1 = new JRadioButton("Admin");
		radio1.setForeground(Color.BLACK);
		radio1.setBackground(Color.LIGHT_GRAY);
		radio1.setFont(new Font("Calibri", Font.BOLD, 18));
		radio1.setBounds(50, 338, 140, 40);
		panel1.add(radio1);
		
		JRadioButton radio2 = new JRadioButton("Employee");
		radio2.setSelected(true);
		radio2.setBackground(Color.LIGHT_GRAY);
		radio2.setForeground(Color.BLACK);
		radio2.setFont(new Font("Calibri", Font.BOLD, 18));
		radio2.setBounds(206, 338, 140, 40);
		panel1.add(radio2);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radio1);
		bg.add(radio2);
		
		JButton button1 = new JButton("Sign up");
		button1.setForeground(Color.BLACK);
		button1.setBackground(Color.WHITE);
		button1.setFont(new Font("Calibri", Font.BOLD, 15));
		button1.setBounds(399, 339, 109, 40);
		panel1.add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{  
					con = DBCon.dbcon();  
					PreparedStatement stmt=con.prepareStatement("insert into employee (eid, ename, etype, email, contact, password, address, salary, secret) values (seid.nextval,?,?,?,?,?,?,?,?)");
					stmt.setString(1, textField1.getText());
					if(radio1.isSelected()){
						stmt.setString(2, radio1.getText());
					}
					else{
						stmt.setString(2, radio2.getText());
					}
					stmt.setString(3, textField2.getText()+"@"+comboBox1.getSelectedItem());
					stmt.setInt(4, Integer.parseInt(textField4.getText()));
					stmt.setString(5, passwordField1.getText());
					stmt.setString(6, textField3.getText());
					stmt.setInt(7, Integer.parseInt(textField5.getText()));
					stmt.setString(8, textField6.getText());
					
					int i = stmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "User added");
					stmt.close();
					con.close();
					
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
					textField5.setText("");
					textField6.setText("");
					passwordField1.setText("");
					
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Enter all the info correctly. If problem persists, use another email.");
				}
			}
		});
		
		
		JButton button2 = new JButton("Close");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel ap = new AdminPanel(p1);
				ap.setVisible(true);
				dispose();
			}
		});
		button2.setForeground(Color.BLACK);
		button2.setBackground(Color.WHITE);
		button2.setFont(new Font("Calibri", Font.BOLD, 15));
		button2.setBounds(549, 339, 99, 40);
		panel1.add(button2);
		
		
		JLabel label1 = new JLabel("Name:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 18));
		label1.setBounds(229, 17, 50, 27);
		panel1.add(label1);
		
		JLabel label2 = new JLabel("Email:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 18));
		label2.setBounds(229, 58, 58, 33);
		panel1.add(label2);
		
		JLabel label3 = new JLabel("Contact number:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 17));
		label3.setBounds(229, 133, 127, 69);
		panel1.add(label3);
		
		JLabel label4 = new JLabel("Address:");
		label4.setForeground(Color.WHITE);
		label4.setFont(new Font("Calibri", Font.BOLD, 18));
		label4.setBounds(229, 102, 66, 37);
		panel1.add(label4);
		
		JLabel label5 = new JLabel("Password:");
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font("Calibri", Font.BOLD, 18));
		label5.setBounds(229, 199, 83, 27);
		panel1.add(label5);
		
		JLabel label6 = new JLabel("Salary:");
		label6.setForeground(Color.WHITE);
		label6.setFont(new Font("Calibri", Font.BOLD, 18));
		label6.setBounds(229, 245, 152, 27);
		panel1.add(label6);
		
		JLabel label9 = new JLabel("@");
		label9.setForeground(Color.WHITE);
		label9.setBackground(new Color(240, 240, 240));
		label9.setFont(new Font("Calibri", Font.BOLD, 18));
		label9.setBounds(522, 57, 58, 40);
		panel1.add(label9);
		
		JLabel label10 = new JLabel("Secret Code:");
		label10.setForeground(Color.WHITE);
		label10.setBackground(new Color(240, 240, 240));
		label10.setFont(new Font("Calibri", Font.BOLD, 18));
		label10.setBounds(229, 290, 117, 26);
		panel1.add(label10);
		
		JLabel label7 = new JLabel("");
		label7.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\11218622_860905744002996_2919853945693511245_n.jpg"));
		label7.setBounds(10, 92, 190, 170);
		panel1.add(label7);
		
		JLabel label8 = new JLabel("");
		label8.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		label8.setBounds(0, 0, 684, 411);
		panel1.add(label8);
	}
}
