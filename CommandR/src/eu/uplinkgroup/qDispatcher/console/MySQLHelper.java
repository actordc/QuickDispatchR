package eu.uplinkgroup.qDispatcher.console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;

public class MySQLHelper {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String serverString;
	
	MySQLHelper(String[] server) {
		serverString = "jdbc:mysql://" + server[0] + "/berndvsql5?" +
	"user=" + server[1] + "&password=" + server[2];
	}
	
	public void readDataBase() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connect = DriverManager.getConnection(serverString);
			
			statement = connect.createStatement();
			
			resultSet = statement.executeQuery("select ")
		}
	}
}
