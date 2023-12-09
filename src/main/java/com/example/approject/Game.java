package com.example.approject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button pauseButton;
    private Rectangle platformCurrent=null;
    private Rectangle platformNext=null;
    private ImageView cherry=null;
    private Scoreboard scoreboard=null;
    private Scoreboard highScoreboard=null;
    private Scoreboard cherryScore=null;
    private Scoreboard lastScoreboard=null;
    private Scoreboard totalCherryScore=null;
    private Rectangle stick;
    private Player playerObject;
    private ImageView player;
    public static boolean isFlipped=false;
    public boolean isIsFlipped() {
        return isFlipped;
    }
    private static boolean isStill=true;
    private static boolean isRotated=false;
    private boolean isAlive=true;
    private boolean cherryCollected=false;
    private boolean transitioning=false;
    public int getCurrentScore() {
        return currentScore;
    }
    private int currentScore=0;
    private int highScore=0;
    private int cherryCount=0;
    private int lastScore=0;
    private int totalCherries=0;
    private double dist;
    private MediaPlayer bgmusicplayer;
    Random random = new Random();
    @Override
    public void start(Stage primaryStage) throws Exception {

        stage=primaryStage;
        mainPane=setup();
        mainScene = new Scene(mainPane, 500, 800);
        bgmusicplayer=new MediaPlayer(new Media(getClass().getResource("bg.mp3").toString()));
        bgmusicplayer.play();
        bgmusicplayer.volumeProperty().setValue(0.5);
        mainScene.setOnKeyPressed(event -> {


            KeyCode keyCode = event.getCode();
            if(!isAlive){
                if(keyCode==KeyCode.R){
                    if(cherryCount>1){
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

        GamePlatform gamePlatform = new GamePlatform(1,1,1,1,1);
        gamePlatform.setDistanceX(0);
        gamePlatform.setPerfectPoint(0);
        gamePlatform.setPositionX(0);
        gamePlatform.setWidth(0);
        gamePlatform.setVisible(true);
        gamePlatform.hide();

        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.setTitle("Stick Hero");
        stage.setScene(mainScene);
        stage.show();
    }

    private void handleMousePress(MouseEvent event) {
        if(transitioning){
            return;
        }
        System.out.println("press");
        if (isRotated){
            return;
        }
        transitioning=true;
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
        transitioning=false;
    }

    private void handleMouseReleased(MouseEvent event) {
        if(transitioning){
            return;
        }
        if (sh != null && sy != null) {
            sh.stop();
            sy.stop();
        }
        transitioning=true;
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
            transitioning=false;
            if (isAlive && stick.getHeight() < platformNext.getX() - platformCurrent.getX() -
                    platformCurrent.getWidth() || stick.getHeight()>platformNext.getX()+platformNext.getWidth() -
                    platformCurrent.getX() - platformCurrent.getWidth()){
                isAlive=false;
                bgmusicplayer.stop();
                MediaPlayer mediaPlayer=new MediaPlayer(new Media(getClass().getResource("collision.mp3").toString()));
                mediaPlayer.play();
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
        if (transitioning){
            return;
        }
        transitioning=true;
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

        transitioning=false;
        translateTransition4.setOnFinished(event -> {
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
            out.println(currentScore);
            out.println(Math.max(highScore, currentScore));
            out.println(Math.max(cherryCount, totalCherries));
        }
        finally {
            if(out!=null){
                out.close();
            }
        }
    }


    public ArrayList deserializeHigh() throws IOException, ClassNotFoundException {
            BufferedReader in = null;
            try {
                 in = new BufferedReader( new FileReader("highscore.txt"));
                 Integer ls= Integer.parseInt(in.readLine());
                 Integer hs= Integer.parseInt(in.readLine());
                 Integer cs= Integer.parseInt(in.readLine());
                 ArrayList<Integer> arr = new ArrayList<>();
                    arr.add(ls);
                    arr.add(hs);
                    arr.add(cs);
                    return arr;
            }

            finally {
                if(in!=null) {
                    in.close();
                }
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
        platformCurrent.setFill(Color.GHOSTWHITE);
        platformNext.setFill(Color.GHOSTWHITE);

        stick = new Rectangle(0,0, Color.MEDIUMPURPLE);
        stick.setWidth(5);
        playerObject=Player.getPlayer();
        playerObject.setPlayerimage(new ImageView(Controller1.getCharacter()));
        player = Player.getPlayer().getplayerimage();
        player.setFitHeight(30);
        player.setFitWidth(30);
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
            cherry.setFitHeight(20);
            cherry.setFitWidth(20);
            cherry.setX(platformCurrent.getX()+platformCurrent.getWidth()+cherry.getFitWidth());
            cherry.setY(platformCurrent.getY());
            mainPane.getChildren().add(cherry);
        }

        mainPane.getChildren().addAll(platformCurrent, platformNext, stick, player);

//        mainPane.getChildren().addAll(player);
        Image backgroundImage = new Image("bg1.png");

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        mainPane.setBackground(new Background(background));

        if(lastScoreboard!=null){
            mainPane.getChildren().remove(lastScoreboard);
        }
        if(totalCherryScore!=null){
            mainPane.getChildren().remove(totalCherryScore);
        }
        if(scoreboard!=null){
            mainPane.getChildren().remove(scoreboard);
        }
        if(highScoreboard!=null){
            mainPane.getChildren().remove(highScoreboard);
        }
        if(cherryCount!=0){
            mainPane.getChildren().remove(cherryScore);
        }

        if(currentScore==0){
            try {
                ArrayList<Integer> arr = deserializeHigh();
                lastScore=arr.get(0);
                highScore=arr.get(1);
                totalCherries=Math.max(arr.get(2),cherryCount);

                lastScoreboard = new Scoreboard("LastScore: "+lastScore,"black");
                totalCherryScore = new Scoreboard("TotalCherries: "+totalCherries,"red");
                highScoreboard = new Scoreboard("HighScore: "+highScore,"black");
            } catch (Exception e) {
                lastScoreboard = new Scoreboard("LastScore: "+this.lastScore,"black");
                totalCherryScore = new Scoreboard("TotalCherries: "+this.totalCherries,"red");
                highScoreboard = new Scoreboard("HighScore: "+this.highScore,"black");
            }
        }
        else{
            highScoreboard = new Scoreboard("HighScore: "+this.highScore,"black");
        }

        scoreboard = new Scoreboard("Score: "+currentScore,"black");
        cherryScore = new Scoreboard("Cherry: "+cherryCount,"red");


        scoreboard.update();
        highScoreboard.update();
        cherryScore.update();
        lastScoreboard.update();
        totalCherryScore.update();

        mainPane.getChildren().add(scoreboard);
        mainPane.getChildren().add(highScoreboard);
        mainPane.getChildren().add(cherryScore);
        mainPane.getChildren().add(lastScoreboard);
        mainPane.getChildren().add(totalCherryScore);

        AnchorPane.setTopAnchor(scoreboard, 10.0);
        AnchorPane.setLeftAnchor(scoreboard, 10.0);

        AnchorPane.setTopAnchor(cherryScore, 30.0);
        AnchorPane.setLeftAnchor(cherryScore, 10.0);

        AnchorPane.setTopAnchor(highScoreboard, 10.0);
        AnchorPane.setRightAnchor(highScoreboard, 10.0);

        AnchorPane.setTopAnchor(lastScoreboard, 30.0);
        AnchorPane.setRightAnchor(lastScoreboard, 10.0);

        AnchorPane.setTopAnchor(totalCherryScore, 50.0);
        AnchorPane.setRightAnchor(totalCherryScore, 10.0);

        isFlipped=false;
        isStill=true;
        isRotated=false;
        cherryCollected=false;
        isAlive=true;
        return mainPane;
    }

    public Rectangle randomRectangle(){
        int width = random.nextInt(100)+50;
        int x = random.nextInt(50);
        Rectangle rectangle = new Rectangle(x, 600, width, 200);
        rectangle.setFill(Color.GHOSTWHITE);
        return rectangle;
    }

    public Scene reScene(){
        mainScene= new Scene(mainPane, 500, 800);
        mainScene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if(!isAlive){
                if(keyCode==KeyCode.R){
                    if(cherryCount>1){
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

        javafx.animation.AnimationTimer timer = new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                if (player.getBoundsInParent().intersects(platformNext.getBoundsInParent()) && isFlipped) {
                    if(!isAlive){
                       return;
                    }
                    System.out.println("Collision detected!");
                    isAlive = false;
                    bgmusicplayer.stop();
                    MediaPlayer mediaPlayer=new MediaPlayer(new Media(getClass().getResource("collision.mp3").toString()));
                    mediaPlayer.play();
                    try {
                        gameOver();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
                        totalCherries++;
                    }
                }
            }
        };

        timer.start();
    }
}
