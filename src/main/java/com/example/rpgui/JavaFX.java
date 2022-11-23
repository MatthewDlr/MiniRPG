package com.example.rpgui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JavaFX extends Application {
    static Game game ;
    public static void main(String[] args) {
        game = new Game();
        game.initializeGame();
    }

    @Override
    public void start(Stage firstStage) throws Exception {
        firstStage.setTitle ("The legend of Selma") ;
        //Code to add background image
        Image image=new Image("C:\\Users\\matth\\OneDrive - ISEP\\A1\\Algorithmes et Programmation\\Codes\\RPG UI\\RPG-UI\\src\\main\\resources\\com\\example\\rpgui\\Anim.jpg");
        ImageView mv=new ImageView(image);

        Button button =  new Button() ;
        button.setText("Access to Animus");
        Font font = Font.font("Montserrat", FontWeight.BOLD, 20);
        button.setFont(font);
        button.setOnAction(e -> game.initializeGameNext());

        StackPane layout = new StackPane() ;
        layout.getChildren().addAll(mv);
        layout.getChildren().add(button) ;
        Scene scene = new Scene (layout, 1280, 720);
        firstStage.setScene(scene);
        firstStage.setResizable(true);
        firstStage.show();
    }

}