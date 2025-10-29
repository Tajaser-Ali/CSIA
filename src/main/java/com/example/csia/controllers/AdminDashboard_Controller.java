package com.example.csia.controllers;

import com.example.csia.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AdminDashboard_Controller {

    @FXML
    private Button logoutButton;

    @FXML
    private ImageView searchImage;

    @FXML
    private ImageView usersImage;

    @FXML
    private ImageView editorImage;

    public void initialize() {
        Image searchImg = new Image(getClass().getResourceAsStream("/Images/dummy-removebg-preview.png"));
        searchImage.setImage(searchImg);

        Image usersImg = new Image(getClass().getResourceAsStream("/Images/Default_pfp.svg.png"));
        usersImage.setImage(usersImg);

        Image editorImg = new Image(getClass().getResourceAsStream("/Images/1-12630fd6.png"));
        editorImage.setImage(editorImg);
    }

    public void onClickLogout(ActionEvent event) {
        SceneManager.switchScene("login.fxml", "Login");
    }

    public void takeToSearch(MouseEvent event) {
        SceneManager.switchScene("adminSearch.fxml", "Admin Search");
    }

    public void takeToUsers(MouseEvent event) {
        SceneManager.switchScene("Users.fxml", "Users");
    }

    public void takeToEditor(MouseEvent event) {
        SceneManager.switchScene("editor.fxml", "Editor");
    }
}
