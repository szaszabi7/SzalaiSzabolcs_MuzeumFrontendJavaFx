package com.example.muzeumfrontendjavafx.controllers;

import com.example.muzeumfrontendjavafx.Api;
import com.example.muzeumfrontendjavafx.Controller;
import com.example.muzeumfrontendjavafx.Statue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class StatueAddController extends Controller {
    @FXML
    private TextField textFieldStatueAddPerson;
    @FXML
    private Spinner<Integer> spinnerStatueAddPrice;
    @FXML
    private Spinner<Integer> spinnerStatueAddHeight;

    @javafx.fxml.FXML
    public void onClickStatueAdd(ActionEvent actionEvent) {
        String person = textFieldStatueAddPerson.getText();
        int height = 0;
        int price = 0;
        if (person.isEmpty()){
            alert("You have to add a person");
            return;
        }
        try {
            height = spinnerStatueAddHeight.getValue();
        } catch (NullPointerException ex){
            alert("You have to add the height of the statue");
            return;
        } catch (Exception ex){
            alert("The height has to be between 1 and 255");
            return;
        }
        if (height < 1 || height > 255) {
            alert("The height has to be between 1 and 255");
            return;
        }
        try {
            price = spinnerStatueAddPrice.getValue();
        } catch (NullPointerException ex){
            alert("You have to add the price of the statue");
            return;
        } catch (Exception ex){
            alert("The price has to be between 10000 and 10000000");
            return;
        }
        if (price < 10000 || price > 10000000) {
            alert("The price has to be between 10000 and 10000000");
            return;
        }

        try {
            Statue newStatue = new Statue(0, person, height, price);
            Statue createdStatue = Api.addStatue(newStatue);
            if (createdStatue != null){
                alert("Statue added successfully");
                resetInputs();
            } else {
                alert("Statue could not be added");
            }
        } catch (Exception e) {
            errorAlert(e);
        }
    }

    private void resetInputs() {
        textFieldStatueAddPerson.setText("");
        spinnerStatueAddHeight.getValueFactory().setValue(100);
        spinnerStatueAddPrice.getValueFactory().setValue(10000);
    }
}
