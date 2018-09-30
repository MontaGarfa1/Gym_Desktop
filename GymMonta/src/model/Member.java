package model;

import java.io.Serializable;


import javafx.scene.control.Alert;

public class Member extends Personne implements Serializable {
	String gender;
	String membership;
	String section;

	public Member() {

	}

	public Member(String fullName, String username, String password, String gender, String dateofbirth, String phone,
			String email, String membership, String section) {
		super(fullName,username,password,dateofbirth,phone,email);
		this.gender = gender;
		this.membership = membership;
		this.section = section;
	}

	public void Set(String fullName, String username, String password, String gender, String dateofbirth, String phone,
			String email, String membership, String section) {
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.dateofbirth = dateofbirth;
		this.phone = phone;
		this.email = email;
		this.membership = membership;
		this.section = section;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}


	public boolean isValid(Member member) {
		if (member.getFullName() == null || member.getDateofbirth() == null || member.getUsername() == null
				|| member.getPassword() == null) {
			showRequired();
			return false;
		} else if (member.getFullName().length() == 0 || member.getUsername().length() == 0
				|| member.getDateofbirth().length() == 0 || member.getPassword().length() == 0) {
			showRequired();
			return false;
		}
		return true;
	}

	public boolean isUnique(Member member) {
		boolean unique = true;

/*		try {
			con = connection.geConnection();
			pst = con.prepareStatement("select * from member where namemember=?");
			pst.setString(1, member.getFullName());
			rs = pst.executeQuery();
			if (rs.next()) {
				showUniqueMessage(member);
				unique = false;
			}
			rs.close();
			pst.close();
			con.close();
			connection.con.close();
		} catch (SQLException ex) {
			Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
		}*/
		return unique;
	}

	public void showRequired() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Validation error");
		alert.setHeaderText("Validation error");
		alert.setContentText("All input  is Required /n ");
		alert.showAndWait();
	}

	public void showUniqueMessage(Member member) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Member Exist");
		alert.setHeaderText(member.getFullName() + " Already exist in database");
		alert.setContentText("Member name already exist in database. try another one");
		alert.show();
	}

}
