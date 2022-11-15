import java.util.*;
// Rule N°1 : Never trust the user, these functions check that the inputs are good

public class AskUserForInput {

    public static int AnInt() {
        boolean isInputCorrect;
        int userInput = 0;
        do {
            isInputCorrect = true;
            Scanner scanner = new Scanner(System.in);

            try {
                userInput = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Error, your choice must be a number");
                isInputCorrect = false;
            }
            if (userInput <= 0) {
                System.out.println("Error, your choice can't be negative");
                isInputCorrect = false;
            }

        } while (!isInputCorrect);
        return userInput;
    }

    public static int AnIntBetween(int min , int max) {
        boolean isInputCorrect;
        int userInput = 0;

        do {
            isInputCorrect = true;
            System.out.print("What is your choice ? ");
            Scanner scanner = new Scanner(System.in);

            try {
                userInput = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Error, your choice must be a number");
                isInputCorrect = false;
            }

            if (userInput < min || userInput > max) {
                System.out.println("Error, choice is out of range of range");
                isInputCorrect = false;
            }

        } while (!isInputCorrect);
        return userInput;
    }
    public static int AnEnemyBetween(int min , int max) {
        System.out.print("Which enemy do you want to attack ? ");
        int targetChoice = AnInt();

        if (targetChoice < min || targetChoice > max) {
            System.out.println("Error, choice is out of range");
            AnEnemyBetween(min, max) ;
        }
        System.out.println("Got it, Enemy " + targetChoice + " will be attacked");
        return targetChoice;
    }

    public static String AString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
