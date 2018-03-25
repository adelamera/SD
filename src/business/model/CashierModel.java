package business.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CashierModel {

	private int idUser;
	private String username;
	private String password;
	private String role;

	public CashierModel() {
		this.role = "cashier";
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPasswordEncrypt(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			this.password = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
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

	public String toString() {
		return this.username;
	}

}
