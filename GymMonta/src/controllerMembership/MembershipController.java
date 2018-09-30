package controllerMembership;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.OpenWindow;
import controller.LoginController;
import dao.MembershipDao;
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
import model.Membership;
import viewCommon.Actions;

public class MembershipController implements Initializable {

	boolean search = false;
	static int ind = 0;
	String chemin = "";
	String title = "";
	Stage primaryStage = new Stage();
	public static ObservableList<Membership> membershipList = FXCollections.observableArrayList();
	public static ArrayList<Membership> membershiparray = new ArrayList<Membership>();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField membershipSearch;

	@FXML
	private Label lblTotalDrug;

	@FXML
	private Label lblShowingDrug;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnPrev, addmembership;

	@FXML
	private TableView<Membership> membershipTable;

	@FXML
	private TableColumn<Membership, String> clnName, clnPeriod, clnFee;

	@FXML
	private TableColumn clmAction;

	@FXML
	void AddMembershipOnAction(ActionEvent event) {
		chemin = "/viewMembership/AddMembership.fxml";
		title = "Add Member Page";
		new OpenWindow(primaryStage, chemin, title);

	}

	@FXML
	void SearchOnAction(ActionEvent event) {

	}

	@FXML
	void btnSearchOnAction(ActionEvent event) {
		if (membershipSearch.getText().length() == 0 || membershipSearch.getText() == null) {
			loadMembership();
		} else {
			search = true;
			search(membershipSearch.getText());

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
			addmembership.setVisible(false);
		}
		if (search == false)
			loadMembership();

	}

	private void editMembership(Membership membership) {

		title = "Edit Member Page";
		chemin = "/viewMembership/EditMembership.fxml";
		FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/viewMembership/EditMembership.fxml"));

		Parent root;
		try {
			root = fXMLLoader.load();
			EditMembershipController controller = fXMLLoader.getController();
			controller.membershipName.setText(membership.getName());
			// controller.dateofbirth;
			controller.periode.setText(membership.getPeriod());
			controller.signUpFee.setText(membership.getFee());
			// controller.password.setText(member.getPassword());
			// controller.email.setText(member.getEmail());

			// controller.section.setValue(value);
			// controller.membership.setValue(value);

			// controller.setDrugId(drug.getId());
			// controller.taNote.setText(drug.getNote());
			// controller.tfTradeName.setText(drug.getName());
			// controller.tfGenericName.setText(drug.getGenricName());
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Edit Membership Page");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadMembership() {

		membershiparray.clear();
		membershipList.clear();
		MembershipDao dao = new MembershipDao();
		try {
			membershiparray = dao.lectureFichierC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		membershipList.addAll(membershiparray);
		clnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		clnFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
		clnPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
		// clnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		// clnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		// clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		// clmSections .setCellFactory(value);
		// clmMembership.setCellFactory(value);
		clmAction.setCellFactory(action);
		// clnUsername.setCellValueFactory(cellData -> cellData.getValue().getFullName()
		// );
		// String fullName, username, password, gender, dateofbirth, phone, email;

		membershipTable.setItems(membershipList);
	}

	private void deleteMembership(String name) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete Member ?");
		alert.setHeaderText("Delete " + name + " ?");
		alert.setContentText("Are you sure to delete this Membership ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			int i = 0;
			while (name != membershiparray.get(i).getName())
				i++;
			membershipList.remove(i);
			membershiparray.remove(i);
			MembershipDao dao = new MembershipDao();
			try {
				dao.AjoutFichierC(membershiparray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void search(String name) {
		membershiparray.clear();
		membershipList.clear();
		MembershipDao dao = new MembershipDao();
		try {
			membershiparray = dao.lectureFichierC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < membershiparray.size(); i++) {

			if (membershiparray.get(i).getName().equals(name))
				membershipList.add(membershiparray.get(i));
		}

		clnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		clnFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
		clnPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
		// clnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		// clnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		// clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		// clmSections .setCellFactory(value);
		// clmMembership.setCellFactory(value);
		clmAction.setCellFactory(action);
		// clnUsername.setCellValueFactory(cellData -> cellData.getValue().getFullName()
		// );
		// String fullName, username, password, gender, dateofbirth, phone, email;

		membershipTable.setItems(membershipList);

	}

	Callback<TableColumn<Membership, String>, TableCell<Membership, String>> action = (
			TableColumn<Membership, String> param) -> {
		final TableCell<Membership, String> cell = new TableCell<Membership, String>() {
			@Override
			public void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
					setText(null);
				} else {
					Actions actions = new Actions() {
						Membership membership = getTableView().getItems().get(getIndex());
						int index = getIndex();

						@Override
						protected void handleEditButton(ActionEvent actionEvent) {
							System.out.println("Edit button " + membership.getName());
							ind = index;

							editMembership(membership);
						}

						@Override
						protected void handleDeleteButton(ActionEvent actionEvent) {

							System.out.println("Delete button " + membership.getName());
							deleteMembership(membership.getName());

						}

						@Override
						protected void handleViewButton(ActionEvent actionEvent) {
							System.out.println("View button " + membership.getName());

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
