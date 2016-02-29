package project2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectify                                                                                                     
 {
    public Connectify()
    {
        super();
    }
    
    public static Connection getConnection()
    {
        Connection con=null;
        try {
        	System.out.println("my name is golu");
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			try {
				con=DriverManager.getConnection("jdbc:db2://localhost:50000/one","ss","hsim3012");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.println("my name is njnjgj");
    return con;
    }

}