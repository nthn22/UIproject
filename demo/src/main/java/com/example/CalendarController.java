// CalendarController.java
package com.example;

import javafx.fxml.FXML;

public class CalendarController {
    @FXML
    private void goBack() {
        App.showView("primary.fxml", "Task Manager");
    }
}