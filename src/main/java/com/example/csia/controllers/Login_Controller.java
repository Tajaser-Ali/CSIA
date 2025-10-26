package com.example.csia.controllers;

import com.example.csia.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;


public class Login_Controller {

    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button forgotButton;

    @FXML
    private Label statusLabel;

    public void onClickCheckCredentials(ActionEvent actionEvent) {
        String id = idField.getText();
        String password = passwordField.getText();

        if (id.equals("admin") && password.equals("admin123")) {
            // Admin login → go to AdminDashboard
            SceneManager.switchScene("admindashboard.fxml", "Admin Dashboard");
        } else if (id.equals("user") && password.equals("user123")) {
            // User login → go to UserSearch
            SceneManager.switchScene("usersearch.fxml", "User Search");
        } else {
            // Invalid login
            statusLabel.setText("Invalid ID or password");
        }
    }

    public void onClickSendToForgot(ActionEvent actionEvent) {
        SceneManager.switchScene("forgot.fxml", "Forgot ID/Password");
    }
}
