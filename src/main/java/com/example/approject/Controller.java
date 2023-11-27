package com.example.approject;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Objects;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static double left=0;

    public static boolean isIsSpacePressed() {
        return isSpacePressed;
    }

    public static void setIsSpacePressed(boolean isSpacePressed) {
        Controller.isSpacePressed = isSpacePressed;
    }

    private static boolean isSpacePressed=false;

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
    @FXML
    public void switchToScene3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = loader.load();
        AnchorPane pillarPane = (AnchorPane) loader.getNamespace().get("myPane");
        GamePlatform.generate(pillarPane);
        Image image = new Image("character.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        imageView.setX(0);
        imageView.setY(485);
        pillarPane.getChildren().add(imageView);
        Stick stick = new Stick();
        Line line = stick.getStickLine();
        line.setStartX(Controller.getLeft()+GamePlatform.getWidth());
        line.setStartY(500);
        line.setEndX(Controller.getLeft()+GamePlatform.getWidth());
        line.setEndY(500);
//        pillarPane.setOnKeyPressed(stick::extend);
//        pillarPane.setOnKeyReleased(stick::reset);
        pillarPane.getChildren().add(line);
        GamePlatform.generate(pillarPane);
        Player.moveForward(imageView);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pillarPane);
        scrollPane.setPrefSize(500, 800);
        Scene mainScene = new Scene(scrollPane, 500, 800);
        stage.setTitle("Actual Game");
        stage.setScene(mainScene);
        stage.show();

    }
    public void exit(){
        System.exit(0);
    }
}

