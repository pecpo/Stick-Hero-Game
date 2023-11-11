package com.example.approject;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;

public class Game {

    public void start(Stage stage) throws IOException {
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