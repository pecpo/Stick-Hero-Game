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
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import  java.io.*;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
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
        Result result= JUnitCore.runClasses(GameTest.class);
        for(Failure failure: result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        Application.launch(args);
    }

}