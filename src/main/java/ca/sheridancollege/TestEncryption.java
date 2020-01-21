package ca.sheridancollege;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncryption {
	
	public static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String p = "123";
		System.out.println(encryptPassword(p));
		System.out.println(encryptPassword(p));
		
	}

}
