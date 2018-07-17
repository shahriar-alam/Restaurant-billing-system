package GUI;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditMenu extends JFrame {

	private JPanel panel1;
	private JTable table1;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	Connection con = null;
	private JTextField textField4;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditMenu frame = new EditMenu();
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
			PreparedStatement stmt = con.prepareStatement("select * from table1 order by ftype, fname");
			ResultSet rs = stmt.executeQuery();
			
			table1.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public EditMenu(People p1) {
		setTitle("Edit Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		JLabel label1 = new JLabel("FID:");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 15));
		label1.setBounds(45, 38, 46, 14);
		panel1.add(label1);
		
		JLabel label2 = new JLabel("Food Name:");
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setForeground(Color.WHITE);
		label2.setBounds(45, 86, 83, 33);
		panel1.add(label2);
		
		JLabel label3 = new JLabel("Food Type:");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Calibri", Font.BOLD, 15));
		label3.setBounds(45, 147, 83, 22);
		panel1.add(label3);
		
		JLabel label4 = new JLabel("Price:");
		label4.setForeground(Color.WHITE);
		label4.setFont(new Font("Calibri", Font.BOLD, 15));
		label4.setBounds(45, 201, 46, 14);
		panel1.add(label4);
		
		JButton button1 = new JButton("Add");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("Insert into table1 values(sfid.nextval,?,?,?)");
					stmt.setString(1, textField2.getText());
					stmt.setString(2, textField4.getText());
					stmt.setInt(3, Integer.parseInt(textField3.getText()));
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Item added");
					stmt.close();
					con.close();
					table1con();
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}catch(Exception f){
					JOptionPane.showMessageDialog(null, "Enter all the info correctly. If problem persists, use different food name");
				}
			}
		});
		button1.setFont(new Font("Calibri", Font.PLAIN, 15));
		button1.setForeground(Color.BLACK);
		button1.setBackground(Color.WHITE);
		button1.setBounds(38, 272, 90, 40);
		panel1.add(button1);
		
		JButton button2 = new JButton("Update");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("update table1 set fname = ?, ftype = ?, fprice = ?  where fid = ?");
					stmt.setInt(4, Integer.parseInt(textField1.getText()));
					stmt.setString(1, textField2.getText());
					stmt.setString(2, textField4.getText());
					stmt.setInt(3, Integer.parseInt(textField3.getText()));
					int i = stmt.executeUpdate();
					if(i>0){
						JOptionPane.showMessageDialog(null, "Item updated");
					}
					stmt.close();
					con.close();
					table1con();
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}catch(Exception f){
					JOptionPane.showMessageDialog(null, "Enter all the info correctly. If problem persists, use different food name");
				}
			}
		});
		button2.setFont(new Font("Calibri", Font.PLAIN, 15));
		button2.setForeground(Color.BLACK);
		button2.setBackground(Color.WHITE);
		button2.setBounds(146, 272, 90, 40);
		panel1.add(button2);
		
		JButton button3 = new JButton("Delete");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("delete from table1 where fid = ?");
					stmt.setInt(1, Integer.parseInt(textField1.getText()));
					
					int i = stmt.executeUpdate();
					if(i>0){
						JOptionPane.showMessageDialog(null, "Item deleted");
					}
					stmt.close();
					con.close();
					table1con();
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}catch(Exception f){
					JOptionPane.showMessageDialog(null, "Please select one of the item");
				}
			}
		});
		button3.setFont(new Font("Calibri", Font.PLAIN, 15));
		button3.setForeground(Color.BLACK);
		button3.setBackground(Color.WHITE);
		button3.setBounds(255, 272, 90, 40);
		panel1.add(button3);
		
		JButton button4 = new JButton("Close");
		button4.addActionListener(new ActionListener() {
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
		button4.setFont(new Font("Calibri", Font.PLAIN, 15));
		button4.setForeground(Color.BLACK);
		button4.setBackground(Color.WHITE);
		button4.setBounds(146, 338, 90, 40);
		panel1.add(button4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 40, 285, 339);
		panel1.add(scrollPane);
		
		table1 = new JTable();
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try{
					int row = table1.getSelectedRow();
					int fid = Integer.parseInt((table1.getModel().getValueAt(row, 0)).toString());
					
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("select * from table1 where fid=?");
					stmt.setInt(1, fid);
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()){
						textField1.setText(rs.getString("FID"));
						textField2.setText(rs.getString("FNAME"));
						textField3.setText(rs.getString("FPRICE"));
						textField4.setText(rs.getString("FTYPE"));
					}
					rs.close();
					stmt.close();
					con.close();
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		scrollPane.setViewportView(table1);
		table1con();
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField1.setEditable(false);
		textField1.setBounds(123, 30, 170, 30);
		panel1.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField2.setBounds(123, 87, 170, 30);
		panel1.add(textField2);
		textField2.setColumns(10);
		
		textField3 = new JTextField();
		textField3.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField3.setBounds(123, 193, 170, 30);
		panel1.add(textField3);
		textField3.setColumns(10);
		
		textField4 = new JTextField();
		textField4.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField4.setBackground(Color.WHITE);
		textField4.setBounds(123, 143, 170, 30);
		panel1.add(textField4);
		textField4.setColumns(10);
		
		JLabel label5 = new JLabel("*In case of new product, ID will be auto added");
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font("Calibri", Font.BOLD, 12));
		label5.setBounds(123, 62, 234, 14);
		panel1.add(label5);
		
		
		JLabel label6 = new JLabel("");
		label6.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		label6.setBounds(0, 0, 684, 411);
		panel1.add(label6);
	}
}
