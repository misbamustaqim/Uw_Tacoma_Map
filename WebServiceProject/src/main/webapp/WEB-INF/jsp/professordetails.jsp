<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>

<%ResultSet resultset =null;%>


<html>
<h2>Professor list</h2><br>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search for Professors</title>

</head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

<body>


<form name=""  >

<%
    try{
	    String query="select DISTINCT name from faculty";	 
    	String url = "jdbc:sqlite:database.db";
    		 Connection connect = DriverManager.getConnection(url);
    		 Statement statement = (Statement) connect.createStatement();
    		 resultset =statement.executeQuery(query);
%>
        
      <select type="hidden" name ="Professor name">  
      
      <option >Professor name</option>
        <%  while(resultset.next()){ %>
       <option value="<%= resultset.getString(1)%>"><%= resultset.getString(1)%></option>
             <% } %>
        </select>       
        


      <input type="submit" value="submit">    
      
<%

  }
		
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }

%>
</form>

</BODY>
</HTML>