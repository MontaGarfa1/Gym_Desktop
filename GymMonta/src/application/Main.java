package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		//String chemin = "/view/SignUpAdmin.fxml";
		//String chemin ="/viewSection/AddSection.fxml";
		 //String chemin ="/viewMember/Member.fxml";
		String chemin="/view/Login.fxml";
		//String chemin ="/viewStaff/Staff.fxml";

		String title = "Login Page";
		try { 
			/*	  Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
				  Scene scene = new Scene(root);
				  //scene.getStylesheets().add(getClass().getResource("application.css"). toExternalForm());
				  primaryStage.setScene(scene);
				  primaryStage.setTitle("Login Page"); primaryStage.show();
				  primaryStage.setResizable(false);*/
				 
			primaryStage.setWidth(1008);
			primaryStage.setHeight(643);
			new OpenWindow(primaryStage, chemin, title);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
