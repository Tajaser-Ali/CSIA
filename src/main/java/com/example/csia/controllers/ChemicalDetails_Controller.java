package com.example.csia.controllers;

import com.example.csia.models.Chemical;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.csia.utils.DatabaseHandler;
import java.sql.PreparedStatement;

public class ChemicalDetails_Controller {

    @FXML
    private Label nameLabel;

    @FXML
    private Label hCodeLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label disposalLabel;

    @FXML
    private TextField disposalField;

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
            disposalLabel.setText(chemical.getDisposal());
        }
    }

    private void saveDisposal() {
        if (chemical == null) return;

        String newDisposal = disposalField.getText().trim();
        if (newDisposal.isEmpty()) return; // optional: prevent empty input

        // update the Chemical object
        chemical = new Chemical(chemical.getId(), chemical.getName(), chemical.getHCode(), newDisposal);

        // update the label
        disposalLabel.setText(newDisposal);

        // update the database
        DatabaseHandler db = new DatabaseHandler();
        String query = "UPDATE chemicals SET disposal = ? WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, newDisposal);
            stmt.setInt(2, chemical.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void setEditMode(boolean editable) {
        if (editable) {
            if (chemical != null) {
                disposalField.setText(chemical.getDisposal());
            }
            disposalLabel.setVisible(false);
            disposalLabel.setManaged(false);

            disposalField.setVisible(true);
            disposalField.setManaged(true);
            disposalField.toFront();

            disposalField.setOnAction(e -> saveDisposal());
            disposalField.focusedProperty().addListener((obs, oldV, newV) -> {
                if (!newV) saveDisposal();
            });

        } else {
            disposalField.setVisible(false);
            disposalField.setManaged(false);

            disposalLabel.setVisible(true);
            disposalLabel.setManaged(true);
        }
    }


}