package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.OpenWindow;
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
import javafx.stage.Stage;
import model.Member;
import model.Membership;
import model.Section;

public class SignUpMemberController implements Initializable {

	Stage primaryStage = new Stage();
	String chemin = "";
	String title = "";

	ArrayList<Membership> membershiplist = new ArrayList<Membership>();
	ArrayList<Section> sectionlist = new ArrayList<Section>();

	Member dbll = new Member();

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
	private ComboBox<String> membership;

	@FXML
	private ComboBox<String> section;

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
			Member member = new Member(fullname.getText(), username.getText(), password.getText(), fullname.getText(),
					dateofbirth.getValue().toString(), phone.getText(), email.getText(),
					membership.getValue().toString(), section.getValue().toString());
			if (save(member)) {
				System.out.println("Member Full Name : " + member.getFullName());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Member saved");
				alert.setHeaderText("Member Sign Up ");
				alert.showAndWait();
				ArrayList<Member> memberarray = new ArrayList<Member>();
				MemberDao dao = new MemberDao();
				try {
					memberarray = dao.lectureFichier();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				memberarray.add(member);
				try {
					dao.AjoutFichierC(memberarray);
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

	private boolean isValidForm() {
		if (username.getText() == null || fullname.getText() == null || password.getText().length() == 0) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Validation error");
			alert.setHeaderText("Validation error");
			alert.setContentText("All Input is Required \n");
			alert.showAndWait();
			return false;
		}
		return true;
	}

	public boolean save(Member member) {
		if (dbll.isValid(member)) {
			if (dbll.isUnique(member)) {

				return true;
			}
		}

		return false;
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

	private void resetForm() {
		username.setText(null);
		fullname.setText(null);
		password.setText(null);
		phone.setText(null);
		dateofbirth.setValue(LocalDate.now());
	}
}
