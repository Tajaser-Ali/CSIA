package com.example.csia.controllers;

import com.example.csia.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.csia.models.Chemical;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class AdminSearch_Controller {

    @FXML
    private TextField chemicalSearch;

    @FXML
    private Button searchButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Label welcomeUser;

    @FXML
    private MenuButton searchSelector;

    @FXML
    private MenuItem hCodeSelection;

    @FXML
    private MenuItem idSelection;

    @FXML
    private MenuItem nameSelection;

    @FXML
    private CheckBox editToggler;

    @FXML
    private Button backButton;

    @FXML
    private Label statusLabel;

    private List<Chemical> allChemicals;

    public void onClickLogout(ActionEvent event) {
        SceneManager.switchScene("login.fxml", "Login");
    }

    @FXML
    public void onClickSearch(ActionEvent event) {
        String query = chemicalSearch.getText().trim();

        if (query.isEmpty()) {
            statusLabel.setText("Please enter a chemical name or HCode.");
            return;
        }

        boolean found = false;

        for (Chemical chem : allChemicals) {
            if (chem.getName().equalsIgnoreCase(query) || chem.getHCode().equalsIgnoreCase(query)) {
                found = true;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/csia/ChemicalDetails.fxml"));

                    Parent root = loader.load();

                    ChemicalDetails_Controller controller = loader.getController();
                    controller.setChemical(chem);

                    Scene scene = new Scene(root);
                    Stage stage = (Stage) searchButton.getScene().getWindow();
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        if (!found) {
            statusLabel.setText("Chemical not found.");
        }
    }


    public void onClickGoBack(ActionEvent event) {
        SceneManager.switchScene("adminDashboard.fxml", "Admin Dashboard");
    }

    public void selectHCode(ActionEvent event) {
        searchSelector.setText("H-Code");
    }

    public void selectID(ActionEvent event) {
        searchSelector.setText("ID");
    }

    public void selectName(ActionEvent event) {
        searchSelector.setText("Name");
    }
}
