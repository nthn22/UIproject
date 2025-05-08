// App.java
package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage primaryStage;
    private static PrimaryController primaryController;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showView("primary.fxml", "Task Manager");
        stage.show();
    }

    public static void showView(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
    
            // Always set controller if it's the primary view
            if ("primary.fxml".equals(fxml)) {
                primaryController = loader.getController();
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void addNewTask(String taskText) {
        if (primaryController != null) {
            primaryController.addTaskToToday(taskText);
        } else {
            System.out.println("PrimaryController is null. Cannot add task.");
        }
    }    
    
}
