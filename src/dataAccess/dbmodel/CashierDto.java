package dataAccess.dbmodel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CashierDto {
	private int idUser;
	private String username;
	private String password;
	private String role;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPasswordEncrypt(String password) throws NoSuchAlgorithmException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[12];
		random.nextBytes(salt);
		MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
		byte[] hashVal = msgDigest.digest((password + salt).getBytes());
		this.password = hashVal.toString();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
