package org.gt.pw.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DynamoSearchService {
		ArrayList<String> recList;
		public DynamoSearchService(){
			recList=new ArrayList<String>();
		}
		public ArrayList<String> getRec(String design_seed_min_, String design_seed_max_, String site_inset_min_, String site_inset_max_){
			Double design_seed_min=Double.parseDouble(design_seed_min_);
			Double design_seed_max=Double.parseDouble(design_seed_max_);
			Double site_inset_min=Double.parseDouble(site_inset_min_);
			Double site_inset_max=Double.parseDouble(site_inset_max_);
			recList.clear();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/test","root","Root");
				PreparedStatement stmt=conn.prepareStatement("Select * from geometry");
				ResultSet rs=stmt.executeQuery();
				while(rs.next()){
					double design_seed_read=Double.parseDouble(rs.getString("design_seed"));
					double site_inset_read=Double.parseDouble(rs.getString("site_inset"));
					if((design_seed_read>design_seed_min && design_seed_read<design_seed_max && site_inset_read>site_inset_min && site_inset_read<site_inset_max)){
						//0
						String sl_no=rs.getString("sl_no");
						//1
						String design_seed=rs.getString("design_seed");
						//2
						String site_inset=rs.getString("site_inset");
						//3
						String cell_dim=rs.getString("cell_dim");
						//4
						String min_notch_distance=rs.getString("min_notch_distance");
						//5
						String site_coverage=rs.getString("site_coverage");
						//6
						String design_seed_randomize_department=rs.getString("design_seed_randomize_department");
						//7
						String design_seed_randomize_adjacency_department=rs.getString("design_seed_randomize_adjacency_department");
						//8
						String design_seed_program=rs.getString("design_seed_program");
						//9
						String minimum_allowed_dimension=rs.getString("minimum_allowed_dimension");
						//10
						String external_weight_view=rs.getString("external_weight_view");
						//11
						String travel_distance_weight=rs.getString("travel_distance_weight");
						//12
						String circulation_width=rs.getString("circulation_width");
						//13
						String program_name=rs.getString("program_name");
						recList.add(sl_no+","+design_seed+","+site_inset+","+cell_dim+","+min_notch_distance+","+site_coverage+","+design_seed_randomize_department+","+design_seed_randomize_adjacency_department+","+design_seed_program+","+minimum_allowed_dimension+","+external_weight_view+","+travel_distance_weight+","+circulation_width+","+program_name);
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Database connection problem");
				e.printStackTrace();
			}
			return recList;
		}
}

