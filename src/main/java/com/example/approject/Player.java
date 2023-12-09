package com.example.approject;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.transform.Scale;

import java.util.Vector;

public class Player {

    private ImageView playerimage;

    private static Player playerobj=null;

    public static Player getPlayer(){
        if(playerobj==null){
            playerobj=new Player();
        }
        return playerobj;
    }
    private Player() {
        playerimage=null;
    }


    public void setPlayerimage(ImageView imageView) {
        this.playerimage = imageView;
    }

    public ImageView getplayerimage() {
        return playerimage;
    }
}
