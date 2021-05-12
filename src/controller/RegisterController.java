package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {
    public JFXTextField txtNameRegister;
    public JFXTextField txtUsernameRegister;
    public JFXTextField txtAddressRegister;
    public JFXTextField txtPasswordRegister;
    public JFXTextField txtConfirmPasswordRegister;
    public AnchorPane root;

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String name = txtNameRegister.getText();
        String userName = txtUsernameRegister.getText();
        String address = txtAddressRegister.getText();
        String password = txtPasswordRegister.getText();
        String confirmPassword = txtConfirmPasswordRegister.getText();

        String addUser = "INSERT INTO userdetails(name,address,userName,password,confirmPassword) VALUES(?,?,?,?,?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preStmt = connection.prepareStatement(addUser);
            preStmt.setObject(1,name);
            preStmt.setObject(2,address);
            preStmt.setObject(3,userName);
            preStmt.setObject(4,password);
            preStmt.setObject(5,confirmPassword);
            int res = preStmt.executeUpdate();
            if (res > 0){
                txtNameRegister.clear();
                txtAddressRegister.clear();
                txtUsernameRegister.clear();
                txtPasswordRegister.clear();
                txtConfirmPasswordRegister.clear();
                new Alert(Alert.AlertType.INFORMATION,"User has been successfully inserted", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Something gone wrong, Please try again!", ButtonType.OK).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void lblLogInIfHaveAnAccount(MouseEvent mouseEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.
                load(this.getClass().
                        getResource("/view/Login.fxml"))));
        stage.centerOnScreen();
    }


}
