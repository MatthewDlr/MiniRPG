import java.util.*;

public class Combatant extends Game {
    public String name;
    public int lifePoints;
    public int maximumLifePoints;
    public double attack;
    public double maximumAttack;
    public double defense; // The lower the better (between 0 and 1)
    public double maximumDefense;
    public double speed; // The higher the better (between 0 and 100)
    public double maximumSpeed ;
    public int combatantID;

    ArrayList<Object> nextAttack = new ArrayList<>();
    int nextTargets ;
}