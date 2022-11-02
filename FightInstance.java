import java.util.*;

import static java.lang.System.*;

public class FightInstance {
    public ArrayList<Combatant> listOfCombatantsInFight = new ArrayList<>();
    public ArrayList<Combatant> listOfHerosInFight = new ArrayList<>();
    public ArrayList<Enemy> listOfEnemiesInFight = new ArrayList<>();
    int range = 3;
    int name = 0;
    int damage = 2;

    public void creatingEnemiesWave(Game game) {
        game.difficultyCoef += 0.13;
        int id = 0;
        for (Combatant hero : game.listOfHeros) {
            id++;
            Enemy enemy = new Enemy();
            enemy.combatantID = id;
            enemy.name = "Enemy";
            enemy.attack = hero.attack * game.difficultyCoef;
            enemy.defense = hero.defense * game.difficultyCoef;
            enemy.lifePoints = (int) (hero.lifePoints * game.difficultyCoef);
            enemy.speed = (int) (hero.speed * game.difficultyCoef);

            int temp = UsefulFunctions.randomInt(1, 2);
            if (temp == 2) enemy.set = 2;
            else enemy.set = 1;

            listOfEnemiesInFight.add(enemy);
        }
        out.println("Well, " + game.nameOfThePlayer + " you'll have 5 waves of enemies to beat before challenging the boss mouhahahahaaha");
        out.print("Press any key to continue");
        AskUserForInput.askAString();
    }

