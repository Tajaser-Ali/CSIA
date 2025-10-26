package com.example.csia.controllers;

import com.example.csia.models.Chemical;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChemicalDetails_Controller {

    @FXML
    private Label nameLabel;

    @FXML
    private Label hCodeLabel;

    @FXML
    private Label idLabel;

    private Chemical chemical;

    public void setChemical(Chemical chem) {
        this.chemical = chem;
        showChemicalDetails();
    }

    private void showChemicalDetails() {
        if (chemical != null) {
            idLabel.setText(chemical.getId() + "");
            nameLabel.setText(chemical.getName());
            hCodeLabel.setText(chemical.getHCode());
        }

    }
}