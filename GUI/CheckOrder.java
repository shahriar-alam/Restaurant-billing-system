package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class CheckOrder extends JFrame {

	private JPanel panel1;
	private JTable table1;
	private JTable table2;
	Connection con = null;
	int serial = 0;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CheckOrder frame = new CheckOrder();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public void table1con(People p1){
		try{
			con = DBCon.dbcon();
			PreparedStatement stmt = con.prepareStatement("select sid, tprice, confirm from orderfood where cid = ?");
			stmt.setInt(1, p1.getID());
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
			PreparedStatement stmt = con.prepareStatement("select fname, fprice, quantity, tprice  from sell where sid = ?");
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
	public CheckOrder(People p1) {
		setTitle("Check Order Status");
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
		
		JButton button1 = new JButton("Close");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerPanel cp = new CustomerPanel(p1);
				cp.setVisible(true);
				dispose();
			}
		});
		button1.setFont(new Font("Calibri", Font.PLAIN, 16));
		button1.setForeground(Color.BLACK);
		button1.setBackground(Color.WHITE);
		button1.setBounds(515, 185, 120, 50);
		panel1.add(button1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 406, 139);
		panel1.add(scrollPane);
		
		table1 = new JTable();
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int row = table1.getSelectedRow();
				serial = Integer.parseInt((table1.getModel().getValueAt(row, 0)).toString());
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
		scrollPane_1.setViewportView(table2);
		
		table1con(p1);
		table2con(0);
		
		JLabel label3 = new JLabel("");
		label3.setIcon(new ImageIcon("E:\\Codes\\JavaCodes\\Restaurant\\img\\login_back.jpg"));
		label3.setBounds(0, 0, 684, 411);
		panel1.add(label3);
		
	}

}
