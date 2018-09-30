
package model;

import java.io.Serializable;


import javafx.scene.control.Alert;

public class Membership implements Serializable {
	
	String name, period, fee;

	public Membership() {
		// TODO Auto-generated constructor stub
	}

	public Membership(String name, String period, String fee) {
		// this.id = id ;
		this.name = name;
		this.period = period;
		this.fee = fee;
	}

	public void Set(String name, String period, String fee) {
		this.name = name;
		this.period = period;
		this.fee = fee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}


	public boolean isValid(Membership membership) {
		if (membership.getName() == null || membership.getPeriod() == null || membership.getFee() == null) {
			showRequired();
			return false;
		} else if (membership.getName().length() == 0 || membership.getPeriod().length() == 0
				|| membership.getFee().length() == 0) {
			showRequired();
			return false;
		}
		return true;
	}

	public boolean isUnique(Membership membership) {
		boolean unique = true;

		/*con = connection.geConnection();
		try {
			pst = con.prepareStatement("select * from membership where namemembership=?");
			pst.setString(1, membership.getName());
			rs = pst.executeQuery();
			if (rs.next()) {
				showUniqueMessage(membership);
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

	public void showUniqueMessage(Membership membership) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Membership Exist");
		alert.setHeaderText(membership.getName() + " Already exist in database");
		alert.setContentText("Membership name already exist in database. try another one");
		alert.show();
	}

}