    public void gatheringAliveHerosAndMonster(Game game) {
        for (Combatant hero : game.listOfHeros) {
            if (hero.lifePoints > 0) {
                listOfCombatantsInFight.add(hero);
                listOfHerosInFight.add(hero);
            } else {
                out.println("Unfortunately, your " + hero.name + " has fallen and won't be able fo fight with you");
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
        out.println("\n---------------------------------------------------------------------------------------------------");
        out.println("Here is the order in which the Combatants are going to attack this tour \n(Ordered by combatant's speed and heroes with prioritized attacks)");
        for (Combatant combatant : listOfCombatantsInFight) {
            if (number == 10) out.println(number + ": " + combatant.name);
            else out.println(number + " : " + combatant.name);
            number++;
        }
        out.println("Press enter to continue");
        AskUserForInput.askAString();
    }

    public void fightEngine() {
        int turn = 1;
        out.println("Turn: " + turn);
        out.println("------------------------------------------------------------------------------------------------------\n");

        for (Combatant combatant : listOfCombatantsInFight) {
            switch (combatant.name) {
                case "Enemy" -> enemyAttackAI((Enemy) combatant);
                case "Warrior" -> askUserForWarriorAttack((Warrior) combatant);
                case "Hunter" -> askUserforHunterAttack((Hunter) combatant);
                case "Mage" -> askUserForMageAttack((Mage) combatant);
                case "Healer" -> askUserForHealerAttack((Healer) combatant);
            }
        }
        prioritizeEligibleHeros();
        printCombatantOrder();
        for (Combatant combatant : listOfCombatantsInFight) {
            performAttacks(combatant);
            out.println("Press enter to continue");
            AskUserForInput.askAString();
        }
        printCombatantsLife();

    }

    public void performAttacks(Combatant combatant) {

        out.println("\n" + combatant.name + " " + combatant.combatantID + " is using " + combatant.nextAttack.get(name));

        if ((int) combatant.nextAttack.get(damage) == 0) {
            if (combatant.name.equals("Warrior")) {
                combatant.attack *= 1.2;
                out.println("Your " + combatant.name + " " + combatant.combatantID + "'s attack has highly increased");
                return;
            }
            if (combatant.name.equals("Hunter")) {
                combatant.attack *= 1.1;
                combatant.speed *= 1.1;
                out.println("Your " + combatant.name + " " + combatant.combatantID + "'s attack and speed have slightly increased");
                return;
            }
            if (combatant.name.equals("Mage")) {
                Mage mage = (Mage) combatant;
                if ((int) combatant.nextAttack.get(range) == 0){
                    mage.numberOfSouls *= 1.2;
                    out.println("Your " + combatant.name + " " + combatant.combatantID + "'s souls have highly increased");
                } else {
                    for (Combatant hero : listOfHerosInFight){
                        hero.defense *= 1.10 ;
                    }
                    out.println("Your team's defense has slightly increased");
                }
                return;
            }
            if (combatant.name.equals("Healer")){
                if ((int) combatant.nextAttack.get(range) == 0) out.println("Your " + combatant.name + " " + combatant.combatantID + " is being protected from damages");
                else {
                    for (Combatant hero : listOfHerosInFight){
                        hero.lifePoints += 15 ;
                    }
                    out.println("Your team's life has increased");
                }
                return;
            }
            if (combatant.name.equals("Enemy")){
                if ((int) combatant.nextAttack.get(range) == 0){
                    combatant.attack *= 1.15 ;
                    out.println("The " + combatant.name + " " + combatant.combatantID + "'s attack has increased");
                } else {
                    for (Combatant hero : listOfHerosInFight){
                        hero.defense *= 0.95 ;
                        hero.speed *= 0.90 ;
                    }
                    out.println("Your team's defense has slightly decreased");
                    out.println("Your team's speed has decreased");
                }
                return;
            }
        }

    }

    public void prioritizeEligibleHeros() {

        int i = 0;
        List<Combatant> copyOflistOfHerosInFight = new ArrayList<>(listOfCombatantsInFight);
        for (Combatant hero : copyOflistOfHerosInFight) {
            if (hero.name.equals("Hunter") && hero.nextAttack.get(name).equals("Quick Attack")) {
                listOfCombatantsInFight.remove(i);

                int y = 0;
                while (listOfCombatantsInFight.get(y).name.equals("Healer")) {
                    y++;
                }
                listOfCombatantsInFight.add(y, hero);
            }

            if (hero.name.equals("Healer") && hero.nextAttack.get(name).equals("Protect")) {
                listOfCombatantsInFight.remove(i);
                listOfCombatantsInFight.add(0, hero);
            }
            i++;
        }
    }


    public void printCombatantsLife() {
        out.println("--------------------------------------------------------------------------------------------------------");
        for (Enemy enemy : listOfEnemiesInFight) {
            out.print("Enemy " + enemy.combatantID + " : " + enemy.lifePoints + " pv --- ");
        }
        out.println("\n");
        for (Combatant hero : listOfHerosInFight) {
            out.print("Hero " + hero.combatantID + " : " + hero.lifePoints + " pv --- ");
        }
        out.println("\n------------------------------------------------------------------------------------------------------");
    }

    public void askUserForWarriorAttack(Warrior warrior) {

        boolean LastPower = !(warrior.lifePoints > warrior.maximumLifePoints * 0.25);

        out.println("Here are the moves your Warrior " + warrior.combatantID + " can do: \n");
        out.println("1/ Sword Cut    : Attack the enemy with the sword                                               " + " [Damage: " + WarriorAttacks.attack1.get(2) + ", Range : " + WarriorAttacks.attack1.get(3) + ", Defense change: " + WarriorAttacks.attack1.get(4) + ", Atk change: " + WarriorAttacks.attack1.get(5) + ", Speed change: " + WarriorAttacks.attack1.get(6) + "]");
        out.println("2/ Swords Dance : Highly increase the attack of the Warrior                                     " + " [Damage: " + WarriorAttacks.attack2.get(2) + " , Range : " + WarriorAttacks.attack2.get(3) + ", Defense change: " + WarriorAttacks.attack2.get(4) + ", Atk change: " + WarriorAttacks.attack2.get(5) + ", Speed change: " + WarriorAttacks.attack2.get(6) + "]");
        out.println("3/ Wide Strike  : Attack all the enemies at once                                                " + " [Damage: " + WarriorAttacks.attack3.get(2) + ", Range : " + WarriorAttacks.attack3.get(3) + ", Defense change: " + WarriorAttacks.attack3.get(4) + ", Atk change: " + WarriorAttacks.attack3.get(5) + ", Speed change: " + WarriorAttacks.attack3.get(6) + "]");
        out.println("4/ Close Combat : Use all his power to attack, decrease the attack, defense and speed in return " + " [Damage: " + WarriorAttacks.attack4.get(2) + ", Range : " + WarriorAttacks.attack4.get(3) + ", Defense change: " + WarriorAttacks.attack4.get(4) + ", Atk change: " + WarriorAttacks.attack4.get(5) + ", Speed change: " + WarriorAttacks.attack4.get(6) + "]");
        out.println("   Life points  : " + warrior.lifePoints + " pv");
        out.print("   Last Power   ? ");
        if (LastPower) out.println("Yes (25% attack increase)");
        else out.println("No");

        int userChoice;
        do {
            out.print("What is your userChoice ? (1 to 4) ");
            userChoice = AskUserForInput.askAnInt();
        } while (userChoice < 1 || userChoice > 4);

        switch (userChoice) {
            case 1 -> warrior.nextAttack = WarriorAttacks.attack1;
            case 2 -> warrior.nextAttack = WarriorAttacks.attack2;
            case 3 -> warrior.nextAttack = WarriorAttacks.attack3;
            case 4 -> warrior.nextAttack = WarriorAttacks.attack4;
        }

        if (LastPower) warrior.nextAttack.set(damage, (int) warrior.nextAttack.get(damage) * 1.20);

        out.println("Your Warrior's " + warrior.combatantID + " move is " + warrior.nextAttack.get(0)); // 0 = Name of the attack

        // Asking for targets
        if ((Integer) warrior.nextAttack.get(range) >= listOfEnemiesInFight.size()) {
            out.println("All the enemies will be attacked");
            warrior.nextTargets = 5;

        } else if ((Integer) warrior.nextAttack.get(range) == 1) {
            out.println();

            for (Enemy enemy : listOfEnemiesInFight) {
                out.print("Enemy " + enemy.combatantID + " : " + enemy.lifePoints + " pv --- ");
            }

            out.println();
            do {
                out.print("Which enemy do you want to attack ? ");
                userChoice = AskUserForInput.askAnInt();
            } while (userChoice < 1 || userChoice > listOfEnemiesInFight.size());

            warrior.nextTargets = userChoice;
            out.println("Got it, Enemy " + userChoice + " will be attacked");
        } else {
            warrior.nextTargets = 0;
        }
        out.println("\n");
    }

    public void askUserforHunterAttack(Hunter hunter) {

        boolean fullLife = hunter.maximumLifePoints == hunter.lifePoints;

        out.println("Here are the moves your Hunter " + hunter.combatantID + " can do: \n");
        out.println("1/ Quick Attack : Always attack in first                      " + " [Damage: " + HunterAttacks.attack1.get(2) + ", Range : " + HunterAttacks.attack1.get(3) + ", Arrow cost: " + HunterAttacks.attack1.get(4) + ", Defense change: " + HunterAttacks.attack1.get(5) + ", Atk change: " + HunterAttacks.attack1.get(6) + ", Speed change: " + HunterAttacks.attack1.get(7) + "]");
        out.println("2/ X Bow        : Shoot a big arrow to an enemy               " + " [Damage: " + HunterAttacks.attack2.get(2) + ", Range : " + HunterAttacks.attack2.get(3) + ", Arrow cost: " + HunterAttacks.attack2.get(4) + ", Defense change: " + HunterAttacks.attack2.get(5) + ", Atk change: " + HunterAttacks.attack2.get(6) + ", Speed change: " + HunterAttacks.attack2.get(7) + "]");
        out.println("3/ Arrow Rain   : Shoot many arrows to touch multiple targets " + " [Damage: " + HunterAttacks.attack3.get(2) + ", Range : " + HunterAttacks.attack3.get(3) + ", Arrow cost: " + HunterAttacks.attack3.get(4) + ", Defense change: " + HunterAttacks.attack3.get(5) + ", Atk change: " + HunterAttacks.attack3.get(6) + ", Speed change: " + HunterAttacks.attack3.get(7) + "]");
        out.println("4/ Hone Caws    : Increase speed and attack of the Hunter     " + " [Damage: " + HunterAttacks.attack4.get(2) + " , Range : " + HunterAttacks.attack4.get(3) + ", Arrow cost: " + HunterAttacks.attack4.get(4) + ", Defense change: " + HunterAttacks.attack4.get(5) + ", Atk change: " + HunterAttacks.attack4.get(6) + ", Speed change: " + HunterAttacks.attack4.get(7) + "]");
        out.println("   Life points  : " + hunter.lifePoints + " pv");
        out.println("   Arrows       : " + hunter.arrowsNumber);
        out.print("   Full Life    ? ");
        if (fullLife) out.println("Yes (Arrows will not be used)");
        else out.println("No");

        int userChoice;
        boolean isChoiceCorrect;
        do {
            out.print("What is your Choice ? ");
            isChoiceCorrect = true;
            userChoice = AskUserForInput.askAnInt();

            if (userChoice < 1 || userChoice > 4) {
                isChoiceCorrect = false;
                out.println("Choice out of range !");
            }
            if (!fullLife) {
                if ((userChoice == 2 && hunter.arrowsNumber < 1) || (userChoice == 3 && hunter.arrowsNumber < 2)) {
                    isChoiceCorrect = false;
                    out.print("Ooops ! You don't have enough arrows to use this attack ");
                }
            }
        } while (!isChoiceCorrect);

        switch (userChoice) {
            case 1 -> hunter.nextAttack = HunterAttacks.attack1;
            case 2 -> hunter.nextAttack = HunterAttacks.attack2;
            case 3 -> hunter.nextAttack = HunterAttacks.attack3;
            case 4 -> hunter.nextAttack = HunterAttacks.attack4;
        }
        out.println("Your Hunter's " + hunter.combatantID + " move is " + hunter.nextAttack.get(0)); // 0 = Name of the attack

        // Asking for targets
        if ((Integer) hunter.nextAttack.get(range) >= listOfEnemiesInFight.size()) {
            out.println("All the enemies will be attacked");
            hunter.nextTargets = 5;

        } else if ((Integer) hunter.nextAttack.get(range) == 1) {
            out.println();

            for (Enemy enemy : listOfEnemiesInFight) {
                out.print("Enemy " + enemy.combatantID + " : " + enemy.lifePoints + " pv --- ");
            }

            out.println();
            do {
                out.print("Which enemy do you want to attack ? ");
                userChoice = AskUserForInput.askAnInt();
            } while (userChoice < 1 || userChoice > listOfEnemiesInFight.size());

            hunter.nextTargets = userChoice;
            out.println("Got it, Enemy " + userChoice + " will be attacked");
        } else {
            hunter.nextTargets = 0;
        }
        out.println("\n");
    }

    public void askUserForMageAttack(Mage mage) {
        out.println("Here are the moves your Mage " + mage.combatantID + " can do: \n");
        out.println("1/ Shadow Sneak : Attack an Enemy from behind                                                " + " [Damage: " + MageAttacks.attack1.get(2) + " , Range : " + MageAttacks.attack1.get(3) + ", Soul cost: " + MageAttacks.attack1.get(4) + "  , Soul change: " + MageAttacks.attack1.get(5) + ", Defense change: " + MageAttacks.attack1.get(6) + "]");
        out.println("2/ Souls focus  : Focus on increasing the current number of souls                            " + " [Damage: " + MageAttacks.attack2.get(2) + "  , Range : " + MageAttacks.attack2.get(3) + ", Soul cost: " + MageAttacks.attack2.get(4) + "  , Soul change: " + MageAttacks.attack2.get(5) + ", Defense change: " + MageAttacks.attack2.get(6) + "]");
        out.println("3/ Souls Guard  : Increase the defense of the team                                           " + " [Damage: " + MageAttacks.attack3.get(2) + "  , Range : " + MageAttacks.attack3.get(3) + ", Soul cost: " + MageAttacks.attack3.get(4) + " , Soul change: " + MageAttacks.attack3.get(5) + ", Defense change: " + MageAttacks.attack3.get(6) + "]");
        out.println("4/ Final Shout  : A powerful attack where the damages depend of the number of souls gathered " + " [Damage: " + " - " + ", Range : " + MageAttacks.attack4.get(3) + ", Soul cost: " + "All" + ", Soul change: " + MageAttacks.attack4.get(5) + ", Defense change: " + MageAttacks.attack4.get(6) + "]");
        out.println("   Life points  : " + mage.lifePoints + " pv");
        out.println("Souls gathered  : " + mage.numberOfSouls);

        int userChoice;
        boolean isCorrect;
        do {
            out.print("What is your userChoice ? ");
            isCorrect = true;
            userChoice = AskUserForInput.askAnInt();

            if (userChoice < 1 || userChoice > 4) {
                isCorrect = false;
                out.println("Choice out of range !");
            }
            if ((userChoice == 3 && mage.numberOfSouls < 20) || (userChoice == 4 && mage.numberOfSouls == 0)) {
                isCorrect = false;
                out.println("Hey ! You don't have enough souls to use this attack");
            }
            if (userChoice == 2 && mage.numberOfSouls == 0) {
                isCorrect = false;
                out.println("Soul focus is useless when your souls gathered are at 0");
            }
        } while (!isCorrect);

        switch (userChoice) {
            case 1 -> mage.nextAttack = MageAttacks.attack1;
            case 2 -> mage.nextAttack = MageAttacks.attack2;
            case 3 -> mage.nextAttack = MageAttacks.attack3;
            case 4 -> mage.nextAttack = MageAttacks.attack4;
        }
        out.println("Your Mage's " + mage.combatantID + " move is " + mage.nextAttack.get(0)); // 0 = Name of the attack

        // Asking for targets
        if ((Integer) mage.nextAttack.get(range) == 1) {
            out.println();

            for (Enemy enemy : listOfEnemiesInFight) {
                out.print("Enemy " + enemy.combatantID + " : " + enemy.lifePoints + " pv --- ");
            }
            out.println();
            do {
                out.print("Which enemy do you want to attack ? ");
                userChoice = AskUserForInput.askAnInt();
            } while (userChoice < 1 || userChoice > listOfEnemiesInFight.size());
            mage.nextTargets = userChoice;
            out.println("Got it, Enemy " + userChoice + " will be attacked");

        } else {
            mage.nextTargets = (int) mage.nextAttack.get(range);
        }

        out.println("\n");
    }

    public void askUserForHealerAttack(Healer healer) {
        out.println("Here are the moves your Healer " + healer.combatantID + " can do: \n");
        out.println("1/ Charge       : Basically charge on the enemy                                   " + " [Damage: " + HealerAttacks.attack1.get(2) + ", Range : " + HealerAttacks.attack1.get(3) + ", Heal: " + HealerAttacks.attack1.get(4) + " ]");
        out.println("2/ Protect      : Protect the healer from received damages (Prioritized)          " + " [Damage: " + HealerAttacks.attack2.get(2) + " , Range : " + HealerAttacks.attack2.get(3) + ", Heal: " + HealerAttacks.attack2.get(4) + " ]");
        out.println("3/ Giga Drain   : Attack an enemy and convert a small part of the damages to heal " + " [Damage: " + HealerAttacks.attack3.get(2) + ", Range : " + HealerAttacks.attack3.get(3) + ", Heal: " + HealerAttacks.attack3.get(4) + " ]");
        out.println("4/ Heal Wave    : Heal the team a little                                          " + " [Damage: " + HealerAttacks.attack4.get(2) + " , Range : " + HealerAttacks.attack4.get(3) + ", Heal: " + HealerAttacks.attack4.get(4) + "]");
        out.println("   Life points  : " + healer.lifePoints + " pv");

        int userChoice;
        do {
            out.print("What is your userChoice ? (1 to 4) ");
            userChoice = AskUserForInput.askAnInt();
        } while (userChoice < 1 || userChoice > 4);

        switch (userChoice) {
            case 1 -> healer.nextAttack = HealerAttacks.attack1;
            case 2 -> healer.nextAttack = HealerAttacks.attack2;
            case 3 -> healer.nextAttack = HealerAttacks.attack3;
            case 4 -> healer.nextAttack = HealerAttacks.attack4;
        }
        out.println("Your Healer's " + healer.combatantID + " move is " + healer.nextAttack.get(0)); // 0 = Name of the attack

        // Asking for targets
        if ((Integer) healer.nextAttack.get(range) == 1) {
            out.println();

            for (Enemy enemy : listOfEnemiesInFight) {
                out.print("Enemy " + enemy.combatantID + " : " + enemy.lifePoints + " pv --- ");
            }
            out.println();
            do {
                out.print("Which enemy do you want to attack ? ");
                userChoice = AskUserForInput.askAnInt();
            } while (userChoice < 1 || userChoice > listOfEnemiesInFight.size());
            healer.nextTargets = userChoice;
            out.println("Got it, Enemy " + userChoice + " will be attacked");

        } else {
            healer.nextTargets = (int) healer.nextAttack.get(range);
        }
        out.println("\n");
    }

    public void enemyAttackAI(Enemy enemy) {
        int choice = UsefulFunctions.randomInt(1, 4);

        switch (choice) {
            case 1 -> enemy.nextAttack = EnemyAttacks.attack1;
            case 2 -> {
                if (enemy.set == 1) enemy.nextAttack = EnemyAttacks.attack2;
                else enemy.nextAttack = EnemyAttacks.attack2b;
            }
            case 3 -> enemy.nextAttack = EnemyAttacks.attack3;
            case 4 -> enemy.nextAttack = EnemyAttacks.attack4;
        }

        if ((int) enemy.nextAttack.get(range) == 0) {
            enemy.nextTargets = 0;

        } else if ((int) enemy.nextAttack.get(range) == 5) {
            enemy.nextTargets = 5;
        } else {
            enemy.nextTargets = UsefulFunctions.randomInt(1, listOfHerosInFight.size());
        }
    }
}