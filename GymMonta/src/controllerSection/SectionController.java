package controllerSection;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.OpenWindow;
import controller.LoginController;
import dao.SectionDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Section;
import viewCommon.Actions;

public class SectionController implements Initializable {

	boolean search = false;

	static int ind = 0;
	String chemin = "";
	String title = "";
	Stage primaryStage = new Stage();

	public static ObservableList<Section> sectionList = FXCollections.observableArrayList();
	public static ArrayList<Section> sectionarray = new ArrayList<Section>();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField sectionSearch;

	@FXML
	private Label lblTotalDrug;

	@FXML
	private Label lblShowingDrug;

	@FXML
	private Button btnNext, addsection;

	@FXML
	private Button btnPrev;

	@FXML
	private TableView<Section> sectionTable;

	@FXML
	private TableColumn<Section, String> clnName, clnstaffMember, clnLocation, clndays;

	@FXML
	private TableColumn clmAction;

	@FXML
	void AddSectionOnAction(ActionEvent event) {
		chemin = "/viewSection/AddSection.fxml";
		title = "Add Section Page";
		new OpenWindow(primaryStage, chemin, title);

	}

	@FXML
	void SearchOnAction(ActionEvent event) {

	}

	@FXML
	void btnSearchOnAction(ActionEvent event) {
		if (sectionSearch.getText().length() == 0 || sectionSearch.getText() == null) {
			loadSection();
		} else {
			search = true;
			search(sectionSearch.getText());
		}
	}

	@FXML
	void handleNextButton(ActionEvent event) {

	}

	@FXML
	void handlePrevButton(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (LoginController.member == true) {
			clmAction.setVisible(false);
			addsection.setVisible(false);
		}
		if (search == false)
			loadSection();

	}

	private void editSection(Section section) {

		title = "Edit Member Page";
		chemin = "/viewSection/EditSection.fxml";
		FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/viewSection/EditSection.fxml"));

		Parent root;
		try {
			root = fXMLLoader.load();
			EditSectionController controller = fXMLLoader.getController();
			controller.sectionname.setText(section.getSectionName());
			// controller.dateofbirth;
			controller.locationRoom.setText(section.getLocation());

			// controller.section.setValue(value);
			// controller.membership.setValue(value);

			// controller.setDrugId(drug.getId());
			// controller.taNote.setText(drug.getNote());
			// controller.tfTradeName.setText(drug.getName());
			// controller.tfGenericName.setText(drug.getGenricName());
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Edit Section Page");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadSection() {

		sectionarray.clear();
		sectionList.clear();
		SectionDao dao = new SectionDao();
		try {
			sectionarray = dao.lectureFichierC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sectionList.addAll(sectionarray);
		clnName.setCellValueFactory(new PropertyValueFactory<>("sectionName"));
		// clnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		clnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
		// clnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		// clnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		// clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		// clmSections .setCellFactory(value);
		// clmMembership.setCellFactory(value);
		clnstaffMember.setCellValueFactory(new PropertyValueFactory<>("StaffMember"));
		clmAction.setCellFactory(action);
		// clnUsername.setCellValueFactory(cellData -> cellData.getValue().getFullName()
		// );
		// String fullName, username, password, gender, dateofbirth, phone, email;

		sectionTable.setItems(sectionList);
	}

	private void deleteSection(String name) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete Section ?");
		alert.setHeaderText("Delete " + name + " ?");
		alert.setContentText("Are you sure to delete this Section ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			int i = 0;
			while (name != sectionarray.get(i).getSectionName())
				i++;
			sectionList.remove(i);
			sectionarray.remove(i);
			SectionDao dao = new SectionDao();
			try {
				dao.AjoutFichierC(sectionarray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void search(String name) {
		sectionarray.clear();
		sectionList.clear();
		SectionDao dao = new SectionDao();
		try {
			sectionarray = dao.lectureFichierC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < sectionarray.size(); i++) {

			if (sectionarray.get(i).getSectionName().equals(name))
				sectionList.add(sectionarray.get(i));
		}
		clnName.setCellValueFactory(new PropertyValueFactory<>("sectionName"));
		// clnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		clnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
		// clnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		// clnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		// clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		// clmSections .setCellFactory(value);
		// clmMembership.setCellFactory(value);
		clmAction.setCellFactory(action);
		// clnUsername.setCellValueFactory(cellData -> cellData.getValue().getFullName()
		// );
		// String fullName, username, password, gender, dateofbirth, phone, email;

		sectionTable.setItems(sectionList);

	}

	Callback<TableColumn<Section, String>, TableCell<Section, String>> action = (
			TableColumn<Section, String> param) -> {
		final TableCell<Section, String> cell = new TableCell<Section, String>() {
			@Override
			public void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
					setText(null);
				} else {
					Actions actions = new Actions() {
						Section section = getTableView().getItems().get(getIndex());
						int index = getIndex();

						@Override
						protected void handleEditButton(ActionEvent actionEvent) {
							System.out.println("Edit button " + section.getSectionName());
							ind = index;
							editSection(section);
						}

						@Override
						protected void handleDeleteButton(ActionEvent actionEvent) {

							System.out.println("Delete button " + section.getSectionName());
							deleteSection(section.getSectionName());

						}

						@Override
						protected void handleViewButton(ActionEvent actionEvent) {
							System.out.println("View button " + section.getSectionName());

						}
					};
					setGraphic(actions);
					setText(null);
				}
			}
		};
		return cell;
	};

}
