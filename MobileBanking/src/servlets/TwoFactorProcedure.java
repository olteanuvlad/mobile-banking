package servlets;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

import com.sun.identity.authentication.modules.hotp.HOTPAlgorithm;

/**
 * Servlet implementation class TwoFactorProcedure
 */
@WebServlet("/twofactorauth")
public class TwoFactorProcedure extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/bank")
	private DataSource dbRes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwoFactorProcedure() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void twoFailure(HttpServletRequest request, HttpServletResponse response) {
    	
    }
    
    protected void updateCount(int uid, int cnt) {
    	//Update server count
    			try(Connection con = dbRes.getConnection();
    				PreparedStatement ps = con.prepareStatement("UPDATE USERS SET HOTP_COUNT=? WHERE USER_ID=?");){
    				ps.setInt(1, cnt+1);
    				ps.setInt(2, uid);
    				ps.executeUpdate();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientOTP = request.getParameter("otp");
		int uid = (int)request.getSession().getAttribute("user_id");
		if(uid<0) {
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		int cnt =0;
		
		//Get server serial and count and check OTP
		try(Connection con = dbRes.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT HOTP_SERIAL, HOTP_COUNT FROM USERS WHERE USER_ID=?")){
			ps.setInt(1, uid);
			try(ResultSet rs=ps.executeQuery();){
				if(rs.next()) {
					String serial = rs.getString("HOTP_SERIAL");
					cnt = rs.getInt("HOTP_COUNT");
					byte[] serialBytes = serial.getBytes();
					String serverOTP = HOTPAlgorithm.generateOTP(serialBytes, cnt, 6, false, cnt);
					if(clientOTP.equals(serverOTP)) {
						request.getSession().setAttribute("logged", true);
						updateCount(uid,cnt);
						getServletContext().getRequestDispatcher("/transactions").forward(request, response);
					}
					else {
						response.getWriter().append("Invalid code");
						updateCount(uid,cnt);
					}
				}
				else {
					getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
				}
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
