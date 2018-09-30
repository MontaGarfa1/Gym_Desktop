package model;

import java.io.Serializable;
import javafx.scene.control.Alert;

public class Section implements Serializable {
	
	String sectionName;
	String StaffMember;
	String Location;

	public Section(String sectionName, String staffMember, String location) {
		this.sectionName = sectionName;
		this.StaffMember = staffMember;
		this.Location = location;
	}

	public Section() {
		// TODO Auto-generated constructor stub
	}

	public void Set(String sectionName, String staffMember, String location) {
		this.sectionName = sectionName;
		this.StaffMember = staffMember;
		this.Location = location;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getStaffMember() {
		return StaffMember;
	}

	public void setStaffMember(String staffMember) {
		StaffMember = staffMember;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}


	public boolean isValid(Section section) {
		if (section.getSectionName() == null || section.getLocation() == null) {
			showRequired();
			return false;
		} else if (section.getSectionName().length() == 0 || section.getLocation().length() == 0) {
			showRequired();
			return false;
		}
		return true;
	}

	public boolean isUnique(Section section) {
		boolean unique = true;

		/*con = connection.geConnection();
		try {
			pst = con.prepareStatement("select * from section where namesection=?");
			pst.setString(1, section.getSectionName());
			rs = pst.executeQuery();
			if (rs.next()) {
				showUniqueMessage(section);
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

	public void showUniqueMessage(Section section) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Staff Exist");
		alert.setHeaderText(section.getSectionName() + " Already exist in database");
		alert.setContentText("Section name already exist in database. try another one");
		alert.show();
	}

}