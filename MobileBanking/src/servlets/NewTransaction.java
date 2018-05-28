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
		try(Connection con=dbRes.getConnection();){
			
		}
	}

}
