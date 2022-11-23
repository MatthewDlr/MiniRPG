package com.example.rpgui;
import java.util.ArrayList;

public interface InputParser {

    void pressEnterToContinue();

    void printSeparation();

    void printDialog(String textToPrint);

    void printDialogNoNewLine(String textToPrint);

    void printFightCombatantLife(ArrayList<Combatant> listOfEnemiesInFight, ArrayList<Combatant> listOfHeroesInFight);

    void printCombatantOrder(ArrayList<Combatant> listOfCombatantsInFight);

    void printEnemiesLife(ArrayList<Combatant> listOfEnemiesInFight);

    int AskAnInt();

    int AskAnIntBetween(int min, int max);

    int AskAnEnemyBetween(int min, int max);

    String AskAString() ;

}