package com.example.csia.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

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
