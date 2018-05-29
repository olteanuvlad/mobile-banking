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
	<%
	if(request.getSession().getAttribute("user_id")!=null&&(boolean)request.getSession().getAttribute("logged")==true){
		getServletContext().getRequestDispatcher("/transactions").forward(request,response);
	}
	%>
	<header>
      <div class="container">
        <div id="brand">
          <h1>Online Banking</h1>
        </div>        
      </div>
    </header>
    
    <div class="containerLog">
	      <form>
 	         <div class="imgcontainer">
    		       <img src="logoB.png" alt="Avatar" class="avatar">
             </div>
          </form>
	</div>


    <form method="post" action="login" class="containerLog">
          Email ID:<input type="text" name="unamelog" required/><br/>
          Password:<input type="password" name="pwdlog" required/><br/>
          <input type="submit" value="login" id="butonSubmitUserSiParola">
    </form>
    
    
    <div class="containerLog">
    <a href="register.jsp">Creare cont.</a>
    </div>
  			
  	
	
    
</body>
</html>