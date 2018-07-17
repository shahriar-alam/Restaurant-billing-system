package GUI;

import java.sql.*;
import javax.swing.*;

public class DBCon {
	Connection con = null;
	public static Connection dbcon(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");    
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","RESTAURANT","restaurant1234");
			return con;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
