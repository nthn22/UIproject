package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class PrimaryController {

    // Store today's tasks in static list so they persist between views
    private static final List<String> todayTasksList = new ArrayList<>();


    @FXML
    private VBox taskContainer;

    @FXML
    private VBox todayTaskBox = new VBox(5);  


    @FXML
    private void initialize() {
        // Load static groups
        addTaskGroup("Tomorrow (May 8)", new String[]{"Nothing to do!"});
        addTaskGroup("May 9", new String[]{"Buy groceries", "Call mom"});

        // Populate today's tasks from list
        TitledPane todayGroup = new TitledPane();
        todayGroup.setText("Today (May 7) ▼");
        todayGroup.setContent(todayTaskBox);
        todayGroup.setExpanded(false);
        taskContainer.getChildren().add(0, todayGroup); // Add at top

        // Load pre-added items
        for (String task : todayTasksList) {
            CheckBox cb = createInteractiveCheckbox(task);
            todayTaskBox.getChildren().add(cb);;
        }
    }

    private void addTaskGroup(String dateLabel, String[] tasks) {
        TitledPane group = new TitledPane();
        group.setText(dateLabel + " \u25BC");
    
        VBox box = new VBox(5);
        for (String task : tasks) {
            CheckBox checkBox = createInteractiveCheckbox(task);
            box.getChildren().add(checkBox);
        }
    
        group.setContent(box);
        group.setExpanded(false);
        taskContainer.getChildren().add(group);
    }

    public void addTaskToToday(String taskText) {
        todayTasksList.add(taskText); // Persist the task
        if (todayTaskBox != null) {
            CheckBox task = createInteractiveCheckbox(taskText);
            todayTaskBox.getChildren().add(new CheckBox(taskText));
       }
    }

    @FXML
    private void showCalendar() {
        App.showView("calendar.fxml", "Calendar View");
    }

    @FXML
    private void showAddTask() {
        App.showView("secondary.fxml", "Add Task");
    }

    @FXML
    private void logCompletedTasks() {
        System.out.println("✅ Completed tasks:");
        for (var node : todayTaskBox.getChildren()) {
            if (node instanceof CheckBox cb && cb.isSelected()) {
            System.out.println("- " + cb.getText());
            }
        }
    }

    private CheckBox createInteractiveCheckbox(String text) {
        CheckBox checkBox = new CheckBox(text);
        checkBox.setStyle("-fx-font-size: 14px;");
        checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                checkBox.setStyle("-fx-font-size: 14px; -fx-text-fill: gray; -fx-strikethrough: true;");
            } else {
                checkBox.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-strikethrough: false;");
            }
        });
        return checkBox;
    }
}
