import java.util.*;


public class Game {
    public String nameOfThePlayer;
    public int numberOfHerosInput;
    public int difficultyCoef = 0 ;
    public List<Combatant> listOfAllHeros = new ArrayList<>();
    public List<Warrior> listOfWarriorsHeros = new ArrayList<>();
    public ArrayList<Healer> listOfHealerHeros = new ArrayList<>();
    public ArrayList<Mage> listOfMageHeros = new ArrayList<>();
    public ArrayList<Hunter> listOfHunterHeros = new ArrayList<>();
    public ArrayList<Enemy> listOfEnemies = new ArrayList<>();

    public void initializeGame(){
        GameInitialization gameInitialize = new GameInitialization() ;
        gameInitialize.askUserHisName();
        gameInitialize.askUserTheNumberOfHero();
        gameInitialize.askUserTheClassOfHeros();
        //gameInitialize.settingUpHeroClass(); this function is automatically called by the previous one ; it is just displaye as information title.
    }

    public void initializeFight(){
        FightInstance fight = new FightInstance();
        fight.creatingEnemiesWave();
        fight.gatheringAliveHerosAndMonster();
    }

}
