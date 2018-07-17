package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowTotal extends JFrame {

	private JPanel panel1;
	private JTable table1;
	Connection con = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ShowTotal frame = new ShowTotal();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public void table1con(){
		try{
			con = DBCon.dbcon();
			PreparedStatement stmt = con.prepareStatement("select * from customersale where total > 0 order by cid");
			ResultSet rs = stmt.executeQuery();
			
			table1.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			stmt.close();
			con.close();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void table2con(){
		try{
			con = DBCon.dbcon();
			PreparedStatement stmt = con.prepareStatement("select sid, fid, fname, fprice, quantity, tprice from sell group by sid, fid, fname, fprice, quantity, tprice order by sid");
			ResultSet rs = stmt.executeQuery();
			
			table1.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			stmt.close();
			con.close();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public ShowTotal(People p1) {
		setTitle("Total Purchase");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 75, 607, 263);
		panel1.add(scrollPane);
		
		table1 = new JTable();
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table1);
		
		JRadioButton radio1 = new JRadioButton("Total Customer Purchase");
		radio1.setSelected(true);
		radio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table1con();
			}
		});
		radio1.setBackground(Color.GRAY);
		radio1.setFont(new Font("Calibri", Font.BOLD, 15));
		radio1.setForeground(Color.WHITE);
		radio1.setBounds(165, 32, 179, 23);
		panel1.add(radio1);
		
		JRadioButton radio2 = new JRadioButton("Total Purchase");
		radio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table2con();
			}
		});
		radio2.setBackground(Color.GRAY);
		radio2.setFont(new Font("Calibri", Font.BOLD, 15));
		radio2.setForeground(Color.WHITE);
		radio2.setBounds(382, 32, 179, 23);
		panel1.add(radio2);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radio1);
		bg.add(radio2);
		
		if(radio1.isSelected()) {
			table1con();
		}else {
			table2con();
		}
		
		JButton button1 = new JButton("Close");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel ap = new AdminPanel(p1);
				ap.setVisible(true);
				dispose();	
			}
		});
		button1.setForeground(Color.BLACK);
		button1.setBackground(Color.WHITE);
		button1.setFont(new Font("Calibri", Font.BOLD, 15));
		button1.setBounds(289, 353, 97, 35);
		panel1.add(button1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		lblNewLabel.setBounds(0, 0, 684, 411);
		panel1.add(lblNewLabel);
	}
}
