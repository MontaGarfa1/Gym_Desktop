package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.OpenWindow;
import dao.StaffDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Staff;

public class SignUpAdminController implements Initializable {
	Stage primaryStage = new Stage();
	String chemin = "";
	String title = "";

	Staff dbll = new Staff();
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField username;

	@FXML
	private TextField email;

	@FXML
	private PasswordField password;

	@FXML
	private TextField fullname;

	@FXML
	private TextField phone;

	@FXML
	private Button btnConfirm;

	@FXML
	private Button btnCancel;

	@FXML
	private DatePicker dateofbirth;

	@FXML
	void CancelOnAction(ActionEvent event) {
		btnConfirm.getScene().getWindow().hide();
		chemin = "/view/Login.fxml";
		title = "Login";
		primaryStage.setWidth(1008);
		primaryStage.setHeight(643);
		new OpenWindow(primaryStage, chemin, title);
	}

	@FXML
	void btnConfirmOnAction(ActionEvent event) {
		if (isValidForm()) {
			Staff staff = new Staff(fullname.getText(), username.getText(), password.getText(),
					dateofbirth.getValue().toString(), phone.getText(), email.getText());
			if (save(staff)) {
				System.out.println("Staff Full Name : " + staff.getFullName());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Staff saved");
				alert.setHeaderText("Staff Saved ");
				alert.showAndWait();
				ArrayList<Staff> staffarray = new ArrayList<Staff>();

				StaffDao dao = new StaffDao();
				try {
					staffarray = dao.lectureFichierC();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				staffarray.add(staff);
				try {
					dao.AjoutFichierC(staffarray);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resetForm();
			}

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	private boolean isValidForm() {
		if (username.getText() == null || fullname.getText() == null || password.getText().length() == 0
				|| dateofbirth.getValue().toString().length() == 0) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Validation error");
			alert.setHeaderText("Validation error");
			alert.setContentText("All Input is Required \n");
			alert.showAndWait();
			return false;
		}
		return true;
	}

	public boolean save(Staff staff) {
		if (dbll.isValid(staff)) {
			if (dbll.isUnique(staff)) {

				return true;

			}

		}
		return false;
	}

	private void resetForm() {
		username.setText(null);
		fullname.setText(null);
		password.setText(null);
		email.setText(null);
		phone.setText(null);
		dateofbirth.setValue(LocalDate.now());
	}
}
