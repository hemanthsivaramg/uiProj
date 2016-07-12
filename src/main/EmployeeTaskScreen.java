package main;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.time.LocalDate;

public class EmployeeTaskScreen {
	static TableView<Task> table = new TableView<Task>(getTask());

	@SuppressWarnings("unchecked")
	public static GridPane getExample() {
		GridPane gridLayout = new GridPane();
		gridLayout.setPadding(new Insets(10, 10, 10, 10));
		gridLayout.setVgap(8);
		gridLayout.setHgap(10);

		// Label
		Label label = new Label("Employee Task Screen");
		table.setEditable(true);

		// table view

		TableColumn<Task, Integer> t_idColumn = new TableColumn<Task, Integer>("Task Id");
		TableColumn<Task, String> t_nameColumn = new TableColumn<Task, String>("Task Name");
		TableColumn<Task, LocalDate> t_assignedDateColumn = new TableColumn<Task, LocalDate>("Assigned date");
		TableColumn<Task, LocalDate> t_completedDateColumn = new TableColumn<Task, LocalDate>("Completed date");
		TableColumn<Task, Double> t_hoursColumn = new TableColumn<Task, Double>("Hours");
		TableColumn<Task, Integer> t_empIdColumn = new TableColumn<Task, Integer>("Emp Id");

		// options
		t_idColumn.setMinWidth(60);
		t_nameColumn.setMinWidth(200);
		t_assignedDateColumn.setMinWidth(150);
		t_completedDateColumn.setMinWidth(150);
		t_hoursColumn.setMinWidth(70);
		t_empIdColumn.setMinWidth(100);

		t_idColumn.setCellValueFactory(new PropertyValueFactory<Task, Integer>("task_id"));
		t_nameColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task_name"));
		t_assignedDateColumn.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("task_assigned_date"));
		t_completedDateColumn.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("task_completed_date"));
		t_hoursColumn.setCellValueFactory(new PropertyValueFactory<Task, Double>("task_hours_worked"));
		t_empIdColumn.setCellValueFactory(new PropertyValueFactory<Task, Integer>("task_FK_employee_id"));

		table.setEditable(true);
		Callback<TableColumn<Task, Double>, TableCell<Task, Double>> cellFactoryDouble = new Callback<TableColumn<Task, Double>, TableCell<Task, Double>>() {
			public TableCell<Task, Double> call(TableColumn<Task, Double> p) {
				return new EditingCellTaskDouble();
			}
		};
		
		Callback<TableColumn<Task, LocalDate>, TableCell<Task, LocalDate>> cellFactoryLocalDate = new Callback<TableColumn<Task, LocalDate>, TableCell<Task, LocalDate>>() {
			public TableCell<Task, LocalDate> call(TableColumn<Task, LocalDate> p) {
				return new EditingCellTaskLocalDate();
			}
		};

		t_hoursColumn.setCellFactory(cellFactoryDouble);
		t_hoursColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, Double>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Task, Double> t) {
				((Task) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setTask_hours_worked(t.getNewValue());
			}
		});
		
		t_completedDateColumn.setCellFactory(cellFactoryLocalDate);
		t_completedDateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, LocalDate>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Task, LocalDate> t) {
				((Task) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setTask_completed_date(t.getNewValue());
			}
		});
		
		table.getColumns().addAll(t_idColumn, t_nameColumn, t_assignedDateColumn, t_completedDateColumn, t_hoursColumn,
				t_empIdColumn);
		table.setMinWidth(800);

		GridPane.setConstraints(label, 0, 0);
		GridPane.setConstraints(table, 0, 1);

		gridLayout.getChildren().addAll(label, table);

		return gridLayout;
	}
	
	public static void delButtonAction(){
		ObservableList<Task> selected,all;
		all= table.getItems();
		selected= table.getSelectionModel().getSelectedItems();
		
		selected.forEach(all::remove);
	}
	
	public static ObservableList<Task> getTask() {
		ObservableList<Task> items = DbOperations.retrieveResultsFromTask();
		return items;
	}
}
