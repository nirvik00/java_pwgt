package org.gt.pw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;

@WebServlet(description = "Draws given a serial number", urlPatterns = { "/draw" })
public class Draw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH=700;
	private static final int HEIGHT=700;
	//private static final Color BACKGROUND_COLOR=new Color(200,200,200);
	//private static final Color COLOR=new Color(0,0,200);
	private static final Font FONT= new Font("Times New Roman", Font.BOLD, 14);
	private static final Font FOOT_FONT= new Font("Courier", Font.ITALIC, 14);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sl_no=Integer.parseInt(request.getParameter("sl_no").trim());
		getCoordinates(sl_no);
		ArrayList<String> pts=new ArrayList<String>();
		pts.clear();
		pts.addAll(getCoordinates(sl_no));
		
		
		ArrayList<String> names=new ArrayList<String>();
		names.clear();
		names.addAll(getNames(sl_no));
		
		String[] names_str=names.get(0).split(";");
		System.out.println("name size="+names_str.length);
		for(int i=0; i<names_str.length;i++){
			System.out.println(names_str[i]);
		}
		
		
		response.setContentType("image/jpg");
		ServletOutputStream out=response.getOutputStream();
		
		BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_INDEXED);
		Graphics2D graphics = (Graphics2D)image.createGraphics();
		graphics.setColor(new Color(255,255,255));
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		graphics.translate(WIDTH/2, HEIGHT/2);
		//graphics.rotate(Math.PI);
		graphics.scale(3.0, 3.0);
		graphics.setColor(new Color(200,0,0));
		
		//graphics.fillRect(100, 100, 200, 200);
		String s_pts=pts.get(0);
		String[] sptsArr=s_pts.split("&");		
		System.out.println(sptsArr.length);
		
		for(int i=0; i<sptsArr.length-2; i++){
			//System.out.println("ok"+ i +"," + sptsArr[i]);
			String[] str_pts0=sptsArr[i].split(";");
			String PT0_X=str_pts0[0];
			String PT0_Y=str_pts0[str_pts0.length-1];						
			Line2D shape=new Line2D.Double();
			graphics.setColor(new Color(0,0,0));
			for(int j=0; j<str_pts0.length-1; j++){
				System.out.println(str_pts0[j]);
				Double x0=Double.parseDouble(str_pts0[j].split(",")[0]);
				Double y0=Double.parseDouble(str_pts0[j].split(",")[1]);
				Double x1=Double.parseDouble(str_pts0[j+1].split(",")[0]);
				Double y1=Double.parseDouble(str_pts0[j+1].split(",")[1]);
				shape.setLine(x0, y0, x1, y1);
				graphics.draw(shape);
			}
			graphics.setColor(new Color(0,0,0));
			Double ptx00=Double.parseDouble(PT0_X.split(",")[0]); 
			Double pty00=Double.parseDouble(PT0_X.split(",")[1]); 
			Double ptx01=Double.parseDouble(PT0_Y.split(",")[0]); 
			Double pty01=Double.parseDouble(PT0_Y.split(",")[1]);
			shape.setLine(ptx00,pty00,ptx01,pty01);
			graphics.draw(shape);
			
			graphics.setColor(new Color(200,200,200));
			int reqX=(int)((ptx00+ptx01)/2);
			int reqY=(int)((pty00+pty01)/2);
			graphics.drawString(i+"> ", reqX, reqY);
		}		
		//graphics.rotate(Math.PI);
		graphics.scale(0.25, 0.25);
		graphics.translate(200, -400);
		graphics.setColor(new Color(0,0,0));
		for(int i=0; i<names_str.length;i++){			
			graphics.drawString(i+"> "+names_str[i], 14, 10*i);
		}
		
		//graphics.setColor(new Color(200,0,0));
		//graphics.fillRect(10, 10, 200, 200);
		//graphics.drawString("Created by "+getServletContext().getServerInfo(), 10, HEIGHT-30);
		JPEGCodec.createJPEGEncoder(out).encode(image);
				
	}
	public ArrayList<String> getCoordinates(int sl_no){
		ArrayList<String> coordinates=new ArrayList<String>();
		coordinates.clear();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "Root");
			PreparedStatement stmt=conn.prepareStatement("Select * from geometry");
			ResultSet rs=stmt.executeQuery();			
			while(rs.next()){			
				int sl_no_read=Integer.parseInt(rs.getString("sl_no"));
				if(sl_no_read==sl_no){
					coordinates.add(rs.getString("polylines"));
				}
			}
			//conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("no database connection");
		}
		return coordinates;
	}
	public ArrayList<String> getNames(int sl_no){
		ArrayList<String>  nameList=new ArrayList<String>();
		 nameList.clear();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "Root");
			PreparedStatement stmt=conn.prepareStatement("Select * from geometry");
			ResultSet rs=stmt.executeQuery();			
			while(rs.next()){			
				int sl_no_read=Integer.parseInt(rs.getString("sl_no"));
				if(sl_no_read==sl_no){
					 nameList.add(rs.getString("program_name"));
				}
			}
			//conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("no database connection");
		}
		return nameList;
	}
}