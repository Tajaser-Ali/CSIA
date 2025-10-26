package com.example.csia.controllers;

import com.example.csia.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class Forgot_Controller {

    @FXML
    private TextField emailField;

    @FXML
    private Button emailButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Button loginButton;

    public void onClickSendEmail(ActionEvent actionEvent) {
        String email = emailField.getText().trim();

        if(email.isEmpty()) {
            statusLabel.setText("Please enter your email");
        } else {
            statusLabel.setText("If this email exists, credentials will be sent.");
        }
    }

    public void onClickSendToLogin(ActionEvent actionEvent) {
        SceneManager.switchScene("login.fxml", "Login");
    }
}
