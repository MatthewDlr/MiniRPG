import java.util.ArrayList;

public class UIParser implements InputParser {

    @Override
    public void pressEnterToContinue() {

    }

    @Override
    public void printSeparation() {

    }

    @Override
    public void printDialog(String textToPrint) {

    }

    @Override
    public void printDialogNoNewLine(String textToPrint) {

    }

    @Override
    public void printFightCombatantLife(ArrayList<Combatant> listOfEnemiesInFight, ArrayList<Combatant> listOfHeroesInFight) {

    }

    @Override
    public void printCombatantOrder(ArrayList<Combatant> listOfCombatantsInFight) {

    }

    @Override
    public void printEnemiesLife(ArrayList<Combatant> listOfEnemiesInFight) {

    }


    @Override
    public int AskAnInt() {
        return 0;
    }

    @Override
    public int AskAnIntBetween(int min, int max) {
        return 0;
    }

    @Override
    public int AskAnEnemyBetween(int min, int max) {
        return 0;
    }

    @Override
    public String AskAString() {
        return null;
    }
}