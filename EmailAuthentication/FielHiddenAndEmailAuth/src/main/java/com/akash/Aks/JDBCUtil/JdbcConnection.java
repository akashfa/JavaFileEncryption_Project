package com.akash.Aks.JDBCUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class JdbcConnection {
   private static Connection conn=null;
	public static Connection getDBConnection() throws SQLException, IOException {
		String path="src/main/java/com/akash/Aks/Properties/database.properties";
		FileInputStream file=null;
		
			file = new FileInputStream(path);
			Properties prop=new Properties();
			prop.load(file);
			
			String url=prop.getProperty("url");
			String username=prop.getProperty("username");
			String password=prop.getProperty("password");
			return DriverManager.getConnection(url,username,password);
		
		
		
		
		
		
	}
	
	
}
