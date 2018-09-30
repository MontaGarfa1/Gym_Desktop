package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OpenWindow {
	Stage primaryStage;
	String chemin;
	String title;
	StackPane sPane;

	public OpenWindow(Stage primaryStage, String chemin, String title) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(chemin));
			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(title);

			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OpenWindow(StackPane sPane, String chemin) {
		try {
			sPane.getChildren().clear();
			sPane.getChildren().add(FXMLLoader.load(getClass().getResource(chemin)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
