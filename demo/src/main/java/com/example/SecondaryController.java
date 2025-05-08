package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SecondaryController {

    @FXML
    private TextField taskField;

    @FXML
    private void submitTask() {
        String taskText = taskField.getText();
        if (taskText != null && !taskText.isEmpty()) {
            App.addNewTask(taskField.getText());
            taskField.clear();
        }
        App.showView("primary.fxml", "Task Manager");
    }

    @FXML
    private void goBack() {
        App.showView("primary.fxml", "Task Manager");
    }
}