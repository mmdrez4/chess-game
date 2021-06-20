package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sample.Model.User;

import java.io.IOException;
import java.util.Objects;

public class LoginMenuController {

    public TextField usernameField;
    public PasswordField passwordField;
    public Label errorMessage;
    public PasswordField currentPasswordField;
    public PasswordField newPasswordField;

    public void signUp(MouseEvent mouseEvent) throws IOException, InterruptedException {
        if (!(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (Manager.isRegisteredBefore(username)) {
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("this username has already token");
            } else {
                if (Manager.usernameAndPasswordRegexChecker(username, password)) {
                    errorMessage.setTextFill(Color.GREEN);
                    errorMessage.setText("you signed up successfully");
                    Manager.allUsers.add(new User(username, password));
                } else {
                    errorMessage.setTextFill(Color.RED);
                    errorMessage.setText("use correct type");
                }
            }
        } else {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("enter both username & password");
        }
    }

    public void backToLoginMenu(MouseEvent mouseEvent) throws IOException {
        Manager.backToLoginMenu();
    }

    public void deleteAccount(MouseEvent mouseEvent) throws IOException, InterruptedException {
        if (!(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (Manager.isRegisteredBefore(username)) {
                if (Manager.isPasswordMatch(username, password)) {
                    if (ConfirmBox.display("delete confirmation", "are you sure you want to delete the account?")) {
                        errorMessage.setTextFill(Color.GREEN);
                        errorMessage.setText("deleted successfully");
                        Manager.allUsers.remove(Manager.getUserByName(username));
                    }
                } else {
                    errorMessage.setTextFill(Color.RED);
                    errorMessage.setText("wrong password!");
                }
            } else {
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("no user exists with this username");
            }
        } else {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("enter both username & password");
        }
    }


    public void changePassword(MouseEvent mouseEvent) {
        if (!(usernameField.getText().isEmpty() || currentPasswordField.getText().isEmpty())) {
            String username = usernameField.getText();
            String currentPassword = currentPasswordField.getText();
            String newPassword = newPasswordField.getText();
            if (Manager.isRegisteredBefore(username)) {
                if (Manager.isPasswordMatch(username, currentPassword)) {
                    if (ConfirmBox.display("change password confirmation", "are you sure you want to change the password?")) {
                        Objects.requireNonNull(Manager.getUserByName(username)).setPassword(newPassword);
                    }
                } else {
                    errorMessage.setTextFill(Color.RED);
                    errorMessage.setText("wrong password!");
                }
            } else {
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("no user exists with this username");
            }
        } else {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("enter both username & password");
        }
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        if (!(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (Manager.isRegisteredBefore(username)) {
                if (Manager.isPasswordMatch(username, password)) {
                    goToMainMenu(Manager.getUserByName(username));
                } else {
                    errorMessage.setTextFill(Color.RED);
                    errorMessage.setText("wrong password!");
                }
            } else {
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("no user exists with this username");
            }
        } else {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("enter both username & password");
        }
    }

    public static void goToMainMenu(User user) throws IOException {
        Parent root = FXMLLoader.load(LoginMenuController.class.getResource("MainMenu.fxml"));
        Scene mainMenu = new Scene(root, 300, 275);
        MainMenuController.player1 = user;
        Main.window.setTitle("Sign In Menu");
        Main.window.setScene(mainMenu);
        Main.window.show();
    }
}
