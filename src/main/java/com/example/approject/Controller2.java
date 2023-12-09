package com.example.approject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller2 {
    @FXML
    private void switchToScene3(ActionEvent event) throws Exception {
        Game game=new Game();
        game.start((Stage)((Node)event.getSource()).getScene().getWindow());
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
