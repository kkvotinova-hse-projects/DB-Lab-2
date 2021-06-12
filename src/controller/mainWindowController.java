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
import javafx.scene.input.MouseEvent;

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
    void clickClearAll(MouseEvent event) {

    }

    @FXML
    void clickClearHousehold(MouseEvent event) {

    }

    @FXML
    void clickClearStudents(MouseEvent event) {

    }

    @FXML
    void clickDelete(MouseEvent event) {
    }

    @FXML
    void clickRecordHousehold(MouseEvent event) {

        if (!numberOfRooms.getText().isEmpty()) {
            try {
                int rooms = Integer.parseInt(numberOfRooms.getText());
                callableStatement = connection.prepareCall("{call add_to_household(" + rooms + "," + "current_date)}");
                callableStatement.execute();
                CallableStatement callableStatement1 = connection.prepareCall("{call get_household}");
                CallableStatement callableStatement2 = connection.prepareCall("{call get_students}");
                refreshTable(callableStatement1, callableStatement2);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @FXML
    void clickRecordStudents(MouseEvent event) {
        if (!studentsName.getText().isEmpty() || !floorNumber.getText().isEmpty() || !roomNumber.getText().isEmpty()) {
            try {
                String name = studentsName.getText();
                int floor = Integer.parseInt(floorNumber.getText());
                int room = Integer.parseInt(roomNumber.getText());
                callableStatement = connection.prepareCall("{call add_to_students('" + name + "'," + floor + "," + room +")}");
                callableStatement.execute();
                CallableStatement callableStatement1 = connection.prepareCall("{call get_household}");
                CallableStatement callableStatement2 = connection.prepareCall("{call get_students}");
                refreshTable(callableStatement1, callableStatement2);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @FXML
    void clickSearch(MouseEvent event) {
        if (!studentName.getText().isEmpty()) {
            try {
                String name = studentName.getText();
                callableStatement = connection.prepareCall("{call find_students('" + name + "')}");
                callableStatement.execute();
                CallableStatement callableStatement1 = callableStatement;
                resultSet = callableStatement.getResultSet();

                if (resultSet.next()) {
                    int floor = resultSet.getInt("floor");
                    callableStatement = connection.prepareCall("{call find_household(" + floor + ")}");
                    callableStatement.execute();
                    CallableStatement callableStatement2 = callableStatement;
                    refreshTable(callableStatement2, callableStatement1);
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @FXML
    void clickDeleteDataBase(MouseEvent event) {
    }

    @FXML
    void initialize() throws SQLException {
        loadDate();
    }

    Connection connection = null;
    ResultSet resultSet = null;
    CallableStatement callableStatement = null;

    ObservableList<Household> householdList = FXCollections.observableArrayList();
    ObservableList<Students> studentsList = FXCollections.observableArrayList();

    private void loadDate() throws SQLException {
        connection = DataBaseConnect.getConnect();
        CallableStatement callableStatement1 = connection.prepareCall("{call get_household}");
        CallableStatement callableStatement2 = connection.prepareCall("{call get_students}");
        refreshTable(callableStatement1, callableStatement2);

        columnRooms.setCellValueFactory(new PropertyValueFactory<Household, Integer>("rooms"));
        columnId.setCellValueFactory(new PropertyValueFactory<Household, Integer>("id"));
        columnRelevance.setCellValueFactory(new PropertyValueFactory<Household, Date>("relevance"));
        tableHousehold.setItems(householdList);

        columnName.setCellValueFactory(new PropertyValueFactory<Students, String>("name"));
        columnFloor.setCellValueFactory(new PropertyValueFactory<Students, Integer>("floor"));
        columnRoom.setCellValueFactory(new PropertyValueFactory<Students, Integer>("room"));
        tableStudents.setItems(studentsList);
    }

    private void refreshTable(CallableStatement callableStatement1, CallableStatement callableStatement2) {
        householdList.clear();
        try {
            callableStatement = callableStatement1;
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();

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
            callableStatement = callableStatement2;
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();

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

