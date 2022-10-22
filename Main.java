import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.askUserName();
        game.askUserHeroNumber();
        game.askUserOfHeroType();
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
            if (userInput == 0) {
                System.out.println("Hey, you just entered a wrong Input. Is typing correctly on your keyboard to hard for you ?");
                isInputCorrect = false;
            }

        } while (!isInputCorrect);
        return userInput;
    }

    public static String askAString() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = userInput.replaceAll("\\s", "");
        if (userInput.equals("")) {
            userInput = "Asshole";
        }
        return userInput;
    }
}

class Game {
    public String nameOfThePlayer;
    public int numberOfHeros;

    public List<String> heroClassList;

    private Combatant combatant;

    public void askUserName() {
        System.out.println("Hello player on this new adventure !");
        System.out.print("Please remind me your name: ");
        nameOfThePlayer = askUserForInput.askAString();
        System.out.println();

    }

    public void askUserHeroNumber() {
        System.out.println("Oh, " + nameOfThePlayer + " ! Yeah I remember that now.");
        System.out.print("And... ahem, how many hero are going to fight by your side ? ");
        numberOfHeros = askUserForInput.askAnInt();
        System.out.println();
        while (numberOfHeros > 5) {
            System.out.println("Oh no no, you are too many on this adventure. Your teammates can be up to 5");
            System.out.print("So, how many hero are going to fight by your side ? ");
            System.out.println();
            numberOfHeros = askUserForInput.askAnInt();
        }
        System.out.println("Umm, so you are going to be " + numberOfHeros + " heros, that's a great start");
    }

    public void askUserOfHeroType() {
        List<Integer> takenHeros = new ArrayList<>();

        for (int i = 1; i <= numberOfHeros; i++) {
            System.out.println("What is the class of hero " + i + " hum ?");
            System.out.println(" 1/ Warrior \n 2/ Hunter \n 3/ Mage \n 4/ Healer");
            int userInput = askUserForInput.askAnInt();
            while (userInput > 4 || userInput < 1 || takenHeros.contains(userInput)) {
                System.out.println("You're choice isn't recognized or you already chose this class \n");
                userInput = askUserForInput.askAnInt();
            }

            takenHeros.add(userInput);
            settingUpHeroClass(userInput);
        }
        System.out.print("Alright, so your team is composed of: ");
        System.out.println(heroClassList);
        System.out.println("It look terrible but anyway, at least we can jump in");
    }

    public void settingUpHeroClass(int choiceAnswer) {
        heroClassList = new ArrayList<>();
        switch (choiceAnswer) {
            case 1 -> {
                Warrior warrior = new Warrior();
                heroClassList.add("Warrior");
                System.out.println("So this hero will be a warrior !");
            }
            case 2 -> {
                Hunter hunter = new Hunter();
                heroClassList.add("Hunter");
                System.out.println("So this hero will be a hunter !");
            }
            case 3 -> {
                Mage mage = new Mage();
                heroClassList.add("Mage");
                System.out.println("So this hero will be a mage !");
            }
            case 4 -> {
                Healer healer = new Healer();
                heroClassList.add("Healer");
                System.out.println("So this hero will be a healer !");
            }
        }
        System.out.println();
    }
}

abstract class Combatant {
    public String CombatantName;
    public int CombatantLifePoints;
    public int CombatantAttack;
    public int CombatantDefense;

}

class Enemy extends Combatant {

}

abstract class Hero extends Combatant {
    public String heroExperienceLevel;
}

class Warrior extends Hero {
    public int WarriorAttackBonusPoints;
}

class Hunter extends Hero {
    public int HunterArrowsNumber;
}

class Mage extends Hero {
    public int mageManaPoints;
}

class Healer extends Hero {
    public int healerDefenseBonusPoints;
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