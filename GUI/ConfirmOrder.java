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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ConfirmOrder extends JFrame {

	private JPanel panel1;
	private JTable table1;
	private JTable table2;
	Connection con = null;
	int serial = 0;
	int cid = 0;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ConfirmOrder frame = new ConfirmOrder();
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
			PreparedStatement stmt = con.prepareStatement("select sid, cid, tprice from orderfood where confirm != 'YES'");
			ResultSet rs = stmt.executeQuery();
			
			table1.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			stmt.close();
			con.close();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void table2con(int serial){
		try{
			con = DBCon.dbcon();
			PreparedStatement stmt = con.prepareStatement("select fid, fname, quantity from sell where sid = ?");
			stmt.setInt(1, serial);
			ResultSet rs = stmt.executeQuery();
			
			table2.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			stmt.close();
			con.close();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public ConfirmOrder(People p1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		JLabel label1 = new JLabel("Order List");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Calibri", Font.BOLD, 15));
		label1.setBounds(170, 11, 120, 36);
		panel1.add(label1);
		
		JLabel label2 = new JLabel("Order Details");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Calibri", Font.BOLD, 15));
		label2.setBounds(170, 203, 120, 46);
		panel1.add(label2);
		
		JButton button1 = new JButton("Confirm");
		button1.setEnabled(false);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con = DBCon.dbcon();
					PreparedStatement stmt = con.prepareStatement("update orderfood set confirm = 'YES' where sid = ?");
					stmt.setInt(1, serial);
					stmt.executeUpdate();
					
					stmt = con.prepareStatement("update customersale set total = (select total from customersale where cid = ?)+(select tprice from orderfood where sid = ?) where cid = ?");
					stmt.setInt(1, cid);
					stmt.setInt(2, serial);
					stmt.setInt(3, cid);
					stmt.executeUpdate();
					
					stmt.close();
					
					Document doc = new Document();
					try {
						PdfWriter.getInstance(doc, new FileOutputStream("E:\\Codes\\JavaCodes\\Restaurant\\Receipt\\"+serial+".pdf"));
						doc.open();
						Paragraph par = new Paragraph();
						
						stmt = con.prepareStatement("select ename, contact, address from employee where eid = ?");
						stmt.setInt(1, cid);
						ResultSet rs = stmt.executeQuery();
						while(rs.next()) {
							par.add("Name: "+rs.getString("ENAME"));
							doc.add(par);
							par.clear();
							par.add("Contact: 0"+rs.getString("CONTACT"));
							doc.add(par);
							par.clear();
							par.add("Address: "+rs.getString("ADDRESS"));
							doc.add(par);
							par.clear();
						}
						stmt = con.prepareStatement("select fname, fprice, quantity, tprice from sell where sid = ?");
						stmt.setInt(1, serial);
						rs = stmt.executeQuery();
						while(rs.next()) {
							par.add("\n"+rs.getString("FNAME")+"\nQuantity:"+rs.getString("QUANTITY")+"\nPrice:"+rs.getString("TPRICE"));
							doc.add(par);
							par.clear();
						}
						
						stmt = con.prepareStatement("select sum(tprice) from sell where sid = ?");
						stmt.setInt(1, serial);
						rs = stmt.executeQuery();
						while(rs.next()) {
							par.add("\nTotal Price: "+rs.getString(1));
							doc.add(par);
							par.clear();
						}
						
						doc.close();
						rs.close();
						stmt.close();
						con.close();
					} catch (Exception f) {
						JOptionPane.showMessageDialog(null, "Error");;
					}
					
					table1con();
					table2con(0);
					
					
				}catch(Exception f) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		button1.setBackground(Color.WHITE);
		button1.setForeground(Color.BLACK);
		button1.setFont(new Font("Calibri", Font.PLAIN, 16));
		button1.setBounds(517, 169, 120, 50);
		panel1.add(button1);
		
		JButton button2 = new JButton("Close");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		button2.setFont(new Font("Calibri", Font.PLAIN, 16));
		button2.setForeground(Color.BLACK);
		button2.setBackground(Color.WHITE);
		button2.setBounds(517, 239, 120, 50);
		panel1.add(button2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 406, 139);
		panel1.add(scrollPane);
		
		table1 = new JTable();
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				button1.setEnabled(true);
				int row = table1.getSelectedRow();
				serial = Integer.parseInt((table1.getModel().getValueAt(row, 0)).toString());
				cid = Integer.parseInt((table1.getModel().getValueAt(row, 1)).toString());
				table2con(serial);
			}
		});
		scrollPane.setViewportView(table1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 243, 406, 139);
		panel1.add(scrollPane_1);
		
		table2 = new JTable();
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				button1.setEnabled(false);
			}
		});
		scrollPane_1.setViewportView(table2);
		
		table1con();
		table2con(0);
		
		JLabel label3 = new JLabel("");
		label3.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		label3.setBounds(0, 0, 684, 411);
		panel1.add(label3);
	}
}
