<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
	import="org.gt.pw.dto.DynamoObject"    
	import="org.gt.pw.dto.DynamoSearchObject"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Space Planning</title>
<style>
	table{
		border-collapse:collapse;
	}
	th,td{
		padding: 10 px;
		text-align:left;
		font-family:courier; 		
		color:black;		
		font-size:12px;				
		//border-top: thin solid;     		
		cellspacing:4px;
		cellpadding:4px;
	}
</style>
</head>
<body>
<br>
Legend :
<br>
	<table>
	<tr>
	<td width="150" height="30px">1.Seed &ensp;</td><td>Design seed</td><td>&ensp;2-1000, 675</td>
	</tr>
	<tr>
	<td height="35px">2. Inset &ensp;</td><td>Site inset</td><td>&ensp;1-25, 6</td>
	</tr>
	<tr>
	<td height="35px">3. Cell Dim &ensp;</td><td>Minimum Cell Dimension</td><td>&ensp;1-25, 6</td>
	</tr>
	<tr>
	<td height="35px">4. Notch &ensp; </td><td>Minimum notch distance</td><td>&ensp;10-40, 10</td>
	</tr>
	<tr>
	<td height="35px">5. Coverage &ensp; </td><td>Site Coverage</td><td>&ensp;0-1, 0.4</td>
	</tr>
	<tr>
	<td height="35px">6. Department &ensp; </td><td>Design seed randomize for department</td><td>&ensp;1-1000, 324</td>
	</tr>	
	<tr>
	<td height="35px">7. Adjacency &ensp; </td><td>Design seed randomize adjacency for department</td><td>&ensp;1-1000, 290</td>
	</tr>
	<tr>
	<td height="35px">8. Program &ensp; </td><td>Design seed for program</td><td>&ensp;1-1000, 901</td>
	</tr>
	<tr>
	<td height="35px">9. Min Dim &ensp;</td><td>Minimum allowed dimension</td><td>&ensp;1-20, 4</td>
	</tr>
	<tr>
	<td height="35px">10. View &ensp;</td><td>External weight view</td><td>&ensp;0-1, 1</td>
	</tr>
	<tr>
	<td height="35px">11. Travel &ensp;</td><td>Travel distance weight</td><td>&ensp;0-1,0.9</td>
	</tr>
	<tr>
	<td height="30px">12. Circulation &ensp;</td><td>Circulation width</td><td>&ensp;0-50, 2.6</td>
	</tr>
	</table>
<br><br>


<form method="get" action="draw" target="_blank">
<font style="color:blue">Draw the record with serial number (Sl Num)</font><br><br>
<table>
<tr>
<td>SL Num</td><td>&ensp;<input style="color: #C0C0C0;" type="text" name="sl_no" value="10" onfocus="this.value=''; this.style.color='#000000'"></td><td>&ensp;<input type="Submit" value="Draw" /></td>
</tr>
</table>
<br><br>
</form>





<jsp:useBean id="dynamoObjects" class="org.gt.pw.dto.DynamoObject" scope="request"></jsp:useBean>
<jsp:useBean id="dynamoSearchObjects" class="org.gt.pw.dto.DynamoSearchObject" scope="request"></jsp:useBean>

<font style="color:blue">Search Input Records stored in the database:</font>
<br><br>
<form action="dynamo" method="get">
<table>
<tr>
<td>Design Seed Minimum</td><td><input style="color: #C0C0C0;" type="text" name="design_seed_min" value="700" onfocus="this.value=''; this.style.color='#000000'"/></td>
<td>Design Seed Maximum</td><td><input style="color: #C0C0C0;" type="text" name="design_seed_max" value="900" onfocus="this.value=''; this.style.color='#000000'"/></td>
<td>Site Inset Minimum</td><td><input style="color: #C0C0C0;" type="text" name="site_inset_min" value="10" onfocus="this.value=''; this.style.color='#000000'"/></td>
<td>Site Inset Maximum</td><td><input style="color: #C0C0C0;" type="text" name="site_inset_max" value="20" onfocus="this.value=''; this.style.color='#000000'"/></td>
<td><input type="submit" value="search"/></td>
</tr>
</table>
</form>

