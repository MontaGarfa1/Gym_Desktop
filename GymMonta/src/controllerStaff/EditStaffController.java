package controllerStaff;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.StaffDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Staff;

public class EditStaffController implements Initializable {
	

	Staff dbll = new Staff();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	public TextField username;

	@FXML
	public TextField email;

	@FXML
	public PasswordField password;

	@FXML
	public TextField fullname;

	@FXML
	public TextField phone;

	@FXML
	private Button btnConfirm;

	@FXML
	private Button btnCancel;

	@FXML
	public DatePicker dateofbirth;

	@FXML
	void CancelOnAction(ActionEvent event) {

	}

	@FXML
	void btnConfirmOnAction(ActionEvent event) {
		if (isValidForm()) {
			Staff staff = new Staff(fullname.getText(), username.getText(), password.getText(),
					dateofbirth.getValue().toString(), phone.getText(), email.getText());
			if (update(staff)) {
				System.out.println("Staff Full Name : " + staff.getFullName());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Staff saved");
				alert.setHeaderText("Staff Saved ");
				alert.showAndWait();
				int i;
				i = StaffController.ind;
				StaffController.staffList.get(i).Set(fullname.getText(), username.getText(), password.getText(),
						dateofbirth.getValue().toString(), phone.getText(), email.getText());
				StaffController.staffarray.get(i).Set(fullname.getText(), username.getText(), password.getText(),
						dateofbirth.getValue().toString(), phone.getText(), email.getText());
				StaffDao dao = new StaffDao();
				try {
					dao.AjoutFichierC(StaffController.staffarray);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			resetForm();

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	private boolean isValidForm() {
		if (username.getText() == null || fullname.getText() == null || password.getText() == null
				|| dateofbirth.getValue().toString() == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Validation error");
			alert.setHeaderText("Validation error");
			alert.setContentText("All Input is Required \n");
			alert.showAndWait();
			return false;
		}
		return true;
	}

	private void resetForm() {
		username.setText(null);
		fullname.setText(null);
		password.setText(null);
		email.setText(null);
		dateofbirth.setValue(LocalDate.now());
	}

	public boolean update(Staff staff) {
		if (dbll.isValid(staff)) {
			if (dbll.isUnique(staff)) {

				return true;

			}
		}
		return false;
	}
}
