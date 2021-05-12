package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    public JFXTextField txtLoginUserName;
    public AnchorPane rootLogin;
    public JFXPasswordField txtLoginPassword;

    public void txtLoginButton(ActionEvent actionEvent) throws IOException {
        String usrName = txtLoginUserName.getText();
        String uName = null;
        String password= null;
        String name = null;
        ResultSet res = null;

        String getUser = "SELECT * FROM userDetails WHERE username='"+usrName+"'";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            res = stmt.executeQuery(getUser);
            while (res.next()){
                name = res.getString("name");
                uName = res.getString("username");
                password = res.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (usrName.equals(uName) && password.equals(password)){
            txtLoginPassword.clear();
            txtLoginUserName.clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
            Parent root = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.transferData(name);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard Window");
            stage.show();


        }else{
            new Alert(Alert.AlertType.WARNING,"Password or Username is incorrect, Please try again!", ButtonType.OK).show();
        }
    }

    public void lblCreateAnAccount(MouseEvent mouseEvent) throws IOException {
        Stage stage= (Stage) rootLogin.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.
                load(this.getClass().
                        getResource("/view/Register.fxml"))));
        stage.centerOnScreen();
    }
}
