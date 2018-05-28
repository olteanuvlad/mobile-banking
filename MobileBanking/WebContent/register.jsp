<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="register" method="post">
		Username:<br/>
		<input type="text" name="uname" required /><br/>
		Email:<br/>
		<input type="text" name="email" required /><br/>
		Password:<br/>
		<input type="text" name="pwd" required /><br/>
		IBAN:<br/>
		<input type="text" name="iban" required /><br/>
		Digipass device serial key(optional):<br/>
		<input type="text" name="serial" /><br/>
		
		<input type="submit" name="register" value="Register">
	</form>
</body>
</html>