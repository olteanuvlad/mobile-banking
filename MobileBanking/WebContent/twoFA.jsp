<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="style.css" rel="stylesheet">
<meta name="viewport" content="width=device-width">
<title>Autentificare</title>
</head>
<body>
<header>
      <div class="container">
        <div id="brand">
          <h1>Online Banking</h1>
        </div>
      </div>
</header>
	
	<form action="twofactorauth" method="post" class="containerLog">
		Digipass Code:<br/>
		<input type="text" name="otp" /><br/>
		
		<input type="submit" name="twofactorauth" value="Submit" id="butonSubmitUserSiParola">
	</form>
</body>
</html>