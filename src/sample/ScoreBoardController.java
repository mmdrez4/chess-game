package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Model.User;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;


public class ScoreBoardController implements Initializable {
    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> userNameColumn;

    @FXML
    private TableColumn<User, Integer> scoreColumn;

    @FXML
    private TableColumn<User, Integer> winsColumn;

    @FXML
    private TableColumn<User, Integer> drawsColumn;

    @FXML
    private TableColumn<User, Integer> lossesColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("points"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("wins"));
        drawsColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("draws"));
        lossesColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("losses"));

        tableView.setItems(getUsers());
    }

    public ObservableList<User> getUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();
        Collections.sort(Manager.allUsers);
        users.addAll(Manager.allUsers);
        return users;
    }
}
