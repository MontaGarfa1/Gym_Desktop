package model;

import java.io.Serializable;

public class Personne implements Serializable {
	
	String fullName, username, password, dateofbirth, phone, email;

	public Personne(String fullName, String username, String password, String dateofbirth, String phone, String email) {
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.dateofbirth = dateofbirth;
		this.phone = phone;
		this.email = email;
	}

	public Personne() {
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
