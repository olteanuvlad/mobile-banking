<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>OnlineBanking</title>
</head>
<body>
	<header>
      <div class="container">
        <div id="brand">
          <h1>Online Banking</h1>
        </div>        
      </div>
    </header>
    
    <form method="post" action="login">
	        Email ID:<input type="text" name="unamelog" required/><br/>
	        Password:<input type="text" name="pwdlog" required/><br/>
	        <input type="submit" value="login" />
	        </form>
    		<% out.print(request.getAttribute("uname")); %>
    
    <section> 
	<div class="containerLog">
	<form>
 	<div class="imgcontainer">
    		<img src="logoB.png" alt="Avatar" class="avatar">
  	</div>

  	
  			
  	
	</form>
	</div>
    </section>    
    
</body>
</html>