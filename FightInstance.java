import java.util.*;

public class FightInstance extends Game {
    public ArrayList<Combatant> listOfFightCombatants = new ArrayList<>();

    public void creatingEnemiesWave() {
        difficultyCoef++;
        for (int enemyNumber = 1; enemyNumber <= numberOfHerosInput; enemyNumber++) {
            Enemy enemy = new Enemy();
            enemy.setEnemyNumber(enemyNumber);
            enemy.attack = UsefulFunctions.roundADouble(UsefulFunctions.randomDouble(0.5, 0.7) * difficultyCoef);
            enemy.defense = UsefulFunctions.roundADouble(1 - (UsefulFunctions.randomDouble(0.065, 0.11) * difficultyCoef));
            enemy.lifePoints = UsefulFunctions.randomInt(50, 75) * Math.max(1, difficultyCoef / 2);
            enemy.speed = UsefulFunctions.randomInt(20, 40) * difficultyCoef;
            listOfEnemies.add(enemy);
        }

        System.out.println("Well, " + nameOfThePlayer + " you'll have 5 waves of enemies to beat before challenging the boss mouhahahahaaha");
    }

    public void gatheringAliveHerosAndMonster() {
        for (Combatant hero : listOfAllHeros) {
            System.out.println(hero);
            if (hero.lifePoints > 0) {
                listOfFightCombatants.add(hero);
            }
        }
        listOfFightCombatants.addAll(listOfEnemies);
    }

    public void determiningFastestCombatant() {
        listOfFightCombatants.sort(Comparator.comparing(combatant -> combatant.speed));
    }


}