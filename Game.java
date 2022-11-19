import java.util.*;


public class Game {

    public InputParser inputParser ;
    public String nameOfThePlayer;
    public int numberOfHerosInput;
    public double difficultyCoef = 0.6;
    public List<Combatant> listOfHeros = new ArrayList<>();

    public FightInstance fight;

    public Game(){
    }


    public void initializeGame() {
        this.inputParser = ChoseParserMode();
        askUserHisName();
        askUserTheNumberOfHero();
        askUserTheClassOfHeros();
        for (int numberOfFights = 1; numberOfFights <= 5 ; numberOfFights ++){
            initializeFight(numberOfFights);
            playFight(numberOfFights);
        }
        initializeBossFight();
        playFight(6);

        inputParser.printDialog("Okay cool you just won the game (finally)");
        inputParser.printDialog("Now get out of my world before I kick your ass");

    }

    public InputParser ChoseParserMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play in Console mode (1) or in UI mode (2) ? ");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("1") || userInput.contains("console") || userInput.equalsIgnoreCase("console") || userInput.replace("\\s", "").equals("")) {
            System.out.println("Game is starting in console mode... \n\n\n");
            inputParser = new ConsoleParser();
        } else {
            System.out.println("Starting UI mode... \n\n\n");
            inputParser = new UIParser();
        }
        return inputParser ;
    }


    public void initializeFight(int numberOfFights) {
        this.fight = new FightInstance(inputParser);
        if (numberOfFights > 1) upgradeHeroes();
        fight.creatingEnemiesWave(this);
        fight.gatheringHerosAndMonster(this);
        fight.sortingCombatantsBySpeed();

        inputParser.printDialog("Hurry up " + nameOfThePlayer + " the fight is about to start !");
        inputParser.pressEnterToContinue();

    }

    public void initializeBossFight() {
        this.fight = new FightInstance(inputParser);
        upgradeHeroes();
        fight.creatingBossWave(this, inputParser);
        fight.sortingCombatantsBySpeed();
        inputParser.printDialog("Let's go " + nameOfThePlayer + " the end game is approaching");
        inputParser.pressEnterToContinue();

    }

    public void upgradeHeroes() {
        inputParser.printDialog("\nYour team have been healed to be ready for next fight");

        for (Combatant hero : listOfHeros) {

            inputParser.printDialog("Which stats of your " + hero.name + " " + hero.combatantID + " do you want to upgrade ? ");
            inputParser.printDialog(" 1/ Attack \n 2/ Defense \n 3/ Life Points \n 4/ Speed");
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
            inputParser.printDialog("Boomm, the stat " + userChoice + " of your " + hero.name + " " + hero.combatantID + " has been increased by 25% \n");
        }
    }

    public void playFight(int numberOfFights) {
        fight.fightEngine();

        if (fight.isFightWon) {
            inputParser.printDialog("Congrats " + nameOfThePlayer + " you just won your fight " + numberOfFights + " !");
        } else {
            inputParser.printSeparation();
            inputParser.printDialog("Unforthunately, you're too weak to fight against those monsters");
            inputParser.printDialog("I shouldn't have given to you a pokemo... Uhm the chance of being here");
            inputParser.printDialog("Well get out now " + nameOfThePlayer);
            inputParser.printSeparation();
            System.exit(0);
        }
    }

    public void askUserHisName() {
        inputParser.printDialog("Hello player on this new adventure !");
        inputParser.printDialogNoNewLine("Please remind me your name: ");

        nameOfThePlayer = (inputParser.AskAString()).replaceAll("\\s", "");

        if (nameOfThePlayer.equals("")) {
            nameOfThePlayer = "Dumbass";
        }
    }

    public void askUserTheNumberOfHero() {
        inputParser.printDialog("Oh, " + nameOfThePlayer + " ! Yeah I remember you now.\n");
        inputParser.printDialogNoNewLine("And... ahem, how many hero are going to fight by your side ? ");
        numberOfHerosInput = inputParser.AskAnInt();

        while (numberOfHerosInput > 5) {
            inputParser.printDialog("Oh no no, you are too many on this adventure. Your teammates can be up to 5");
            inputParser.printDialogNoNewLine("So, how many hero are going to fight by your side ? ");
            numberOfHerosInput = inputParser.AskAnInt();
        }
        inputParser.printDialog("Umm, so you are going to be " + numberOfHerosInput + " heros, that's a great start " + nameOfThePlayer + ".");
        inputParser.pressEnterToContinue();
    }

    public void askUserTheClassOfHeros() {
        inputParser.printDialog("It's time to chose which heroes are going with you ?");
        inputParser.printDialog(" 1/ Warrior   Talent: Last Power      (When his life is under 25%, his attack is increased by 20%)");
        inputParser.printDialog(" 2/ Hunter    Talent: Brave Bow       (When his life is full, arrows are not used) ");
        inputParser.printDialog(" 3/ Mage      Talent: Soul gathering  (Damaging enemies produce a small amount of souls)");
        inputParser.printDialog(" 4/ Healer    Talent: Self Help       (Life points are increased by 10% if no damages are received during a turn) \n");
        for (int heroNumber = 1; heroNumber <= numberOfHerosInput; heroNumber++) {
            inputParser.printDialog("What is the class of hero " + heroNumber + " hum ? ");

            int userInput = inputParser.AskAnIntBetween(1, 4);
            settingUpHeroClass(userInput, heroNumber);
        }
        inputParser.printDialog("Alright, there is your team : ");
        for (Combatant combatant : listOfHeros) {
            inputParser.printDialogNoNewLine(combatant.name + ", ");
        }
        inputParser.printDialog("\nYour team look terrible but anyway, at least we can jump in.");
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
                this.listOfHeros.add(warrior);
                inputParser.printDialog("Boooom! Hero number " + heroNumber + " is now a warrior");
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
                listOfHeros.add(hunter);
                inputParser.printDialog("And snap ! Hero number " + heroNumber + " is now a hunter");
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
                listOfHeros.add(mage);
                inputParser.printDialog("Wooaaa ! Hero number " + heroNumber + " is now a mage");
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
                listOfHeros.add(healer);
                inputParser.printDialog("Flouf ! Hero number " + heroNumber + " is now a healer");
            }
            default -> throw new IllegalStateException("Unexpected value: " + choiceAnswer);
        }
        inputParser.pressEnterToContinue();
    }
}
