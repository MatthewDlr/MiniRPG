import java.util.*;


public class Game {
    public String nameOfThePlayer;
    public int numberOfHerosInput;
    public double difficultyCoef = 0.47;
    public List<Combatant> listOfHeros = new ArrayList<>();

    public FightInstance fight;

    public void initializeGame() {
        askUserHisName();
        askUserTheNumberOfHero();
        askUserTheClassOfHeros();
        //gameInitialize.settingUpHeroClass(); this function is automatically called by the previous one ; it is just displaye as information title.
    }

    public void initializeFight() {
        this.fight = new FightInstance();
        fight.creatingEnemiesWave(this);
        fight.gatheringAliveHerosAndMonster(this);
        fight.sortingCombatantsBySpeed();
        System.out.println("Hurry up " + nameOfThePlayer + " the fight is about to start !");
        System.out.println("Press any key to continue");
        AskUserForInput.askAString();

    }

    public void playFight(int numberOfFights) {
        fight.fightEngine();

        if (fight.isFightWon ){
            System.out.println("Congrats " + nameOfThePlayer + " you just won your fight " + numberOfFights + " !");
        } else {
            System.out.println("\n-------------------------------------------------------------------------------------------------");
            System.out.println("Unforthunately, you're too weak to fight against these monsters");
            System.out.println("I shouldn't have given to you a pokemo... Uhm the chance of being here");
            System.out.println("Well get out now " + nameOfThePlayer);
            System.exit(0);
        }

    }

    public void askUserHisName() {
        System.out.println("Hello player on this new adventure !");
        System.out.print("Please remind me your name: ");
        nameOfThePlayer = (AskUserForInput.askAString()).replaceAll("\\s", "");

        if (nameOfThePlayer.equals("")) {
            nameOfThePlayer = "Dumbass";
        }
        System.out.println();
    }

    public void askUserTheNumberOfHero() {
        System.out.println("Oh, " + nameOfThePlayer + " ! Yeah I remember you now.");
        System.out.print("And... ahem, how many hero are going to fight by your side ? ");
        numberOfHerosInput = AskUserForInput.askAnInt();
        System.out.println();
        while (numberOfHerosInput > 5) {
            System.out.println("Oh no no, you are too many on this adventure. Your teammates can be up to 5");
            System.out.print("So, how many hero are going to fight by your side ? ");
            numberOfHerosInput = AskUserForInput.askAnInt();
        }
        System.out.println("Umm, so you are going to be " + numberOfHerosInput + " heros, that's a great start " + nameOfThePlayer + ".");
        System.out.print("Press any key to continue");
        AskUserForInput.askAString();
    }

    public void askUserTheClassOfHeros() {
        System.out.println("It's time to chose which heroes are going with you ?");
        System.out.println(" 1/ Warrior   Talent: Last Power      (When his life is under 25%, his attack is increased by 20%)");
        System.out.println(" 2/ Hunter    Talent: Brave Bow       (When his life is full, arrows are not used) ");
        System.out.println(" 3/ Mage      Talent: Soul gathering  (Damaging enemies produce a small amount of souls)");
        System.out.println(" 4/ Healer    Talent: Self Help       (Life points are increased by 10% if no damages are received during a turn) \n");
        for (int heroNumber = 1; heroNumber <= numberOfHerosInput; heroNumber++) {
            System.out.print("What is the class of hero " + heroNumber + " hum ? ");

            int userInput = AskUserForInput.askAnInt();
            while (userInput > 4 || userInput < 1) {
                System.out.print("You're choice isn't recognized");
                userInput = AskUserForInput.askAnInt();
            }
            settingUpHeroClass(userInput, heroNumber);
        }
        System.out.print("Alright, there is your team : ");
        for (Combatant combatant : listOfHeros) {
            System.out.print(combatant.name + ", ");
        }
        System.out.println("\nYour team look terrible but anyway, at least we can jump in.");
    }

    public void settingUpHeroClass(int choiceAnswer, int heroNumber) {
        switch (choiceAnswer) {
            case 1 -> {
                Warrior warrior = new Warrior();
                warrior.name = "Warrior";
                warrior.combatantID = heroNumber;
                warrior.attack = 1.6;
                warrior.defense = 0.80;
                warrior.lifePoints = 100;
                warrior.maximumLifePoints = 100;
                warrior.speed = 40;
                warrior.talent = "Last Power";
                warrior.talentDescription = "When his life is under 25%, his attack is increased by 20%";
                this.listOfHeros.add(warrior);
                System.out.println("Boooom! Hero number " + heroNumber + " is now a warrior");
            }
            case 2 -> {
                Hunter hunter = new Hunter();
                hunter.name = "Hunter";
                hunter.attack = 1.3;
                hunter.defense = 0.90;
                hunter.lifePoints = 135;
                hunter.maximumLifePoints = 135;
                hunter.speed = 75;
                hunter.combatantID = heroNumber;
                hunter.arrowsNumber = 10;
                hunter.talent = "Brave Bow";
                hunter.talentDescription = "When his life is full, arrows are not used ";
                listOfHeros.add(hunter);
                System.out.println("And snap ! Hero number " + heroNumber + " is now a hunter");
            }
            case 3 -> {
                Mage mage = new Mage();
                mage.name = "Mage";
                mage.attack = 1.1;
                mage.defense = 0.65;
                mage.lifePoints = 125;
                mage.maximumLifePoints = 125 ;
                mage.speed = 50;
                mage.numberOfSouls = 20;
                mage.talent = "Soul gathering";
                mage.talentDescription = "Damaging enemies produce a small amount of souls";
                mage.combatantID = heroNumber;
                listOfHeros.add(mage);
                System.out.println("Wooaaa ! Hero number " + heroNumber + " is now a mage");
            }
            case 4 -> {
                Healer healer = new Healer();
                healer.name = "Healer";
                healer.attack = 1;
                healer.defense = 0.90;
                healer.lifePoints = 80;
                healer.maximumLifePoints = 80;
                healer.currentLifePoints = 0 ;
                healer.speed = 45;
                healer.talent = "Self Help";
                healer.talentDescription = "Life points are increased by 10% if no damages are received during a turn";
                healer.combatantID = heroNumber;
                listOfHeros.add(healer);
                System.out.println("Flouf ! Hero number " + heroNumber + " is now a healer");
            }
            default -> throw new IllegalStateException("Unexpected value: " + choiceAnswer);
        }
        System.out.println();
    }
}
