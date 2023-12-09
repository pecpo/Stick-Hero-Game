package com.example.approject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class Controller1 {
    public static String getCharacter() {
        return Character;
    }

    private static String Character="character.png";
    @FXML
    private ImageView myCharacter;
    @FXML
    private void switchCharacter1(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("scene1b.fxml"));
        Parent root= loader.load();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Character="sword_hero.png";
    }
    @FXML
    private void switchCharacter2(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("scene1a.fxml"));
        Parent root= loader.load();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Character="amogus.png";
    }
    @FXML
    private void switchCharacter3(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("scene1.fxml"));
        Parent root= loader.load();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Character="original.png";
    }
    @FXML
    private void switchToScene2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent root= loader.load();
        Controller2 controller2 = loader.getController();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
