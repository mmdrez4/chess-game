package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class FirstSceneController {
    Scene registerScene, deleteScene, signInScene, changePassScene;

    public void createAccountClick(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterMenu.fxml"));
        registerScene = new Scene(root, 300, 275);
        Main.window.setTitle("Register Menu");
        Main.window.setScene(registerScene);
        Main.window.show();
    }

    public void deleteAccountClick(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteMenu.fxml"));
        deleteScene = new Scene(root, 300, 275);
        Main.window.setTitle("Delete Account Menu");
        Main.window.setScene(deleteScene);
        Main.window.show();
    }

    public void loginClick(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignInMenu.fxml"));
        signInScene = new Scene(root, 300, 275);
        Main.window.setTitle("Sign In Menu");
        Main.window.setScene(signInScene);
        Main.window.show();
    }

    public void changePassClick(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChangePass.fxml"));
        changePassScene = new Scene(root, 300, 275);
        Main.window.setTitle("Change Password Menu");
        Main.window.setScene(changePassScene);
        Main.window.show();
    }

    public void exitClick(MouseEvent mouseEvent) {
        boolean answer = ConfirmBox.display("exit game", "are you sure you want to exit?");
        if (answer) {
            Main.window.close();
        }
    }
}
