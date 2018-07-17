package GUI;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class CustomerOrder extends JFrame {

	private JPanel panel1;
	private JTextField textField1;
	private JTable table1;
	Connection con;
	private JTextField textField2;
	private JTextField textField3;
	private JTable table2;
	private JTextField textField4;
	private int serial=0;
	int price;
	int quantity;
	int item = 0;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CustomerOrder frame = new CustomerOrder();
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
	public void table1con(){
		try{
			con = DBCon.dbcon();
			PreparedStatement stmt = con.prepareStatement("select fid, ftype, fname, fprice from table1 where fid not in (select fid from sell where sid = ?) order by ftype, fname");
			stmt.setInt(1, serial);
			ResultSet rs = stmt.executeQuery();
			
			table1.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			stmt.close();
			con.close();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void setSerial(){
		try{
			con = DBCon.dbcon();
			PreparedStatement stmt = con.prepareStatement("select max(sid) from sell");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				if(rs.getString(1) == null){
					serial = 1;
				}
				else{
					serial = (Integer.parseInt(rs.getString(1)))+1;
				}	
			}		
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void table2con(){
		try{
			con = DBCon.dbcon();
			PreparedStatement stmt = con.prepareStatement("select fid, fname, ftype, fprice, quantity, tprice from sell where sid = ? order by fid");
			stmt.setInt(1, serial);
			ResultSet rs = stmt.executeQuery();
			table2.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public int getSerial(){
		return serial;
	}
	public CustomerOrder(People p1) {
		setSerial();
		setTitle("Customer Order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		JLabel label1 = new JLabel("Category:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 15));
		label1.setBounds(39, 91, 76, 30);
		panel1.add(label1);
		
		JLabel label2 = new JLabel("Name:");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(39, 158, 46, 14);
		panel1.add(label2);
		
		JLabel label3 = new JLabel("Quantity:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(39, 207, 89, 37);
		panel1.add(label3);
		
		JLabel label5 = new JLabel("Menu");
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font("Calibri", Font.BOLD, 15));
		label5.setBounds(468, 11, 58, 30);
		panel1.add(label5);
		
		JLabel label6 = new JLabel("Cart");
		label6.setFont(new Font("Calibri", Font.BOLD, 15));
		label6.setForeground(Color.WHITE);
		label6.setBounds(468, 210, 58, 30);
		panel1.add(label6);
		
		JLabel label7 = new JLabel("FID:");
		label7.setForeground(Color.WHITE);
		label7.setFont(new Font("Calibri", Font.BOLD, 15));
		label7.setBounds(39, 33, 46, 36);
		panel1.add(label7);
		
		JButton button1 = new JButton("Add");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField1.getText()!=null){
					try{
						con = DBCon.dbcon();
						PreparedStatement stmt = con.prepareStatement("Insert into sell values(?,?,?,?,?,?,?)");
						stmt.setInt(1, serial);
						stmt.setInt(2,(Integer.parseInt(textField4.getText())));
						stmt.setString(3, textField3.getText());
						stmt.setInt(4, (Integer.parseInt(textField1.getText())));
						stmt.setInt(5, (Integer.parseInt(textField1.getText()))*price);
						stmt.setString(6, textField2.getText());
						stmt.setInt(7, price);
						stmt.executeUpdate();
						stmt.close();
						con.close();
						table2con();
						table1con();
						item++;
						
						textField1.setText("1");
						textField2.setText("");
						textField3.setText("");
						textField4.setText("");
						
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please select one of the item");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Enter the quantity");
				}
				
			}
		});
		button1.setEnabled(false);
		button1.setFont(new Font("Calibri", Font.BOLD, 15));
		button1.setForeground(Color.BLACK);
		button1.setBackground(Color.WHITE);
		button1.setBounds(56, 265, 105, 55);
		panel1.add(button1);
		
		JButton button2 = new JButton("Update");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("update sell set quantity = ?, tprice = ? where sid = ? and fid = ?");
					stmt.setInt(1, (Integer.parseInt(textField1.getText())));
					stmt.setInt(2, (Integer.parseInt(textField1.getText()))*price);
					stmt.setInt(3, serial);
					stmt.setInt(4, (Integer.parseInt(textField4.getText())));
					
					stmt.executeUpdate();
					
					stmt.close();
					con.close();
					
					textField1.setText("1");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Please select one of the item");
				}
				table2con();
				
			}
		});
		button2.setEnabled(false);
		button2.setBackground(Color.WHITE);
		button2.setForeground(Color.BLACK);
		button2.setFont(new Font("Calibri", Font.BOLD, 15));
		button2.setBounds(187, 265, 105, 55);
		panel1.add(button2);
		
		JButton button3 = new JButton("Delete");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("delete from sell where sid = ? and fid = ?");
					stmt.setInt(1, serial);
					stmt.setInt(2, (Integer.parseInt(textField4.getText())));
					stmt.executeUpdate();
					stmt.close();
					con.close();
					table2con();
					table1con();
					item--;
					
					textField1.setText("1");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
					
				}catch(Exception f){
					JOptionPane.showMessageDialog(null,"Please select one of the item");
				}
			}
		});
		button3.setEnabled(false);
		button3.setBackground(Color.WHITE);
		button3.setForeground(Color.BLACK);
		button3.setFont(new Font("Calibri", Font.BOLD, 15));
		button3.setBounds(56, 331, 105, 55);
		panel1.add(button3);
		
		JButton button4 = new JButton("Done");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(item>0) {
					try {
						con = DBCon.dbcon();
						int tprice = 0;
						PreparedStatement stmt = con.prepareStatement("select sum(tprice) from sell where sid = ?");
						stmt.setInt(1, serial);
						ResultSet rs = stmt.executeQuery();
						while(rs.next()) {
							tprice = Integer.parseInt(rs.getString(1));
						}
						rs.close();
						stmt.close();
						
						stmt = con.prepareStatement("insert into orderfood values(?,?,?,?)");
						stmt.setInt(1, serial);
						stmt.setInt(2, p1.getID());
						stmt.setInt(3, tprice);
						stmt.setString(4, "NO");
						stmt.executeUpdate();
						stmt.close();
						con.close();
						
						CustomerPanel cp = new CustomerPanel(p1);
						cp.setVisible(true);
						dispose();
					
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, "Can't Confirm. Try again");
					}
				}
				else {
					CustomerPanel cp = new CustomerPanel(p1);
					cp.setVisible(true);
					dispose();
				}	
			}
		});
		button4.setEnabled(true);
		button4.setBackground(Color.WHITE);
		button4.setForeground(Color.BLACK);
		button4.setFont(new Font("Calibri", Font.BOLD, 15));
		button4.setBounds(187, 331, 105, 55);
		panel1.add(button4);
		
		textField2 = new JTextField();
		textField2.setEditable(false);
		textField2.setBounds(102, 86, 190, 40);
		panel1.add(textField2);
		textField2.setColumns(10);
		
		textField3 = new JTextField();
		textField3.setEditable(false);
		textField3.setBounds(102, 145, 190, 40);
		panel1.add(textField3);
		textField3.setColumns(10);
		
		textField4 = new JTextField();
		textField4.setEditable(false);
		textField4.setBounds(102, 31, 190, 40);
		panel1.add(textField4);
		textField4.setColumns(10);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField1.setText("1");
		textField1.setBounds(102, 205, 190, 40);
		panel1.add(textField1);
		textField1.setColumns(10);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(334, 48, 320, 156);
		panel1.add(scrollPane1);
		
		table1= new JTable();
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				button1.setEnabled(true);
				button2.setEnabled(false);
				button3.setEnabled(false);
				button4.setEnabled(true);
				
				try{
					int row = table1.getSelectedRow();
					String fname = (table1.getModel().getValueAt(row, 2)).toString();
					
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("select * from table1 where fname='"+fname+"'");
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()){
						textField1.setText("1");
						textField2.setText(rs.getString("FTYPE"));
						textField3.setText(rs.getString("FNAME"));
						textField4.setText(rs.getString("FID"));
						price = Integer.parseInt(rs.getString("FPRICE"));
					}
					rs.close();
					stmt.close();
					con.close();
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
				
			}
		});
		scrollPane1.setViewportView(table1);		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(334, 245, 320, 141);
		panel1.add(scrollPane2);
		
		table2 = new JTable();
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				button1.setEnabled(false);
				button2.setEnabled(true);
				button3.setEnabled(true);
				button4.setEnabled(true);
				
				try{
					int row = table2.getSelectedRow();
					int sid = serial;
					int fid = Integer.parseInt((table2.getModel().getValueAt(row, 0)).toString());
					
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("select * from sell where sid = ? and fid = ?");
					stmt.setInt(1, sid);
					stmt.setInt(2, fid);
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()){
						textField1.setText(rs.getString("QUANTITY"));
						textField2.setText(rs.getString("FTYPE"));
						textField3.setText(rs.getString("FNAME"));
						textField4.setText(rs.getString("FID"));
						price = Integer.parseInt(rs.getString("FPRICE"));
					}
					rs.close();
					stmt.close();
					con.close();
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
				
			}
		});
		scrollPane2.setViewportView(table2);
		
		table2con();
		table1con();
		
		JLabel label4 = new JLabel("");
		label4.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		label4.setBounds(0, 0, 684, 411);
		panel1.add(label4);
	}
}
