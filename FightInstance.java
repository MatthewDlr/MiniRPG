import java.util.*;

public class FightInstance {
    public ArrayList<Combatant> listOfCombatantsInFight = new ArrayList<>();
    public ArrayList<Combatant> listOfHerosInFight = new ArrayList<>();
    public ArrayList<Enemy> listOfEnemiesInFight = new ArrayList<>();

    public void creatingEnemiesWave(Game game) {
        game.difficultyCoef += 0.13;
        int id = 0;
        for (Combatant hero : game.listOfHeros) {
            id++;
            Enemy enemy = new Enemy();
            enemy.setEnemyNumber(id);
            enemy.name = "Enemy";
            enemy.attack = hero.attack * game.difficultyCoef;
            enemy.defense = hero.defense * game.difficultyCoef;
            enemy.lifePoints = (int) (hero.lifePoints * game.difficultyCoef);
            enemy.speed = (int) (hero.speed * game.difficultyCoef);
            listOfEnemiesInFight.add(enemy);
        }
        System.out.println("Well, " + game.nameOfThePlayer + " you'll have 5 waves of enemies to beat before challenging the boss mouhahahahaaha");
        System.out.print("Press any key to continue");
        AskUserForInput.askAString();
    }

    public void gatheringAliveHerosAndMonster(Game game) {
        for (Combatant hero : game.listOfHeros) {
            if (hero.lifePoints > 0) {
                listOfCombatantsInFight.add(hero);
                listOfHerosInFight.add(hero);
            } else {
                System.out.println("Unfortunately, your " + hero.name + " has fallen and won't be able fo fight with you");
            }
        }
        listOfCombatantsInFight.addAll(listOfEnemiesInFight);
    }

    public void sortingCombatantsBySpeed() {
        listOfCombatantsInFight.sort(Comparator.comparing(combatant -> combatant.speed));
        Collections.reverse(listOfCombatantsInFight);

        listOfHerosInFight.sort(Comparator.comparing(combatant -> combatant.speed));
        Collections.reverse(listOfHerosInFight);

        listOfEnemiesInFight.sort(Comparator.comparing(enemy -> enemy.speed));
        Collections.reverse(listOfEnemiesInFight);
    }

