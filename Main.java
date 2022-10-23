import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.askUserHisName();
        game.askUserTheNumberOfHeros();
        game.askUserTheClassOfHeros();
        game.creatingEnemiesWave();
    }
}

// Rule N°1 : Never trust the user, these functions check that the inputs are good
class askUserForInput {

    public static int askAnInt() {
        boolean isInputCorrect;
        int userInput = 0;
        do {
            isInputCorrect = true;
            Scanner scanner = new Scanner(System.in);
            try {
                userInput = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Hey, you just entered a wrong Input. Is typing correctly on your keyboard to hard for you ?");
                isInputCorrect = false;
            }
            if (userInput <= 0) {
                System.out.println("Hey, you just entered a wrong Input. Is typing correctly on your keyboard to hard for you ?");
                isInputCorrect = false;
            }

        } while (!isInputCorrect);
        return userInput;
    }

    public static String askAString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

class Game {
    public String nameOfThePlayer;
    public int numberOfHerosInput;

    public int difficultyCoef = 0 ;
    public List<String> listOfHeroClassNames = new ArrayList<>();
    public List<Warrior> listOfWarriorsHeros = new ArrayList<>();
    public ArrayList<Healer> listOfHealerHeros = new ArrayList<>();
    public ArrayList<Mage> listOfMageHeros = new ArrayList<>();
    public ArrayList<Hunter> listOfHunterHeros = new ArrayList<>();
    public ArrayList<Enemy> listOfEnemies = new ArrayList<>();

    public void askUserHisName() {
        System.out.println("Hello player on this new adventure !");
        System.out.print("Please remind me your name: ");
        nameOfThePlayer = (askUserForInput.askAString()).replaceAll("\\s", "");

        if (nameOfThePlayer.equals("")) {
            nameOfThePlayer = "Dumbass";
        }

        System.out.println();
    }

    public void askUserTheNumberOfHeros() {
        System.out.println("Oh, " + nameOfThePlayer + " ! Yeah I remember you now.");
        System.out.print("And... ahem, how many hero are going to fight by your side ? ");
        numberOfHerosInput = askUserForInput.askAnInt();
        System.out.println("\n");
        while (numberOfHerosInput > 5) {
            System.out.println("Oh no no, you are too many on this adventure. Your teammates can be up to 5");
            System.out.print("So, how many hero are going to fight by your side ? ");
            System.out.println("\n");
            numberOfHerosInput = askUserForInput.askAnInt();
        }
        System.out.println("Umm, so you are going to be " + numberOfHerosInput + " heros, that's a great start " + nameOfThePlayer + ". \n");
    }

    public void askUserTheClassOfHeros() {

        for (int heroNumber = 1; heroNumber <= numberOfHerosInput; heroNumber++) {
            System.out.println("What is the class of hero " + heroNumber + " hum ?");
            System.out.println(" 1/ Warrior \n 2/ Hunter \n 3/ Mage \n 4/ Healer");

            int userInput = askUserForInput.askAnInt();
            while (userInput > 4 || userInput < 1) {
                System.out.println("You're choice isn't recognized \n");
                userInput = askUserForInput.askAnInt();
            }
            settingUpHeroClass(userInput, heroNumber);
        }
        System.out.println("Alright, there is your team : " + listOfHeroClassNames.toString().replace("[", "").replace("]", "") );
        System.out.println("Your team look terrible but anyway, at least we can jump in.");
    }

