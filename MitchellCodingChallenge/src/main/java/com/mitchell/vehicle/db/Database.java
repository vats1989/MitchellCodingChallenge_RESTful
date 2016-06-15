package com.mitchell.vehicle.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	static Connection connection = null;
	
	public static Connection getConnection()	{
		try	{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager
						 .getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");
		} catch(Exception e)	{
			e.printStackTrace();
			System.err.println(e.getClass().getName()+" : "+e.getMessage());
			System.exit(0);
		}
		//System.out.println("Opened Database Successfully");
		return connection;
	}
}