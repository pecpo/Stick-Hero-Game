package com.example.approject;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import static javafx.application.Application.launch;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
            stage.setTitle("Home Screen");
            Button start=new Button("Start");
            Button exit=new Button("Exit");
            HBox hbox = new HBox(10);
            hbox.getChildren().addAll(start, exit);
            Scene scene=new Scene(hbox,1000,500);
            stage.setScene(scene);
            stage.show();
            Player player = new Player();
            GamePlatform gamePlatform = new GamePlatform(100, 0, 100, 0, 0);
            Scoreboard scoreboard = new Scoreboard();
            Stick stick = new Stick();
            Cherry cherry = new Cherry(0);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Application.launch(args);
    }

}