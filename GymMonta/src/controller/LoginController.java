package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import application.OpenWindow;
import dao.MemberDao;
import dao.StaffDao;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Member;
import model.Staff;

public class LoginController implements Initializable {

	public static String usernameforHome = "";
	public static String statuforHome = "";
	public static boolean member = false;
	@FXML
	private Pane backgroundPane;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField adminusername;

	@FXML
	private PasswordField passwordadmin;

	@FXML
	private Button btnLoginA;

	@FXML
	private Button btnSignupA;

	@FXML
	private TextField usernamemember;

	@FXML
	private PasswordField passwordmember;

	@FXML
	private Button btnLoginM;

	@FXML
	private Button btnSignupM;

	Stage primaryStage = new Stage();
	String chemin = "";
	String title = "";

	@FXML
	void btnLoginAOnAction(ActionEvent event) throws FileNotFoundException, IOException {
		ArrayList<Staff> staffarray = new ArrayList<Staff>();
		StaffDao dao = new StaffDao();
		staffarray = dao.lectureFichierC();
		boolean verif = false;
		int i = 0;
		while (i < staffarray.size() && verif == false) {

			if (adminusername.getText().equals(staffarray.get(i).getEmail())
					|| adminusername.getText().equals(staffarray.get(i).getUsername())
					|| adminusername.getText().equals(staffarray.get(i).getUsername()))
				if (passwordadmin.getText().equals(staffarray.get(i).getPassword())) {
					verif = true;
					usernameforHome = staffarray.get(i).getFullName();
					statuforHome = "ADMIN";
				}

			i++;
		}

		if (verif == true) {

			btnLoginA.getScene().getWindow().hide();
			chemin = "/view/HomeAdmin.fxml";
			title = "Home Page Admin";
			Notifications notification = Notifications.create().title("Sign in complete ")
					.text(usernameforHome + " has loged in").hideAfter(Duration.seconds(7)).position(Pos.BOTTOM_RIGHT);
			new OpenWindow(primaryStage, chemin, title);
			notification.showInformation();

		} else {
			valid();
		}

	}

	@FXML
	void btnLoginMOnAction(ActionEvent event) throws FileNotFoundException, IOException {
		ArrayList<Member> memberarray = new ArrayList<Member>();
		MemberDao dao = new MemberDao();
		memberarray = dao.lectureFichier();
		boolean verif = false;
		int i = 0;
		while (i < memberarray.size() && verif == false) {

			if (usernamemember.getText().equals(memberarray.get(i).getEmail())
					|| usernamemember.getText().equals(memberarray.get(i).getUsername())
					|| usernamemember.getText().equals(memberarray.get(i).getUsername()))
				if (passwordmember.getText().equals(memberarray.get(i).getPassword())) {
					verif = true;
					usernameforHome = memberarray.get(i).getUsername();
					statuforHome = "MEMBER";
				}

			i++;
		}

		if (verif == true) {
			member = true;
			btnLoginM.getScene().getWindow().hide();
			chemin = "/view/HomeAdmin.fxml";
			title = "Home Page Member";
			Notifications notification = Notifications.create().title("Sign in complete ")
					.text(usernameforHome + " has loged in").hideAfter(Duration.seconds(7)).position(Pos.BOTTOM_RIGHT);
			new OpenWindow(primaryStage, chemin, title);
			notification.showInformation();

		} else {
			valid();

		}

	}

	@FXML
	void btnSignupAOnAction(ActionEvent event) {

		btnSignupA.getScene().getWindow().hide();
		chemin = "/view/SignUpAdmin.fxml";
		title = "Sign Up Page Admin";
		new OpenWindow(primaryStage, chemin, title);

	}

	@FXML
	void btnSignupMOnAction(ActionEvent event) {
		btnSignupM.getScene().getWindow().hide();
		chemin = "/view/SignUpMember.fxml";
		title = "Sign Up Page Member";
		new OpenWindow(primaryStage, chemin, title);

	}

	@FXML
	void hlForgetPassword(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FadeTransition fadeout = new FadeTransition(Duration.seconds(2.5), backgroundPane);
		fadeout.setFromValue(1);
		fadeout.setToValue(0);
		fadeout.play();

		fadeout.setOnFinished(event -> {

			backgroundPane.setStyle(" -fx-background-image: url(\"/images/Home1.jpg\");");

			FadeTransition fadein = new FadeTransition(Duration.seconds(2.5), backgroundPane);
			fadein.setFromValue(0);
			fadein.setToValue(0.6);
			fadein.play();

			fadein.setOnFinished(e -> {

				backgroundPane.setStyle(" -fx-background-image: url(\"/images/Home2.jpg\");");
				FadeTransition fadein2 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
				fadein2.setFromValue(0);
				fadein2.setToValue(1);
				fadein2.play();

				fadein2.setOnFinished(event2 -> {

					backgroundPane.setStyle(" -fx-background-image: url(\"/images/Home3.jpg\");");

					FadeTransition fadein3 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
					fadein3.setFromValue(1);
					fadein3.setToValue(0);
					fadein3.play();

					fadein3.setOnFinished(event3 -> {
						backgroundPane.setStyle(" -fx-background-image: url(\"/images/Home1.jpg\");");

						FadeTransition fadein4 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
						fadein4.setFromValue(0);
						fadein4.setToValue(1);
						fadein4.play();

						fadein4.setOnFinished(event4 -> {
							backgroundPane.setStyle(" -fx-background-image: url(\"/images/Home2.jpg\");");

							FadeTransition fadein5 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
							fadein5.setFromValue(0);
							fadein5.setToValue(1);
							fadein5.play();

						});

					});
				});

			});

		});
	}

	public void valid() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Validation error");
		alert.setHeaderText("Validation error");
		alert.setContentText("Verify your Username Or Password \n");
		alert.showAndWait();
	}
}