    public void settingUpHeroClass(int choiceAnswer, int heroNumber) {
        switch (choiceAnswer) {
            case 1 -> {
                Warrior warrior = new Warrior();
                warrior.setHeroNumber(heroNumber);
                warrior.attack = 1.6;
                warrior.defense = 0.80;
                warrior.lifePoints = 100 ;
                warrior.speed = 40;
                listOfHeroClassNames.add(warrior.CombatantName);
                listOfWarriorsHeros.add(warrior);
                System.out.println("Boooom! Hero number " + heroNumber + " is now a warrior");
            }
            case 2 -> {
                Hunter hunter = new Hunter();
                hunter.attack = 1.3;
                hunter.defense = 0.90;
                hunter.lifePoints = 135;
                hunter.speed = 75 ;
                hunter.setHeroNumber(heroNumber);
                listOfHeroClassNames.add(hunter.CombatantName);
                listOfHunterHeros.add(hunter);
                System.out.println("And snap ! Hero number " + heroNumber + " is now a hunter");
            }
            case 3 -> {
                Mage mage = new Mage();
                mage.attack = 1.1;
                mage.defense = 0.65;
                mage.lifePoints = 125;
                mage.speed = 50;
                mage.setHeroNumber(heroNumber);
                listOfHeroClassNames.add(mage.CombatantName);
                listOfMageHeros.add(mage);
                System.out.println("Fiouff ! Hero number " + heroNumber + " is now a mage");
            }
            case 4 -> {
                Healer healer = new Healer();
                healer.attack = 1;
                healer.defense = 0.90;
                healer.lifePoints = 80;
                healer.speed = 45;
                healer.setHeroNumber(heroNumber);
                listOfHeroClassNames.add(healer.CombatantName);
                listOfHealerHeros.add(healer);
                System.out.println("Wooaaa ! Hero number " + heroNumber + " is now a healer");
            }
        }
        System.out.println();
    }
    public void creatingEnemiesWave(){
        difficultyCoef++;
        for (int enemyNumber =  1 ; enemyNumber <= numberOfHerosInput; enemyNumber++) {
            Enemy enemy = new Enemy();
            enemy.setEnemyNumber(enemyNumber);
            enemy.attack = roundADouble(randomDouble(0.5, 0.7)*difficultyCoef);
            enemy.defense = roundADouble( 1 - (randomDouble(0.065, 0.11)*difficultyCoef));
            enemy.lifePoints = randomInt(50, 75)*Math.max(1,difficultyCoef/2);
            enemy.speed = randomInt(20, 40)*difficultyCoef;
            listOfEnemies.add(enemy);
        }
        System.out.println("Well, " + nameOfThePlayer + " you'll have 5 waves of enemies to beat before challenging the boss mouhahahahaaha");
    }

    public int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    public double randomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
    public double roundADouble(double value){
        value *= 100;
        value = Math.round(value);
        value /= 100;
        return value;
    }
}

abstract class Combatant extends Game {
    public String name;
    public int lifePoints;
    public double attack;
    public double defense; // The lower the better (between 0 and 1)
    public int speed; // The higher the better (between 0 and 100)

}

class Enemy extends Combatant {
    private int enemyNumber;

    public int getEnemyNumber() {
        return enemyNumber;
    }

    public void setEnemyNumber(int enemyNumber) {
        this.enemyNumber = enemyNumber;
    }
}

abstract class Hero extends Combatant {
    public String heroExperienceLevel;
    private int heroNumber;
    public int getHeroNumber() {
        return heroNumber;
    }

    public void setHeroNumber(int heroNumber) {
        this.heroNumber = heroNumber;
    }
}

class Warrior extends Hero {
    String CombatantName = "Warrior";

}

class Hunter extends Hero {
    String CombatantName = "Hunter";
    public int HunterArrowsNumber = 15;
}

class Mage extends Hero {
    String CombatantName = "Mage";
    public int mageManaPoints = 10;
}

class Healer extends Hero {
    String CombatantName = "Healer";
    public int healerDefenseBonusPoints = 5;
}

abstract class Item {
    public String itemName;
    public String itemDescription;
}

class Weapon extends Item {
    public int weaponAttack;
    public int weaponRange;
}

abstract class Consumable {
    public int ConsumableLifePointsBonus;
}

class Food extends Consumable {
}

class Potion extends Consumable {

}