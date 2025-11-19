package com.example.csia.controllers;

import com.example.csia.models.Chemical;
import com.example.csia.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.csia.utils.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSearch_Controller {

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
    private Label statusLabel;

    private List<Chemical> allChemicals = new ArrayList<>();

    public void onClickLogout(ActionEvent event) {
        SceneManager.switchScene("login.fxml", "Login");
    }

    @FXML
    public void onClickSearch(ActionEvent event) {
        String query = chemicalSearch.getText().trim();
        String selected = searchSelector.getText();

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

                ChemicalDetails_Controller controller = loader.getController();
                controller.setChemical(chem);
                Stage otherStage = new Stage();

                Stage stage = (Stage) searchButton.getScene().getWindow();
                otherStage.setScene(scene);
                otherStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Chemical not found.");
        }
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
