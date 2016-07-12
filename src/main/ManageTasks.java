package main;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.*;
import java.util.*;
public class ManageTasks {
	static TextField taskId = new TextField();
	static TextField taskName = new TextField();
	static DatePicker taskAssigned = new DatePicker();
	static TextField empId = new TextField();
	static TableView<Task> table = new TableView<Task>(getTasks());

	@SuppressWarnings("unchecked")
	public static GridPane getExample() {
		GridPane gridLayout = new GridPane();
		gridLayout.setPadding(new Insets(10, 10, 10, 10));
		gridLayout.setVgap(8);
		gridLayout.setHgap(10);

		// Label
		Label label = new Label("Manage Tasks:");

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

		//
		t_idColumn.setCellValueFactory(new PropertyValueFactory<Task, Integer>("task_id"));
		t_nameColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task_name"));
		t_assignedDateColumn.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("task_assigned_date"));
		t_completedDateColumn.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("task_completed_date"));
		t_hoursColumn.setCellValueFactory(new PropertyValueFactory<Task, Double>("task_hours_worked"));
		t_empIdColumn.setCellValueFactory(new PropertyValueFactory<Task, Integer>("task_FK_employee_id"));


		//add all the columns to the table
		table.getColumns().addAll(t_idColumn, t_nameColumn, t_assignedDateColumn, t_completedDateColumn, t_hoursColumn, t_empIdColumn);
		table.setMinWidth(800);


		// Fields
		taskId.setPromptText("Task Id");
		taskName.setPromptText("Task Name");
		taskAssigned.setPromptText("Assign Date");
		empId.setPromptText("Emp Id");

		Button addRow = new Button("Add");
		addRow.setOnAction(e -> addButtonAction());

		Button delRow = new Button("Del");
		delRow.setOnAction(e -> delButtonAction());

		HBox hbox = new HBox();
		hbox.getChildren().addAll(taskId,taskName, taskAssigned, empId, addRow, delRow);



		GridPane.setConstraints(label, 0, 0);
		GridPane.setConstraints(table, 0, 1);
		//GridPane.setConstraints(button, 0, 2);
		GridPane.setConstraints(hbox, 0, 3, 2, 1);
		gridLayout.getChildren().addAll(label, table, hbox);
		return gridLayout;
	}

	private static void addButtonAction(){

		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		LocalDate aDate = taskAssigned.getValue();
		String aStr = aDate.format(formatter);
		//System.out.print(aStr);
		SimpleDateFormat sdf;Date myDate;
		try{
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			myDate = sdf.parse(aStr);
			DbOperations.insertARowIntoTasks(Integer.parseInt(taskId.getText()), taskName.getText(),myDate ,Integer.parseInt(empId.getText()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		table.getItems().add(new Task(Integer.parseInt(taskId.getText()), taskName.getText(), LocalDate.parse(aStr, DateTimeFormatter.ISO_DATE),Integer.parseInt(empId.getText())));

	}

	private static void delButtonAction(){
		ObservableList<Task> selected,all;
		all= table.getItems();
		selected= table.getSelectionModel().getSelectedItems();
		selected.forEach(all::remove);
	}

	private static ObservableList<Task> getTasks() {
		ObservableList<Task> items = DbOperations.retrieveResultsFromTask();
		return items;
	}
}
