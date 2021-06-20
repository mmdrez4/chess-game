package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import sample.Model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    public static ArrayList<User> allUsers = new ArrayList<>();

    public static boolean usernameAndPasswordRegexChecker(String username, String password) {
        if (regexChecker(username, "^[A-Za-z0-9_]+$")) {
            if (regexChecker(password, "^[A-Za-z0-9_]+$")) {
                return true;
            } else {
                System.out.println("password format is invalid");
            }
        } else {
            System.out.println("username format is invalid");
        }
        return false;
    }

    public static boolean isRegisteredBefore(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static User getUserByName(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isPasswordMatch(String username, String password) {
        if (getUserByName(username) == null) return false;
        return getUserByName(username).getPassword().equals(password);
    }

    public static boolean regexChecker(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static void backToLoginMenu() throws IOException {
        Parent root = FXMLLoader.load(Manager.class.getResource("LoginMenu.fxml"));
        Main.window.setTitle("Login Menu");
        Main.window.setScene(new Scene(root, 300, 275));
        Main.window.show();
    }

    public static Background setBackground(){
        ImageView backGround = new ImageView();
        Image image = new Image(Manager.class.getResourceAsStream("wood.jpg"));
        backGround.setImage(image);
        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
        return background;
    }

}


