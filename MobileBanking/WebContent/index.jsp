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
    
    <section> 
	<div class="containerLog">
	<form>
 	<div class="imgcontainer">
    		<img src="logoB.png" alt="Avatar" class="avatar">
  	</div>

  	<div class="container">
    		<label for="uname"><b>Username</b></label>
    		<input type="text" placeholder="Enter Username" name="uname" required>

    		<label for="psw"><b>Password</b></label>
    		<input type="password" placeholder="Enter Password" name="psw" required>
        
    		<button id="butonSubmitUserSiParola" type="submit">Login</button>
  	</div>
	</form>
	</div>
    </section>    
    
</body>
</html>