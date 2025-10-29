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

        if (email.isEmpty()) {
            statusLabel.setText("Please enter your email.");
            return;
        }

        boolean hasAt = email.contains("@");
        boolean hasDot = email.contains(".");

        if (!hasAt && !hasDot) {
            statusLabel.setText("Invalid email.");
            return;
        }

        if (!hasAt) {
            statusLabel.setText("Invalid email. Missing '@' sign.");
            return;
        }

        String[] parts = email.split("@");
        if (parts.length != 2 || parts[0].isEmpty()) {
            statusLabel.setText("Invalid email. Missing text before '@'.");
            return;
        }

        String domainPart = parts[1];
        if (!domainPart.contains(".")) {
            statusLabel.setText("Invalid email. Missing domain (e.g. .com, .org, .co.uk).");
            return;
        }

        String[] domainSections = domainPart.split("\\.");
        if (domainSections.length < 2 || domainSections[0].isEmpty() || domainSections[domainSections.length - 1].isEmpty()) {
            statusLabel.setText("Invalid email. Incorrect domain format.");
            return;
        }

        statusLabel.setText("If this email exists, credentials will be sent.");
    }





    public void onClickSendToLogin(ActionEvent actionEvent) {
        SceneManager.switchScene("login.fxml", "Login");
    }
}
