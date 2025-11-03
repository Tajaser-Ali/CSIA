package com.example.csia.controllers;

import com.example.csia.utils.DatabaseHandler;
import com.example.csia.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.csia.models.Chemical;

public class Editor_Controller {

    @FXML
    private Button backButton;

    @FXML
    private Button addChemButton;

    @FXML
    private Button deleteChemButton;

    @FXML
    private Button addUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private TextField nameFieldChem;

    @FXML
    private TextField nameFieldUser;

    @FXML
    private TextField hCodeField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField idFieldChem;

    @FXML
    private TextField idFieldUser;

    @FXML
    private TextField disposalField;

    @FXML
    private Label statusLabel;

    private final DatabaseHandler db = new DatabaseHandler();

    public void onClickGoBack(ActionEvent actionEvent) {
        SceneManager.switchScene("admindashboard.fxml", "Admin Dashboard");
    }

    public void onClickAddChem(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idFieldChem.getText().trim());
            String name = nameFieldChem.getText().trim();
            String hCode = hCodeField.getText().trim();
            String disposal = disposalField.getText().trim();


            if (name.isEmpty() || hCode.isEmpty() || disposal.isEmpty()) {
                statusLabel.setText("Please fill all chemical fields.");
                return;
            }

            Chemical chem = new Chemical(id, name, hCode, disposal);
            db.insertChemical(chem);
            statusLabel.setText("Chemical added successfully.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid chemical ID.");
        }
    }

    public void onClickDeleteChem(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idFieldChem.getText().trim());
            db.deleteChemical(id);
            statusLabel.setText("Chemical deleted successfully.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid chemical ID.");
        }
    }

    public void onClickAddUser(ActionEvent actionEvent) {
        String id = idFieldUser.getText().trim();
        String name = nameFieldUser.getText().trim();
        String password = passwordField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please fill all user fields.");
            return;
        }
        db.insertUser(id, name, password);
        statusLabel.setText("User added successfully.");
    }

    public void onClickDeleteUser(ActionEvent actionEvent) {
        String id = idFieldUser.getText().trim();
        if (id.isEmpty()) {
            statusLabel.setText("Please enter a user ID.");
            return;
        }

        db.deleteUser(id);
        statusLabel.setText("User deleted successfully.");
    }
}
