package model;


import java.io.Serializable;
import javafx.scene.control.Alert;

public class Staff extends Personne implements Serializable {

	

	public Staff(String name, String username, String password, String dateofbirth, String phone, String email) {
		super(name,username,password,dateofbirth,phone,email);

	}

	public Staff() {
		// TODO Auto-generated constructor stub
	}

	public void Set(String name, String username, String password, String dateofbirth, String phone, String email) {
		this.username = username;
		this.fullName = name;
		this.password = password;
		this.dateofbirth = dateofbirth;
		this.phone = phone;
		this.email = email;
	}



	public boolean isValid(Staff staff) {
		if (staff.getFullName() == null || staff.getDateofbirth() == null || staff.getUsername() == null
				|| staff.getPassword() == null) {
			showRequired();
			return false;
		} else if (staff.getFullName().length() == 0 || staff.getUsername().length() == 0
				|| staff.getDateofbirth().length() == 0 || staff.getPassword().length() == 0) {
			showRequired();
			return false;
		}
		return true;
	}

	public boolean isUnique(Staff staff) {
		boolean unique = true;
/*
		con = connection.geConnection();
		try {
			pst = con.prepareStatement("select * from staff where namestaff=?");
			pst.setString(1, staff.getFullName());
			rs = pst.executeQuery();
			if (rs.next()) {
				showUniqueMessage(staff);
				unique = false;
			}
			rs.close();
			pst.close();
			con.close();
			connection.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		return unique;
	}

	public void showRequired() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Validation error");
		alert.setHeaderText("Validation error");
		alert.setContentText("All input  is Required /n ");
		alert.showAndWait();
	}

	public void showUniqueMessage(Staff staff) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Staff Exist");
		alert.setHeaderText(staff.getFullName() + " Already exist in database");
		alert.setContentText("Staff name already exist in database. try another one");
		alert.show();
	}

}
