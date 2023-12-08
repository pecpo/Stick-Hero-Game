package com.example.approject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.input.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    private Stage stage;
    private Parent root;
    private Stick stick=new Stick();
    Timeline extendStickHeight;
    Timeline extendStickY;
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
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToScene3(ActionEvent event) throws IOException {
        boolean isMoving=false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = loader.load();
        AnchorPane pillarPane = (AnchorPane) loader.getNamespace().get("myPane"); //our main pane
        GamePlatform.generate(pillarPane); //to generate a platform

        ImageView imageView=Player.initialize(); //to create player
        pillarPane.getChildren().add(imageView);
        pillarPane.getChildren().add(stick.stickRectangle);

        Scene mainScene = null;
        double prev_left=Controller.getLeft();
        GamePlatform.generate(pillarPane);
        ImageView cherry=Cherry.initialize(pillarPane,prev_left);
        Player.moveForward(imageView,cherry,pillarPane);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pillarPane);
        scrollPane.setPrefSize(500, 800);
//            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScene = new Scene(scrollPane, 500, 800);

//        mainScene.setOnKeyPressed(even -> {
//            if (even.getCode().toString().equals("SPACE")) {
//                Player.setFlipped(imageView);
//                // Perform actions when spacebar is pressed
//            }
//        });
        mainScene.setOnKeyPressed(even -> {
            System.out.println("nigger");
            stick=new Stick();
//            handleMousePressed(even);
            // Perform actions on mouse click
        });

        stage.setTitle("Actual Game");
        stage.setScene(mainScene);
        stage.show();
    }
    public void exit(){
        System.exit(0);
    }
    public static void intersect(ImageView cherry, ImageView player){
        Bounds cherrys=cherry.localToScene(cherry.getBoundsInLocal());
        Bounds players=player.localToScene(player.getBoundsInLocal());
        if(cherrys.intersects(players)){
            System.out.println("Intersected");
            cherry.setVisible(false);
        }
    }
//    private void handleMousePressed(KeyEvent event) {
////        if (rotated){
////            return;
////        }
//        extendStickHeight = new Timeline(
//                new KeyFrame(Duration.seconds(2), new KeyValue(stick.stickRectangle.heightProperty(), 510))
//        );
//        extendStickHeight.play();
//        extendStickY = new Timeline(
//                new KeyFrame(Duration.seconds(2), new KeyValue(stick.stickRectangle.yProperty(), -510))
//        );
//        extendStickY.play();
////        rotated = false;
//    }
//    private void handleMouseReleased(MouseEvent event) {
//        if (extendStickHeight != null && extendStickY != null) {
//            extendStickHeight.stop();
//            extendStickY.stop();
//        }
//
//        if (!rotated) {
//            Rotate rotateTransition = new Rotate();
//            rotateTransition.setAngle(90);
//            rotateTransition.setPivotX(stick.getX());
//            rotateTransition.setPivotY(0);
//            rotateTransition.setAxis(Rotate.Z_AXIS);
//            stick.getTransforms().add(rotateTransition);
//
//            rotated = true;
//        }
//
//        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), player);
//        translateTransition.setByX(stick.getHeight() + 15);
//
//        if (stick.getHeight() < rectangle2.getX() - rectangle1.getX() - rectangle1.getWidth() || stick.getHeight()>rectangle2.getX()+rectangle2.getWidth() - rectangle1.getX() - rectangle1.getWidth()){
//            gameOver();
//        }
//        else{
//            gameContinue();
//        }
//
//        translateTransition.play();
//    }

}
