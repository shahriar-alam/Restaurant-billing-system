package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.interfaces.RSAKey;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditUser extends JFrame {

	private JPanel panel1;
	Connection con;
	private JTable table;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private JTextField textField7;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditUser frame = new EditUser();
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
	public void table1con(People p1){
		try{
			con = DBCon.dbcon();
			PreparedStatement stmt = con.prepareStatement("select * from employee where eid != ? order by eid");
			stmt.setInt(1, p1.getID());
			ResultSet rs = stmt.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public EditUser(People p1) {
		setTitle("Edit User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		JLabel label1 = new JLabel("ID:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 15));
		label1.setBounds(39, 24, 105, 30);
		panel1.add(label1);
		
		JLabel label2 = new JLabel("Name:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(39, 65, 65, 30);
		panel1.add(label2);
		
		JLabel label3 = new JLabel("Email:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(39, 106, 65, 30);
		panel1.add(label3);
		
		JLabel label4 = new JLabel("Contact:");
		label4.setForeground(Color.WHITE);
		label4.setFont(new Font("Calibri", Font.BOLD, 15));
		label4.setBounds(39, 147, 81, 32);
		panel1.add(label4);
		
		JLabel label5 = new JLabel("Password:");
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		label5.setBounds(39, 182, 65, 40);
		panel1.add(label5);
		
		JLabel label6 = new JLabel("Address:");
		label6.setForeground(Color.WHITE);
		label6.setFont(new Font("Calibri", Font.BOLD, 15));
		label6.setBounds(39, 224, 65, 31);
		panel1.add(label6);
		
		JLabel label7 = new JLabel("Salary:");
		label7.setForeground(Color.WHITE);
		label7.setFont(new Font("Calibri", Font.BOLD, 15));
		label7.setBounds(39, 249, 81, 55);
		panel1.add(label7);
		
		JRadioButton radioButton1 = new JRadioButton("Admin");
		radioButton1.setForeground(Color.WHITE);
		radioButton1.setFont(new Font("Calibri", Font.BOLD, 15));
		radioButton1.setBackground(Color.GRAY);
		radioButton1.setBounds(35, 311, 109, 23);
		panel1.add(radioButton1);
		
		JRadioButton radioButton2 = new JRadioButton("Employee");
		radioButton2.setForeground(Color.WHITE);
		radioButton2.setFont(new Font("Calibri", Font.BOLD, 15));
		radioButton2.setBackground(Color.GRAY);
		radioButton2.setBounds(175, 311, 109, 23);
		panel1.add(radioButton2);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioButton1);
		bg.add(radioButton2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(356, 24, 298, 353);
		panel1.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try{
					int row = table.getSelectedRow();
					int EID = Integer.parseInt((table.getModel().getValueAt(row, 0)).toString());
					
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("select * from employee where eid=?");
					stmt.setInt(1, EID);
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()){
						textField1.setText(rs.getString("EID"));
						textField2.setText(rs.getString("ENAME"));
						textField3.setText(rs.getString("EMAIL"));
						textField4.setText(rs.getString("CONTACT"));
						textField5.setText(rs.getString("PASSWORD"));
						textField6.setText(rs.getString("ADDRESS"));
						textField7.setText(rs.getString("SALARY"));
						if(rs.getString("ETYPE").equals("Admin")){
							radioButton1.setEnabled(true);
							radioButton2.setEnabled(true);
							textField7.setEnabled(true);
							label7.setEnabled(true);
							radioButton1.setSelected(true);
						}
						else if(rs.getString("ETYPE").equals("Employee")){
							radioButton1.setEnabled(true);
							radioButton2.setEnabled(true);
							textField7.setEnabled(true);
							label7.setEnabled(true);
							radioButton2.setSelected(true);
						}
						else {
							radioButton1.setEnabled(false);
							radioButton2.setEnabled(false);
							textField7.setEnabled(false);
							label7.setEnabled(false);
							
						}
					}
					rs.close();
					stmt.close();
					con.close();
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		scrollPane.setViewportView(table);
		table1con(p1);
		
		JButton button1 = new JButton("Update");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("update employee set ename = ?, etype = ?, email = ?, contact = ?, password = ?, address = ?, salary = ? where eid = ?");
					stmt.setString(1, textField2.getText());
					stmt.setString(3, textField3.getText());
					stmt.setInt(4, Integer.parseInt(textField4.getText()));
					stmt.setString(5, textField5.getText());
					stmt.setString(6, textField6.getText());
					stmt.setString(7, textField7.getText());
					stmt.setInt(8, Integer.parseInt(textField1.getText()));
					
					if(radioButton1.isSelected()){
						stmt.setString(2, "Admin");
					}
					else{
						stmt.setString(2, "Employee");
					}
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "User updated");
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
					textField5.setText("");
					textField6.setText("");
					textField7.setText("");
					table1con(p1);
					stmt.close();
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Select a user or enter all the info correctly");
				}
			}
		});
		button1.setFont(new Font("Calibri", Font.PLAIN, 12));
		button1.setForeground(Color.BLACK);
		button1.setBackground(Color.WHITE);
		button1.setBounds(10, 352, 98, 37);
		panel1.add(button1);
		
		JButton button2 = new JButton("Delete");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("delete from employee where eid = ?");
					stmt.setInt(1, Integer.parseInt(textField1.getText()));
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "User deleted");
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
					textField5.setText("");
					textField6.setText("");
					textField7.setText("");
					table1con(p1);
					stmt.close();
					con.close();
				}catch(Exception f){
					JOptionPane.showMessageDialog(null, "Please select one of the user");
				}
			}
		});
		button2.setFont(new Font("Calibri", Font.PLAIN, 12));
		button2.setForeground(Color.BLACK);
		button2.setBackground(Color.WHITE);
		button2.setBounds(121, 352, 100, 37);
		panel1.add(button2);
		
		JButton button3 = new JButton("Close");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p1.getType().equals("Admin")){
					AdminPanel ap = new AdminPanel(p1);
					ap.setVisible(true);
					dispose();
				}
				else{
					UserPanel up = new UserPanel(p1);
					up.setVisible(true);
					dispose();
				}
			}
		});
		button3.setFont(new Font("Calibri", Font.PLAIN, 12));
		button3.setForeground(Color.BLACK);
		button3.setBackground(Color.WHITE);
		button3.setBounds(232, 352, 90, 37);
		panel1.add(button3);
		
		textField1 = new JTextField();
		textField1.setEditable(false);
		textField1.setBounds(135, 24, 190, 30);
		panel1.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(135, 65, 190, 30);
		panel1.add(textField2);
		textField2.setColumns(10);
		
		textField3 = new JTextField();
		textField3.setBounds(135, 106, 190, 30);
		panel1.add(textField3);
		textField3.setColumns(10);
		
		textField4 = new JTextField();
		textField4.setBounds(135, 148, 190, 30);
		panel1.add(textField4);
		textField4.setColumns(10);
		
		textField5 = new JTextField();
		textField5.setBounds(135, 187, 190, 30);
		panel1.add(textField5);
		textField5.setColumns(10);
		
		textField6 = new JTextField();
		textField6.setBounds(135, 224, 190, 30);
		panel1.add(textField6);
		textField6.setColumns(10);
		
		textField7 = new JTextField();
		textField7.setBounds(135, 261, 190, 30);
		panel1.add(textField7);
		textField7.setColumns(10);
		
		JLabel label8 = new JLabel("");
		label8.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		label8.setBounds(0, 0, 684, 411);
		panel1.add(label8);
		

	}
}
