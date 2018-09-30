package controllerMember;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.OpenWindow;
import controller.LoginController;
import dao.MemberDao;

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
import model.Member;
import viewCommon.Actions;

public class MemberController implements Initializable {

	boolean search = false;
	static int ind = 0;
	String chemin = "";
	String title = "";
	Stage primaryStage = new Stage();
	public static ObservableList<Member> memberList = FXCollections.observableArrayList();
	public static ArrayList<Member> memberarray = new ArrayList<Member>();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField memberSearch;

	@FXML
	private Label lblTotalDrug;

	@FXML
	private Label lblShowingDrug;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnPrev;

	@FXML
	private Button addmember;

	@FXML
	private TableView<Member> sectionTable;

	@FXML
	private TableColumn<Member, String> clnUsername, clnFullName, clnDatebirth, clnGender, clnPhone, clnEmail,
			clmSections, clmMembership;
	@FXML
	private TableColumn clmAction;

	@FXML
	void AddMemberOnAction(ActionEvent event) {
		chemin = "/viewMember/AddMember.fxml";
		title = "Add Member Page";
		new OpenWindow(primaryStage, chemin, title);

	}

	@FXML
	void SearchOnAction(ActionEvent event) {

	}

	@FXML
	void btnSearchOnAction(ActionEvent event) {
		if (memberSearch.getText() == null || memberSearch.getText().length() == 0)
			loadMembers();
		else {
			search(memberSearch.getText());
			search = true;
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
			addmember.setVisible(false);
		}
		if (search == false)
			loadMembers();
	}

	private void editMember(Member member) {

		title = "Edit Member Page";
		chemin = "/viewMember/EditMember.fxml";
		FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/viewMember/EditMember.fxml"));

		Parent root;
		try {
			root = fXMLLoader.load();
			EditMemberController controller = fXMLLoader.getController();
			controller.fullname.setText(member.getFullName());
			controller.dateofbirth.setValue(LocalDate.now());
			controller.phone.setText(member.getPhone());
			controller.username.setText(member.getUsername());
			controller.password.setText(member.getPassword());
			controller.email.setText(member.getEmail());

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

	private void loadMembers() {

		memberarray.clear();
		memberList.clear();

		MemberDao dao = new MemberDao();
		try {
			memberarray = dao.lectureFichier();
			System.out.println(memberarray.size() + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * for(int i =0;i<memberarray.size();i++) {
		 * memberList.add(i,memberarray.get(i));
		 * System.out.println(memberarray.get(i).getFullName()); }
		 */
		memberList.addAll(memberarray);

		clnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		clnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		clnDatebirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
		clnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		clnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		clmSections.setCellValueFactory(new PropertyValueFactory<>("section"));
		clmMembership.setCellValueFactory(new PropertyValueFactory<>("membership"));

		// clmSections .setCellFactory(value);
		// clmMembership.setCellFactory(value);
		clmAction.setCellFactory(action);
		// clnUsername.setCellValueFactory(cellData -> cellData.getValue().getFullName()
		// );
		// String fullName, username, password, gender, dateofbirth, phone, email;

		sectionTable.setItems(memberList);
	}

	private void deleteMember(String name) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete Member ?");
		alert.setHeaderText("Delete " + name + " ?");
		alert.setContentText("Are you sure to delete this Member ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			int i = 0;
			while (name != memberarray.get(i).getFullName())
				i++;
			memberList.remove(i);
			memberarray.remove(i);
			MemberDao dao = new MemberDao();
			try {
				dao.AjoutFichierC(memberarray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void search(String name) {
		memberList.clear();
		memberarray.clear();
		MemberDao dao = new MemberDao();
		try {
			memberarray = dao.lectureFichier();
			System.out.println(memberarray.size() + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < memberarray.size(); i++) {

			if (memberarray.get(i).getFullName().equals(name))
				memberList.add(memberarray.get(i));
		}
		// memberList.addAll(memberarray);

		clnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		clnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		clnDatebirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
		clnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		clnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		clmSections.setCellValueFactory(new PropertyValueFactory<>("section"));
		clmMembership.setCellValueFactory(new PropertyValueFactory<>("membership"));
		// clmSections .setCellFactory(value);
		// clmMembership.setCellFactory(value);
		clmAction.setCellFactory(action);
		// clnUsername.setCellValueFactory(cellData -> cellData.getValue().getFullName()
		// );
		// String fullName, username, password, gender, dateofbirth, phone, email;

		sectionTable.setItems(memberList);

	}

	Callback<TableColumn<Member, String>, TableCell<Member, String>> action = (TableColumn<Member, String> param) -> {
		final TableCell<Member, String> cell = new TableCell<Member, String>() {
			@Override
			public void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
					setText(null);
				} else {
					Actions actions = new Actions() {
						Member member = getTableView().getItems().get(getIndex());

						int index = getIndex();

						@Override
						protected void handleEditButton(ActionEvent actionEvent) {
							System.out.println("Edit button " + member.getFullName());
							ind = index;
							System.out.println("index:  " + index);
							System.out.println("index:  " + ind);

							editMember(member);
						}

						@Override
						protected void handleDeleteButton(ActionEvent actionEvent) {

							System.out.println("Delete button " + member.getFullName());

							deleteMember(member.getFullName());

						}

						@Override
						protected void handleViewButton(ActionEvent actionEvent) {

							System.out.println("View button " + member.getFullName());

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
