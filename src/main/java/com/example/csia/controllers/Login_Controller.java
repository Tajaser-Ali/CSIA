package com.example.csia.controllers;

import com.example.csia.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import com.example.csia.utils.DatabaseHandler;
import java.sql.*;


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

        if (id.equals("admin") && password.equals("123one")) {
            // Admin login → go to AdminDashboard
            SceneManager.switchScene("admindashboard.fxml", "Admin Dashboard");
        } else {
            // Check user credentials from database
            DatabaseHandler dbHandler = new DatabaseHandler(); // create instance
            try (Connection conn = dbHandler.getConnection()) { // use instance
                String query = "SELECT * FROM `User` WHERE ID = ? AND Password = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, id);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // User found → go to UserSearch
                    SceneManager.switchScene("usersearch.fxml", "User Search");
                } else {
                    // Invalid login
                    statusLabel.setText("Invalid ID or password");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                statusLabel.setText("Database error");
            }

        }
    }


    public void onClickSendToForgot(ActionEvent actionEvent) {
        SceneManager.switchScene("forgot.fxml", "Forgot ID/Password");
    }
}
