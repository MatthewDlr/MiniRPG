package com.example.rpgui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;


public class UIParser implements InputParser {

    private static Stage mainStage;
    private static Game game;
    private static Group mainGroup;
    private static Scene mainScene;

    public static void setupGameTitleScreen(Stage stage, Game gameInput) {
        mainStage = stage;
        game = gameInput;
        Image image = new Image("file:///C:/Users/matth/OneDrive%20-%20ISEP/A1/Algorithmes%20et%20Programmation/Codes/RPG%20UI/RPG-UI/src/main/java/com/example/rpgui/Ressources/TitleScreen.png");
        ImageView background = new ImageView(image);

        Button button = new Button();
        button.setText("Start Game");
        Font font = Font.font("Montserrat", FontWeight.BOLD, 25);
        button.setFont(font);
        button.setOnAction(e -> setupGameFirstTalk());

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(background);
        layout.getChildren().add(button);

        AnchorPane.setTopAnchor(button, 740d);
        AnchorPane.setRightAnchor(button, 715d);

        Scene scene = new Scene(layout, 1920, 1080);
        mainStage.setScene(scene);
        mainStage.setFullScreen(true);
    }

    public static void setupGameFirstTalk() {
        Image image = new Image("file:///C:/Users/matth/OneDrive%20-%20ISEP/A1/Algorithmes%20et%20Programmation/Codes/RPG%20UI/RPG-UI/src/main/java/com/example/rpgui/Ressources/Princess2.png");
        ImageView background = new ImageView(image);

        Button button = new Button();
        button.setText("Start Speaking");

        Font font = Font.font("Montserrat", FontWeight.NORMAL, 20);
        button.setFont(font);
        button.setOnAction(e -> game.initializeGameNext());
        button.setTranslateX(1375);
        button.setTranslateY(910);

        Label text = new Label("");
        text.setFont(Font.font("Montserrat", FontWeight.NORMAL, 30));
        text.setTextFill(Color.WHITE);
        text.setWrapText(true);
        text.setMaxWidth(1000);
        text.setTranslateX(440);
        text.setTranslateY(810);
        mainGroup = new Group();
        mainGroup.getChildren().addAll(background, button, text);

        mainScene = new Scene(mainGroup, 1920, 1080);
        mainStage.setScene(mainScene);
        mainStage.setFullScreen(true);

    }

    @Override
    public void PressEnterToContinue() {
        
        Button button = new Button();
        button.setText("Continue");
        Font font = Font.font("Montserrat", FontWeight.NORMAL, 20);
        button.setFont(font);
        button.setOnAction(e -> game.askUserHisNameNext());
        button.setTranslateX(1400);
        button.setTranslateY(910);
        mainGroup.getChildren().add(button);


    }

    @Override
    public void PrintSeparation() {

    }

    @Override
    public void PrintDialog(String textToPrint) {

        Label text = new Label(textToPrint);
        text.setFont(Font.font("Montserrat", FontWeight.NORMAL, 30));
        text.setTextFill(Color.WHITE);
        text.setWrapText(true);
        text.setMaxWidth(1000);
        text.setTranslateX(440);
        text.setTranslateY(810);

        mainGroup.getChildren().addAll(text);
        mainScene.setRoot(mainGroup);
        mainStage.setScene(mainScene);


    }


    @Override
    public void PrintDialogNoNewLine(String textToPrint) {
        PrintDialog(textToPrint);
    }

    @Override
    public void PrintFightCombatantLife(ArrayList<Combatant> listOfEnemiesInFight, ArrayList<Combatant> listOfHeroesInFight) {

    }

    @Override
    public void PrintCombatantOrder(ArrayList<Combatant> listOfCombatantsInFight) {

    }

    @Override
    public void PrintEnemiesLife(ArrayList<Combatant> listOfEnemiesInFight) {

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

    public static void DeleteAll() {
        mainGroup.getChildren().clear();
    }

    public static void DeleteLast() {
        mainGroup.getChildren().remove(mainGroup.getChildren().size() - 1);
    }

    public static void DeleteLastTwo() {
        mainGroup.getChildren().remove(mainGroup.getChildren().size() - 1);
        mainGroup.getChildren().remove(mainGroup.getChildren().size() - 1);
    }
}
