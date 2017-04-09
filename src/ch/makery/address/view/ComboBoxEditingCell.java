package ch.makery.address.view;

import ch.makery.address.model.Person;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;

public class ComboBoxEditingCell extends TableCell<Person, String> {

	private ComboBox<String> comboBox;

	ComboBoxEditingCell() {
	}

	@Override
	public void startEdit() {
		if (!isEmpty()) {
			super.startEdit();
			createComboBox();
			setText(null);
			setGraphic(comboBox);
		}
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();

		setText(comboBox.getSelectionModel().getSelectedItem().toString());
		setGraphic(null);
	}

	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (comboBox != null) {
					comboBox.setValue(getString());
				}
				setText(comboBox.getSelectionModel().getSelectedItem().toString());
				setGraphic(comboBox);
			} else {
				setText(comboBox.getSelectionModel().getSelectedItem().toString());
				setGraphic(null);
			}
		}
	}

	private void createComboBox() {
		comboBox = new ComboBox<>();
		comboBoxConverter(comboBox);
		comboBox.valueProperty().set(getString());
		comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		comboBox.setOnAction((e) -> {
			System.out.println("Committed: " + comboBox.getSelectionModel().getSelectedItem());
			commitEdit(comboBox.getSelectionModel().getSelectedItem());
		});
		// comboBox.focusedProperty().addListener((ObservableValue<? extends
		// Boolean> observable, Boolean oldValue, Boolean newValue) -> {
		// if (!newValue) {
		// commitEdit(comboBox.getSelectionModel().getSelectedItem());
		// }
		// });
	}

	private void comboBoxConverter(ComboBox<String> comboBox) {
		// Define rendering of the list of values in ComboBox drop down.
		comboBox.setCellFactory((c) -> {
			return new ListCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(comboBox.getSelectionModel().getSelectedItem().toString());
					}
				}
			};
		});
	}

	private String getString() {
		return getItem() == null ? new String("") : getItem();
	}
}
