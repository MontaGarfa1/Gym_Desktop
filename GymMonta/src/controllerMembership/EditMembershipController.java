package controllerMembership;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import dao.MembershipDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Membership;

public class EditMembershipController implements Initializable {
	Membership dbll = new Membership();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	public TextField membershipName;

	@FXML
	public TextField periode;

	@FXML
	private Button btnConfirm;

	@FXML
	private Button btnCancel;

	@FXML
	public TextField signUpFee;

	@FXML
	private ComboBox<String> sections;

	@FXML
	void CancelOnAction(ActionEvent event) {
		resetForm();
	}

	@FXML
	void btnConfirmOnAction(ActionEvent event) {
		if (isValidForm()) {
			Membership membership = new Membership(membershipName.getText(), periode.getText(), signUpFee.getText());
			if (update(membership)) {
				System.out.println("Member Full Name : " + membership.getName());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Membership saved");
				alert.setHeaderText("Membership Saved ");
				alert.showAndWait();
				int i;
				i = MembershipController.ind;
				MembershipController.membershipList.get(i).Set(membershipName.getText(), periode.getText(),
						signUpFee.getText());
				MembershipController.membershiparray.get(i).Set(membershipName.getText(), periode.getText(),
						signUpFee.getText());
				MembershipDao dao = new MembershipDao();
				try {
					dao.AjoutFichierC(MembershipController.membershiparray);
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

	}

	private boolean isValidForm() {
		if (membershipName.getText() == null || periode.getText() == null || signUpFee.getText() == null) {
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
		membershipName.setText(null);
		periode.setText(null);
		signUpFee.setText(null);
	}

	public boolean update(Membership membership) {
		if (dbll.isValid(membership)) {
			if (dbll.isUnique(membership)) {

				return true;
			}
		}

		return false;
	}

}
