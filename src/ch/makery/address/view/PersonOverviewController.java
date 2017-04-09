package ch.makery.address.view;

import ch.makery.address.EditingCell;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;

public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private TableColumn<Person, Boolean> active;
	@FXML
	private TableColumn<Person, Boolean> active2;

	// Reference to the main application.
	private MainApp mainApp;

	private ObservableList<Person> personData = FXCollections.observableArrayList();

	public PersonOverviewController() {
	}

	@FXML
	private void initialize() {
		ObservableList<String> levelChoice = FXCollections.observableArrayList(new String("Bla"), new String("Blo"));

		personTable.setEditable(true);
		personData.add(new Person("Hans", "Muster", false, false));
		personData.add(new Person("Ruth", "Mueller", false, false));
		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		firstNameColumn.setCellFactory(e -> new EditingCell());

		firstNameColumn.setEditable(true);
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		lastNameColumn.setCellFactory(ComboBoxTableCell.forTableColumn(levelChoice));
		lastNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> t) {
				((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
			};
		});
		active.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
		active.setCellFactory(param -> new BooleanCell<>());
		active2.setCellValueFactory(cellData -> cellData.getValue().active2Property());
		active2.setCellFactory(param -> new BooleanCell<>());
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		personTable.setItems(personData);
	}

	@FXML
	void action(ActionEvent event) {
		System.out.println("Quero validar se os valores foram alterados");
		personData.forEach(f -> System.out.println(f));
	}

	class BooleanCell<T> extends TableCell<Person, T> {
		private CheckBox checkBox;
		private ObservableValue<T> ov;

		public BooleanCell() {
			checkBox = new CheckBox();
			this.setGraphic(checkBox);
			this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			this.setEditable(true);
		}

		@Override
		public void updateItem(T item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				setGraphic(checkBox);
				if (ov instanceof BooleanProperty) {
					checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
				}
				ov = getTableColumn().getCellObservableValue(getIndex());
				if (ov instanceof BooleanProperty) {
					checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
				}
			}
		}

	}
}