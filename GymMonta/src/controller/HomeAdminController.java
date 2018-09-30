package controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import application.OpenWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeAdminController implements Initializable {
	String title = "";
	Stage primaryStage = new Stage();
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnHome, btnStaff, btnMember, btnSection, btnMTyp, btnAbout, logout;

	@FXML
	private Label username, stafinfo;

	@FXML

	public StackPane homeContent;

	String chemin = "";

	@FXML
	void AboutOnAction(ActionEvent event) {
		// chemin="/viewMembership/Membership.fxml";

	}

	@FXML
	void HomeOnAction(ActionEvent event) {
		chemin = "/view/HomeContent.fxml";
		new OpenWindow(homeContent, chemin);

	}

	@FXML
	void LogOutOnAction(ActionEvent event) {
		logout.getScene().getWindow().hide();
		String chemin = "/view/Login.fxml";
		title = "Login Page";
		Notifications notification = Notifications.create().title("Log Out complete ")
				.text(LoginController.usernameforHome + " has loged out").hideAfter(Duration.seconds(7))
				.position(Pos.BOTTOM_RIGHT);
		new OpenWindow(primaryStage, chemin, title);
		notification.showInformation();

	}

	@FXML
	void MemberOnAction(ActionEvent event) {
		chemin = "/viewMember/Member.fxml";
		// new OpenWindow(primaryStage,chemin,title);
		new OpenWindow(homeContent, chemin);

	}

	@FXML
	void MembershipOnAction(ActionEvent event) {
		chemin = "/viewMembership/Membership.fxml";
		new OpenWindow(homeContent, chemin);

		/*
		 * try { homeContent.getChildren().clear();
		 * homeContent.getChildren().add(FXMLLoader.load(getClass().getResource(
		 * "/viewMembership/Membership.fxml"))); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	@FXML
	void SectionOnAction(ActionEvent event) {
		chemin = "/viewSection/Section.fxml";
		new OpenWindow(homeContent, chemin);

	}

	@FXML
	void StaffOnAction(ActionEvent event) {
		chemin = "/viewStaff/Staff.fxml";
		new OpenWindow(homeContent, chemin);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chemin = "/view/HomeContent.fxml";
		username.setText(LoginController.usernameforHome);
		stafinfo.setText(LoginController.statuforHome);
		new OpenWindow(homeContent, "/view/HomeContent.fxml");
		/*
		 * try { homeContent.getChildren().clear();
		 * homeContent.getChildren().add(FXMLLoader.load(getClass().getResource(
		 * "/view/HomeContent.fxml"))); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */

	}
}