    public void printCombatantOrder() {
        int number = 1;
        System.out.println("\n---------------------------------------------------------------------------------------------------");
        System.out.println("Here is the order in which the Combatants are going to attack this tour (fastest are in first)");
        for (Combatant combatant : listOfCombatantsInFight) {
            System.out.println(number + ": " + combatant.name);
            number++;
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.print("Press any key to start the fight");
        AskUserForInput.askAString();
    }

    public void fightEngine() {
        int turn = 1;
        System.out.println("Turn: " + turn);
        System.out.println("------------------------------------------------------------------------------------------------------\n");

        for (Combatant combatant : listOfCombatantsInFight) {

            if (combatant.lifePoints <= 0)
                System.out.println("Damn ! It seems that the " + combatant.name + " is not able to fight anymore !");
            else {
                switch (combatant.name) {
                    case "Enemy" -> enemyAttackAI(combatant);
                    case "Warrior" -> askUserForWarriorAttack((Warrior) combatant);
                    case "Hunter" -> askUserforHunterAttack((Hunter) combatant);
                    case "Mage" -> askUserForMageAttack((Mage) combatant);
                    case "Healer" -> askUserForHealerAttack((Healer) combatant);
                }
            }
        }
    }

    public void askUserForWarriorAttack(Warrior warrior) {
        System.out.println("Here are the moves your Warrior " + warrior.combatantID + " can do: \n");
        System.out.println("1/ Sword Cut    : Attack the enemy with the sword                                               " + " [Damage: " + WarriorAttacks.attack1.get(2) + ", Range : " + WarriorAttacks.attack1.get(3) + ", Defense change: " + WarriorAttacks.attack1.get(4) + ", Atk change: " + WarriorAttacks.attack1.get(5) + ", Speed change: " + WarriorAttacks.attack1.get(6) + "]");
        System.out.println("2/ Swords Dance : Highly increase the attack of the Warrior                                     " + " [Damage: " + WarriorAttacks.attack2.get(2) + " , Range : " + WarriorAttacks.attack2.get(3) + ", Defense change: " + WarriorAttacks.attack2.get(4) + ", Atk change: " + WarriorAttacks.attack2.get(5) + ", Speed change: " + WarriorAttacks.attack2.get(6) + "]");
        System.out.println("3/ Wide Strike  : Attack all the enemies at once                                                " + " [Damage: " + WarriorAttacks.attack3.get(2) + ", Range : " + WarriorAttacks.attack3.get(3) + ", Defense change: " + WarriorAttacks.attack3.get(4) + ", Atk change: " + WarriorAttacks.attack3.get(5) + ", Speed change: " + WarriorAttacks.attack3.get(6) + "]");
        System.out.println("4/ Close Combat : Use all his power to attack, decrease the attack, defense and speed in return " + " [Damage: " + WarriorAttacks.attack4.get(2) + ", Range : " + WarriorAttacks.attack4.get(3) + ", Defense change: " + WarriorAttacks.attack4.get(4) + ", Atk change: " + WarriorAttacks.attack4.get(5) + ", Speed change: " + WarriorAttacks.attack4.get(6) + "]");
        System.out.print("What is your choice ? ");

        int choice;
        do {
            choice = AskUserForInput.askAnInt();
            System.out.print("\nWhat is your choice ? (1 to 4) ");
        }  while (choice < 1 || choice > 4);

        switch (choice) {
            case 1 -> WarriorAttacks.nextAttack = WarriorAttacks.attack1;
            case 2 -> WarriorAttacks.nextAttack = WarriorAttacks.attack2;
            case 3 -> WarriorAttacks.nextAttack = WarriorAttacks.attack3;
            case 4 -> WarriorAttacks.nextAttack = WarriorAttacks.attack4;
        }
        System.out.println("Your Warrior's " + warrior.combatantID + " move is " + WarriorAttacks.nextAttack.get(0) + "\n\n"); // 0 = Name of the attack
    }

    public void askUserforHunterAttack(Hunter hunter) {
        boolean fullLife = hunter.maximumLifePoints == hunter.lifePoints;

        System.out.println("Here are the moves your Hunter " + hunter.combatantID + " can do: \n");
        System.out.println("1/ Quick Attack : Basic attack without arrows needed          " + " [Damage: " + HunterAttacks.attack1.get(2) + ", Range : " + HunterAttacks.attack1.get(3) + ", Arrow cost: " + HunterAttacks.attack1.get(4) + ", Defense change: " + HunterAttacks.attack1.get(5) + ", Atk change: " + HunterAttacks.attack1.get(6) + ", Speed change: " + HunterAttacks.attack1.get(7) + "]");
        System.out.println("2/ X Bow        : Shoot a big arrow to an enemy               " + " [Damage: " + HunterAttacks.attack2.get(2) + ", Range : " + HunterAttacks.attack2.get(3) + ", Arrow cost: " + HunterAttacks.attack2.get(4) + ", Defense change: " + HunterAttacks.attack2.get(5) + ", Atk change: " + HunterAttacks.attack2.get(6) + ", Speed change: " + HunterAttacks.attack2.get(7) + "]");
        System.out.println("3/ Arrow Rain   : Shoot many arrows to touch multiple targets " + " [Damage: " + HunterAttacks.attack3.get(2) + ", Range : " + HunterAttacks.attack3.get(3) + ", Arrow cost: " + HunterAttacks.attack3.get(4) + ", Defense change: " + HunterAttacks.attack3.get(5) + ", Atk change: " + HunterAttacks.attack3.get(6) + ", Speed change: " + HunterAttacks.attack3.get(7) + "]");
        System.out.println("4/ Hone Caws    : Increase speed and attack of the Hunter     " + " [Damage: " + HunterAttacks.attack4.get(2) + " , Range : " + HunterAttacks.attack4.get(3) + ", Arrow cost: " + HunterAttacks.attack4.get(4) + ", Defense change: " + HunterAttacks.attack4.get(5) + ", Atk change: " + HunterAttacks.attack4.get(6) + ", Speed change: " + HunterAttacks.attack4.get(7) + "]");
        System.out.println("Arrows in stock : " + hunter.arrowsNumber);
        System.out.print("Full Life ?     : ");
        if (fullLife) System.out.println("Yes (Arrows will not be used)");
        else System.out.println("No");
        System.out.print("What is your userChoice ? ");

        int userChoice;
        boolean isChoiceCorrect;
        do {
            isChoiceCorrect = true;
            userChoice = AskUserForInput.askAnInt();

            if (userChoice < 1 || userChoice > 4) {
                isChoiceCorrect = false;
                System.out.println("Choice out of range !");
            }
            if (!fullLife) {
                if ((userChoice == 2 && hunter.arrowsNumber < 1) || (userChoice == 3 && hunter.arrowsNumber < 2)) {
                    isChoiceCorrect = false;
                    System.out.print("Ooops ! You don't have enough arrows to use this attack ");
                }
            }
        } while (!isChoiceCorrect);

        switch (userChoice) {
            case 1 -> HunterAttacks.nextAttack = HunterAttacks.attack1;
            case 2 -> HunterAttacks.nextAttack = HunterAttacks.attack2;
            case 3 -> HunterAttacks.nextAttack = HunterAttacks.attack3;
            case 4 -> HunterAttacks.nextAttack = HunterAttacks.attack4;
        }
        System.out.println("Your Hunter's " + hunter.combatantID + " move is " + HunterAttacks.nextAttack.get(0) + "\n\n"); // 0 = Name of the attack
    }

    public void askUserForMageAttack(Mage mage) {
        System.out.println("Here are the moves your Mage " + mage.combatantID + " can do: \n");
        System.out.println("1/ Shadow Sneak : Attack an Enemy from behind                                                " + " [Damage: " + MageAttacks.attack1.get(2) + " , Range : " + MageAttacks.attack1.get(3) + ", Soul cost: " + MageAttacks.attack1.get(4) + "  , Soul change: " + MageAttacks.attack1.get(5) + ", Defense change: " + MageAttacks.attack1.get(6) + "]");
        System.out.println("2/ Souls focus  : Focus on increasing the current number of souls                            " + " [Damage: " + MageAttacks.attack2.get(2) + "  , Range : " + MageAttacks.attack2.get(3) + ", Soul cost: " + MageAttacks.attack2.get(4) + "  , Soul change: " + MageAttacks.attack2.get(5) + ", Defense change: " + MageAttacks.attack2.get(6) + "]");
        System.out.println("3/ Souls Guard  : Increase the defense of the team                                           " + " [Damage: " + MageAttacks.attack3.get(2) + "  , Range : " + MageAttacks.attack3.get(3) + ", Soul cost: " + MageAttacks.attack3.get(4) + " , Soul change: " + MageAttacks.attack3.get(5) + ", Defense change: " + MageAttacks.attack3.get(6) + "]");
        System.out.println("4/ Final Shout  : A powerful attack where the damages depend of the number of souls gathered " + " [Damage: " + " - " + ", Range : " + MageAttacks.attack4.get(3) + ", Soul cost: " + "All"+ ", Soul change: " + MageAttacks.attack4.get(5) + ", Defense change: " + MageAttacks.attack4.get(6) + "]");
        System.out.println("Souls gathered  : " + mage.numberOfSouls);

        int choice;
        boolean isCorrect;
        do {
            System.out.print("What is your choice ? ");
            isCorrect = true;
            choice = AskUserForInput.askAnInt();

            if (choice < 1 || choice > 4) {
                isCorrect = false;
                System.out.println("Choice out of range !");
            }
            if ((choice == 3 && mage.numberOfSouls < 20) || (choice == 4 && mage.numberOfSouls == 0)) {
                isCorrect = false;
                System.out.println("Hey ! You don't have enough souls to use this attack");
            }
            if (choice == 2 && mage.numberOfSouls == 0) {
                isCorrect = false;
                System.out.println("Soul focus is useless when your souls gathered are at 0");
            }
        } while (!isCorrect);

        switch (choice) {
            case 1 -> MageAttacks.nextAttack = MageAttacks.attack1;
            case 2 -> MageAttacks.nextAttack = MageAttacks.attack2;
            case 3 -> MageAttacks.nextAttack = MageAttacks.attack3;
            case 4 -> MageAttacks.nextAttack = MageAttacks.attack4;
        }
        System.out.println("Your Mage's " + mage.combatantID + " move is " + MageAttacks.nextAttack.get(0) + "\n\n"); // 0 = Name of the attack
    }

    public void askUserForHealerAttack(Healer healer) {
        System.out.println("Here are the moves your Healer " + healer.combatantID + " can do: \n");
        System.out.println("1/ Charge       : Basically charge on the enemy                                   " + " [Damage: " + HealerAttacks.attack1.get(2) + ", Range : " + HealerAttacks.attack1.get(3) + ", Heal: " + HealerAttacks.attack1.get(4) + " ]");
        System.out.println("2/ Protect      : Protect the healer from received damages                        " + " [Damage: " + HealerAttacks.attack2.get(2) + " , Range : " + HealerAttacks.attack2.get(3) + ", Heal: " + HealerAttacks.attack2.get(4) + " ]");
        System.out.println("3/ Giga Drain   : Attack an enemy and convert a small part of the damages to heal " + " [Damage: " + HealerAttacks.attack3.get(2) + ", Range : " + HealerAttacks.attack3.get(3) + ", Heal: " + HealerAttacks.attack3.get(4) + " ]");
        System.out.println("4/ Heal Wave    : Heal the team a little                                          " + " [Damage: " + HealerAttacks.attack4.get(2) + " , Range : " + HealerAttacks.attack4.get(3) + ", Heal: " + HealerAttacks.attack4.get(4) + "]");
        System.out.print("What is your choice ? ");

        int choice;
        do {
            choice = AskUserForInput.askAnInt();
            System.out.print("\nWhat is your choice ? (1 to 4) ");
        }  while (choice < 1 || choice > 4);

        switch (choice) {
            case 1 -> HealerAttacks.nextAttack = HealerAttacks.attack1;
            case 2 -> HealerAttacks.nextAttack = HealerAttacks.attack2;
            case 3 -> HealerAttacks.nextAttack = HealerAttacks.attack3;
            case 4 -> HealerAttacks.nextAttack = HealerAttacks.attack4;
        }
        System.out.println("Your Healer's " + healer.combatantID + " move is " + HealerAttacks.nextAttack.get(0) + "\n\n"); // 0 = Name of the attack
    }

    public void enemyAttackAI(Combatant enemy) {

    }


}