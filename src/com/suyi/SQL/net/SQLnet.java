package com.suyi.SQL.net;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class SQLnet {

	public static void main(String[] args) {
		try {
			new Driver();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int ik=1;
		for(int i=0;i<ik;i++){
			link();
			System.out.println(""+i);
		}

	}

	private static void link() {
		// TODO Auto-generated method stub
		
//		try {
////	DriverManager.getConnection(url, user, password)
//		Connection mConnection=	(Connection) DriverManager.getConnection("jdbc:mysql://192.168.2.222:3306/d_website_0620","root","root");
//		System.out.println(mConnection==null);
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	

//	String Ip="101.0.6.155";
	String Ip="182.92.9.202";
	
	try {
//		DriverManager.getConnection(url, user, password)
		Connection mConnection=	(Connection) DriverManager.getConnection("jdbc:mysql://"+Ip+":3306/d_baozheng","root","root");

		System.out.println(mConnection==null);			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
	}

}
