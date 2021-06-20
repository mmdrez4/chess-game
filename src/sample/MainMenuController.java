package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Model.User;

import java.io.IOException;
import java.util.Objects;

public class MainMenuController {
    public static User player1;
    public static User player2;
    public TextField turnLimitField;
    public TextField undoNumber;
    public TextField opponentUsername;
    public Label errorMessage;

    public void startGame(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartGame.fxml"));
        Main.window.setTitle("StartGame Menu");
        Main.window.setScene(new Scene(root, 300, 275));
        Main.window.show();
    }

    public void showScoreBoard(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScoreBoard.fxml"));
        Stage scoreBoardWindow = new Stage();
        scoreBoardWindow.setTitle("Score Board");
        scoreBoardWindow.setScene(new Scene(root, 600, 600));
        scoreBoardWindow.showAndWait();
    }

    public void logOut(MouseEvent mouseEvent) throws IOException {
        if (ConfirmBox.display("log out confirmation", "are you sure you want to logout?")) {
            player1 = null;
            player2 = null;
            Manager.backToLoginMenu();
        }
    }

    public void applyClick(MouseEvent mouseEvent) throws IOException {
        if (!(undoNumber.getText().isEmpty() || turnLimitField.getText().isEmpty() || opponentUsername.getText().isEmpty())) {
            if (Manager.regexChecker(turnLimitField.getText(), "^([0-9]+)$") && (Manager.regexChecker(undoNumber.getText(), "^([0-9]+)$")) ) {
                if (Manager.isRegisteredBefore(opponentUsername.getText()) && !(opponentUsername.getText().equalsIgnoreCase(player1.getUsername()))) {
                    player2 = Manager.getUserByName(opponentUsername.getText());
                    StartGameController game = new StartGameController(Integer.parseInt(turnLimitField.getText()), Integer.parseInt(undoNumber.getText()));
                    game.initializeGame();
                } else {
                    errorMessage.setText("no other user exists with this username");
                }
            } else {
                errorMessage.setText("undo number & turn limit\nshould be Arithmetic number");
            }
        } else {
            errorMessage.setText("fill all the blanks");
        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws IOException {
        LoginMenuController.goToMainMenu(player1);
    }
}
