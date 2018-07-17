package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class PasswordRecover extends JFrame {

	private JPanel panel1;
	private JTextField textField1;
	private JTextField textField2;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	Connection con = null;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PasswordRecover frame = new PasswordRecover();
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
	public PasswordRecover() {
		setTitle("Password Recovery");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		JLabel label1 = new JLabel("Email:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 18));
		label1.setBounds(251, 73, 66, 33);
		panel1.add(label1);
		
		JLabel label2 = new JLabel("Secret Code:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 18));
		label2.setBounds(251, 128, 150, 42);
		panel1.add(label2);
		
		JLabel label3 = new JLabel("New Password:");
		label3.setFont(new Font("Calibri", Font.BOLD, 18));
		label3.setForeground(Color.WHITE);
		label3.setBounds(251, 181, 134, 54);
		panel1.add(label3);
		
		JLabel label4 = new JLabel("Confirm Password:");
		label4.setFont(new Font("Calibri", Font.BOLD, 18));
		label4.setForeground(Color.WHITE);
		label4.setBounds(251, 250, 165, 33);
		panel1.add(label4);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField1.setBounds(399, 69, 123, 42);
		panel1.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Calibri", Font.PLAIN, 18));
		textField2.setBounds(399, 129, 256, 42);
		panel1.add(textField2);
		textField2.setColumns(10);
		
		JLabel label7 = new JLabel("@");
		label7.setForeground(Color.WHITE);
		label7.setFont(new Font("Calibri", Font.BOLD, 18));
		label7.setBounds(532, 69, 66, 42);
		panel1.add(label7);
		
		String array1[] = {"gmail.com", "yahoo.com", "outlook.com"};
		JComboBox comboBox1 = new JComboBox(array1);
		comboBox1.setFont(new Font("Calibri", Font.PLAIN, 18));
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setForeground(new Color(0, 0, 0));
		comboBox1.setBounds(554, 69, 101, 42);
		panel1.add(comboBox1);
		
		passwordField1 = new JPasswordField();
		passwordField1.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordField1.setBounds(399, 188, 256, 42);
		panel1.add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordField2.setBounds(399, 246, 256, 42);
		panel1.add(passwordField2);
		
		JButton button1 = new JButton("Submit");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{  
					con = DBCon.dbcon(); 
					PreparedStatement stmt=con.prepareStatement("select * from employee where email=? and secret=?");
					stmt.setString(1, textField1.getText()+"@"+comboBox1.getSelectedItem());
					stmt.setString(2, textField2.getText());
					ResultSet rs = stmt.executeQuery();
					
					int i = 0;
					
					while(rs.next()){
						i++;
					}
					rs.close();
					stmt.close();
					if(i>0){
						
						if(passwordField1.getText().equals(passwordField2.getText())){
							stmt = con.prepareStatement("update employee set password = ? where email = ? and contact = ?");
							stmt.setString(1, passwordField1.getText());
							stmt.setString(2, textField1.getText()+"@"+comboBox1.getSelectedItem());
							stmt.setInt(3, Integer.parseInt(textField2.getText()));
							int j = stmt.executeUpdate();
							if(j>0){
								JOptionPane.showMessageDialog(null, j+" password changed");
								
								Login ln = new Login();
								ln.setVisible(true);
								
								dispose();
							}
							else{
								JOptionPane.showMessageDialog(null, "Enter the passwords");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Password Mismatch");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Invalid Email or Secret Code");
					}
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		button1.setBackground(Color.WHITE);
		button1.setForeground(Color.BLACK);
		button1.setFont(new Font("Calibri", Font.BOLD, 18));
		button1.setBounds(408, 321, 114, 52);
		panel1.add(button1);
		
		JButton button2 = new JButton("Close");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ln = new Login();
				ln.setVisible(true);
				dispose();
			}
		});
		button2.setBackground(Color.WHITE);
		button2.setForeground(Color.BLACK);
		button2.setFont(new Font("Calibri", Font.BOLD, 18));
		button2.setBounds(532, 321, 114, 52);
		panel1.add(button2);
		
		JLabel label5 = new JLabel("");
		label5.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\11218622_860905744002996_2919853945693511245_n.jpg"));
		label5.setBounds(29, 95, 190, 170);
		panel1.add(label5);
		
		JLabel label6 = new JLabel("");
		label6.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		label6.setBounds(0, 0, 684, 411);
		panel1.add(label6);
	}

}
