package controllerSection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.SectionDao;
import dao.StaffDao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Section;
import model.Staff;

public class AddSectionController implements Initializable {
	ArrayList<Staff> stafflist = new ArrayList<Staff>();

	Section dbll = new Section();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField sectionname;

	@FXML
	private Button btnConfirm;

	@FXML
	private Button btnCancel;

	@FXML
	private TextField locationRoom;

	@FXML
	private ComboBox<String> staffsection;

	@FXML
	void CancelOnAction(ActionEvent event) {
		resetForm();
	}

	@FXML
	void btnConfirmOnAction(ActionEvent event) {
		if (isValidForm()) {
			Section section = new Section(sectionname.getText(), staffsection.getValue().toString(),
					locationRoom.getText());
			if (save(section)) {
				System.out.println("Section Full Name : " + section.getSectionName());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Section saved");
				alert.setHeaderText("Section Saved ");
				alert.showAndWait();
				SectionController.sectionList.add(section);
				SectionController.sectionarray.add(section);
				SectionDao dao = new SectionDao();
				try {
					dao.AjoutFichierC(SectionController.sectionarray);
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
		if (sectionname.getText() == null || locationRoom.getText() == null) {
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
		sectionname.setText(null);
		locationRoom.setText(null);
	}

	public boolean save(Section section) {
		if (dbll.isValid(section)) {
			if (dbll.isUnique(section)) {

				return true;
			}

		}
		return false;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		StaffDao daos = new StaffDao();
		try {
			stafflist = daos.lectureFichierC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < stafflist.size(); i++)
			staffsection.getItems().add(stafflist.get(i).getFullName());
	}
}
