package com.example.approject;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static double left=0;

    public static double getLeft() {
        return left;
    }

    public static void setLeft(double lefts) {
        left = lefts;
    }

    public static int getFirst() {
        return first;
    }

    public static void setFirst(int firsst) {
        first = firsst;
    }

    private static int first=1;
    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scene2.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = loader.load();
        AnchorPane pillarPane = (AnchorPane) loader.getNamespace().get("myPane");
        GamePlatform.generate(pillarPane);
        GamePlatform.generate(pillarPane);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pillarPane);
        scrollPane.setPrefSize(400, 800);
        Scene mainScene = new Scene(scrollPane, 500, 800);
        stage.setTitle("Partial Scene View Example");
        stage.setScene(mainScene);
        stage.show();

        // Set up a timeline to automatically scroll the ScrollPane
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.seconds(3), even -> {
//                    double newHValue = Math.min(scrollPane.getHvalue() + 0.25, 1.0);
//                    scrollPane.setHvalue(newHValue);
//                })
//
//        );
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
    }
    public void exit(){
        System.exit(0);
    }
}

