/*
 * Copyright (c) 2021 Ksuvot
 */

package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Household;

public class mainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField studentName;

    @FXML
    private Button buttonDeleteStudent;

    @FXML
    private Button buttonSearchUser;

    @FXML
    private TextField numberOfRooms;

    @FXML
    private TextField floorNumber;

    @FXML
    private TextField roomNumber;

    @FXML
    private TextField studentsName;

    @FXML
    private Button buttonAddHousehold;

    @FXML
    private Button buttonAddStudents;

    @FXML
    private Button buttonClearHousehold;

    @FXML
    private Button buttonClearStudents;

    @FXML
    private Button buttonClearAllRecords;

    @FXML
    private Button buttonDeleteDataBase;

    @FXML
    private TableView<Household> tableHousehold;

    @FXML
    private TableColumn<Household, Integer> columnId;

    @FXML
    private TableColumn<Household, Integer> columnRooms;

    @FXML
    private TableColumn<Household, Integer> columnRelevance;

    @FXML
    private TableView<?> tableStudents;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnFloor;

    @FXML
    private TableColumn<?, ?> columnRoom;

    ObservableList<Household> householdList = FXCollections.observableArrayList(
            new Household(1, 326, 25),
            new Household(2, 101, 26),
            new Household(3, 404, 27),
            new Household(4, 125, 250)
    );

    @FXML
    void initialize() {
        buttonAddHousehold.setOnAction(actionEvent -> {
            columnRooms.setCellValueFactory(new PropertyValueFactory<Household, Integer>("rooms"));
            columnId.setCellValueFactory(new PropertyValueFactory<Household, Integer>("id"));
            columnRelevance.setCellValueFactory(new PropertyValueFactory<Household, Integer>("relevance"));
            tableHousehold.setItems(householdList);
        });
    }
}

