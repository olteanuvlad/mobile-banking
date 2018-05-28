<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="style.css" rel="stylesheet">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>Lobby</title>
</head>
<body>
	<header>
	      <div class="container">
	        <div id="brand">
	          <h1>Online Banking</h1>
	        </div>
	        <nav>
	          <ul>
	            <li class="current"><a href="mainPage.jsp">Lobby</a></li>
	            <li><a href="tranzactii.jsp">Tranzactii Online</a></li>
	            <li><a href="plata.jsp">Shop</a></li>
	          </ul>
	        </nav>
	      </div>
	</header>
	
	<section>
      <div class="container">
    	   <h2>Sold curent: <% out.print(request.getAttribute("sold")); %></h2>
      </div>
    </section>
    
    <%@page import="classes.Transaction"%>
    <%Transaction[] x=request.getAttribute("transactions");%>
    	
    <table id="tabelUltimeleTranzactii">
    	<tr>
        <th>Nr Tranzactie</th>
        <th>Nume Sursa</th>
    	<th>IBAN Sursa</th>
    	<th>Nume Destinatie</th>
    	<th>IBAN Destinatie</th>
        <th>Suma</th>
        <th>Data</th>
    	</tr>
      <tr>
        <td><% if(x[0].getNrTranzactie()>0){
        	out.print(x[0].getNrTranzactie());}
        	else
        		out.print("");
       	 %></td>
        <td><% if(x[0].getNumeSursa()!=null)
        		   out.print(x[0].getNumeSursa());
        		else
        			out.print("");
        %></td>
        <td><% if(x[0].getIbanSursa()!=null) 
        		   out.print(x[0].getIbanSursa());
        	   else
        		   out.print("");
        %></td>
        <td><% if(x[0].getNumeDestinatie()!=null) 
        			out.print(x[0].getNumeDestinatie());
        	   else
        		   out.print("");
        %></td>
        <td><%if(x[0].getIbanDestinatie()!=null)
        			out.print(x[0].getIbanDestinatie());
        	   else
        	   		out.print("");
       	%></td>
        <td><%if(x[0].getSuma()>0)
        			out.print(x[0].getSuma());
        	  else
        			out.print("");
        %></td>
        <td><% if(x[0].getData()!=null)
                   out.print(x[0].getData());
               else
               	   out.print("");
       %></td>
      </tr>
      
      <tr>
        <td><% if(x[1].getNrTranzactie()>0){
        	out.print(x[1].getNrTranzactie());}
        	else
        		out.print("");
       	 %></td>
        <td><% if(x[1].getNumeSursa()!=null)
        		   out.print(x[1].getNumeSursa());
        		else
        			out.print("");
        %></td>
        <td><% if(x[1].getIbanSursa()!=null) 
        		   out.print(x[1].getIbanSursa());
        	   else
        		   out.print("");
        %></td>
        <td><% if(x[1].getNumeDestinatie()!=null) 
        			out.print(x[1].getNumeDestinatie());
        	   else
        		   out.print("");
        %></td>
        <td><%if(x[1].getIbanDestinatie()!=null)
        			out.print(x[1].getIbanDestinatie());
        	   else
        	   		out.print("");
       	%></td>
        <td><%if(x[1].getSuma()>0)
        			out.print(x[1].getSuma());
        	  else
        			out.print("");
        %></td>
        <td><% if(x[1].getData()!=null)
                   out.print(x[1].getData());
               else
               	   out.print("");
       %></td>
      </tr>
      
      <tr>
        <td><% if(x[2].getNrTranzactie()>0){
        	out.print(x[2].getNrTranzactie());}
        	else
        		out.print("");
       	 %></td>
        <td><% if(x[2].getNumeSursa()!=null)
        		   out.print(x[2].getNumeSursa());
        		else
        			out.print("");
        %></td>
        <td><% if(x[2].getIbanSursa()!=null) 
        		   out.print(x[2].getIbanSursa());
        	   else
        		   out.print("");
        %></td>
        <td><% if(x[2].getNumeDestinatie()!=null) 
        			out.print(x[2].getNumeDestinatie());
        	   else
        		   out.print("");
        %></td>
        <td><%if(x[2].getIbanDestinatie()!=null)
        			out.print(x[2].getIbanDestinatie());
        	   else
        	   		out.print("");
       	%></td>
        <td><%if(x[2].getSuma()>0)
        			out.print(x[2].getSuma());
        	  else
        			out.print("");
        %></td>
        <td><% if(x[2].getData()!=null)
                   out.print(x[2].getData());
               else
               	   out.print("");
       %></td>
      </tr>
      
      <tr>
        <td><% if(x[3].getNrTranzactie()>0){
        	out.print(x[3].getNrTranzactie());}
        	else
        		out.print("");
       	 %></td>
        <td><% if(x[3].getNumeSursa()!=null)
        		   out.print(x[3].getNumeSursa());
        		else
        			out.print("");
        %></td>
        <td><% if(x[3].getIbanSursa()!=null) 
        		   out.print(x[3].getIbanSursa());
        	   else
        		   out.print("");
        %></td>
        <td><% if(x[3].getNumeDestinatie()!=null) 
        			out.print(x[3].getNumeDestinatie());
        	   else
        		   out.print("");
        %></td>
        <td><%if(x[3].getIbanDestinatie()!=null)
        			out.print(x[3].getIbanDestinatie());
        	   else
        	   		out.print("");
       	%></td>
        <td><%if(x[3].getSuma()>0)
        			out.print(x[3].getSuma());
        	  else
        			out.print("");
        %></td>
        <td><% if(x[3].getData()!=null)
                   out.print(x[3].getData());
               else
               	   out.print("");
       %></td>
      </tr>
      
      <tr>
        <td><% if(x[4].getNrTranzactie()>0){
        	out.print(x[4].getNrTranzactie());}
        	else
        		out.print("");
       	 %></td>
        <td><% if(x[4].getNumeSursa()!=null)
        		   out.print(x[4].getNumeSursa());
        		else
        			out.print("");
        %></td>
        <td><% if(x[4].getIbanSursa()!=null) 
        		   out.print(x[4].getIbanSursa());
        	   else
        		   out.print("");
        %></td>
        <td><% if(x[4].getNumeDestinatie()!=null) 
        			out.print(x[4].getNumeDestinatie());
        	   else
        		   out.print("");
        %></td>
        <td><%if(x[4].getIbanDestinatie()!=null)
        			out.print(x[4].getIbanDestinatie());
        	   else
        	   		out.print("");
       	%></td>
        <td><%if(x[4].getSuma()>0)
        			out.print(x[4].getSuma());
        	  else
        			out.print("");
        %></td>
        <td><% if(x[4].getData()!=null)
                   out.print(x[4].getData());
               else
               	   out.print("");
       %></td>
      </tr>
      

    </table>
	
	
</body>
</html>