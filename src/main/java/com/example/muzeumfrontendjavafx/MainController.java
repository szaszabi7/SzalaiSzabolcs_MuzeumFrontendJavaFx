package com.example.muzeumfrontendjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {

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
}