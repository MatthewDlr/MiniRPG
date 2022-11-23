package com.example.rpgui;
import static java.lang.System.*;
import java.util.*;

// Rule N°1 : Never trust the user, these functions check that the inputs are good
public class ConsoleParser implements InputParser {

    @Override
    public void pressEnterToContinue() {
        out.println("Press enter to continue ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        out.println();
    }

    @Override
    public void printSeparation() {
        for (int i = 0; i < 150; i++) {
            out.print("-");
        }
        printDialog("");
    }

    @Override
    public void printDialog(String textToPrint) {
        out.println(textToPrint);
    }

    @Override
    public void printDialogNoNewLine(String textToPrint) {
        out.print(textToPrint);
    }

    @Override
    public void printFightCombatantLife(ArrayList<Combatant> listOfEnemiesInFight, ArrayList<Combatant> listOfHeroesInFight) {

        printSeparation();
        for (Combatant enemy : listOfEnemiesInFight) {
            printDialogNoNewLine("Enemy " + enemy.combatantID + " : " + enemy.lifePoints + "/" + enemy.maximumLifePoints + " hp   |   ");
        }
        printDialog("\n");
        for (Combatant hero : listOfHeroesInFight) {
            printDialogNoNewLine(hero.name + " " + hero.combatantID + " : " + hero.lifePoints + "/" + hero.maximumLifePoints + " hp   |   ");
        }
        printDialog("");
        printSeparation();
    }

    @Override
    public void printCombatantOrder(ArrayList<Combatant> listOfCombatantsInFight) {
        int number = 1;
        printSeparation();
        printDialog("Here is the order in which the Combatants are going to attack this tour \n(Ordered by combatant's speed and heroes with prioritized attacks)");
        for (Combatant combatant : listOfCombatantsInFight) {
            if (number == 10) printDialog(number + ": " + combatant.name);
            else printDialog(number + " : " + combatant.name + " " + combatant.combatantID);
            number++;
        }
        printSeparation();
        pressEnterToContinue();
    }

    @Override
    public void printEnemiesLife(ArrayList<Combatant> listOfEnemiesInFight) {

        printSeparation();
        int i = 0;
        for (Combatant enemy : listOfEnemiesInFight) {
            printDialogNoNewLine("Enemy " + i + " : " + enemy.lifePoints + "/" + enemy.maximumLifePoints + " hp   |   ");
            i++;
        }
        printDialog("");
        printSeparation();
    }


    @Override
    public int AskAnInt() {
        out.print("\nWhat is your choice ? ");
        int userInput = 0;
        boolean isCorect;

        do {
            isCorect = true;
            Scanner scanner = new Scanner(System.in);

            try {
                userInput = scanner.nextInt();
            } catch (Exception e) {
                out.println("Bad input, your choice must be a number");
                isCorect = false;
            }
        } while (!isCorect);

        out.println();
        return userInput;
    }

    @Override
    public int AskAnIntBetween(int min, int max) {
        out.print("\nWhat is your choice ? ");
        int userInput = 0;
        boolean isCorect;

        do {

            Scanner scanner = new Scanner(System.in);
            isCorect = true;

            try {
                userInput = scanner.nextInt();
            } catch (Exception e) {
                out.println("Bad input, your choice must be a number");
                isCorect = false;
            }

            if (userInput < min || userInput > max) {
                out.println("Bad input, choice is out of range");
                isCorect = false;
            }
        } while (!isCorect);

        out.println();
        return userInput;
    }

    @Override
    public int AskAnEnemyBetween(int min, int max) {
        System.out.print("Which enemy do you want to attack ? ");
        int targetChoice = AskAnIntBetween(min, max);

        System.out.println("Got it, Enemy " + targetChoice + " will be attacked \n");
        return targetChoice;
    }

    @Override
    public String AskAString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

