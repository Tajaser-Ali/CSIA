package com.example.csia.controllers;

import com.example.csia.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.csia.models.Chemical;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.csia.utils.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @FXML
    private Label editLabel;

    private List<Chemical> allChemicals = new ArrayList<>();
    private ChemicalDetails_Controller chemicalDetailsController;

    public void onClickLogout(ActionEvent event) {
        SceneManager.switchScene("login.fxml", "Login");
    }

    @FXML
    public void onClickSearch(ActionEvent event) {
        String query = chemicalSearch.getText().trim();
        String selected = searchSelector.getText();

        // Validation
        if (selected.isEmpty() || selected.equals("Search By:")) {
            statusLabel.setText("Please select a search type.");
            return;
        }
        if (query.isEmpty()) {
            statusLabel.setText("Please enter a search value.");
            return;
        }

        DatabaseHandler db = new DatabaseHandler();
        Chemical chem = null;

        // Determine search type
        switch (selected) {
            case "ID":
                try {
                    int idQuery = Integer.parseInt(query);
                    chem = db.getChemicalById(idQuery);
                } catch (NumberFormatException e) {
                    statusLabel.setText("Please enter a valid numeric ID.");
                    return;
                }
                break;
            case "Name":
                chem = db.getChemicalByName(query);
                break;
            case "H-Code":
                if (!query.matches("^H[1-4].*")) {
                    statusLabel.setText("Invalid H-Code format. Must start with H1–H4.");
                    return;
                }
                chem = db.getChemicalByHCode(query);
                break;
        }

        if (chem != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/csia/ChemicalDetails.fxml"));
                Scene scene = new Scene(loader.load(), 600, 400);

                // Get controller reference
                chemicalDetailsController = loader.getController();
                chemicalDetailsController.setChemical(chem);

                // Immediately apply edit mode if checkbox is selected
                chemicalDetailsController.setEditMode(editToggler.isSelected());

                // Open the new window
                Stage otherStage = new Stage();
                otherStage.setScene(scene);
                otherStage.show();

            } catch (IOException e) {
                e.printStackTrace();
                statusLabel.setText("Failed to open chemical details window.");
            }
        } else {
            statusLabel.setText("Chemical not found.");
        }
    }



    public void onClickToggleEdit(ActionEvent event) {
        boolean isEditMode = editToggler.isSelected();

        if (isEditMode) {
            editLabel.setText("Edit mode enabled.");
            // enable editing fields, or pass a flag to the next scene
        } else {
            editLabel.setText("Edit mode disabled.");
            // disable editing fields
        }

        // pass edit mode to the ChemicalDetails_Controller
        // assuming you have a reference to the controller instance:
        if (chemicalDetailsController != null) {
            chemicalDetailsController.setEditMode(isEditMode);
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

    public void initialize() {
        loadChemicals();
    }

    private void loadChemicals() {
        DatabaseHandler db = new DatabaseHandler();
        try {
        ResultSet rs = db.getConnection().createStatement().executeQuery("SELECT * FROM Chemicals");
            while (rs.next()) {
                allChemicals.add(new Chemical(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("hcode"),
                        rs.getString("disposal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
