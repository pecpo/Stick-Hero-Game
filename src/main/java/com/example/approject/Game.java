package com.example.approject;

import javafx.application.Application;
import javafx.geometry.Pos;
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
        try{
            stage.setTitle("Home Screen");
            Button welcome=new Button("Welcome");
            Image image=new Image("stick.png");
            ImageView imageview=new ImageView(image);
            Pane layout = new Pane();
            layout.getChildren().add(imageview);
            layout.getChildren().add(welcome);
            welcome.setLayoutX(240);
            welcome.setLayoutY(600);
            welcome.setOnAction(e -> {
                try {
                    SecondScene(stage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            Scene scene=new Scene(layout,500,800);
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
    public void SecondScene(Stage stage) throws IOException {
        try{
            Button start =new Button("Start");
            Button exit=new Button("Exit");
            Image image=new Image("game_bg.png");
            ImageView imageview=new ImageView(image);
            Pane layout = new Pane();
            layout.getChildren().add(imageview);
            layout.getChildren().add(start);
            start.setLayoutX(225);
            start.setLayoutY(225);
            layout.getChildren().add(exit);
            exit.setLayoutX(450);
            exit.setLayoutY(600);
            Scene scene=new Scene(layout,500,800);
            stage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

}