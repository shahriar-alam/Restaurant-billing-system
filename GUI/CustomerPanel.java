package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomerPanel extends JFrame {

	private JPanel panel1;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CustomerPanel frame = new CustomerPanel();
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
	public CustomerPanel(People p1) {
		setTitle("Customer Panel");
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
		
		JLabel label2 = new JLabel("Customer ID: "+p1.getID());
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
		label5.setBounds(23, 262, 262, 31);
		panel1.add(label5);
		
		JLabel label6 = new JLabel("Address: "+p1.getAddress());
		label6.setForeground(Color.WHITE);
		label6.setFont(new Font("Calibri", Font.BOLD, 14));
		label6.setBounds(23, 326, 293, 31);
		panel1.add(label6);
		
		JButton button1 = new JButton("Customer Order");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerOrder co = new CustomerOrder(p1);
				co.setVisible(true);
				dispose();
			}
		});
		button1.setForeground(Color.BLACK);
		button1.setFont(new Font("Calibri", Font.PLAIN, 16));
		button1.setBackground(Color.WHITE);
		button1.setBounds(505, 104, 150, 60);
		panel1.add(button1);
		
		JButton button2 = new JButton("Log Out");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ln = new Login();
				ln.setVisible(true);
				dispose();
			}
		});
		button2.setFont(new Font("Calibri", Font.PLAIN, 16));
		button2.setForeground(Color.BLACK);
		button2.setBackground(Color.WHITE);
		button2.setBounds(505, 196, 150, 60);
		panel1.add(button2);
		
		JButton button3 = new JButton("Edit Profile");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProfile ep = new EditProfile(p1);
				ep.setVisible(true);
				dispose();
			}
		});
		button3.setForeground(Color.BLACK);
		button3.setFont(new Font("Calibri", Font.PLAIN, 15));
		button3.setBackground(Color.WHITE);
		button3.setBounds(328, 196, 150, 60);
		panel1.add(button3);
		
		JButton button4 = new JButton("Check Order");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckOrder co = new CheckOrder(p1);
				co.setVisible(true);
				dispose();
			}
		});
		button4.setForeground(Color.BLACK);
		button4.setFont(new Font("Calibri", Font.PLAIN, 15));
		button4.setBackground(Color.WHITE);
		button4.setBounds(328, 104, 150, 60);
		panel1.add(button4);
		
		JLabel label7 = new JLabel("New label");
		label7.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		label7.setBounds(0, 0, 684, 411);
		panel1.add(label7);
	}

}
