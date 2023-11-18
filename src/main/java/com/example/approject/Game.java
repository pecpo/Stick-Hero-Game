package com.example.approject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import static javafx.application.Application.launch;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
            Scene scene1 = new Scene(root);
            stage.setScene(scene1);
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