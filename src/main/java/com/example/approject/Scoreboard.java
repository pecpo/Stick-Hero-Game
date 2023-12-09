package com.example.approject;//package com.example.approject;

import javafx.scene.text.Text;

import java.io.Serializable;

public class Scoreboard extends Text implements Serializable, Board{

    public Scoreboard(String s, String color){
        super(s);
        if(color.equals("red")){
            setStyle("-fx-font-size: 18; -fx-text-fill: red; -fx-font-weight: bold;");
        }
        else{
            setStyle("-fx-font-size: 18; -fx-text-fill: red; -fx-font-weight: bold;");
        }
        // Additional styling and formatting can be added as needed
    }

    public void update(){
        setStyle("-fx-font-size: 18; -fx-text-fill: purple; -fx-font-weight: bold;");
    }

}