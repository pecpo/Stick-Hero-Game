package com.example.approject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import javafx.scene.Scene;
import  java.io.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
//            Path sourcePath = Paths.get("src/main/resources/original.png");
//            Path destinationPath = Paths.get("src/main/resources/character.png");
//            try {
//                // Copy the image using Files.copy method
//                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//
//                System.out.println("Image copied successfully!");
//            } catch (IOException e) {
//                // Handle IOException, e.g., file not found, permission issues, etc.
//                e.printStackTrace();
//            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene1.fxml"));
            Parent root= loader.load();
            Controller1 controller1 = loader.getController();
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
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

}