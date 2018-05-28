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
 * Servlet implementation class LoginProcedure
 */
@WebServlet("/login")
public class LoginProcedure extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/bank")
	private DataSource dbRes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginProcedure() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void loginFailure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//request.setAttribute("message", "Invalid username or password.");
    	//getServletContext().getRequestDispatcher("index.jsp").forward(request, response);
    	request.setAttribute("message", "Login failure");
    	getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uname = request.getParameter("unamelog");
		String pwd = request.getParameter("pwdlog");
		
		if(uname==null||pwd==null) {
			loginFailure(request,response);
			return;
		}
		try(Connection con = dbRes.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE USERNAME=? OR EMAIL=?");){
			ps.setString(1,uname);
			ps.setString(2,uname);
			try(ResultSet rs = ps.executeQuery();){
				if(rs.next()){
					if(BCrypt.checkpw(pwd, rs.getString("PWD_HASH"))) {
						request.getSession().setAttribute("user_id", rs.getInt("USER_ID"));
						if(rs.getString("HOTP_SERIAL")!=null) {
							getServletContext().getRequestDispatcher("/twoFA.jsp").forward(request, response);;
						}
						else {
							request.getSession().setAttribute("logged", true);
						//request.setAttribute("message","Succes");
						//getServletContext().getRequestDispatcher("index.jsp").forward(request, response);
							getServletContext().getRequestDispatcher("/transactions").forward(request, response);
						}
						//TO DO  Forward to dashboard page
					}
					else {
						loginFailure(request,response);
						return;
					}
				}
				else {
					loginFailure(request,response);
					return;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.getWriter().append(e.getMessage());		}
	}

}
