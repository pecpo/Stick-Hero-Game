package com.example.approject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = loader.load();
        AnchorPane pillarPane = (AnchorPane) loader.getNamespace().get("myPane");
        GamePlatform.generate(pillarPane);
        Image image = new Image("character.png");
        ImageView imageView = new ImageView(image);
        Player.initialize(imageView);
        pillarPane.getChildren().add(imageView);
        Stick stick = new Stick();
        Line line = stick.getStickLine();
        stick.initialize(line);
        pillarPane.getChildren().add(line);
        Scene mainScene = null;
        for(int i=0;i<2;i++){
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
        }
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
}
