import java.util.*;

public class FightInstance {
    public ArrayList<Combatant> listOfFightCombatants = new ArrayList<>();

    public void creatingEnemiesWave(Game game) {
        game.difficultyCoef++;
        for (int enemyNumber = 1; enemyNumber <= game.numberOfHerosInput; enemyNumber++) {
            Enemy enemy = new Enemy();
            enemy.setEnemyNumber(enemyNumber);
            enemy.attack = UsefulFunctions.roundADouble(UsefulFunctions.randomDouble(0.5, 0.7) * game.difficultyCoef);
            enemy.defense = UsefulFunctions.roundADouble(1 - (UsefulFunctions.randomDouble(0.065, 0.11) * game.difficultyCoef));
            enemy.lifePoints = UsefulFunctions.randomInt(50, 75) * Math.max(1, game.difficultyCoef / 2);
            enemy.speed = UsefulFunctions.randomInt(20, 40) * game.difficultyCoef;
            game.listOfEnemies.add(enemy);
        }

        System.out.println("Well, " + game.nameOfThePlayer + " you'll have 5 waves of enemies to beat before challenging the boss mouhahahahaaha");
    }

    public void gatheringAliveHerosAndMonster(Game game) {
        for (Combatant hero : game.listOfAllHeros) {
            System.out.println(hero);
            if (hero.lifePoints > 0) {
                listOfFightCombatants.add(hero);
            }
        }
        listOfFightCombatants.addAll(game.listOfEnemies);
    }

    public void determiningFastestCombatant() {
        listOfFightCombatants.sort(Comparator.comparing(combatant -> combatant.speed));
    }


}