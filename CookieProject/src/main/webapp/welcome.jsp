<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Cookie With JSP</title>
</head>
<body>
Welcome ${sessionScope.username}

<p> 
	<%
	Cookie[] cookies = null;
	cookies = request.getCookies();
	
	if(cookies != null){ // Eger cookie secildiyse
		if(cookies.length >= 2){ // Eger cookie uzunlugu 2 veya daha fazla ise
			String name = cookies[0].getName();
			String password = cookies[0].getValue();
			out.println("<p>HELLO, MY NEW FRIEND</p>");
		}
		else{
			out.println("<p>HELLO FRIEND</p>");
		}
	}
	else{ // cookie kabul edilmediyse 
		out.println("COOKIES NOT AGREE!");
	}
		
	%>
</p>
<br>
<a href="AccounController?action=Logout">Logout</a>

</body>
</html>