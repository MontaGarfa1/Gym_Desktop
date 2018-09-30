package controllerMember;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.MemberDao;
import dao.MembershipDao;
import dao.SectionDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Member;
import model.Membership;
import model.Section;

public class EditMemberController implements Initializable {

	ArrayList<Membership> membershiplist = new ArrayList<Membership>();
	ArrayList<Section> sectionlist = new ArrayList<Section>();

	Member dbll = new Member();

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
	public Button btnConfirm;

	@FXML
	public Button btnCancel;

	@FXML
	public DatePicker dateofbirth;

	@FXML
	public ComboBox<String> membership;

	@FXML
	public ComboBox<String> section;

	@FXML
	void CancelOnAction(ActionEvent event) {

	}

	@FXML
	void btnConfirmOnAction(ActionEvent event) {
		if (isValidForm()) {
			Member member = new Member(fullname.getText(), username.getText(), password.getText(), fullname.getText(),
					dateofbirth.getValue().toString(), phone.getText(), email.getText(),
					membership.getValue().toString(), section.getValue().toString());
			if (update(member)) {
				System.out.println("Member Full Name : " + member.getFullName());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Member saved");
				alert.setHeaderText("Member Saved ");
				alert.showAndWait();
				int i;
				i = MemberController.ind;
				MemberController.memberList.get(i).Set(fullname.getText(), username.getText(), password.getText(),
						fullname.getText(), dateofbirth.getValue().toString(), phone.getText(), email.getText(),
						membership.getValue().toString(), section.getValue().toString());
				;
				MemberController.memberarray.get(i).Set(fullname.getText(), username.getText(), password.getText(),
						fullname.getText(), dateofbirth.getValue().toString(), phone.getText(), email.getText(),
						membership.getValue().toString(), section.getValue().toString());
				;
				MemberDao dao = new MemberDao();
				try {
					dao.AjoutFichierC(MemberController.memberarray);
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
		MembershipDao daom = new MembershipDao();
		try {
			membershiplist = daom.lectureFichierC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < membershiplist.size(); i++)
			membership.getItems().add(membershiplist.get(i).getName());// .getName()+"
																		// "+membershiplist.get(i).getFee()+"TND
																		// "+membershiplist.get(i).getPeriod() +
																		// "Jours");

		SectionDao daos = new SectionDao();
		try {
			sectionlist = daos.lectureFichierC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < sectionlist.size(); i++)
			section.getItems().add(sectionlist.get(i).getSectionName());// .getSectionName());

	}

	private boolean isValidForm() {
		if (username.getText() == null || fullname.getText() == null || password.getText() == null) {
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
		dateofbirth.setValue(LocalDate.now());
	}

	public boolean update(Member member) {
		if (dbll.isValid(member)) {
			if (dbll.isUnique(member)) {

				return true;

			}

		}
		return false;
	}
}
