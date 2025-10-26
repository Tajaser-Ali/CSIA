package com.example.csia.controllers;

import com.example.csia.models.User;
import com.example.csia.utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Users_Controller {

    @FXML
    private TextField userSearch;

    @FXML
    private ListView<User> userListView;

    @FXML
    private Button searchButton;

    @FXML
    private MenuButton searchSelector;

    @FXML
    private MenuItem idSelection;

    @FXML
    private MenuItem nameSelection;

    @FXML
    private Label statusLabel;

    private List<User> allUsers;

    private String searchType = "";

    public void selectID(ActionEvent event) {
        searchType = "ID";
        searchSelector.setText("ID");
    }

    public void selectName(ActionEvent event) {
        searchType = "Name";
        searchSelector.setText("Name");
    }

    public void onClickSearch(ActionEvent event) {
        String query = userSearch.getText().trim();
        if (query.isEmpty() || searchType.isEmpty()) {
            statusLabel.setText("Select search type and enter a value.");
            return;
        }

        boolean found = false;
        for (User user : allUsers) {
            if ((searchType.equals("Name") && user.getName().equalsIgnoreCase(query)) ||
                    (searchType.equals("ID") && user.getId().equalsIgnoreCase(query))) {
                found = true;
                statusLabel.setText("User found: " + user.getName());
                userListView.getSelectionModel().select(user);
                userListView.scrollTo(user);
                break;
            }
        }

        if (!found) {
            statusLabel.setText("User not found.");
        }
    }

    public void onClickGoBack(ActionEvent event) {
        SceneManager.switchScene("adminDashboard.fxml", "Admin Dashboard");
    }

}
