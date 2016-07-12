package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        //primaryStage.setTitle("Semester Project Main Screen");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();


        window = primaryStage;
        window.setTitle("Semester Project Main Screen");
        //System.out.println("hello");

        VBox vBox = new VBox();
        vBox.setMinWidth(vBox.getMaxWidth());
        vBox.setMinHeight(vBox.getMaxHeight());
        vBox.setStyle("-fx-background-color:#000000");

        //--------------------------------------navigation panel------------------------------------
        GridPane navigationLayout = new GridPane();
        navigationLayout.setPadding(new Insets(10, 10, 10, 10));
        navigationLayout.setVgap(8);
        navigationLayout.setHgap(10);

        // Label
        Label label = new Label("Navigation Panel");

        // list view
        ListView<String> list = new ListView<String>();
        list.getItems().addAll("Manage Employees", "Manage Tasks", "Employee Task Screen", "Employee Task Summary") ;
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(list, 0, 1);
        navigationLayout.getChildren().addAll(label, list);
        //-------------------------------navigation panel ends--------------------------------------------

        BorderPane pane = new BorderPane();

        //adding a gridlayout in the center.
        pane.setCenter(ManageEmployees.getExample());
        pane.getStylesheets().add(this.getClass().getResource("style.css").toString());
        //pane.setCenter(null);
        //pane.setCenter(ManageEmployees.getExample());
        //pane.getCsetCenter(null);
        //add vertical box on right side
        pane.setRight(vBox);
        Button okButton = new Button("Ok");
        okButton.setOnAction(e -> {
            System.out.println("opened window: "+list.getSelectionModel().getSelectedIndex());
            pane.setCenter(null);

            switch(list.getSelectionModel().getSelectedIndex())
            {
                case 0:
                    pane.setCenter(ManageEmployees.getExample());
                    break;
                case 1:
                    pane.setCenter(ManageTasks.getExample());
                    break;
                case 2:
                    pane.setCenter(EmployeeTaskScreen.getExample());
                    break;
                case 3:
                    pane.setCenter(EmployeeTaskSummary.getExample());
                    break;
                default:
                    pane.setCenter(ManageEmployees.getExample());
                    break;
            }
        });

        vBox.getChildren().addAll(navigationLayout,okButton);

        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #020297");
        menuBar.setMinWidth(1200);
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("Source");
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        MenuItem menuItemOpen = new MenuItem("Open");
        MenuItem menuItemSave = new MenuItem("Save");


        // add style by class name
        menuItemOpen.getStyleClass().add("openMenuItem");
        // add style by id
        menuItemSave.setId("saveMenuItem");


        menuFile.getItems().add(menuItemOpen);
        menuFile.getItems().add(menuItemSave);

        HBox hBox = new HBox();
        hBox.setFillHeight(true);
        hBox.getChildren().addAll(menuBar);
        pane.setTop(hBox);

        Scene scene = new Scene(pane, 1200, 500);
        window.setScene(scene);
        window.show();

        //System.out.println(vBox.getChildren().get(1));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
