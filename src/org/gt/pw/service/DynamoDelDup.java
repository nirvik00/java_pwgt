package org.gt.pw.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DynamoDelDup {
	public DynamoDelDup(){
	}
	public void delDups(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties=new Properties();
			properties.setProperty("user","root");
			properties.setProperty("password","Root");
			properties.setProperty("useSSL","false");
			properties.setProperty("autoReconnect", "true");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/test",properties);
			PreparedStatement stmt=conn.prepareStatement("Select * from geometry");
			ResultSet rs=stmt.executeQuery();
			ArrayList<String> list=new ArrayList<String>();
			while(rs.next()){				
				int ds0=Integer.parseInt(rs.getString("design_seed"));
				int sn0=Integer.parseInt(rs.getString("sl_no"));
				list.add(ds0 +"," +sn0);
			}
			for(int i=0; i<list.size(); i++){
				int ds0=Integer.parseInt(list.get(i).split(",")[0]);
				int sn0=Integer.parseInt(list.get(i).split(",")[1]);
				for(int j=0; j<list.size(); j++){
					int ds1=Integer.parseInt(list.get(j).split(",")[0]);
					int sn1=Integer.parseInt(list.get(j).split(",")[1]);
					if(ds0==ds1 && sn0<sn1){
						String query="delete from geometry where sl_no=?";
						PreparedStatement stmt1=conn.prepareStatement(query);
						stmt1.setInt(1, sn1);
						stmt1.executeUpdate();				
					}
				}
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("ok not working database");
			e.printStackTrace();
		}
	}
	public void delNullPoly(){
		try {
			System.out.println("trying null polylines");
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties=new Properties();
			properties.setProperty("user","root");
			properties.setProperty("password","Root");
			properties.setProperty("useSSL","false");
			properties.setProperty("autoReconnect", "true");
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/test",properties);
			PreparedStatement stmt=conn.prepareStatement("Select * from geometry");
			ResultSet rs=stmt.executeQuery();
			ArrayList<String> list=new ArrayList<String>();
			while(rs.next()){
				String str=rs.getString("polylines");
				int t=Integer.parseInt(rs.getString("sl_no"));
				if(str==null || str.equals("") || str.isEmpty()){
					System.out.println("sl_no = "+t);
					String query="delete from geometry where sl_no=?";
					PreparedStatement stmt1=conn.prepareStatement(query);
					stmt1.setInt(1, t);
					stmt1.executeUpdate();				
					System.out.println("found null polylines");
				}
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("db error nullpoly");			
		}		
	}
}
