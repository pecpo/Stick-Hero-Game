package com.example.approject;//package com.example.approject;
//
//public class Scoreboard {
//    private int score;
//
//    public Scoreboard() {
//        this.score = 0;
//    }
//
//    public int getScore() {
//        return score;
//    }
//
//    public void increaseScore(int points) {
//        if (points > 0) {
//            score += points;
//        }
//    }
//
//    public void resetScore() {
//        score = 0;
//    }
//}
import javafx.scene.text.Text;

public class Scoreboard extends Text {

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


}