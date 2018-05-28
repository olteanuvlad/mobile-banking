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

import classes.Transaction;

/**
 * Servlet implementation class TransactionList
 */
@WebServlet("/transactions")
public class TransactionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/bank")
	private DataSource dbRes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Check for user session
		int uid = (int)request.getSession().getAttribute("user_id");
		if(uid<1||(boolean)request.getSession().getAttribute("logged")!=true) {
			getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
			return;
		}
		
		//Get transactions AND BALANCE
		try(Connection con=dbRes.getConnection();
			PreparedStatement ps=con.prepareStatement("SELECT \r\n" + 
					"	CONCAT(DA.LAST_NAME,' ',DA.FIRST_NAME) AS 'DONOR_NAME',\r\n" + 
					"	CONCAT(RA.LAST_NAME,' ',RA.FIRST_NAME) AS 'RECEIVER_NAME',\r\n" + 
					"	DONOR_TEXT AS 'DONOR_IBAN',\r\n" + 
					"	RECEIVER_TEXT AS 'RECEIVER_IBAN',\r\n" + 
					"	DATE_FORMAT(TRANSACTION_DATE,'%d %m %Y') AS 'TR_DATE',\r\n" + 
					"	TRANSACTION_ID,\r\n" + 
					"	AMMOUNT\r\n" + 
					"FROM\r\n" + 
					"	TRANSACTIONS T, ACCOUNTS DA, ACCOUNTS RA, USERS DU, USERS RU\r\n" + 
					"WHERE (RU.USER_ID=? OR DU.USER_ID=?)\r\n" + 
					"	AND T.DONOR=DA.ACCOUNT_NUMBER \r\n" + 
					"	AND T.RECEIVER=RA.ACCOUNT_NUMBER\r\n" + 
					"	AND DU.ACC_NUMBER=DA.ACCOUNT_NUMBER\r\n" + 
					"	AND RU.ACC_NUMBER=RA.ACCOUNT_NUMBER\r\n" + 
					"ORDER BY TRANSACTION_DATE DESC;" + 
					"");
			PreparedStatement psold = con.prepareStatement("SELECT BALANCE FROM ACCOUNTS A, USERS U WHERE U.USER_ID=? AND U.ACC_NUMBER=A.ACCOUNT_NUMBER");){
			psold.setInt(1, uid);
			try(ResultSet rsold = psold.executeQuery()){
				if(rsold.next()) {
					request.setAttribute("sold", rsold.getDouble("BALANCE"));
				}
			}
			ps.setInt(1, uid);
			ps.setInt(2, uid);
			try(ResultSet rs = ps.executeQuery();){
				int counter=-1;
				Transaction[] trans = new Transaction[5];
				while(rs.next()&&++counter<=5) {
					trans[counter] = new Transaction(rs.getString("DONOR_NAME"),rs.getString("RECEIVER_NAME"),rs.getString("DONOR_IBAN"),rs.getString("RECEIVER_IBAN"),rs.getDouble("AMMOUNT"),rs.getString("TR_DATE"),rs.getInt("TRANSACTION_ID"));
				}
				request.setAttribute("transactions", trans);
				getServletContext().getRequestDispatcher("/mainPage.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.getWriter().append(e.getMessage());
		}
	}

}
