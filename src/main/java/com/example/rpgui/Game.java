package com.example.rpgui;
import javafx.application.Application;

import java.util.*;
import static javafx.application.Application.launch;

public class Game {

    public InputParser inputParser;
    public String nameOfThePlayer;
    public int numberOfHeroesInput;
    public double difficultyCoef = 0.6;
    public List<Combatant> listOfHeroes = new ArrayList<>();

    public FightInstance fight;

    public Game() {
    }


    public void initializeGame() {
        this.inputParser = ChoseParserMode();
        initializeGameNext();

    }

    public void initializeGameNext(){
        InputParser.DeleteLastTwo();
        askUserHisName();
        askUserTheNumberOfHeroes();
        askUserTheClassOfHeroes();
        for (int numberOfFights = 1; numberOfFights <= 5; numberOfFights++) {
            initializeFight(numberOfFights);
            playFight(numberOfFights);
        }
        initializeBossFight();
        playFight(6);

        inputParser.PrintDialog("Okay cool you just won the game (finally)");
        inputParser.PrintDialog("Now get out of my world before I kick your ass");

    }

    public InputParser ChoseParserMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to play in Console mode (1) or in UI mode (2) ? ");
        String userInput = scanner.nextLine();

        if (userInput.equals("1") || userInput.contains("console") || userInput.equalsIgnoreCase("console") || userInput.replace("\\s", "").equals("")) {
            System.out.println("Game is starting in console mode... \n\n\n");
            inputParser = new ConsoleParser();
        } else {
            System.out.println("Starting UI mode... \n\n\n");
            inputParser = new UIParser();
            Application.launch(JavaFX.class);
            System.exit(0);
        }
        return inputParser;
    }


    public void initializeFight(int numberOfFights) {
        this.fight = new FightInstance(inputParser);
        if (numberOfFights > 1) upgradeHeroes();
        fight.creatingEnemiesWave(this);
        fight.gatheringHeroesAndMonster(this);
        fight.sortingCombatantsBySpeed();

        inputParser.PrintDialog("Hurry up " + nameOfThePlayer + " the fight is about to start !");
        inputParser.PressEnterToContinue();

    }

    public void initializeBossFight() {
        this.fight = new FightInstance(inputParser);
        upgradeHeroes();
        fight.creatingBossWave(this, inputParser);
        fight.sortingCombatantsBySpeed();
        inputParser.PrintDialog("Let's go " + nameOfThePlayer + " the end game is approaching");
        inputParser.PressEnterToContinue();

    }

    public void upgradeHeroes() {
        inputParser.PrintDialog("\nYour team have been healed to be ready for next fight");

        for (Combatant hero : listOfHeroes) {

            inputParser.PrintDialog("Which stats of your " + hero.name + " " + hero.combatantID + " do you want to upgrade ? ");
            inputParser.PrintDialog(" 1/ Attack \n 2/ Defense \n 3/ Life Points \n 4/ Speed");
            int userChoice = inputParser.AskAnIntBetween(1, 4);

            hero.attack = hero.maximumAttack;
            hero.defense = hero.maximumDefense;
            hero.lifePoints = hero.maximumLifePoints;
            hero.speed = hero.maximumSpeed;


            switch (userChoice) {

                case 1 -> hero.attack *= 1.25;
                case 2 -> hero.defense *= 1.25;
                case 3 -> {
                    hero.lifePoints *= 1.25;
                    hero.maximumLifePoints = hero.lifePoints;
                }
                case 4 -> hero.speed *= 1.25;
            }
            inputParser.PrintDialog("Boomm, the stat " + userChoice + " of your " + hero.name + " " + hero.combatantID + " has been increased by 25% \n");
        }
    }

    public void playFight(int numberOfFights) {
        fight.fightEngine();

        if (fight.isFightWon) {
            inputParser.PrintDialog("Congrats " + nameOfThePlayer + " you just won your fight " + numberOfFights + " !");
        } else {
            inputParser.PrintSeparation();
            inputParser.PrintDialog("Unforthunately, you're too weak to fight against those monsters");
            inputParser.PrintDialog("I shouldn't have given to you a pokemo... Uhm the chance of being here");
            inputParser.PrintDialog("Well get out now " + nameOfThePlayer);
            inputParser.PrintSeparation();
            System.exit(0);
        }
    }

    public void askUserHisName() {
        inputParser.PrintDialog("Welcome to the legend of Selma, the story where you will have to save the wonderful princess (me) from the evil teacher.");
        inputParser.PressEnterToContinue();
        askUserHisNameNext();
    }
    public void askUserHisNameNext(){
        inputParser.PrintDialogNoNewLine("Please remind me your name: ");

        nameOfThePlayer = (inputParser.AskAString()).replaceAll("\\s", "");

        if (nameOfThePlayer.equals("")) {
            nameOfThePlayer = "Dumbass";
        }
    }

    public void askUserTheNumberOfHeroes() {
        inputParser.PrintDialog("Oh, " + nameOfThePlayer + " ! Yeah I remember you now.\n");
        inputParser.PrintDialogNoNewLine("And... ahem, how many hero are going to fight by your side ? ");
        numberOfHeroesInput = inputParser.AskAnIntBetween(1, 5);

        inputParser.PrintDialog("Umm, so you are going to be " + numberOfHeroesInput + " heroes, that's a great start " + nameOfThePlayer + ".");
        inputParser.PressEnterToContinue();
    }

    public void askUserTheClassOfHeroes() {
        inputParser.PrintDialog("It's time to chose which heroes are going with you ?");
        inputParser.PrintDialog(" 1/ Warrior   Talent: Last Power      (When his life is under 25%, his attack is increased by 20%)");
        inputParser.PrintDialog(" 2/ Hunter    Talent: Brave Bow       (When his life is full, arrows are not used) ");
        inputParser.PrintDialog(" 3/ Mage      Talent: Soul gathering  (Damaging enemies produce a small amount of souls)");
        inputParser.PrintDialog(" 4/ Healer    Talent: Self Help       (Life points are increased by 10% if no damages are received during a turn) \n");
        for (int heroNumber = 1; heroNumber <= numberOfHeroesInput; heroNumber++) {
            inputParser.PrintDialog("What is the class of hero " + heroNumber + " hum ? ");

            int userInput = inputParser.AskAnIntBetween(1, 4);
            settingUpHeroClass(userInput, heroNumber);
        }
        inputParser.PrintDialog("Alright, there is your team : ");
        for (Combatant combatant : listOfHeroes) {
            inputParser.PrintDialogNoNewLine(combatant.name + ", ");
        }
        inputParser.PrintDialog("\nYour team look terrible but anyway, at least we can jump in.");
    }

    public void settingUpHeroClass(int choiceAnswer, int heroNumber) {
        switch (choiceAnswer) {
            case 1 -> {
                Warrior warrior = new Warrior();
                warrior.name = "Warrior";
                warrior.combatantID = heroNumber;
                warrior.attack = 1.6;
                warrior.maximumAttack = warrior.attack;
                warrior.defense = 0.80;
                warrior.maximumDefense = warrior.defense;
                warrior.lifePoints = 100;
                warrior.maximumLifePoints = 100;
                warrior.speed = 40;
                warrior.maximumSpeed = warrior.speed;
                warrior.talent = "Last Power";
                warrior.talentDescription = "When his life is under 25%, his attack is increased by 20%";
                this.listOfHeroes.add(warrior);
                inputParser.PrintDialog("Boooom! Hero number " + heroNumber + " is now a warrior");
            }
            case 2 -> {
                Hunter hunter = new Hunter();
                hunter.name = "Hunter";
                hunter.attack = 1.3;
                hunter.maximumAttack = hunter.attack;
                hunter.defense = 0.90;
                hunter.maximumDefense = hunter.defense;
                hunter.lifePoints = 135;
                hunter.maximumLifePoints = 135;
                hunter.speed = 75;
                hunter.maximumSpeed = hunter.speed;
                hunter.combatantID = heroNumber;
                hunter.arrowsNumber = 5;
                hunter.talent = "Brave Bow";
                hunter.talentDescription = "When his life is full, arrows are not used ";
                listOfHeroes.add(hunter);
                inputParser.PrintDialog("And snap ! Hero number " + heroNumber + " is now a hunter");
            }
            case 3 -> {
                Mage mage = new Mage();
                mage.name = "Mage";
                mage.attack = 1.1;
                mage.maximumAttack = mage.attack;
                mage.defense = 0.65;
                mage.maximumDefense = mage.defense;
                mage.lifePoints = 125;
                mage.maximumLifePoints = 125;
                mage.speed = 50;
                mage.maximumSpeed = mage.speed;
                mage.numberOfSouls = 20;
                mage.talent = "Soul gathering";
                mage.talentDescription = "Damaging enemies produce a small amount of souls";
                mage.combatantID = heroNumber;
                listOfHeroes.add(mage);
                inputParser.PrintDialog("Wooaaa ! Hero number " + heroNumber + " is now a mage");
            }
            case 4 -> {
                Healer healer = new Healer();
                healer.name = "Healer";
                healer.attack = 1;
                healer.maximumAttack = healer.attack;
                healer.defense = 0.90;
                healer.maximumDefense = healer.defense;
                healer.lifePoints = 80;
                healer.maximumLifePoints = 80;
                healer.currentLifePoints = 0;
                healer.speed = 45;
                healer.maximumSpeed = healer.speed;
                healer.talent = "Self Help";
                healer.talentDescription = "Life points are increased by 10% if no damages are received during a turn";
                healer.combatantID = heroNumber;
                listOfHeroes.add(healer);
                inputParser.PrintDialog("Flouf ! Hero number " + heroNumber + " is now a healer");
            }
            default -> throw new IllegalStateException("Unexpected value: " + choiceAnswer);
        }
        inputParser.PressEnterToContinue();
    }
}
