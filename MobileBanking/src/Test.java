import org.mindrot.jbcrypt.BCrypt;

import com.sun.identity.authentication.modules.hotp.HOTPAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Test {
	public static void main(String[] args) {
	    String pass = "test";
	    byte[] code = pass.getBytes();
	    try {
	    System.out.println(HOTPAlgorithm.generateOTP(code, 2, 6, false, 2));
	    }
	    catch (Exception e) {
	    System.out.println("Exception = " + e);
	    }
	    }
}
