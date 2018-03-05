<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>	

	<form name="findroute" action="findroute" method="GET">
		<input type="submit" value="Find Routes"> 
	</form>
	<form name="findroute" action="findroutetofaculty" method="GET">
		<input type="submit" value="Find Route to Faculty Building"> 
	</form>
	<form name="findroute" action="findroutetorestro" method="GET">
		<input type="submit" value="Find Route to Restaurant"> 
	</form>
	
	<script type="text/javascript" language="javascript">

function getlocation()
{
	var destination = document.getElementById("destination").value
	
    var anUrl = "getproflocation?address=" + destination;
    
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
              document.getElementById("destination").value = location.lat + ',' + location.lng;
          } else {
              alert( " An error has occurred: " + myRequest.statusText);
          }
       }
    }
	
	
};

</script>
	
</body>
</html>