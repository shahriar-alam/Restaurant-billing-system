package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AdminPanel extends JFrame {

	private JPanel panel1;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminPanel frame = new AdminPanel();
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

	public AdminPanel(People p1) {
		setTitle("Admin Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		JLabel label1 = new JLabel("User Details");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 34));
		label1.setBounds(23, 11, 234, 60);
		panel1.add(label1);
		
		JLabel label2 = new JLabel("Employee ID: "+p1.getID());
		label2.setFont(new Font("Calibri", Font.BOLD, 14));
		label2.setForeground(Color.WHITE);
		label2.setBounds(23, 82, 262, 39);
		panel1.add(label2);
		
		JLabel label3 = new JLabel("Name: "+p1.getName());
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 14));
		label3.setBounds(23, 132, 262, 48);
		panel1.add(label3);
		
		JLabel label4 = new JLabel("Email: "+p1.getEmail());
		label4.setFont(new Font("Calibri", Font.BOLD, 14));
		label4.setForeground(Color.WHITE);
		label4.setBounds(23, 191, 262, 48);
		panel1.add(label4);
		
		JLabel label5 = new JLabel("Contact number: 0"+p1.getContact());
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font("Calibri", Font.BOLD, 14));
		label5.setBounds(23, 250, 262, 31);
		panel1.add(label5);
		
		JLabel label6 = new JLabel("Address: "+p1.getAddress());
		label6.setForeground(Color.WHITE);
		label6.setFont(new Font("Calibri", Font.BOLD, 14));
		label6.setBounds(23, 304, 293, 31);
		panel1.add(label6);
		
		JLabel label7 = new JLabel("Salary: "+p1.getSalary());
		label7.setFont(new Font("Calibri", Font.BOLD, 14));
		label7.setForeground(Color.WHITE);
		label7.setBounds(23, 348, 293, 39);
		panel1.add(label7);
		
		JButton button1 = new JButton("Edit Menu");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditMenu em = new EditMenu(p1);
				em.setVisible(true);
				dispose();
			}
		});
		button1.setForeground(Color.BLACK);
		button1.setBackground(Color.WHITE);
		button1.setFont(new Font("Calibri", Font.PLAIN, 16));
		button1.setBounds(342, 235, 150, 60);
		panel1.add(button1);
		
		JButton button2 = new JButton("Add User");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signup su = new signup(p1);
				su.setVisible(true);
				dispose();
			}
		});
		button2.setFont(new Font("Calibri", Font.PLAIN, 16));
		button2.setForeground(Color.BLACK);
		button2.setBackground(Color.WHITE);
		button2.setBounds(342, 153, 150, 60);
		panel1.add(button2);
		
		JButton button3 = new JButton("Edit/Delete User");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditUser eu = new EditUser(p1);
				eu.setVisible(true);
				dispose();
			}
		});
		button3.setBackground(Color.WHITE);
		button3.setForeground(Color.BLACK);
		button3.setFont(new Font("Calibri", Font.PLAIN, 16));
		button3.setBounds(517, 153, 150, 60);
		panel1.add(button3);
		
		JButton button5 = new JButton("Log Out");
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ln = new Login();
				ln.setVisible(true);
				dispose();
			}
		});
		button5.setFont(new Font("Calibri", Font.PLAIN, 16));
		button5.setForeground(Color.BLACK);
		button5.setBackground(Color.WHITE);
		button5.setBounds(436, 316, 150, 60);
		panel1.add(button5);
		
		JButton button6 = new JButton("Edit Profile");
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditProfile ep = new EditProfile(p1);
				ep.setVisible(true);
				dispose();
			}
		});
		button6.setFont(new Font("Calibri", Font.PLAIN, 16));
		button6.setForeground(Color.BLACK);
		button6.setBackground(Color.WHITE);
		button6.setBounds(517, 235, 150, 60);
		panel1.add(button6);
		
		JButton button7 = new JButton("Confirm Order");
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmOrder co = new ConfirmOrder(p1);
				co.setVisible(true);
				dispose();
			}
		});
		button7.setFont(new Font("Calibri", Font.PLAIN, 16));
		button7.setForeground(Color.BLACK);
		button7.setBackground(Color.WHITE);
		button7.setBounds(342, 71, 150, 60);
		panel1.add(button7);
		
		JButton button8 = new JButton("See Sales");
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowTotal st = new ShowTotal(p1);
				st.setVisible(true);
				dispose();
			}
		});
		button8.setFont(new Font("Calibri", Font.PLAIN, 16));
		button8.setForeground(Color.BLACK);
		button8.setBackground(Color.WHITE);
		button8.setBounds(517, 71, 150, 60);
		panel1.add(button8);
		
		JLabel label8 = new JLabel("New label");
		label8.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		label8.setBounds(0, 0, 684, 411);
		panel1.add(label8);
	}
}
