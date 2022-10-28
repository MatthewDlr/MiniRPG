import java.util.*;


public class Game {
    public String nameOfThePlayer;
    public int numberOfHerosInput;
    public int difficultyCoef = 0;
    public List<Combatant> listOfAllHeros = new ArrayList<>();
    public List<Warrior> listOfWarriorsHeros = new ArrayList<>();
    public ArrayList<Healer> listOfHealerHeros = new ArrayList<>();
    public ArrayList<Mage> listOfMageHeros = new ArrayList<>();
    public ArrayList<Hunter> listOfHunterHeros = new ArrayList<>();
    public ArrayList<Enemy> listOfEnemies = new ArrayList<>();

    public FightInstance fight;

    public void initializeGame(){
        askUserHisName();
        askUserTheNumberOfHero();
        askUserTheClassOfHeros();
        //gameInitialize.settingUpHeroClass(); this function is automatically called by the previous one ; it is just displaye as information title.
    }

    public void initializeFight(){
        this.fight = new FightInstance();
        fight.creatingEnemiesWave(this);
        fight.gatheringAliveHerosAndMonster(this);
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
        System.out.println("\n");
        while (numberOfHerosInput > 5) {
            System.out.println("Oh no no, you are too many on this adventure. Your teammates can be up to 5");
            System.out.print("So, how many hero are going to fight by your side ? ");
            System.out.println("\n");
            numberOfHerosInput = AskUserForInput.askAnInt();
        }
        System.out.println("Umm, so you are going to be " + numberOfHerosInput + " heros, that's a great start " + nameOfThePlayer + ". \n");
    }

    public void askUserTheClassOfHeros() {

        for (int heroNumber = 1; heroNumber <= numberOfHerosInput; heroNumber++) {
            System.out.println("What is the class of hero " + heroNumber + " hum ?");
            System.out.println(" 1/ Warrior \n 2/ Hunter \n 3/ Mage \n 4/ Healer");

            int userInput = AskUserForInput.askAnInt();
            while (userInput > 4 || userInput < 1) {
                System.out.println("You're choice isn't recognized \n");
                userInput = AskUserForInput.askAnInt();
            }
            settingUpHeroClass(userInput, heroNumber);
        }
        System.out.println("Alright, there is your team : " + listOfAllHeros.toString().replace("[", "").replace("]", "") );
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
                this.listOfAllHeros.add(warrior);
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
                listOfAllHeros.add(hunter);
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
                listOfAllHeros.add(mage);
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
                listOfAllHeros.add(healer);
                listOfHealerHeros.add(healer);
                System.out.println("Wooaaa ! Hero number " + heroNumber + " is now a healer");
            }
            default -> throw new IllegalStateException("Unexpected value: " + choiceAnswer);
        }
        System.out.println();
    }
}
