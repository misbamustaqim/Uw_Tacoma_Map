<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="uw.webservices.destination.GetDestination, uw.webservices.geolocationresponse.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>

<%ResultSet resultset =null;%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Route for UW Tacoma Campus</title>
</head>

<body>
	
	<form name="findroute" action="directions" method="GET">
		Source<input type="text" id="source" name="source">
		Restaurant Name
		
				

<%
    try{
	    String query="select DISTINCT name from restaurant";	 
    	String url = "jdbc:sqlite:database.db";
    		 Connection connect = DriverManager.getConnection(url);
    		 Statement statement = (Statement) connect.createStatement();
    		 resultset =statement.executeQuery(query);
%>
        
      <select type="hidden" name =" Restaurant Name" id="destination">  
      
      <option >Restaurant name</option>
        <%  while(resultset.next()){ %>
       <option value="<%= resultset.getString(1)%>"><%= resultset.getString(1)%></option>
             <% } %>
        </select>   
        <%

  }
		
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }

%>    
		<input type="button" value="submit" onclick="getlocation()"> 
	</form>
<script type="text/javascript" language="javascript">

function getlocation()
{
	var destination = document.getElementById("destination").value
	
    var anUrl = "getrestrolocation?restro=" + destination;
    
    var myRequest = new XMLHttpRequest();
    callAjax(anUrl);

    function callAjax(url) {
       myRequest.open("GET", url, true);
       myRequest.onreadystatechange = responseAjax;
       myRequest.setRequestHeader("Content-Type", "application/json");
                   myRequest.setRequestHeader("Cache-Control", "no-cache");
       myRequest.send(null);
    }

    function responseAjax() {
       if(myRequest.readyState == 4) {
          if(myRequest.status == 200) {
              result = myRequest.responseText;
              var location = JSON.parse(this.responseText);
              var directionUrl = "directions?source=" + document.getElementById("source").value +"&destination=" + location.lat + "," + location.lng;
              
              window.location.assign(directionUrl);
              
          } else {
              alert( " An error has occurred: " + myRequest.statusText);
          }
       }
    }
};

</script>
<script>
      function displayLocation(latitude,longitude){
        var request = new XMLHttpRequest();

        var method = 'GET';
        var url = 'https://maps.googleapis.com/maps/api/geocode/json?latlng='+latitude+','+longitude+'&key=AIzaSyBuA-pwqjt85cy2_XHhDxcPEBYpDWU0XaU';
        var async = true;

        request.open(method, url, async);
        request.onreadystatechange = function(){
          if(request.readyState == 4 && request.status == 200){
            var data = JSON.parse(request.responseText);
            var address = data.results[0].formatted_address;
            document.getElementById("source").value=address;

          }
        };
        request.send();
      };

      var successCallback = function(position){
        var x = position.coords.latitude;
        var y = position.coords.longitude;
        displayLocation(x,y);
      };

      var errorCallback = function(error){
        var errorMessage = 'Unknown error';
        switch(error.code) {
          case 2:
            errorMessage = 'Position unavailable';
            break;
          case 3:
            errorMessage = 'Timeout';
            break;
        }
        if(error.code==1){
        	 document.getElementById("source").value="";
        	 break;
        }
        document.write(errorMessage);
      };

      var options = {
        enableHighAccuracy: true,
        timeout: 100000,
        maximumAge: 0
      };
      navigator.geolocation.getCurrentPosition(successCallback,errorCallback,options);

    </script>
</body>

</html>