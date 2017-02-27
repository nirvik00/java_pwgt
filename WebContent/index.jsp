<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
Following input values are considered<br><br>
<form action="dynamo" method="post">
		<table>
	<tr>
	<td width="100" height="30px">Seed &ensp;</td><td>Design seed</td>
	</tr>
	<tr>
	<td height="30px">Inset &ensp;</td><td>Site inset</td>
	</tr>
	<tr>
	<td height="30px">Notch &ensp; </td><td>Minimum notch distance</td>
	</tr>
	<tr>
	<td height="30px">Department &ensp; </td><td>Design seed randomize for department</td>
	</tr>
	<tr>
	<td height="30px">Adjacency &ensp; </td><td>Design seed randomize adjacency for department</td>
	</tr>
	<tr>
	<td height="30px">Program &ensp; </td><td>Design seed for program</td>
	</tr>
	<tr>
	<td height="30px">Min Dim &ensp;</td><td>Minimum allowed dimension</td>
	</tr>
	<tr>
	<td height="30px">View &ensp;</td><td> External weight view</td>
	</tr>
	<tr>
	<td height="30px">Travel &ensp;</td><td>Travel distance weight</td>
	</tr>
	<tr>
	<td height="30px">Circulation &ensp;</td><td>Circulation width</td>
	</tr>
	<tr>
	<td height="30px">Name &ensp;</td><td>Program Name</td>
	</tr>
	</table>
	
	<br><br>
	Search Service &ensp;<input type="submit" value="service"/>
</form>
</body>
</html>