package com.example.csia.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import javax.swing.*;

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

    public void onClickCheckCredentials(ActionEvent actionEvent) {}

    public void onClickSendToForgot(ActionEvent actionEvent) {}
}
