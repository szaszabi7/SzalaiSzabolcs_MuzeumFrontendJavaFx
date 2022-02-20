package com.example.muzeumfrontendjavafx.controllers;

import com.example.muzeumfrontendjavafx.Api;
import com.example.muzeumfrontendjavafx.Controller;
import com.example.muzeumfrontendjavafx.Statue;
import com.example.muzeumfrontendjavafx.Painting;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class MainController extends Controller {

    @FXML
    private TableView<Statue> tableViewStatue;
    @FXML
    private TableColumn<Statue, String> colPerson;
    @FXML
    private TableColumn<Statue, Integer> colPrice;
    @FXML
    private TableColumn<Statue, Integer> colHeight;
    @FXML
    private TableView<Painting> tableViewPainting;
    @FXML
    private TableColumn<Painting, String> colTitle;
    @FXML
    private TableColumn<Painting, Integer> colYear;
    @FXML
    private TableColumn<Painting, Boolean> colOnDispaly;
    @FXML
    private TabPane tabPane;

    public void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colOnDispaly.setCellValueFactory(new PropertyValueFactory<>("onDisplay"));

        colPerson.setCellValueFactory(new PropertyValueFactory<>("person"));
        colHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        loadPaintings();
        loadStatues();
    }

    private void loadPaintings() {
        try {
            List<Painting> paintingList = Api.getPaintings();
            tableViewPainting.getItems().clear();
            for (Painting painting : paintingList) {
                tableViewPainting.getItems().add(painting);
            }
        } catch (IOException e) {
            errorAlert(e);
        }
    }

    private void loadStatues() {
        try {
            List<Statue> statueList = Api.getStatues();
            tableViewStatue.getItems().clear();
            for (Statue statue : statueList) {
                tableViewStatue.getItems().add(statue);
            }
        } catch (IOException e) {
            errorAlert(e);
        }
    }
}