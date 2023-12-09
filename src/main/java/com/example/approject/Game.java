package com.example.approject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Application {

    private AnchorPane mainPane;
    private Scene mainScene;
    Stage stage;
    private Timeline sh=null;
    private Timeline sy=null;
    private Rectangle platformCurrent=null;
    private Rectangle platformNext=null;
    private ImageView cherry=null;
    private Scoreboard scoreboard=null;
    private Scoreboard highScoreboard=null;
    private Scoreboard cherryScore=null;
    private Rectangle stick;
    private ImageView player;
    private static boolean isFlipped=false;
    private static boolean isStill=true;
    private static boolean isRotated=false;
    private boolean isAlive=true;
    private boolean cherryCollected=false;
    private int currentScore=0;
    private int highScore=0;
    private int cherryCount=5;
    private double dist;
    Random random = new Random();

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage=primaryStage;
        mainPane=setup();
        mainScene = new Scene(mainPane, 500, 800);

        mainScene.setOnKeyPressed(event -> {


            KeyCode keyCode = event.getCode();
            if(!isAlive){
                if(keyCode==KeyCode.R){
                    if(cherryCount>2){
                        cherryCount-=2;
                        newcontgame();
                    }
                    else{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
                        Parent root= null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Controller2 controller2 = loader.getController();
                        Scene scene1 = new Scene(root);
                        stage.setScene(scene1);
                        stage.show();
                    }
                }
                else if(keyCode==KeyCode.Q){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
                    Parent root= null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Controller2 controller2 = loader.getController();
                    Scene scene1 = new Scene(root);
                    stage.setScene(scene1);
                    stage.show();
                }
            } else if (keyCode ==KeyCode.SPACE) {
                System.out.println("flip");
                setFlipped(player);
            }

        });

        mainScene.setOnMousePressed(this::handleMousePress);

        mainScene.setOnMouseReleased(this::handleMouseReleased);
//        mainScene.setOnKeyReleased(event -> {
//            System.out.println("release");
//            handleKeyReleased(event);
//        });

        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.setTitle("Stick Hero");
        stage.setScene(mainScene);
        stage.show();
    }

    private void handleMousePress(MouseEvent event) {
        System.out.println("press");
        if (isRotated){
            return;
        }
        isStill=false;
        sh = new Timeline(
                new KeyFrame(Duration.seconds(2), new KeyValue(stick.heightProperty(), 510))
        );

        sh.play();
        sy = new Timeline(
                new KeyFrame(Duration.seconds(2), new KeyValue(stick.yProperty(), -510))
        );
        sy.play();
        isRotated = false;
    }

    private void handleMouseReleased(MouseEvent event) {
        if (sh != null && sy != null) {
            sh.stop();
            sy.stop();
        }

        if(!isRotated) {
            Rotate rotateTransition = new Rotate();
            rotateTransition.setAngle(90);
            rotateTransition.setPivotX(stick.getX());
            rotateTransition.setPivotY(0);
            rotateTransition.setAxis(Rotate.Z_AXIS);
            stick.getTransforms().add(rotateTransition);
            isRotated = true;
        }

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), player);
        translateTransition.setByX(stick.getHeight());
        translateTransition.play();

        translateTransition.setOnFinished(event1 -> {
            isStill=true;
            if (isAlive && stick.getHeight() < platformNext.getX() - platformCurrent.getX() -
                    platformCurrent.getWidth() || stick.getHeight()>platformNext.getX()+platformNext.getWidth() -
                    platformCurrent.getX() - platformCurrent.getWidth()){
                isAlive=false;
                try {
                    gameOver();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                gameContinue();
            }
        });

    }

    private void newcontgame(){

        currentScore++;
        highScore=Math.max(currentScore, highScore);

        mainPane=setup();
        mainScene=reScene();
        stage.setScene(mainScene);
        stage.show();
    }

    private void gameContinue() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), player);
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(2), platformCurrent);
        TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(2), platformNext);
        TranslateTransition translateTransition4 = new TranslateTransition(Duration.seconds(2), stick);
        TranslateTransition translateTransition5 = null;
        if(cherry!=null){
            translateTransition5 = new TranslateTransition(Duration.seconds(2), cherry);
            translateTransition5.setByX(-dist-platformCurrent.getWidth());
        }

        translateTransition.setByX(-dist-platformCurrent.getWidth());
        translateTransition2.setByX(-dist-platformCurrent.getWidth());
        translateTransition3.setByX(-dist-platformCurrent.getWidth());
        translateTransition4.setByX(-dist-platformCurrent.getWidth());

        translateTransition.play();
        translateTransition2.play();
        translateTransition3.play();
        translateTransition4.play();
        if(cherry!=null){
            translateTransition5.play();
        }

        checkPlatformCollisions();

        translateTransition4.setOnFinished(event -> {

//            System.out.println(platformNext.getWidth());
//            mainPane.getChildren().remove(platformCurrent);
//            mainPane.getChildren().remove(platformNext);
//            mainPane.getChildren().remove(stick);
//            platformCurrent=new Rectangle(platformCurrent.getX(), platformNext.getY(), platformNext.getWidth(), platformNext.getHeight());
//            platformNext=randomRectangle();
//            dist = random.nextInt(100)+50;
//            platformNext.setX(platformCurrent.getX()+platformCurrent.getWidth()+dist);
//            mainPane.getChildren().add(platformNext);
//            System.out.println(platformCurrent.getWidth());
//            player.setX(platformCurrent.getX());
//            stick = new Rectangle(0,10, Color.BLUE);
//            stick.setWidth(10);
//            stick.setTranslateX(platformCurrent.getX() );
//            stick.setTranslateY(player.getY() + player.getFitHeight());
//            mainPane.getChildren().add(stick);
            if(!isAlive){
                return;
            }
            currentScore++;
            highScore=Math.max(currentScore, highScore);
            mainPane=setup();
            mainScene=reScene();
            stage.setScene(mainScene);
            stage.show();
        });

    }


    private void gameOver() throws IOException {

        Image backgroundImage = new Image("deathbg.png");

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );

        mainPane.getChildren().removeAll(player, stick, platformCurrent, platformNext);
        mainPane.setBackground(new Background(background));


        serializeHigh(highScore);
        stage.setScene(mainScene);
        stage.show();
    }

    public void serializeHigh(Integer highScore) throws IOException {
        PrintWriter out=null;
        try{
            out = new PrintWriter( new
                    FileWriter("highscore.txt"));
            out.println(highScore);
        }
        finally {
            if(out!=null){
                out.close();
            }
        }
    }

    public void serializeGame() throws IOException {
        ObjectOutputStream out=null;
        try{
            out=new ObjectOutputStream(new FileOutputStream("gamefile"));
            out.writeObject(this);
        }
        finally {
            if(out!=null){
                out.close();
            }
        }
    }

    public int deserializeHigh() throws IOException, ClassNotFoundException {
            BufferedReader in = null;
            try {
                 in = new BufferedReader( new FileReader("highscore.txt"));
                 Integer hs= Integer.parseInt(in.readLine());
                    return hs;
            }

            finally {
                in.close();
            }
    }

    public AnchorPane setup() {

        AnchorPane mainPane = new AnchorPane();
        mainPane.setPrefSize(500, 800);
        platformCurrent = randomRectangle();
        if(platformNext==null){
            platformCurrent= randomRectangle();
        }
        else {
            platformCurrent = new Rectangle(platformCurrent.getX(), platformNext.getY(), platformNext.getWidth(), platformNext.getHeight());
        }
        platformNext = randomRectangle();
        int distance = random.nextInt(100)+50;
        platformNext.setX(platformCurrent.getX()+platformCurrent.getWidth()+distance);

        stick = new Rectangle(0,10, Color.BLUE);
        stick.setWidth(10);
        player = new ImageView("character.png");
        player.setFitHeight(40);
        player.setFitWidth(40);
        player.setX(platformCurrent.getX() + platformCurrent.getWidth() - player.getFitWidth());
        dist=distance;
        stick.setTranslateX(player.getX() + player.getFitWidth());
        player.setY(platformCurrent.getY() - player.getFitHeight());
        stick.setTranslateY(player.getY() + player.getFitHeight());

        if(cherry!=null || !cherryCollected){
            mainPane.getChildren().remove(cherry);
        }
        boolean cherrySpawn =random.nextBoolean();
        if(cherrySpawn && currentScore!=0){
            System.out.println("cherry spawned");
            cherry= new ImageView("cherry.png");
            cherry.setFitHeight(30);
            cherry.setFitWidth(30);
            cherry.setX(platformCurrent.getX()+platformCurrent.getWidth()+cherry.getFitWidth());
            cherry.setY(platformCurrent.getY());
            mainPane.getChildren().add(cherry);
        }

        mainPane.getChildren().addAll(platformCurrent, platformNext, stick, player);
//        mainPane.getChildren().addAll(player);
        Image backgroundImage = new Image("bg1.png");

        // Create a background with the image
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        mainPane.setBackground(new Background(background));

        if(scoreboard!=null){
            mainPane.getChildren().remove(scoreboard);
        }
        if(highScoreboard!=null){
            mainPane.getChildren().remove(highScoreboard);
        }
        if(cherryCount!=0){
            mainPane.getChildren().remove(cherry);
        }

        if(currentScore==0){
            try {
                highScore=deserializeHigh();
                highScoreboard = new Scoreboard("HighScore: "+highScore,"black");
            } catch (Exception e) {
                highScoreboard = new Scoreboard("HighScore: "+this.highScore,"black");
            }
        }
        else{
            highScoreboard = new Scoreboard("HighScore: "+this.highScore,"black");
        }

        scoreboard = new Scoreboard("Score: "+currentScore,"black");
        cherryScore = new Scoreboard("Cherry: "+cherryCount,"red");

        mainPane.getChildren().add(scoreboard);
        mainPane.getChildren().add(highScoreboard);
        mainPane.getChildren().add(cherryScore);

        AnchorPane.setTopAnchor(scoreboard, 10.0);
        AnchorPane.setLeftAnchor(scoreboard, 10.0);

        AnchorPane.setTopAnchor(cherryScore, 30.0);
        AnchorPane.setLeftAnchor(cherryScore, 10.0);


        AnchorPane.setTopAnchor(highScoreboard, 55.0);
        AnchorPane.setLeftAnchor(highScoreboard, 10.0);

        isFlipped=false;
        isStill=true;
        isRotated=false;
        cherryCollected=false;
        isAlive=true;
        return mainPane;
    }

    public Rectangle randomRectangle(){
        int width = random.nextInt(100)+100;
        int x = random.nextInt(50);
        Rectangle rectangle = new Rectangle(x, 600, width, 200);
        return rectangle;
    }

    public Scene reScene(){
        mainScene= new Scene(mainPane, 500, 800);
        mainScene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if(!isAlive){
                if(keyCode==KeyCode.R){
                    if(cherryCount>2){
                        cherryCount-=2;
                        newcontgame();
                    }
                    else{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
                        Parent root= null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Controller2 controller1 = loader.getController();
                        Scene scene1 = new Scene(root);
                        stage.setScene(scene1);
                        stage.show();
                    }
                }
                else if(keyCode==KeyCode.Q){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
                    Parent root= null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Controller2 controller1 = loader.getController();
                    Scene scene1 = new Scene(root);
                    stage.setScene(scene1);
                    stage.show();
                }
            } else if (keyCode ==KeyCode.SPACE) {
                System.out.println("flip");
                setFlipped(player);
            }
        });

        mainScene.setOnMousePressed(this::handleMousePress);

        mainScene.setOnMouseReleased(this::handleMouseReleased);
        return mainScene;
    }

    public static void setFlipped(ImageView imageView){
        if(isStill){
            return;
        }
        if(isFlipped){
            imageView.setRotate(0);
            imageView.setScaleX(-imageView.getScaleX());
            imageView.setTranslateY(imageView.getTranslateY()-40);
        }
        else {
            imageView.setRotate(180);
            imageView.setScaleX(-imageView.getScaleX());
            imageView.setTranslateY(imageView.getTranslateY()+40);
        }

        isFlipped = !isFlipped;

    }

    private void checkPlatformCollisions() {
        // Use a Timeline or AnimationTimer to continuously check for collisions
        // For simplicity, a basic AnimationTimer is used in this example

        javafx.animation.AnimationTimer timer = new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                // Check if bounding boxes intersect
                if (player.getBoundsInParent().intersects(platformNext.getBoundsInParent()) && isFlipped) {
                    if(!isAlive){
                       return;
                    }
                    System.out.println("Collision detected!");
                    isAlive = false;
                    MediaPlayer mediaPlayer=new MediaPlayer(new Media(getClass().getResource("collision.mp3").toString()));
                    mediaPlayer.play();
                    try {
                        gameOver();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
//                    gameOver();
                    // Handle collision logic here
                }
                else if(cherry!=null) {
                    if((player.getBoundsInParent().intersects(cherry.getBoundsInParent()) && isFlipped)){
                        if(cherryCollected){
                            return;
                        }
                        MediaPlayer mediaPlayer=new MediaPlayer(new Media(getClass().getResource("cherry.mp3").toString()));
                        mediaPlayer.play();
                        cherryCollected=true;
                        mainPane.getChildren().remove(cherry);
                        System.out.println("Cherry Collision detected!");
                        cherryCount++;
                    }
                    // Handle collision logic here
                }
            }
        };

        // Start the AnimationTimer
        timer.start();
    }



//    public static void main(String[] args) {
//        Application.launch(args);
//    }
}
