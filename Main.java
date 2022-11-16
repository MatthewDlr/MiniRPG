public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.initializeGame();


        for (int numberOfFights = 1; numberOfFights <= 5 ; numberOfFights ++){
            game.initializeFight(numberOfFights);
            game.playFight(numberOfFights);
        }
        
        System.out.println("\n----------------------------------------------------");
        System.out.println("BOSS TIME");
        System.out.println("----------------------------------------------------\n");

        game.initializeBossFight();
        game.playFight(6);

        System.out.println("Okay cool you just won the game (finally) ");
        System.out.println("Now get out of my world before I kick your ass ");
    }
}