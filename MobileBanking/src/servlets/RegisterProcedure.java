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

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class RegisterProcedure
 */
@WebServlet("/register")
public class RegisterProcedure extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/bank")
	private DataSource dbRes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterProcedure() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void registerFailure(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
    	response.getWriter().append(message);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String serial = request.getParameter("serial");
		String iban = request.getParameter("iban");
		int acc_number = 0;
		//response.getWriter().append(uname).append(email).append(pwd).append(serial).append(iban);
		
		//Check if username or email already exists
		try(Connection con = dbRes.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT 'X' FROM USERS WHERE USERNAME=? OR EMAIL=?");){
			ps.setString(1, uname);
			ps.setString(2, email);
			try(ResultSet rs = ps.executeQuery();){
				if(rs.next()) {
					registerFailure(request,response,"Username or email already exists.");
					return;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.getWriter().append(e.getMessage());
		}
		
		//Check if IBAN already has associated account
		try(Connection con = dbRes.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT 'X' FROM USERS U, ACCOUNTS A WHERE U.ACC_NUMBER=A.ACCOUNT_NUMBER AND A.IBAN=?")){
			ps.setString(1, iban);
			try(ResultSet rs = ps.executeQuery();){
				if(rs.next()) {
					registerFailure(request,response,"There is already an account associated with that IBAN.");
					return;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.getWriter().append(e.getMessage());
		}
		//Look up acc number
		try(Connection con = dbRes.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT ACCOUNT_NUMBER FROM ACCOUNTS WHERE IBAN=?")){
			ps.setString(1, iban);
			try(ResultSet rs=ps.executeQuery();){
				if(rs.next()) {
					acc_number=rs.getInt("ACCOUNT_NUMBER");
				}
				else {
					registerFailure(request,response,"There is no bank account associated witht hat IBAN.");
					return;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.getWriter().append(e.getMessage());
		}
		
		//Insert into database
		try(Connection con = dbRes.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO "
					+ "USERS(ACC_NUMBER, USERNAME, PWD_HASH, EMAIL, HOTP_SERIAL, HOTP_COUNT) "
					+ "VALUES("
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?,"
					+ "0);")){
			ps.setInt(1, acc_number);
			ps.setString(2, uname);
			ps.setString(3, BCrypt.hashpw(pwd, BCrypt.gensalt()));
			ps.setString(4, email);
			if(serial.length()==0) {
				ps.setString(5, null);
			}
			else ps.setString(5, serial);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.getWriter().append(e.getMessage());
		}
	}

}
