package com.example.approject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.Scene;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scene1.fxml")));
            Scene scene1 = new Scene(root);
            stage.setScene(scene1);
            stage.setResizable(false);
            stage.setTitle("Stick Hero");
            Image icon = new Image("TCS_Bhediya.png");
            stage.getIcons().add(icon);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Player player = new Player();
        GamePlatform gamePlatform = new GamePlatform(100, 0, 100, 0, 0);
        Scoreboard scoreboard = new Scoreboard();
        Stick stick = new Stick();
        Cherry cherry = new Cherry(0);
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}