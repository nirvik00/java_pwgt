package org.gt.pw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gt.pw.dto.DynamoSearchObject;
import org.gt.pw.dto.DynamoObject;
import org.gt.pw.service.DynamoDelDup;
import org.gt.pw.service.DynamoSearchService;
import org.gt.pw.service.DynamoService;

@WebServlet(description = "servlet for dynamo retrieval and display", urlPatterns = { "/dynamo" })
public class DynamoProject extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		out.println("hello dynamo");
		
		//DELETE DUPLICATES
		DynamoDelDup delService=new DynamoDelDup();	
		delService.delDups();
		delService.delNullPoly();
		
		//GET RECORDS
		String design_seed_min, site_inset_min, design_seed_max, site_inset_max;
		design_seed_min=request.getParameter("design_seed_min").trim().toLowerCase();
		site_inset_min=request.getParameter("site_inset_min").trim().toLowerCase();
		design_seed_max=request.getParameter("design_seed_max").trim().toLowerCase();
		site_inset_max=request.getParameter("site_inset_max").trim().toLowerCase();
		if(design_seed_min.isEmpty()){
			design_seed_min="0";			
		}
		if(design_seed_max.isEmpty()){
			design_seed_max="0";			
		}
		if(site_inset_min.isEmpty()){
			site_inset_min="0";			
		}
		if(site_inset_max.isEmpty()){
			site_inset_max="0";			
		}
		System.out.println(design_seed_min+","+design_seed_max+","+site_inset_min+","+site_inset_max);
		
		//SEARCH SERVICE - FIND RECORDS
		DynamoSearchService dss=new DynamoSearchService();
		ArrayList<String>searchList=new ArrayList<String>();
		searchList=dss.getRec(design_seed_min,design_seed_max, site_inset_min, site_inset_max);
		
		DynamoSearchObject dynamoSearchObject=new DynamoSearchObject();
		dynamoSearchObject.setObj(searchList);
		
		//GENERAL SERVICE - DISPLAY ALL RECORDS
		DynamoService service=new DynamoService();	
		ArrayList<String> getAllRec=new ArrayList<String>();
		getAllRec=service.getAllRec();				
		DynamoObject dynamoObject=new DynamoObject();
		dynamoObject.setObj(getAllRec);		
		request.setAttribute("dynamoObjects", dynamoObject);

		//DISPATCH 	
		request.setAttribute("dynamoSearchObjects", dynamoSearchObject);
		RequestDispatcher dispatcher1=request.getRequestDispatcher("result.jsp");
		dispatcher1.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		out.println("hello dynamo");		
		
		//DELETE DUPLICATES
		DynamoDelDup delService=new DynamoDelDup();	
		delService.delDups();
		delService.delNullPoly();
		
		//GENERAL SERVICE - DISPLAY ALL RECORDS		
		DynamoService service=new DynamoService();	
		ArrayList<String> getAllRec=new ArrayList<String>();
		getAllRec=service.getAllRec();
				
		DynamoObject dynamoObject=new DynamoObject();
		dynamoObject.setObj(getAllRec);
		
		request.setAttribute("dynamoObjects", dynamoObject);
		
		RequestDispatcher dispatcher0=request.getRequestDispatcher("result.jsp");
		dispatcher0.forward(request, response);
	}
}
