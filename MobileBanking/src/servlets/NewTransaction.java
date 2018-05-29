package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class NewTransaction
 */
@WebServlet("/newtransaction")
public class NewTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/bank")
	private DataSource dbRes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected boolean checkCard(HttpServletRequest request, HttpServletResponse response, int uid) {
    	try(Connection con = dbRes.getConnection();
    		PreparedStatement ps = con.prepareStatement("SELECT A.CARD_NUMBER, A.CVV_CODE FROM ACCOUNTS A, USERS U WHERE U.ACC_NUMBER=A.ACCOUNT_NUMBER AND U.USER_ID=?");){
    		ps.setInt(1,uid);
    		try(ResultSet rs=ps.executeQuery()){
    			if(request.getParameter("card_number").equals(rs.getString("CARD_NUMBER"))&&request.getParameter("cvv_code").equals(rs.getString("CVV_CODE"))) {
    				return true;
    			}
    		}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
    }
    
    protected void sendFailure(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
    	response.getWriter().append(message);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid =(int) request.getSession().getAttribute("user_id");
		if(uid<1||(boolean)request.getSession().getAttribute("logged")!=true) {
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		if(request.getParameter("card_number")!=null&&checkCard(request,response, uid)==false) {
			getServletContext().getRequestDispatcher("/plata.jsp").forward(request,response);
			return;
		}
		
		double ammount = Double.parseDouble(request.getParameter("ammount"));
		String iban = request.getParameter("receiveriban");
		
		//Check for sufficient funds
		try(Connection con=dbRes.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT A.BALANCE FROM ACCOUNTS A, USERS U WHERE U.ACC_NUMBER=A.ACCOUNT_NUMBER AND U.USER_ID=?")){
			ps.setInt(1, uid);
			try(ResultSet rs=ps.executeQuery();){
				if(rs.next()) {
					if(ammount>rs.getDouble("BALANCE")) {
						sendFailure(request,response,"Insufficient funds.");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Check if IBAN valid
		try(Connection con=dbRes.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT 'X' FROM ACCOUNTS WHERE IBAN=?");){
			ps.setString(1, iban);
			try(ResultSet rs=ps.executeQuery();){
				if(!rs.next()) {
					sendFailure(request,response,"There is no account with that IBAN.");
					return;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Perform transfer
		try(Connection con = dbRes.getConnection();
			PreparedStatement ps1=con.prepareStatement("UPDATE ACCOUNTS SET BALANCE=BALANCE-? WHERE ACCOUNT_NUMBER=(SELECT ACC_NUMBER FROM USERS WHERE USER_ID=?)");
			PreparedStatement ps2=con.prepareStatement("UPDATE ACCOUNTS SET BALANCE=BALANCE+? WHERE IBAN=?");
			PreparedStatement ps3=con.prepareStatement("INSERT INTO TRANSACTIONS(TRANSACTION_DATE, DONOR, DONOR_TEXT, RECEIVER_TEXT, RECEIVER, AMMOUNT) "
					+ "VALUES("
					+ "NOW(),"
					+ "(SELECT ACC_NUMBER FROM USERS WHERE USER_ID=?),"
					+ "(SELECT A.IBAN FROM ACCOUNTS A, USERS U WHERE U.ACC_NUMBER=A.ACCOUNT_NUMBER AND U.USER_ID=?),"
					+ "?,"
					+ "(SELECT ACCOUNT_NUMBER FROM ACCOUNTS WHERE IBAN=?),"
					+ "?"
					+ ");")){
			
			ps1.setDouble(1, ammount);
			ps1.setInt(2, uid);
			ps1.executeUpdate();
			
			ps2.setDouble(1, ammount);
			ps2.setString(2, iban);
			ps2.executeUpdate();
			
			ps3.setInt(1,uid);
			ps3.setInt(2, uid);
			ps3.setString(3, iban);
			ps3.setString(4, iban);
			ps3.setDouble(5, ammount);
			ps3.executeUpdate();
			
			getServletContext().getRequestDispatcher("/transactions").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.getWriter().append(e.getMessage());
		}
	}

}
