<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="style.css" rel="stylesheet">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>Shop</title>
</head>
<body>
	<header>
	      <div class="container">
	        <div id="brand">
	          <h1>Online Banking</h1>
	        </div>
	        <nav>
	          <ul>
	            <li><a href="mainPage.jsp">Lobby</a></li>
	            <li><a href="tranzactii.jsp">Tranzactii Online</a></li>
	            <li class="current"><a href="plata.jsp">Plata cu cardul</a></li>
	          </ul>
	        </nav>
	      </div>
	</header>
	
	<form action="newtransaction" method="post" class="containerLog">
		Numar card:<br/>
		<input type="text" name="card_number"  required/><br/>
		Data expirarii:<br/>
		<input type="text" name="exp_date"  required/><br/>
		Cod:<br/>
		<input type="text" name="cvv_code"  required/><br/>
		Cod IBAN destinatar:<input type="text" name="receiveriban" required/><br/>
         Suma:<input type="number" name="ammount" min="1" required /><br/>
		<input type="submit" name="newtransaction" value="Send" id="butonSubmitUserSiParola"/>
	</form>
</body>
</html>