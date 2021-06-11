/*
 * Copyright (c) 2021 Ksuvot
 */

package controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import database.DataBaseConnect;
import entities.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import entities.Household;

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
    private TableColumn<Household, Date> columnRelevance;

    @FXML
    private TableView<Students> tableStudents;

    @FXML
    private TableColumn<Students, String> columnName;

    @FXML
    private TableColumn<Students, Integer> columnFloor;

    @FXML
    private TableColumn<Students, Integer> columnRoom;

    @FXML
    void initialize() {
        loadDate();
    }

    Connection connection = null;
    ResultSet resultSet = null;
    CallableStatement callableStatement = null;
    Household household = null;
    Students students = null;

    ObservableList<Household> householdList = FXCollections.observableArrayList();
    ObservableList<Students> studentsList = FXCollections.observableArrayList();

    private void loadDate() {
        connection = DataBaseConnect.getConnect();
        refreshTable();

        columnRooms.setCellValueFactory(new PropertyValueFactory<Household, Integer>("rooms"));
        columnId.setCellValueFactory(new PropertyValueFactory<Household, Integer>("id"));
        columnRelevance.setCellValueFactory(new PropertyValueFactory<Household, Date>("relevance"));
        tableHousehold.setItems(householdList);

        columnName.setCellValueFactory(new PropertyValueFactory<Students, String>("name"));
        columnFloor.setCellValueFactory(new PropertyValueFactory<Students, Integer>("floor"));
        columnRoom.setCellValueFactory(new PropertyValueFactory<Students, Integer>("room"));
        tableStudents.setItems(studentsList);
    }

    private void refreshTable() {
        householdList.clear();
        try {
            callableStatement = connection.prepareCall("{call get_household}");
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                householdList.add(new Household(
                        resultSet.getInt("id"),
                        resultSet.getInt("rooms"),
                        resultSet.getDate("relevance"))
                );
                tableHousehold.setItems(householdList);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        studentsList.clear();
        try {
            callableStatement = connection.prepareCall("{call get_students}");
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();

            while(resultSet.next()) {
                studentsList.add(new Students(
                        resultSet.getString("name"),
                        resultSet.getInt("floor"),
                        resultSet.getInt("room"))
                );
                tableStudents.setItems(studentsList);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}

