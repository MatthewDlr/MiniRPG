package com.example.rpgui;
import java.util.ArrayList;

public interface InputParser {

    void PressEnterToContinue();

    void PrintSeparation();

    void PrintDialog(String textToPrint);

    void PrintDialogNoNewLine(String textToPrint);

    void PrintFightCombatantLife(ArrayList<Combatant> listOfEnemiesInFight, ArrayList<Combatant> listOfHeroesInFight);

    void PrintCombatantOrder(ArrayList<Combatant> listOfCombatantsInFight);

    void PrintEnemiesLife(ArrayList<Combatant> listOfEnemiesInFight);

    int AskAnInt();

    int AskAnIntBetween(int min, int max);

    int AskAnEnemyBetween(int min, int max);

    String AskAString() ;

    static void DeleteLastTwo() {

    }

}