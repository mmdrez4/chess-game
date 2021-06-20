package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


public class Main extends Application {

    public static Stage window = new Stage();

// if you want to play background music uncomment playAudio()

    @Override
    public void start(Stage primaryStage) throws Exception{
//        playAudio();
        Parent root = FXMLLoader.load(getClass().getResource("LoginMenu.fxml"));
        window.setTitle("Login Menu");
        window.setScene(new Scene(root, 300, 275));
        window.show();
    }

//    private void playAudio(){
//        AudioClip audio = new AudioClip(this.getClass().getResource("music.wav").toString());
//        audio.play();
//        audio.setCycleCount(AudioClip.INDEFINITE);
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
