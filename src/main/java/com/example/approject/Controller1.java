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
        Image image=new Image("sword_hero.png");
        myCharacter.setImage(image);
//        Path sourcePath = Paths.get("src/main/resources/sword_hero.png");
//        Path destinationPath = Paths.get("src/main/resources/character.png");
//        try {
//            // Copy the image using Files.copy method
//            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//
//            System.out.println("Image copied successfully!");
//        } catch (IOException e) {
//            // Handle IOException, e.g., file not found, permission issues, etc.
//            e.printStackTrace();
//        }
        Character="sword_hero.png";
    }
    @FXML
    private void switchCharacter2(ActionEvent event) throws IOException {
        Image image=new Image("sword_hero.png");
        myCharacter.setImage(image);
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
