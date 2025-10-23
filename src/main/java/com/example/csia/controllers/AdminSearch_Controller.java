package com.example.csia.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    public void onClickLogout(ActionEvent event) {

    }

    public void onClickSearch(ActionEvent event) {

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
