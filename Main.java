public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.initializeGame();


        for (int numberOfFights = 1; numberOfFights <= 5 ; numberOfFights ++){
            game.initializeFight(numberOfFights);
            game.playFight(numberOfFights);
        }
        
        System.out.println("BOSS TIME");

    }
}