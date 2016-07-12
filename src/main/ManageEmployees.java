package main;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ManageEmployees {
	static TextField empId = new TextField();
	static TextField empName = new TextField();
	//static DatePicker lastUpdateDatePicker = new DatePicker();
	
	static TextField empMgrId = new TextField();
	static TableView<Employee> table = new TableView<Employee>(getEmployees());

	@SuppressWarnings("unchecked")
	public static GridPane getExample() {
		GridPane gridLayout = new GridPane();
		gridLayout.setPadding(new Insets(10, 10, 10, 10));
		gridLayout.setVgap(8);
		gridLayout.setHgap(10);

		// Label
		Label label = new Label("Manage employees:");

		// table view

		TableColumn<Employee, Integer> empIdColumn = new TableColumn<Employee,Integer>("Emp Id");
		TableColumn<Employee, String> empNameColumn = new TableColumn<Employee,String>("Emp Name");
		TableColumn<Employee, Integer> empMgrIDColumn = new TableColumn<Employee,Integer>("Emp Mgr Id");

		// options
		empIdColumn.setMinWidth(60);
		empNameColumn.setMinWidth(200);
		empMgrIDColumn.setMinWidth(100);
		empIdColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("emp_id"));
		empNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("emp_name"));
		empMgrIDColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("emp_mgr_id"));
		//setting the table to editable
		table.setEditable(true);
		
		//call back for employee int fields
		Callback<TableColumn<Employee, Integer>, TableCell<Employee, Integer>> cellFactoryInteger = new Callback<TableColumn<Employee, Integer>, TableCell<Employee, Integer>>() {
			public TableCell<Employee, Integer> call(TableColumn<Employee, Integer> p) {
				return new EditingCellEmployeeInteger();
			}
		};
		
		//editing emp id column
		empIdColumn.setCellFactory(cellFactoryInteger);
		empIdColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, Integer>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Employee, Integer> t) {
				((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setEmp_id(t.getNewValue());
			}
		});

		//call back function for employee string fields
		Callback<TableColumn<Employee, String>, TableCell<Employee, String>> cellFactoryString = new Callback<TableColumn<Employee, String>, TableCell<Employee, String>>() {
			public TableCell<Employee, String> call(TableColumn<Employee, String> p) {
				return new EditingCellEmployeeString();
			}
		};
		
		//editing emp name column
		empNameColumn.setCellFactory(cellFactoryString);
		empNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Employee, String> t) {
				((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setEmp_name(t.getNewValue());
			}
		});
		
		

		//editing emp id column
		empMgrIDColumn.setCellFactory(cellFactoryInteger);
		empMgrIDColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, Integer>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Employee, Integer> t) {
				((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setEmp_id(t.getNewValue());
			}		
		});
				
		table.getColumns().addAll(empIdColumn, empNameColumn, empMgrIDColumn);
		table.setMinWidth(360);

		// Fields
		empId.setPromptText("Employee Id");
		empName.setPromptText("Employee Name");
		//lastUpdateDatePicker.setPromptText("Employee Name");
		empMgrId.setPromptText("Manager Id");
		
		Button addRow = new Button("Add");
		addRow.setOnAction(e -> addButtonAction());

		Button delRow = new Button("Del");
		delRow.setOnAction(e -> delButtonAction());

		HBox hbox = new HBox();
		hbox.getChildren().addAll(empId,empName, empMgrId, addRow, delRow);



		GridPane.setConstraints(label, 0, 0);
		GridPane.setConstraints(table, 0, 1);
		GridPane.setConstraints(hbox, 0, 3, 2, 1);
		
		gridLayout.getChildren().addAll(label, table, hbox);

		return gridLayout;
	}

	public static void addButtonAction(){
		table.getItems().add(new Employee(Integer.parseInt(empId.getText()), empName.getText(), Integer.parseInt(empMgrId.getText())));
		DbOperations.insertARowIntoEmployee(Integer.parseInt(empId.getText()), empName.getText(), Integer.parseInt(empMgrId.getText()));
	}
	
	public static void delButtonAction(){
		ObservableList<Employee> selected,all;
		all= table.getItems();
		selected= table.getSelectionModel().getSelectedItems();
		//Employee empObj = (Employee) table.getSelectionModel().getSelectedItems();
		//System.out.println(empObj.getEmp_id());
		//System.out.println(table.getSelectionModel().getSelectedCells().get(0));
		//DbOperations.deleteARowFromEmployee(selected.g);
		selected.forEach(all::remove);
	}
	
	public static ObservableList<Employee> getEmployees() {
		ObservableList<Employee> items = DbOperations.retrieveResultsFromEmployee();

		return items;
	}
}
