package controller;

import javafx.scene.control.Label;

public class DashboardController {

    public Label lblDashboardWelcome;

    public void transferData(String name){
        lblDashboardWelcome.setText(name);
    }
}
