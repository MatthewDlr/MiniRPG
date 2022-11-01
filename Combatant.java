import java.util.*;

public class Combatant extends Game {
    public String name;
    public int lifePoints;
    public double attack;
    public double defense; // The lower the better (between 0 and 1)
    public double speed; // The higher the better (between 0 and 100)
    public int combatantID;

    ArrayList<Object> nextAttack = new ArrayList<>();
    String nextTargets ;
}