<br><br>
<%
ArrayList<String> dynamoSearchObjectList=new ArrayList<String>();
dynamoSearchObjectList=(dynamoSearchObjects.getObj());
try{
	%>Result Input Records stored in the database:<%=dynamoSearchObjectList.size() %><br><br><%
	if(dynamoSearchObjectList.size()>0){
		for(int i=0;i<dynamoSearchObjectList.size();i++){
			%>
			<form method="get" action="draw">
			<table>
				<th style="color: #C0C0C0;">Sl Num</th>
				<th style="color: #C0C0C0;">Seed</th>
				<th style="color: #C0C0C0;">Inset</th>
				<th style="color: #C0C0C0;">Cell Dim</th>
				<th style="color: #C0C0C0;">Notch</th>
				<th style="color: #C0C0C0;">Coverage</th>
				<th style="color: #C0C0C0;">Department</th>
				<th style="color: #C0C0C0;">Adjacency</th>
				<th style="color: #C0C0C0;">Program</th>
				<th style="color: #C0C0C0;">Min Dim</th>
				<th style="color: #C0C0C0;">View</th>
				<th style="color: #C0C0C0;">Travel</th>
				<th style="color: #C0C0C0;">Circulation</th>
				<tr>
				<td width="100px" height ="50px">&ensp;<%=dynamoSearchObjectList.get(i).split(",")[0] %></td>
				<td width="100px" >&ensp;<%=dynamoSearchObjectList.get(i).split(",")[1] %></td>
				<td width="100px" >&ensp;<%=dynamoSearchObjectList.get(i).split(",")[2] %></td>
				<td width="100px" >&ensp;<%=dynamoSearchObjectList.get(i).split(",")[3] %></td>
				<td width="100px" >&ensp;<%=dynamoSearchObjectList.get(i).split(",")[4] %></td>
				<td width="170px" ><%=dynamoSearchObjectList.get(i).split(",")[5] %></td>
				<td width="170px" ><%=dynamoSearchObjectList.get(i).split(",")[6] %></td>
				<td width="100px" ><%=dynamoSearchObjectList.get(i).split(",")[7] %></td>
				<td width="100px" >&ensp;<%=dynamoSearchObjectList.get(i).split(",")[8] %></td>
				<td width="100px" >&ensp;<%=dynamoSearchObjectList.get(i).split(",")[9] %></td>
				<td width="100px" >&ensp;<%=dynamoSearchObjectList.get(i).split(",")[10] %></td>
				<td width="170px" ><%=dynamoSearchObjectList.get(i).split(",")[11] %></td>	
				<td width="170px" ><%=dynamoSearchObjectList.get(i).split(",")[12] %></td>	
				</tr>
			</table>
			</form>
	<%
	}}
	}catch (Exception e){
	}
%>








<font style="color:green"><br><br>All Input Records stored in the database:</font>
<br><br>
<%
ArrayList<String> dynamoObjectList=new ArrayList<String>();
dynamoObjectList=(dynamoObjects.getObj());
for(int i=0;i<dynamoObjectList.size();i++){
	%>
	<table>
	<th style="color: #C0C0C0;">Sl Num</th>
	<th style="color: #C0C0C0;">Seed</th>
	<th style="color: #C0C0C0;">Inset</th>
	<th style="color: #C0C0C0;">Cell Dim</th>
	<th style="color: #C0C0C0;">Notch</th>
	<th style="color: #C0C0C0;">Coverage</th>
	<th style="color: #C0C0C0;">Department</th>
	<th style="color: #C0C0C0;">Adjacency</th>
	<th style="color: #C0C0C0;">Program</th>
	<th style="color: #C0C0C0;">Min Dim</th>
	<th style="color: #C0C0C0;">View</th>
	<th style="color: #C0C0C0;">Travel</th>
	<th style="color: #C0C0C0;">Circulation</th>
	<tr>
	<td width="100px" height ="50px">&ensp;<%=dynamoObjectList.get(i).split(",")[0] %></td>
	<td width="100px" >&ensp;<%=dynamoObjectList.get(i).split(",")[1] %></td>
	<td width="100px" >&ensp;<%=dynamoObjectList.get(i).split(",")[2] %></td>
	<td width="100px" >&ensp;<%=dynamoObjectList.get(i).split(",")[3] %></td>
	<td width="100px" >&ensp;<%=dynamoObjectList.get(i).split(",")[4] %></td>
	<td width="170px" ><%=dynamoObjectList.get(i).split(",")[5] %></td>
	<td width="170px" ><%=dynamoObjectList.get(i).split(",")[6] %></td>
	<td width="100px" ><%=dynamoObjectList.get(i).split(",")[7] %></td>
	<td width="100px" >&ensp;<%=dynamoObjectList.get(i).split(",")[8] %></td>
	<td width="100px" >&ensp;<%=dynamoObjectList.get(i).split(",")[9] %></td>
	<td width="100px" >&ensp;<%=dynamoObjectList.get(i).split(",")[10] %></td>
	<td width="170px" ><%=dynamoObjectList.get(i).split(",")[11] %></td>	
	<td width="170px" ><%=dynamoObjectList.get(i).split(",")[12] %></td>
	</tr>
	</table>
	<%
}
%>

</body>
</html>
