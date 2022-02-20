package com.example.muzeumfrontendjavafx.controllers;

import com.example.muzeumfrontendjavafx.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableColumn<Painting, Boolean> colOnDisplay;
    @FXML
    private TabPane tabPane;

    public void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colOnDisplay.setCellValueFactory(new PropertyValueFactory<>("on_display"));

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

    @FXML
    public void btnPaintingAdd(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MuzeumApp.class.getResource("paintingadd-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            stage.setTitle("Add new Painting");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> loadPaintings());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnPaintingEdit(ActionEvent actionEvent) {
        alert("Not yet implemented");
    }

    @FXML
    public void btnPaintingDelete(ActionEvent actionEvent) {
        int selectedIndex = tableViewPainting.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("You have to select a painting to delete");
            return;
        }
        Painting painting = tableViewPainting.getSelectionModel().getSelectedItem();
        if (!confirm("Are you sure you want to delete: \"" + painting.getTitle() + "\"")) {
            return;
        }
        try {
            boolean success = Api.deletePainting(painting.getId());
            alert(success ? "Painting deleted successfully" : "Painting could not be deleted");
            loadPaintings();
        } catch (IOException e) {
            errorAlert(e);
        }
    }

    @FXML
    public void btnStatueAdd(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MuzeumApp.class.getResource("statueadd-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            stage.setTitle("Add new Statue");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> loadStatues());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnStatueEdit(ActionEvent actionEvent) {
        alert("Not yet implemented");
    }

    @FXML
    public void btnStatueDelete(ActionEvent actionEvent) {
        int selectedIndex = tableViewStatue.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("You have to select a statue to delete");
            return;
        }
        Statue statue = tableViewStatue.getSelectionModel().getSelectedItem();
        if (!confirm("Are you sure you want to delete: \"" + statue.getPerson() + "\"")) {
            return;
        }
        try {
            boolean success = Api.deleteStatue(statue.getId());
            alert(success ? "Statue deleted successfully" : "Statue could not be deleted");
            loadStatues();
        } catch (IOException e) {
            errorAlert(e);
        }
    }

    @FXML
    public void clickDeselect(Event event) {
        tableViewPainting.getSelectionModel().clearSelection();
        tableViewStatue.getSelectionModel().clearSelection();
    }
}