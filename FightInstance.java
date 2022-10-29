import java.util.*;

public class FightInstance {
    public ArrayList<Combatant> listOfFightCombatants = new ArrayList<>();

    public void creatingEnemiesWave(Game game) {
        game.difficultyCoef += 0.13;
        int id = 0;
        for (Combatant hero : game.listOfAllHeros) {
            id++;
            Enemy enemy = new Enemy();
            enemy.setEnemyNumber(id);
            enemy.attack = hero.attack * game.difficultyCoef;
            enemy.defense = hero.defense * game.difficultyCoef;
            enemy.lifePoints = (int) (hero.lifePoints * game.difficultyCoef);
            enemy.speed = (int) (hero.speed * game.difficultyCoef);
            game.listOfEnemies.add(enemy);
        }
        System.out.println("Well, " + game.nameOfThePlayer + " you'll have 5 waves of enemies to beat before challenging the boss mouhahahahaaha");
    }

    public void gatheringAliveHerosAndMonster(Game game) {
        for (Combatant hero : game.listOfAllHeros) {
            if (hero.lifePoints > 0) {
                listOfFightCombatants.add(hero);
            } else {
                System.out.println("Unfortunately, your " + hero.name + " has fallen and won't be able fo fight with you");
            }
        }
        listOfFightCombatants.addAll(game.listOfEnemies);
    }

    public void sortingCombatantsBySpeed() {
        listOfFightCombatants.sort(Comparator.comparing(combatant -> combatant.speed));
        Collections.reverse(listOfFightCombatants);
    }



}