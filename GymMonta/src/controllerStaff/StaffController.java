package controllerStaff;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.OpenWindow;
import controller.LoginController;
import dao.StaffDao;
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
import model.Staff;
import viewCommon.Actions;

public class StaffController implements Initializable {

	boolean search = false;
	static int ind = 0;
	String chemin = "";
	String title = "";
	Stage primaryStage = new Stage();
	public static ObservableList<Staff> staffList = FXCollections.observableArrayList();
	public static ArrayList<Staff> staffarray = new ArrayList<Staff>();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField staffSearch;

	@FXML
	private Label lblTotalDrug;

	@FXML
	private Label lblShowingDrug;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnPrev, addstaff;

	@FXML
	private TableView<Staff> sectionTable;

	@FXML
	private TableColumn<Staff, String> clnUsername, clnFullname, clnDatebirth, clnPhone, clnEmail, clmSections;

	@FXML
	private TableColumn clmAction;

	@FXML
	void AddStaffOnAction(ActionEvent event) {
		chemin = "/viewStaff/AddStaff.fxml";
		title = "Add Staff Page";
		new OpenWindow(primaryStage, chemin, title);
	}

	@FXML
	void SearchOnAction(ActionEvent event) {

	}

	@FXML
	void btnSearchOnAction(ActionEvent event) {
		if (staffSearch.getText().length() == 0 || staffSearch.getText() == null) {
			loadStaff();
		} else {
			search = true;
			search(staffSearch.getText());

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
			addstaff.setVisible(false);
		}
		if (search == false)
			loadStaff();
	}

	private void loadStaff() {

		staffarray.clear();
		staffList.clear();
		StaffDao dao = new StaffDao();
		try {
			staffarray = dao.lectureFichierC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		staffList.addAll(staffarray);
		clnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		clnFullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		clnDatebirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
		clnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		// clmSections .setCellFactory(value);
		// clmMembership.setCellFactory(value);
		clmAction.setCellFactory(action);
		// clnUsername.setCellValueFactory(cellData -> cellData.getValue().getFullName()
		// );
		// String fullName, username, password, gender, dateofbirth, phone, email;

		sectionTable.setItems(staffList);
	}

	private void editStaff(Staff staff) {

		title = "Edit Member Page";
		chemin = "/viewStaff/EditStaffMember.fxml";
		FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/viewStaff/EditStaff.fxml"));

		Parent root;
		try {
			root = fXMLLoader.load();
			EditStaffController controller = fXMLLoader.getController();
			controller.fullname.setText(staff.getFullName());
			// controller.dateofbirth;
			controller.dateofbirth.setValue(LocalDate.now());
			controller.phone.setText(staff.getPhone());
			controller.username.setText(staff.getUsername());
			controller.password.setText(staff.getPassword());
			controller.email.setText(staff.getEmail());

			// controller.section.setValue(value);
			// controller.membership.setValue(value);

			// controller.setDrugId(drug.getId());
			// controller.taNote.setText(drug.getNote());
			// controller.tfTradeName.setText(drug.getName());
			// controller.tfGenericName.setText(drug.getGenricName());
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Edit Drug");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deleteStaff(String name) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete Staff ?");
		alert.setHeaderText("Delete " + name + " ?");
		alert.setContentText("Are you sure to delete this Member ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			int i = 0;
			while (name != staffarray.get(i).getFullName())
				i++;
			staffList.remove(i);
			staffarray.remove(i);
			StaffDao dao = new StaffDao();
			try {
				dao.AjoutFichierC(staffarray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void search(String name) {
		staffarray.clear();
		staffList.clear();
		StaffDao dao = new StaffDao();
		try {
			staffarray = dao.lectureFichierC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < staffarray.size(); i++) {

			if (staffarray.get(i).getFullName().equals(name))
				staffList.add(staffarray.get(i));
		}

		clnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		clnFullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		clnDatebirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
		clnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		// clmSections .setCellFactory(value);
		// clmMembership.setCellFactory(value);
		clmAction.setCellFactory(action);
		// clnUsername.setCellValueFactory(cellData -> cellData.getValue().getFullName()
		// );
		// String fullName, username, password, gender, dateofbirth, phone, email;

		sectionTable.setItems(staffList);
	}

	Callback<TableColumn<Staff, String>, TableCell<Staff, String>> action = (TableColumn<Staff, String> param) -> {
		final TableCell<Staff, String> cell = new TableCell<Staff, String>() {
			@Override
			public void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
					setText(null);
				} else {
					Actions actions = new Actions() {
						Staff staff = getTableView().getItems().get(getIndex());
						int index = getIndex();

						@Override
						protected void handleEditButton(ActionEvent actionEvent) {
							System.out.println("Edit button " + staff.getPhone());
							ind = index;

							editStaff(staff);
						}

						@Override
						protected void handleDeleteButton(ActionEvent actionEvent) {

							System.out.println("Delete button " + staff.getFullName());
							deleteStaff(staff.getFullName());

						}

						@Override
						protected void handleViewButton(ActionEvent actionEvent) {
							System.out.println("View button " + staff.getFullName());

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
