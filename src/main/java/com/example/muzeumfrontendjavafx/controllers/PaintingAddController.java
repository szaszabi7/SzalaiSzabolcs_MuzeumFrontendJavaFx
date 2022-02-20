package com.example.muzeumfrontendjavafx.controllers;

import com.example.muzeumfrontendjavafx.Api;
import com.example.muzeumfrontendjavafx.Controller;
import com.example.muzeumfrontendjavafx.Painting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.util.Calendar;
import java.util.Date;

public class PaintingAddController extends Controller {
    @FXML
    private TextField textFieldPainitngAddTitle;
    @FXML
    private Spinner<Integer> spinnerPainitngAddYear;
    @FXML
    private ChoiceBox<String> choiceBoxPainitngAddOnDisplay;
    private int currentYear;

    public void initialize() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        currentYear = calendar.get(calendar.YEAR);
        choiceBoxPainitngAddOnDisplay.getItems().add("Yes");
        choiceBoxPainitngAddOnDisplay.getItems().add("No");
    }

    @FXML
    public void onClickPaintingAdd(ActionEvent actionEvent) {
        String title = textFieldPainitngAddTitle.getText();
        int year = 0;
        int onDisplayIndex = choiceBoxPainitngAddOnDisplay.getSelectionModel().getSelectedIndex();
        if (title.isEmpty()){
            alert("You have to add a title");
            return;
        }
        try {
            year = spinnerPainitngAddYear.getValue();
        } catch (NullPointerException ex){
            alert("You have to add the painting's year of creation");
            return;
        } catch (Exception ex){
            alert("The year of creation has to be between 1 and " + currentYear);
            return;
        }
        if (year < 1 || year > currentYear) {
            alert("The year of creation has to be between 1 and " + currentYear);
            return;
        }
        if (onDisplayIndex == -1){
            alert("You have to choose a dispaly state");
            return;
        }
        boolean onDisplay = false;
        if (onDisplayIndex == 0) onDisplay = true;
        System.out.println(onDisplay);

        try {
            Painting newPainting = new Painting(0, title, year, onDisplay);
            Painting createdPainting = Api.addPainting(newPainting);
            if (createdPainting != null){
                alert("Painting added successfully");
                resetInputs();
            } else {
                alert("Painting could not be added");
            }
        } catch (Exception e) {
            errorAlert(e);
        }
    }

    private void resetInputs() {
        textFieldPainitngAddTitle.setText("");
        spinnerPainitngAddYear.getValueFactory().setValue(currentYear);
        choiceBoxPainitngAddOnDisplay.getSelectionModel().clearSelection();
    }
}
