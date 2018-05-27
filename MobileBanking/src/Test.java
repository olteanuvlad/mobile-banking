import org.mindrot.jbcrypt.BCrypt;

public class Test {
	public static void main(String[] args) {
		String s = "olteanuvlat";
		//String hashed = BCrypt.hashpw(s, BCrypt.gensalt(12));
		System.out.println(BCrypt.checkpw(s, "$2a$10$4WlPumvYAfTMVQNFVc1iPuN3ZR.Eo2zHqxIQKVSxzkt4O6n5GVV6S"));
		//System.out.println(hashed);
	}
}
