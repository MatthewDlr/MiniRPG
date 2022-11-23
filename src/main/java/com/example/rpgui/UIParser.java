package com.example.rpgui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.application.Application.launch;


public class UIParser implements InputParser {

    public void setupGameTitleScreen() {
        Stage titleScreen = new Stage();
        Image image = new Image("C:\\Users\\matth\\OneDrive - ISEP\\A1\\Algorithmes et Programmation\\Codes\\RPG UI\\RPG-UI\\src\\main\\resources\\com\\example\\rpgui\\Anim.jpg");
        ImageView background = new ImageView(image);

        Button button = new Button();
        button.setText("Start Game");
        Font font = Font.font("Montserrat", FontWeight.BOLD, 20);
        button.setFont(font);
        //button.setOnAction(e -> );

        StackPane layout = new StackPane();
        layout.getChildren().addAll(background);
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 1280, 720);
        titleScreen.setScene(scene);
        titleScreen.setResizable(true);
        titleScreen.show();
    }

    @Override
    public void pressEnterToContinue() {

    }

    @Override
    public void printSeparation() {

    }

    @Override
    public void printDialog(String textToPrint) {

    }

    @Override
    public void printDialogNoNewLine(String textToPrint) {

    }

    @Override
    public void printFightCombatantLife(ArrayList<Combatant> listOfEnemiesInFight, ArrayList<Combatant> listOfHeroesInFight) {

    }

    @Override
    public void printCombatantOrder(ArrayList<Combatant> listOfCombatantsInFight) {

    }

    @Override
    public void printEnemiesLife(ArrayList<Combatant> listOfEnemiesInFight) {

    }


    @Override
    public int AskAnInt() {
        return 0;
    }

    @Override
    public int AskAnIntBetween(int min, int max) {
        return 0;
    }

    @Override
    public int AskAnEnemyBetween(int min, int max) {
        return 0;
    }

    @Override
    public String AskAString() {
        return null;
    }
}